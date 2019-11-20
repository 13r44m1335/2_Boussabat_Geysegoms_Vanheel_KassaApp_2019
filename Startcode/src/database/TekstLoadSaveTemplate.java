package database;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class TekstLoadSaveTemplate {

    private HashMap<String id, Artikel artikel> db = new HashMap<>();

    public void loadSave(String filepath) {

    }

    public ArrayList<Artikel> load(String filepath) {
        ArrayList<Artikel> artikels = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(filepath);
            
        } catch ()

    }

    public void save(ArrayList<Artikel>) {

    }
}
