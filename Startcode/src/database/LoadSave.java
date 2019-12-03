package database;

import model.Artikel;

import java.util.ArrayList;

/**
 * Deze interface beschrijft een file inlezer / wegschrijver.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public interface LoadSave {
    /**
     * Deze methode leest artikels in, gegeven een inputfile.
     * @param filepath het pad naar de inputfile.
     * @return een lijst van artikels.
     * @author Andreas Geysegoms
     */
    ArrayList<Artikel> load(String filepath);

    /**
     * Deze methode schrijft artikels weg, gegeven een outputfile.
     * @param artikels de artikels om weg te schrijven.
     * @param filepath het pad naar de output file.
     * @author Andreas Geysegoms
     */
    void save(ArrayList<Artikel> artikels, String filepath);
}
