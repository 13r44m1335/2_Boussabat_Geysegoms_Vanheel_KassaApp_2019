package controller;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import model.ComparatorByOmschrijving;
import model.SoortObserver;
import model.Artikel;
import java.util.ArrayList;
import view.panels.ProductOverviewPane;
import model.Winkel;
import model.Observer;

public class StockController extends Observer
{
    private Winkel subject;
    private ProductOverviewPane stock;
    private ArrayList<Artikel> artikels;

    public StockController(final Winkel winkel) {
        super(winkel);
        this.setSubject(winkel);
        this.subject.registerObserver((Observer)this, SoortObserver.STOCK);
    }

    public void setSubject(final Winkel subject) {
        this.subject = subject;
    }

    public void setStock(final ProductOverviewPane stock) {
        this.stock = stock;
    }

    @Override
    public void update(final Object object) {
        Collections.sort(this.artikels = this.subject.getDb().load(), new ComparatorByOmschrijving());
        this.stock.updateStockView(this.artikels);
    }

    public void toonArtikelen() {
        this.subject.toonStock();
    }
}