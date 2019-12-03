package database;

import database.TekstLoadSaveTemplate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import model.Artikel;

public class ArtikelTekstLoadSave
        extends TekstLoadSaveTemplate {
    public ArrayList<Artikel> load(String filepath) {
        ArrayList<Artikel> artikels = new ArrayList<Artikel>();
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
            Collections.sort(artikels);
            return artikels;
        }
        catch (FileNotFoundException e) {
            return null;
        }
    }

    public void save(ArrayList<Artikel> artikels, String filepath) {
        try {
            File file = new File(filepath);
            PrintWriter printWriter = new PrintWriter(file);
            for (Artikel artikel : artikels) {
                String code = artikel.getCode();
                String omschrijving = artikel.getOmschrijving();
                String artikelGroep = artikel.getArtikelGroep();
                double prijs = artikel.getVerkoopprijs();
                int actueleVoorraad = artikel.getActueleVoorraad();
                String res = code + "," + omschrijving + "," + artikelGroep + "," + prijs + "," + actueleVoorraad + "\n";
                printWriter.write(res);
            }
            printWriter.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadAndSave(String filepath) {
        this.save(this.load(filepath), filepath);
    }
}