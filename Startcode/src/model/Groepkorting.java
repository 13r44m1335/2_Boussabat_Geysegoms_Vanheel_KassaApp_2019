package model;

import controller.KassaController;

import java.util.Objects;

public class Groepkorting implements Korting {

    private Double percent;
    private String additional;

    public Groepkorting() {
    }

    @Override
    public double berekenKorting(KassaController kassaController) {
        Korting korting = kassaController.getKorting();
        return 0;
    }

    @Override
    public void setPercent(double parseDouble) {
        this.percent = percent;
    }

    @Override
    public void setAdditional(Object additional) {
        this.additional = (String) additional;
    }
}
