package model;

import java.util.Comparator;
import model.Artikel;

public class ComparatorByOmschrijving
        implements Comparator<Artikel> {
    @Override
    public int compare(Artikel o1, Artikel o2) {
        if (o1 == null || o2 == null) {
            return 1;
        }
        return o1.getOmschrijving().compareTo(o2.getOmschrijving());
    }
}