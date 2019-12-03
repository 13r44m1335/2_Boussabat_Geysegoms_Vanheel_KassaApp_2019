package database;

import model.Artikel;
import java.util.ArrayList;

public interface LoadSave
{
    ArrayList<Artikel> load(final String p0);

    void save(final ArrayList<Artikel> p0, final String p1);
}