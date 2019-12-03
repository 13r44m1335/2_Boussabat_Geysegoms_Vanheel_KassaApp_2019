package model;

import controller.KassaController;
import model.Korting;

public class Drempelkorting
        implements Korting {
    private double percent;
    private double additional;

    public double berekenKorting(KassaController kassaController) {
        return 0.0;
    }

    public void setPercent(double parseDouble) {
        this.percent = parseDouble;
    }

    public void setAdditional(Object additional) {
        this.additional = (Double)additional;
    }
}