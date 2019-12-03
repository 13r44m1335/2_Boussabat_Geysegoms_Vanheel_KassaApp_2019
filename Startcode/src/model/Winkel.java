package model;

import database.*;

import javax.xml.stream.FactoryConfigurationError;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


import static model.SoortObserver.ARTIKELINSCANNEN;
import static model.SoortObserver.KLANTSHOW;
import static model.SoortObserver.STOCK;

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
        LoadSaveFactory factory = loadSaveFactory.getInstance();
        this.properties = new Properties();
        try {
            InputStream is = new FileInputStream("Startcode/src/bestanden/instellingen.xml");
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
        ArrayList<Artikel> artikels = loadSave.load("Startcode/src/bestanden/artikel."+input);
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
    public void notifyObservers(SoortObserver type, Object artikels) {
        List<Observer> obs = observers.get(type);

        if (obs == null)  {
            throw new IllegalArgumentException("Ongeldig type observer");
        }

        for (Observer observer : obs) {
            observer.update(artikels);
        }
    }

    /**
     * Deze methode scant een artikel in.
     * @param code de code van het artikel.
     * @author Andreas Geysegoms
     */
    public void scan(String code) {
        try {
            Artikel a = db.search(code);
            ArrayList<Artikel> res = new ArrayList<>();
            res.add(a);
            notifyObservers(ARTIKELINSCANNEN,res);
            //notifyObservers(KLANTSHOW,res);
        } catch (NullPointerException e) {
            notifyObservers(ARTIKELINSCANNEN,null);
        }
    }

    public void setKorting(String k) {
        this.korting = this.kortingFactory.create(k);
    }

    public Korting getKorting() {
        return this.korting;
    }
}
