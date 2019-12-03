package model;

import controller.KassaController;

public interface Korting {
    public double berekenKorting(KassaController var1);

    public void setPercent(double var1);

    public void setAdditional(Object var1);
}