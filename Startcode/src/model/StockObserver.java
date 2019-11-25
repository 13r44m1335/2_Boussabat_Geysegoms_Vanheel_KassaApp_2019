package model;

import java.util.ArrayList;

/**
 * Deze klasse erft over van Observer en behandelt de update van STOCK.
 * Dit is de communicatie tussen controller en Db.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class StockObserver extends Observer {

    private Winkel subject;

    /**
     * Deze methode maakt een instantie van de klasse aan.
     * @param subject de winkel waar deze klasse aan gekoppeld is.
     * @author Andreas Geysegoms
     */
    public StockObserver(Winkel subject) {
        super(subject);
        this.subject = subject;
    }

    /**
     * Deze methode laad de gegevens in en updatet de waarde voor stock.
     * @author Andreas Geysegoms
     */
    @Override
    public void update() {
        ArrayList<Artikel> artikels = subject.getDb().load();
        for (Artikel artikel : artikels) {
            System.out.println(artikel.getOmschrijving());
        }
    }
}
