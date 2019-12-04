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
    private Winkelwagen[] winkelwagens = new Winkelwagen[2];
    private Winkelwagen current = new Winkelwagen();
    private Winkelwagen hold = new Winkelwagen();

    /**
     * Deze methode maakt een isntantie aan van een kassaController.
     * @param winkel de winkel die gebruikt wordt.
     * @author Andreas Geysegoms
     */
    public KassaController(Winkel winkel) {
        super(winkel);
        setWinkel(winkel);
        winkel.registerObserver(this, SoortObserver.ARTIKELINSCANNEN);
        winkelwagens[0] = current;
        winkelwagens[1] = hold;
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
        current.setTotaal(totaal);
    }

    /**
     * Deze methode haalt het totale bedrag op.
     * @return het totale bedrag.
     * @author Andreas Geysegoms
     */
    public double getTotaal() {
        return current.getTotaal();
    }




    /**
     * Deze methode scant een item in in de winkel.
     * @param code de code van het artikel dat ingescand wordt.
     * @author Andreas Geysegoms
     */
    public void scan(String code) {
        winkel.scan(code);
        view.getArtikelCodeField().clear();
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
            this.current.addArtikel(artikel);
            this.view.addArtikel(artikel);
            if (this.getKorting()!=null) {
                double korting = this.getKorting().berekenKorting(this);
                totaalS = totaalS - korting;
            }
            totaalS = (double) Math.round(totaalS*100.0)/100.0;
            this.view.setTotaal(totaalS);
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

    /**
     * Deze methode haalt de view op.
     * @return de view.
     * @author Andreas Geysegoms
     */
    public KassaPane getView() {
        return view;
    }

    /**
     * Deze methode zet een verkoop op hold.
     * @author Andreas Geysegoms
     */
    public void putOnHold() {
        Winkelwagen temp = this.current;
        this.current = this.hold;
        this.hold = temp;
        view.reset();
        view.setTotaal(current.getTotaal());
    }

    /**
     * Deze methode de verkoop van hold terug op de voorgrond.
     * @author Andreas Geysegoms
     */
    public void resume() {
        this.current = hold;
        view.resume(current.getAll());
        double totaal = current.getTotaal();
        if (this.getKorting()!=null) {
            double korting = this.getKorting().berekenKorting(this);
            totaal = totaal - korting;
        }
        totaal = (double) Math.round(totaal*100.0)/100.0;
        this.view.setTotaal(totaal);
    }
}
