package model;

import java.util.ArrayList;

/**
 * Deze klasse is de abstracte klasse van iedere Observer binnen dit project.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public abstract class Observer {

    private Winkel subject;

    /**
     * Contructor van de klasse.
     * @param subject de winkel.
     * @author Andreas Geysegoms
     */
    public Observer(Winkel subject) {
        this.subject = subject;
    }

    /**
     * Deze methode updatet de stock, de winkelwagen van de klant en de kassa.
     * @author Andreas Geysegoms
     */
    public abstract void update(ArrayList<Artikel> artikels);

}