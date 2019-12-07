package controller;

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

    @Override
    public void update(Object artikels) {
        try {
            ArrayList<Artikel> artikels1 = (ArrayList<Artikel>) artikels;
            Artikel artikel = artikels1.get(0);
            double totaals = getTotaal() + artikel.getVerkoopprijs();
            this.view.setTotaal(totaals);
            this.setTotaal(totaals);

            this.view.addArtikel(artikel);
        } catch (NullPointerException e) {
            this.view.getError().setVisible(true);
        }
    }

}
