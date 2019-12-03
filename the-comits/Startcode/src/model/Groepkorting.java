package model;

import controller.KassaController;

public class Groepkorting implements Korting
{
    private Double percent;
    private String additional;

    public double berekenKorting(KassaController kassaController) {
        Korting korting = kassaController.getKorting();
        return 0.0;
    }

    public void setPercent(double parseDouble) {
        this.percent = this.percent;
    }

    public void setAdditional(Object additional) {
        this.additional = (String)additional;
    }
}