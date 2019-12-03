package model;

import controller.KassaController;

import java.util.ArrayList;
/**
 * Deze klasse implementeert de 'Korting' interface. Specifieker zorgt deze klasse voor de duurstekorting.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class Duurstekorting implements Korting {
    private double percent;
    private Artikel additional;

    /**
     * Deze methode returnt de totale korting.
     * @param kassaController de controller waar de cart te vinden is.
     * @return de totale korting.
     * @author Andreas Geysegoms
     */
    @Override
    public double berekenKorting(KassaController kassaController) {
        ArrayList<Artikel> cart = kassaController.getAll();
        Artikel duurste = new Artikel("XXX","dummy","grdf",0.01,1);
        for (Artikel artikel : cart) {
            if (duurste.getVerkoopprijs() < artikel.getVerkoopprijs()) {
                duurste = artikel;
            }
        }
        return duurste.getVerkoopprijs()*(percent/100);
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
        this.additional = (Artikel)additional;
    }
}