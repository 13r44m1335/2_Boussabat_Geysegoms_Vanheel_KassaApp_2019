package model;

import controller.KassaController;
/**
 * Deze interface dient als template voor alle kortingen.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public interface Korting {

    /**
     * Deze methode returnt de totale korting.
     * @param kassaController de controller waar de cart te vinden is.
     * @return de totale korting.
     * @author Andreas Geysegoms
     */
    double berekenKorting(KassaController kassaController);

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
}