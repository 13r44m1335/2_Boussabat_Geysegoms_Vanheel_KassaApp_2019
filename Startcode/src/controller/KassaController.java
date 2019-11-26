package controller;

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
}
