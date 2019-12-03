package model;

import controller.KassaController;

public class Drempelkorting implements Korting {
    private double percent;
    private double additional;

    @Override
    public double berekenKorting(KassaController kassaController) {
        if (kassaController.getTotaal() > additional) return kassaController.getTotaal()*(1-percent/100);
        else return 0;
    }
    @Override
    public void setPercent(double parseDouble) {
        this.percent = parseDouble;
    }
    @Override
    public void setAdditional(Object additional) {
        this.additional = (Double)additional;
    }
}