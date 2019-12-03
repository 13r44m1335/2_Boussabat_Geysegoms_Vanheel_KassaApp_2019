package model;

import controller.KassaController;

public class DrempelKorting implements Korting {
    private double percent;
    private double additional;

    @Override
    public double berekenKorting(KassaController kassaController) {
        return 0.0;
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