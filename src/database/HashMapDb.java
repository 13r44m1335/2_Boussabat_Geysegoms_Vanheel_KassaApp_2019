package database;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import model.Artikel;
import java.util.HashMap;

public class HashMapDb implements DbBehaviour
{
    private HashMap<String, Artikel> db;

    public HashMapDb() {
        this.db = new HashMap<String, Artikel>();
    }

    @Override
    public Artikel search(final String code) {
        return this.db.get(code);
    }

    @Override
    public ArrayList<Artikel> load() {
        final ArrayList<Artikel> res = new ArrayList<Artikel>();
        res.addAll(this.db.values());
        return res;
    }

    @Override
    public void save(final ArrayList<Artikel> artikels) {
        for (final Artikel artikel : artikels) {
            this.db.put(artikel.getCode(), artikel);
        }
    }
}