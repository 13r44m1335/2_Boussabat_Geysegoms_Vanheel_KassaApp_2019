package model;

import controller.KassaController;

public interface Korting {

    double berekenKorting(KassaController kassaController);

    void setPercent(double parseDouble);

    void setAdditional(Object additional);
}
