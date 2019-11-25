package model;

import database.ArtikelTekstLoadSave;
import database.DbBehaviour;
import database.HashMapDb;

import java.util.*;


import static model.SoortObserver.ARTIKELINSCANNEN;
import static model.SoortObserver.STOCK;

/**
 * Deze klasse beschrijft een winkel met 1 kassa.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class Winkel implements Subject {

    private DbBehaviour db;
    private Map<SoortObserver,List<Observer>> observers;

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
        this.observers = new HashMap<>();
        observers.put(STOCK,new ArrayList<>());
        observers.put(ARTIKELINSCANNEN,new ArrayList<>());
    }

    /**
     * Deze methode haalt de huidge stock op en notifiert de observers.
     * @author Andreas Geysegoms
     */
    public void toonStock() {
        ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave();
        artikelTekstLoadSave.load("src/bestanden/artikel.txt");
        notifyObservers(STOCK);
    }

    /**
     * Deze methode registreert de observers aan de hand van hun type.
     * @param observer observer die geregistreerd wordt
     * @param type type observer
     * @author Andreas Geysegoms
     */
    @Override
    public void registerObserver(Observer observer, Enum type) {
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
    public void notifyObservers(Enum type) {
        List<Observer> obs = null;
        if (type == STOCK) obs = observers.get(STOCK);
        else if (type == ARTIKELINSCANNEN) obs = observers.get(ARTIKELINSCANNEN);


        if (obs == null)  {
            throw new IllegalArgumentException("Ongeldig type observer");
        }

        for (Observer observer : obs) {
            observer.update();
        }
    }
}
