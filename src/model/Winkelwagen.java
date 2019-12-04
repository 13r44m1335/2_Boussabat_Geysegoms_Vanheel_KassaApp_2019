package model;

import java.util.ArrayList;

/**
 * Deze klasse stelt een winkelwagen voor. Dit wordt gebruikt om een verkoop op hold te zetten.
 * @author Andreas Geysegoms
 */
public class Winkelwagen {
    private ArrayList<Artikel> artikels;
    private double totaal;

    /**
     * Deze methode maakt een instance van deze klasse aan.
     * @author Andreas Geysegoms
     */
    public Winkelwagen() {
        this.artikels = new ArrayList<>();
        totaal = 0;
    }

    /**
     * Deze methode haalt de totale prijs van de winkelwagen op.
     * @return de totale prijs van deze winkelwagen.
     * @author Andreas Geysegoms
     */
    public double getTotaal() {
        return totaal;
    }

    /**
     * Deze methode stelt het totaal in van de winkelwagen.
     * @param totaal het nieuwe totaal.
     * @author Andreas Geysegoms
     */
    public void setTotaal(double totaal) {
        this.totaal = totaal;
    }

    /**
     * Deze methode voegt een artikel toe aan de winkelwagen.
     * @param artikel het artikel dat toe te voegen is aan de winkelwagen.
     * @author Andreas Geysegoms
     */
    public void addArtikel(Artikel artikel) {
        this.artikels.add(artikel);
    }

    /**
     * Deze methode haalt alle artikels uit de winkelwagen op.
     * @return de artikels uit de winkelwagen.
     * @author Andreas Geysegoms
     */
    public ArrayList<Artikel> getAll() {
        return artikels;
    }
}
