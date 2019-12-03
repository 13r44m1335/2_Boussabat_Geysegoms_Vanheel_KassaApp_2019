package model;

import controller.KassaController;

import java.util.ArrayList;

/**
 * Deze klasse implementeert de 'Korting' interface. Specifieker zorgt deze klasse voor de groepkorting.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class Groepkorting implements Korting {
    private Double percent;
    private String additional;

    /**
     * Deze methode returnt de totale korting.
     * @param kassaController de controller waar de cart te vinden is.
     * @return de totale korting.
     * @author Andreas Geysegoms
     */
    @Override
    public double berekenKorting(KassaController kassaController) {
        double res = 0;
        ArrayList<Artikel> artikelsMetKorting = kassaController.getAll();
        for (Artikel artikel : artikelsMetKorting) {
            if (artikel.getArtikelGroep().equals(additional)) {
                res += artikel.getVerkoopprijs()*(percent/100);
            }
        }
        return res;
    }

    /**
     * Deze methode stelt de percentage korting in.
     * @param percent het percentage in te stellen.
     * @author Andreas Geysegoms
     */
    @Override
    public void setPercent(double percent) {
        this.percent = percent;
    }

    /**
     * Deze methode stelt de additional in.
     * @param additional de extra info.
     * @author Andreas Geysegoms
     */
    @Override
    public void setAdditional(Object additional) {
        this.additional = (String)additional;
    }
}