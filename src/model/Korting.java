package model;

import controller.KassaController;

import java.util.ArrayList;

/**
 * Deze interface dient als template voor alle kortingen.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public interface Korting {

    /**
     * Deze methode returnt de totale korting.
     * @param artikels de controller waar de cart te vinden is.
     * @return de totale korting.
     * @author Andreas Geysegoms
     */
    double berekenKorting(ArrayList<Artikel> artikels);

    /**
     * Deze methode stelt de percentage korting in.
     * @param percent het percentage in te stellen.
     * @author Andreas Geysegoms
     */
    void setPercent(double percent);

    /**
     * Deze methode stelt de additional in.
     * @param additional de extra info.
     * @author Andreas Geysegoms
     */
    void setAdditional(Object additional);

    default double berekenTotaal(ArrayList<Artikel> artikels) {
        double res = 0;
        for (Artikel a : artikels) {
            res += a.getVerkoopprijs();
        }
        return res;
    }
}