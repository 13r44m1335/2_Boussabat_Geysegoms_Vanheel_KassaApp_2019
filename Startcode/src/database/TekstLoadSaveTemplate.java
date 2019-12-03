package database;

import model.Artikel;
import java.util.ArrayList;

public abstract class TekstLoadSaveTemplate implements LoadSave
{
    @Override
    public abstract ArrayList<Artikel> load(final String p0);

    @Override
    public abstract void save(final ArrayList<Artikel> p0, final String p1);

    public abstract void loadAndSave(final String p0);
}