package model;

import database.DbBehaviour;
import database.HashMapDb;
import database.LoadSave;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import model.Artikel;
import model.Korting;
import model.KortingFactory;
import model.LoadSaveFactory;
import model.Observer;
import model.SoortObserver;
import model.Subject;

public class Winkel
        implements Subject {
    private DbBehaviour db = new HashMapDb();
    private Map<SoortObserver, List<Observer>> observers;
    private Properties properties;
    private LoadSave loadSave;
    private LoadSaveFactory loadSaveFactory;
    private Korting korting;
    private KortingFactory kortingFactory;

    public void setDb(DbBehaviour db) {
        this.db = db;
    }

    public void setObservers(Map<SoortObserver, List<Observer>> observers) {
        this.observers = observers;
    }

    public DbBehaviour getDb() {
        return this.db;
    }

    public Map<SoortObserver, List<Observer>> getObservers() {
        return this.observers;
    }

    public Winkel() {
        LoadSaveFactory factory = LoadSaveFactory.getInstance();
        this.properties = new Properties();
        try {
            FileInputStream is = new FileInputStream("src/bestanden/instellingen.xml");
            this.properties.loadFromXML(is);
            ((InputStream)is).close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.loadSave = factory.createLoadSave(this.properties);
        this.observers = new HashMap<SoortObserver, List<Observer>>();
        this.observers.put(SoortObserver.STOCK, new ArrayList());
        this.observers.put(SoortObserver.ARTIKELINSCANNEN, new ArrayList());
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public void toonStock() {
        Object inputObj = this.properties.get("input");
        String input = (String)inputObj;
        ArrayList artikels = this.loadSave.load("src/bestanden/artikel." + input);
        this.db.save(artikels);
        this.notifyObservers(SoortObserver.STOCK, artikels);
    }

    @Override
    public void registerObserver(Observer observer, SoortObserver type) {
        this.observers.get((Object)type).add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (this.observers.get((Object)SoortObserver.STOCK).contains(observer)) {
            this.observers.get((Object)SoortObserver.STOCK).remove(observer);
        }
        if (this.observers.get((Object)SoortObserver.ARTIKELINSCANNEN).contains(observer)) {
            this.observers.get((Object)SoortObserver.STOCK).remove(observer);
        }
    }

    @Override
    public void notifyObservers(SoortObserver type, Object artikels) {
        List<Observer> obs = this.observers.get((Object)type);
        if (obs == null) {
            throw new IllegalArgumentException("Ongeldig type observer");
        }
        for (Observer observer : obs) {
            observer.update(artikels);
        }
    }

    public void scan(String code) {
        try {
            Artikel a = this.db.search(code);
            ArrayList<Artikel> res = new ArrayList<Artikel>();
            res.add(a);
            this.notifyObservers(SoortObserver.ARTIKELINSCANNEN, res);
        }
        catch (NullPointerException e) {
            this.notifyObservers(SoortObserver.ARTIKELINSCANNEN, null);
        }
    }

    public void setKorting(String k) {
        this.korting = this.kortingFactory.create(k);
    }

    public Korting getKorting() {
        return this.korting;
    }
}