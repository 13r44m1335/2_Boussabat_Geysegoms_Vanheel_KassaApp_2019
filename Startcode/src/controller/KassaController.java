package controller;

<<<<<<< HEAD
import model.Artikel;
import model.Observer;
import model.Winkel;
import view.panels.ProductOverviewPane;

import java.util.ArrayList;

import static model.SoortObserver.STOCK;

public class KassaController extends Observer {

    private Winkel subject;
    private ProductOverviewPane stock;
    private ArrayList<Artikel> artikels;

    @Override
    public void update() {

    }

    public KassaController() {
        Winkel winkel = new Winkel();
        this.setSubject(winkel);
        winkel.registerObserver(this, STOCK);
    }
=======
import model.Winkel;
import view.panels.KassaPane;

public class KassaController {

    private Winkel winkel;
    private KassaPane view;

    public KassaController(Winkel winkel) {
        setWinkel(winkel);
    }

    public void setWinkel(Winkel winkel) {
        this.winkel = winkel;
    }

    public void setView(KassaPane view) {
        this.view = view;
    }


>>>>>>> 43c9c635d7fb31bded42dba0a65c80f087dd515f
}
