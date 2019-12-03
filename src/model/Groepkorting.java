package model;

import controller.KassaController;

import java.util.ArrayList;

public class Groepkorting implements Korting {
    private Double percent;
    private String additional;

    public double berekenKorting(KassaController kassaController) {
        double res = 0;
        ArrayList<Artikel> artikelsMetKorting = kassaController.getAll();
        for (Artikel artikel : artikelsMetKorting) {
            if (artikel.getArtikelGroep().equals(additional)) {
                res += artikel.getVerkoopprijs()*(1-percent/100);
            }
        }
        return res;
    }

    public void setPercent(double parseDouble) {
        this.percent = parseDouble;
    }

    public void setAdditional(Object additional) {
        this.additional = (String)additional;
    }
}