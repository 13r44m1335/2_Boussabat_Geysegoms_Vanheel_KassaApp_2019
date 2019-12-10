package model;

import java.util.ArrayList;

import static model.SoortObserver.ARTIKELINSCANNEN;
import static model.SoortObserver.DELETEARTIKEL;

/**
 * Deze klasse stelt een winkelwagen voor. Dit wordt gebruikt om een verkoop op hold te zetten.
 * @author Andreas Geysegoms
 */
public class Winkelwagen {
    private ArrayList<Artikel> artikels;
    private double totaal;
    private Winkel winkel;

    /**
     * Deze methode maakt een instance van deze klasse aan.
     * @author Andreas Geysegoms
     */
    public Winkelwagen(Winkel winkel) {
        this.artikels = new ArrayList<>();
        totaal = 0;
        this.winkel = winkel;
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

    public void deleteArtikel(Artikel code) {
        try{
            if (artikels.contains(code)) {
                artikels.remove(code);
                ArrayList<Artikel> res = new ArrayList<>();
                res.add(code);
                winkel.notifyObservers(DELETEARTIKEL,res);
            }
            else winkel.notifyObservers(DELETEARTIKEL, null);
        } catch (NullPointerException e){
            winkel.notifyObservers(DELETEARTIKEL, null);
        }
    }

    /**
     * Deze methode scant een artikel in.
     * @param code de code van het artikel.
     * @author Andreas Geysegoms
     */
    public void scan(String code) {
        try {
            Artikel a = winkel.getDb().search(code);
            if (a == null) throw new NullPointerException("Artikel staat niet in het magazijn.");
            artikels.add(a);
            winkel.notifyObservers(ARTIKELINSCANNEN,artikels);
            //notifyObservers(KLANTSHOW,res);
        } catch (NullPointerException e) {
            winkel.notifyObservers(ARTIKELINSCANNEN,null);
        }
    }

    /**
     * Deze methode zoekt een item op in de winkelwagen.
     * @param artikelCode de code van het artikel.
     * @return het artikel dat gezocht wordt.
     * @author Andreas Geysegoms
     */
    public Artikel get(String artikelCode) {
        for (Artikel a : artikels) {
            if (a.getCode().equals(artikelCode)) {
                return a;
            }
        }
        throw new IllegalArgumentException("Dit artikel is nog niet toegevoegd.");
    }
}
