package model;

/**
 * Deze interface beschrijft de methodes om met Observers om te gaan (registreren, verwijderen en notifyen).
 * @author Andreas Geysegoms
 * @version 1.0
 */
public interface Subject {
    /**
     * Deze methode registreert een observer ahv een type.
     * @param observer observer die geregistreerd wordt
     * @param type type observer
     * @author Andreas Geysegoms
     */
    void registerObserver(Observer observer, SoortObserver type);

    /**
     * Deze methode verwijdert een observer zodat deze geen updates meer krijgt.
     * @param observer de observer die verwijderd wordt.
     * @author Andreas Geysegoms
     */
    void removeObserver(Observer observer);

    /**
     * Deze methode notifieert de observers wanneer er veranderingen zijn.
     * @param type het type observer dat genotified wordt.
     * @author Andreas Geysegoms
     */
    void notifyObservers(SoortObserver type, Object object);
}
