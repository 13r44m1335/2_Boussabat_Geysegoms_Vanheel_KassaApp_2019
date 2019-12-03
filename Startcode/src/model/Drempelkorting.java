package model;

import controller.KassaController;

public class Drempelkorting implements Korting {

    private double percent;
    private double additional;

    @Override
    public double berekenKorting(KassaController kassaController) {
        return 0;
    }

    @Override
    public void setPercent(double parseDouble) {
        this.percent = parseDouble;
    }

    @Override
    public void setAdditional(Object additional) {
        this.additional = (double) additional;
    }
}
