package model;

import controller.KassaController;

public class Duurstekorting
        implements Korting {
    private double percent;
    private Artikel additional;

    public double berekenKorting(KassaController kassaController) {
        return 0.0;
    }

    public void setPercent(double parseDouble) {
        this.percent = this.percent;
    }

    public void setAdditional(Object additional) {
        this.additional = (Artikel)additional;
    }
}