package model;

import controller.KassaController;

public class Duurstekorting implements Korting {

    private double percent;
    private Artikel additional;

    @Override
    public double berekenKorting(KassaController kassaController) {
        return 0;
    }

    @Override
    public void setPercent(double parseDouble) {
        this.percent = percent;
    }

    @Override
    public void setAdditional(Object additional) {
        this.additional = (Artikel) additional;
    }
}
