package controller;

import model.Artikel;
import model.ComparatorByOmschrijving;
import model.Observer;
import model.Winkel;
import view.panels.ProductOverviewPane;
import java.util.ArrayList;
import java.util.Collections;

import static model.SoortObserver.STOCK;

/**
 * Deze klasse fungeert als controller tussen tab2 van de kassa (Stock) en de winkel.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class StockController extends Observer {

    private Winkel subject;
    private ProductOverviewPane stock;
    private ArrayList<Artikel> artikels;

    /**
     * Deze methode maakt een instantie van deze klasse aan ahv een winkel.
     * @param winkel de winkel
     * @author Andreas Geysegoms
     */
    public StockController(Winkel winkel) {
        super(winkel);
        this.setSubject(winkel);
        subject.registerObserver(this, STOCK);
    }

    /**
     * Deze methode stelt een winkel in.
     * @param subject de winkel.
     * @author Andreas Geysegoms
     */
    public void setSubject(Winkel subject) {
        this.subject = subject;
    }

    /**
     * Deze methode stelt de stock in.
     * @param stock de stock
     * @author Andreas Geysegoms
     */
    public void setStock(ProductOverviewPane stock) {
        this.stock = stock;
    }

    /**
     * Deze methode updatet de stock bij de view. Deze artikelen zijn gesorteerd op beschrijving.
     * @author Andreas Geysegoms
     */
    @Override
    public void update(ArrayList<Artikel> artikels) {
        artikels = subject.getDb().load();
        Collections.sort(artikels, new ComparatorByOmschrijving());
        stock.updateStockView(artikels);
    }

    /**
     * Deze methode toont de artikelen van de stock.
     */
    public void toonArtikelen(){
        subject.toonStock();
    }
}
