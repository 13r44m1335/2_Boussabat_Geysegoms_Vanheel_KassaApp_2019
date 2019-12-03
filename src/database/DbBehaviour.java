package database;

import java.util.ArrayList;
import model.Artikel;

public interface DbBehaviour {
    public ArrayList<Artikel> load();

    public void save(ArrayList<Artikel> var1);

    public Artikel search(String var1);
}