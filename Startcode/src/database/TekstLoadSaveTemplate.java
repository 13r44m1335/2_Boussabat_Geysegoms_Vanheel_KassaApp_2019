package database;

import model.Artikel;
import java.util.ArrayList;

/**
 * Deze klasse is een template klasse om artikels in te lezen uit een file en op te slaan.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public abstract class TekstLoadSaveTemplate {
    /**
     * Deze methode leest artikels in via een input file.
     * @param filepath pad naar de input file.
     * @return Arraylist van Artikels.
     * @author Andreas Geysegoms
     */
    public abstract ArrayList<Artikel> load(String filepath);

    /**
     * Deze methode slaat artikels op.
     * @param artikels de ArrayList van artikels om op te slaan.
     * @param filepath pad naar de output file.
     * @author Andreas Geysegoms
     */
    public abstract void save(ArrayList<Artikel> artikels, String filepath);
}
