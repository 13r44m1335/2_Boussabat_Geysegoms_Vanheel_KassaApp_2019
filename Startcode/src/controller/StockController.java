package controller;

import model.Artikel;
import model.Observer;
import model.Winkel;
import view.panels.ProductOverviewPane;
import java.util.ArrayList;
import static model.SoortObserver.STOCK;

public class StockController extends Observer {

    private Winkel subject;
    private ProductOverviewPane stock;
    private ArrayList<Artikel> artikels;

    public StockController() {
        super();
        //TODO: cleaner
        Winkel winkel = new Winkel();
        this.setSubject(winkel);
        winkel.registerObserver(this, STOCK);
    }

    public void setSubject(Winkel subject) {
        this.subject = subject;
    }

    public void setStock(ProductOverviewPane stock) {
        this.stock = stock;
    }

    @Override
    public void update() {
        subject.toonStock();
        artikels = subject.getDb().load();
        stock.updateStockView(artikels);
    }
}
