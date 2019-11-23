package database;

import model.Artikel;
import java.util.ArrayList;

/**
 * Deze interface dient als strategy om eenvoudig een andere type database te kiezen.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public interface DbBehaviour {
    /**
     * Deze methode leest data uit de input file en slaat ze op.
     * @param filepath pad naar de input file.
     * @author Andreas Geysegoms
     */
    void loadAndSave(String filepath);
    /**
     * Deze methode leest artikels in via een input file.
     * @param filepath pad naar de input file.
     * @return Arraylist van Artikels.
     * @author Andreas Geysegoms
     */
    ArrayList<Artikel> load(String filepath);
    /**
     * Deze methode slaat artikels op.
     * @param artikels de ArrayList van artikels om op te slaan.
     * @author Andreas Geysegoms
     */
    void save(ArrayList<Artikel> artikels);
}
