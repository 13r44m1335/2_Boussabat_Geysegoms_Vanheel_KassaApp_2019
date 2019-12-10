package controller;

import model.Artikel;
import model.Observer;
import model.SoortObserver;
import model.Winkel;
import view.panels.KassaPane;

import java.util.ArrayList;

/**
 * Deze klasse is de controller tussen winkel en view.
 */
public class KassaController extends Observer {

    private Winkel winkel;
    private KassaPane view;
    private double totaal = 0;

    /**
     * Deze methode maakt een isntantie aan van een kassaController.
     * @param winkel de winkel die gebruikt wordt.
     * @author Andreas Geysegoms
     */
    public KassaController(Winkel winkel) {
        super(winkel);
        setWinkel(winkel);
        winkel.registerObserver(this, SoortObserver.ARTIKELINSCANNEN);
    }

    /**
     * Deze methode stelt de winkel in.
     * @param winkel de winkel.
     * @author Andreas Geysegoms
     */
    public void setWinkel(Winkel winkel) {
        this.winkel = winkel;
    }

    /**
     * Deze methode stelt de view in.
     * @param view de view.
     * @author Andreas Geysegoms
     */
    public void setView(KassaPane view) {
        this.view = view;
    }

    /**
     * Deze methode stelt het totale bedrag in.
     * @param totaal het totale bedrag
     * @author Andreas Geysegoms
     */
    public void setTotaal(double totaal) {
        this.totaal = totaal;
    }

    /**
     * Deze methode haalt het totale bedrag op.
     * @return het totale bedrag.
     * @author Andreas Geysegoms
     */
    public double getTotaal() {
        return totaal;
    }

    /**
     * Deze methode updatet de view van kassa.
     * @param artikels de artikels die doorgegeven worden.
     * @author Andreas Geysegoms
     */
    @Override
    public void update(Object artikels) {
        try {
        ArrayList<Artikel> artikels1 = (ArrayList<Artikel>) artikels;
            Artikel artikel = artikels1.get(0);
            double totaalS = getTotaal() + artikel.getVerkoopprijs();
            this.view.setTotaal(totaalS);
            this.setTotaal(totaalS);
            this.view.addArtikel(artikel);
        } catch (NullPointerException e) {
            this.view.getError().setVisible(true);
        }
    }

    /**
     * Deze methode scant een item in in de winkel.
     * @param code de code van het artikel dat ingescand wordt.
     * @author Andreas Geysegoms
     */
    public void scan(String code) {
        winkel.scan(code);
    }
}