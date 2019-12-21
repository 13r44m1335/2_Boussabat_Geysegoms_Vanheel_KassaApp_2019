package model;

import controller.KassaController;
import database.*;

import javax.xml.stream.FactoryConfigurationError;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static model.SoortObserver.*;

/**
 * Deze klasse beschrijft een winkel met 1 kassa.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class Winkel implements Subject {

    private DbBehaviour db;
    private Map<SoortObserver,List<Observer>> observers;
    private Properties properties;
    private LoadSave loadSave;
    private LoadSaveFactory loadSaveFactory;
    private Korting korting;
    private KortingFactory kortingFactory;
    private Rekening rekening;
    private RekeningFactory rekeningFactory;
    private Winkelwagen[] winkelwagens = new Winkelwagen[2];
    private Winkelwagen current;
    private Winkelwagen hold;

    /**
     * Deze methode is gebruikt om de databank te zetten.
     * @param db de databank
     * @author Andreas Geysegoms
     */
    public void setDb(DbBehaviour db) {
        this.db = db;
    }

    /**
     * Deze methode stelt de observers in, gegeven een lijst van Observers.
     * @param observers de lijst van Observers
     * @author Andreas Geysegoms
     */
    public void setObservers(Map<SoortObserver, List<Observer>> observers) {
        this.observers = observers;
    }

    /**
     * Deze methode geeft de databank terug.
     * @return de databank.
     * @author Andreas Geysegoms
     */
    public DbBehaviour getDb() {
        return db;
    }

    /**
     *Deze methode geeft de Map van Observers en hun soort door.
     * @return de Map van SoortOberver, lijst van Observers
     * @author Andreas Geysegoms
     */
    public Map<SoortObserver, List<Observer>> getObservers() {
        return observers;
    }

    /**
     * Deze methode maakt een isntantiatie van deze klasse aan.
     * @author Andreas Geysegoms
     */
    public Winkel() {
        this.db = new HashMapDb();
        RekeningFactory rekeningFactory = RekeningFactory.getInstance();
        LoadSaveFactory factory = LoadSaveFactory.getInstance();
        KortingFactory kortingFactory = KortingFactory.getInstance();
        this.properties = new Properties();
        try {
            InputStream is = new FileInputStream("src/bestanden/instellingen.xml");
            properties.loadFromXML(is);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadSave = factory.createLoadSave(properties);
        this.observers = new HashMap<>();
        observers.put(STOCK,new ArrayList<>());
        observers.put(ARTIKELINSCANNEN,new ArrayList<>());
        observers.put(DELETEARTIKEL,new ArrayList<>());
        observers.put(RESUME,new ArrayList<>());
        observers.put(HOLD, new ArrayList<>());
        rekeningFactory.setWinkel(this);
        current = new Winkelwagen(this);
        hold = new Winkelwagen(this);
        winkelwagens[0] = current;
        winkelwagens[1] = hold;
    }

    /**
     * Deze methode stelt de properties in.
     * @param properties de properties om in te stellen.
     * @author Andreas Geysegoms
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Deze methode haalt de properties op.
     * @return de properties.
     * @author Andreas Geysegoms
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Deze methode haalt de huidge stock op en notifieert de observers.
     * @author Andreas Geysegoms
     */
    public void toonStock() {
        Object inputObj = properties.get("input");
        String input = (String) inputObj;
        ArrayList<Artikel> artikels = loadSave.load("src/bestanden/artikel."+input);
        db.save(artikels);
        notifyObservers(STOCK, artikels);
    }

    /**
     * Deze methode registreert de observers aan de hand van hun type.
     * @param observer observer die geregistreerd wordt
     * @param type type observer
     * @author Andreas Geysegoms
     */
    @Override
    public void registerObserver(Observer observer, SoortObserver type) {
        observers.get(type).add(observer);
    }

    /**
     * Deze methode zorgt ervoor dat een observer niet meer de updates van de winkel ontvangt.
     * @param observer de observer die verwijderd wordt.
     * @author Andreas Geysegoms
     */
    @Override
    public void removeObserver(Observer observer) {
        if (observers.get(STOCK).contains(observer)) observers.get(STOCK).remove(observer);
        if (observers.get(ARTIKELINSCANNEN).contains(observer)) observers.get(STOCK).remove(observer);
    }

    /**
     * Deze methode notifies de observers aan de hand van hun type.
     * @param type het type observer dat genotified wordt.
     * @author Andreas Geysegoms
     */
    @Override
    public void notifyObservers(SoortObserver type, ArrayList<Artikel> artikels) {
        List<Observer> obs = observers.get(type);

        if (obs == null)  {
            throw new IllegalArgumentException("Ongeldig type observer");
        }

        for (Observer observer : obs) {
            observer.update(artikels, type);
        }
    }

    /**
     * Deze methode stelt de korting in.
     * @param k de input voor korting aan te maken.
     * @author Andreas Geysegoms
     */
    public void setKorting(String k) {
        this.korting = KortingFactory.getInstance().create(k);
    }

    /**
     * Deze methode haalt de korting op.
     * @return de korting.
     * @author Andreas Geysegoms
     */
    public Korting getKorting() {
        return this.korting;
    }

    /**
     * Deze methode print de rekening uit.
     * @author Andreas Geysegoms
     */
    public void printRekening() {
        RekeningAbstract r = RekeningFactory.getInstance().create(properties);
        System.out.println(r.getDescription());
    }

    /**
     * Deze methode zet een verkoop op hold.
     *
     * @author Andreas Geysegoms
     */
    public void putOnHold() {
        Winkelwagen temp = this.current;
        this.current = this.hold;
        this.hold = temp;
    }

    /**
     * Deze methode de verkoop van hold terug op de voorgrond.
     *
     * @author Andreas Geysegoms
     */
    public void resume() {
        this.current = hold;
    }

    public void sell(){
        this.current = new Winkelwagen(this);
        this.hold = new Winkelwagen(this);
    }

    public void cancel(){

    }

    public Winkelwagen getCurrent() {
        return current;
    }

    public Winkelwagen getHold() {
        return hold;
    }

    public void setHold(Winkelwagen winkelwagen) {
        this.hold = winkelwagen;
    }

    public void pasStockAan(){

    }

    public void toonLogs(){

    }

}
