package model;

import controller.KassaController;

public interface Korting {
    public double berekenKorting(KassaController kassaController);

    public void setPercent(double percent);

    public void setAdditional(Object additional);
}