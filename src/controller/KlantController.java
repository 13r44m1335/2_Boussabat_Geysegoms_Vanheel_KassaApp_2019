package controller;

import javafx.util.Pair;
import model.Artikel;
import model.Observer;
import static model.SoortObserver.KLANTSHOW;
import model.Winkel;
import view.panels.KlantPane;



import java.util.ArrayList;

/**
 * Deze klasse is de controller tussen winkel en klantview
 */
public class KlantController extends Observer {

    private Winkel winkel;
    private KlantPane view;
    private double totaal = 0;


    /**
     * Deze methode maakt een instantie aan van een klantController
     * @param winkel de winkel die gebruikt wordt
     */
    public KlantController(Winkel winkel) {
        super(winkel);
        setWinkel(winkel);
        winkel.registerObserver(this, KLANTSHOW);
    }


    public void setWinkel(Winkel winkel) {
        this.winkel = winkel;
    }

    /**
     * Deze methode stelt de view in.
     *
     * @param view de view
     */
    public void setView(KlantPane view) {
        this.view = view;
    }

    /**
     * Deze methode stelt het totale bedrag in.
     *
     * @param total het totale bedrag
     */
    public void setTotaal(double total) {
        this.totaal = total;
    }

    /**
     * Deze methode haalt het totale bedrag op.
     *
     * @return het totale bedrag.
     */
    public double getTotaal() {
        return totaal;
    }

    /**
     *
     * @param artikels
     */
    @Override
    public void update(Object artikels) {
        try {
            ArrayList<Artikel> artikels1 = (ArrayList<Artikel>) artikels;
            Artikel artikel = artikels1.get(0);
            double totaals = getTotaal() + artikel.getVerkoopprijs();
            this.view.setTotaal(totaals);
            this.setTotaal(totaals);

            this.addArtikel(artikel);
        } catch (NullPointerException e) {
            this.view.getError().setVisible(true);
        }
    }

    /**
     * Deze methode haalt het artikel op als het in de klant zijn winkelwagen staat.
     * @param artikel het artikel.
     * @return het artikel.
     * @author Andreas Geysegoms
     */
    public Artikel getArtikel(Artikel artikel){
        Artikel res = null;
        ArrayList<Pair<Artikel, Integer>> artikelsKlant = view.getArtikelsKlant();
        for (Pair pair : artikelsKlant){
            Artikel a = (Artikel) pair.getKey();
            if(a == artikel) res = a;
        }
        return res;
    }

    /**
     *
     * @param artikel
     */
    public void pasAantalAan(Artikel artikel){
        Pair oldPair = getPair(artikel);
        int current = (int) oldPair.getValue();
        Pair <Artikel, Integer> newPair = new Pair<>(artikel,current+1);
        ArrayList<Pair<Artikel, Integer>> artikelsKlant = view.getArtikelsKlant();
        artikelsKlant.remove(oldPair);
        artikelsKlant.add(newPair);
        view.setArtikelsKlant(artikelsKlant);
    }

    /**
     * Deze methode haalt een pair op ahv een artikel.
     * @param artikel het artikel van het pair.
     * @return het pair.
     * @author Andreas Geysegoms
     */
    private Pair<Artikel, Integer> getPair(Artikel artikel) {
        ArrayList<Pair<Artikel, Integer>> artikelsKlant = view.getArtikelsKlant();
        for (Pair pair : artikelsKlant) {
            if (pair.getKey().equals(artikel)) {
                return pair;
            }
        }
        return null;
    }

    /**
     * Deze methode haalt het aantal artikels x van de huidige klant op.
     * @param artikel het artikel x
     * @return het aantal artikels x.
     * @author Andreas Geysegoms
     */
    private int getAantal(Artikel artikel) {
        ArrayList<Pair<Artikel, Integer>> artikelsKlant = view.getArtikelsKlant();
        int res = 0;
        for (Pair pair : artikelsKlant){
            Artikel a = (Artikel) pair.getValue();
            if(a == artikel) res = (int) pair.getValue();
        }
        return res;
    }

    /**
     * Deze methode voegt een artikel toe aan de artikellijst van de klant.
     * @param artikel het artikel toe te voegen aan de lijst van de klant.
     * @author Andreas Geysegoms
     */
    public void addArtikel(Artikel artikel) {
        ArrayList<Pair<Artikel, Integer>> artikelsKlant = view.getArtikelsKlant();
        if(getArtikel(artikel) != null){
            pasAantalAan(artikel);
        } else {
            Pair<Artikel, Integer> newPair = new Pair<>(artikel, 1);
            artikelsKlant.add(newPair);
            view.setArtikelsKlant(artikelsKlant);
        }
        view.refresh();
    }

}
