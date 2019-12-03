package controller;

import model.*;
import view.panels.KassaPane;

import java.util.ArrayList;

/**
 * Deze klasse is de controller tussen winkel en view.
 */
public class KassaController extends Observer {

    private Winkel winkel;
    private KassaPane view;
    private double totaal = 0;
    private Artikel duurste;

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
     * Deze methode scant een item in in de winkel.
     * @param code de code van het artikel dat ingescand wordt.
     * @author Andreas Geysegoms
     */
    public void scan(String code) {
        winkel.scan(code);
    }


    /**
     * Deze methode updatet de view van kassa.
     * @param artikels de artikels die doorgegeven worden.
     * @author Andreas Geysegoms
     */
    @Override
    public void update(Object artikels) {
        try {
            ArrayList<Artikel> artikels2 = (ArrayList<Artikel>) artikels;
            Artikel artikel = artikels2.get(0);
            double totaalS = this.getTotaal() + artikel.getVerkoopprijs();
            this.setTotaal(totaalS);
            this.view.addArtikel(artikel);
            double korting = this.getKorting().berekenKorting(this);
            korting = (double) Math.round(korting*100.0)/100.0;
            this.view.setTotaal(totaalS-korting);
        }
        catch (NullPointerException e) {
            this.view.getError().setVisible(true);
        }
    }

    /**
     * Deze methode haalt de korting op.
     * @return de korting
     * @author Andreas Geysegoms
     */
    public Korting getKorting() {
        return this.winkel.getKorting();
    }

    /**
     * Deze methode haalt alle artikels uit de winkelmand op.
     * @return een ArrayList van Artikels.
     * @author Andreas Geysegoms
     */
    public ArrayList<Artikel> getAll() {
        return view.getAll();
    }
}
