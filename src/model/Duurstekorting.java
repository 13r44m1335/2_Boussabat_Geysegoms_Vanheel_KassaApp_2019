package model;

import controller.KassaController;

import java.util.ArrayList;

public class Duurstekorting implements Korting {
    private double percent;
    private Artikel additional;

    public double berekenKorting(KassaController kassaController) {
        ArrayList<Artikel> cart = kassaController.getAll();
        Artikel duurste = new Artikel("XXX","dummy","grdf",0.01,1);
        for (Artikel artikel : cart) {
            if (duurste.getVerkoopprijs() < artikel.getVerkoopprijs()) {
                duurste = artikel;
            }
        }
        return duurste.getVerkoopprijs()*(1-percent/100);
    }

    public void setPercent(double parseDouble) {
        this.percent = parseDouble;
    }

    public void setAdditional(Object additional) {
        this.additional = (Artikel)additional;
    }
}