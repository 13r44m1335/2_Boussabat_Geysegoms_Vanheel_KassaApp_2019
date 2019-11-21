package database;

import model.Artikel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class TekstLoadSaveTemplate {

    private static HashMap<String, Artikel> db = new HashMap<>();

    public static void loadAndSave(String filepath) {
        ArrayList<Artikel> artikels = load(filepath);
        save(artikels);
    }

    protected static ArrayList<model.Artikel> load(String filepath) {
        ArrayList<Artikel> artikels = new ArrayList<>();

        File file = new File(filepath);

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
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

    protected static void save(ArrayList<Artikel> artikels) {
        for (Artikel artikel : artikels) {
            db.put(artikel.getCode(), artikel);
        }
    }
}
