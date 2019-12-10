package database;

import model.Artikel;
import java.util.ArrayList;

/**
 * Deze interface dient als strategy om eenvoudig een andere type database te kiezen.
 * @author Andreas Geysegoms
 * @version 1.0
 */
//TODO: input @ runtime changen.
public interface DbBehaviour {
    /**
     * Deze methode leest artikels in van de database.
     * @return Arraylist van Artikels.
     * @author Andreas Geysegoms
     */
    ArrayList<Artikel> load();
    /**
     * Deze methode slaat artikels op.
     * @param artikels de ArrayList van artikels om op te slaan.
     * @author Andreas Geysegoms
     */
    void save(ArrayList<Artikel> artikels);

    /**
     * Deze methode zoekt een artikel uit de db.
     * @param code de code van het artikel.
     * @return het artikel.
     * @author Andreas Geysegoms
     */
    Artikel search(String code);
}
