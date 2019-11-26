package database;

import model.Artikel;

import java.util.ArrayList;

public interface LoadSave {

    ArrayList<Artikel> load(String filepath);

    void save(ArrayList<Artikel> artikels, String filepath);
}
