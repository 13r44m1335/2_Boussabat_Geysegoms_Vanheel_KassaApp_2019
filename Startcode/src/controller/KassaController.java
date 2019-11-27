package controller;

import model.Winkel;
import view.panels.KassaPane;

public class KassaController {

    private Winkel winkel;
    private KassaPane view;

    public KassaController(Winkel winkel) {
        setWinkel(winkel);
    }

    public void setWinkel(Winkel winkel) {
        this.winkel = winkel;
    }

    public void setView(KassaPane view) {
        this.view = view;
    }


}
