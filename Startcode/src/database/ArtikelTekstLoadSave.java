package database;

import model.Artikel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Deze klasse wordt gebruikt om artikels in te lezen uit een file en op te slaan, gebruik makende van .txt bestanden.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {

    /**
     * Deze methode maakt een instantie van de klasse aan.
     * @author Andreas Geysegoms
     */
    public ArtikelTekstLoadSave() {
    }

    /**
     * Deze methode leest artikels uit via een txt input file.
     * @param filepath pad naar de input file.
     * @return Arraylist van Artikels.
     * @author Andreas Geysegoms
     */
    @Override
    public ArrayList<Artikel> load(String filepath) {
        ArrayList<Artikel> artikels = new ArrayList<>();
        File file = new File(filepath);

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                Scanner lineScanner = new Scanner(fileScanner.nextLine());
                lineScanner.useDelimiter(",");

                String code = lineScanner.next();
                String omschrijving = lineScanner.next();
                String artikelGroep = lineScanner.next();
                String prijsRaw = lineScanner.next();
                double prijs = Double.parseDouble(prijsRaw);
                String actueleVoorraadRaw = lineScanner.next();
                int actueleVoorraad = Integer.parseInt(actueleVoorraadRaw);

                Artikel artikel = new Artikel(code, omschrijving, artikelGroep, prijs, actueleVoorraad);

                artikels.add(artikel);
            }

            return artikels;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * Deze methode slaat artikels op in artikel.txt
     * @param artikels de ArrayList van artikels om op te slaan.
     * @param filepath pad naar de output file.
     * @author Andreas Geysegoms
     */
    @Override
    public void save(ArrayList<Artikel> artikels, String filepath) {
        try {
            File file = new File(filepath);
            PrintWriter printWriter = new PrintWriter(file);
            for (Artikel artikel : artikels)  {
                String code = artikel.getCode();
                String omschrijving = artikel.getOmschrijving();
                String artikelGroep = artikel.getArtikelGroep();
                double prijs = artikel.getVerkoopprijs();
                int actueleVoorraad = artikel.getActueleVoorraad();
                String res = code + "," + omschrijving + "," + artikelGroep + "," + prijs + "," + actueleVoorraad+"\n";
                printWriter.write(res);
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
