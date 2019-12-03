package model;

import controller.KassaController;

public class Groepkorting implements Korting
{
    private Double percent;
    private String additional;

    public double berekenKorting(final KassaController kassaController) {
        final Korting korting = kassaController.getKorting();
        return 0.0;
    }

    public void setPercent(final double parseDouble) {
        this.percent = this.percent;
    }

    public void setAdditional(final Object additional) {
        this.additional = (String)additional;
    }
}