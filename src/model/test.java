package model;

import controller.KassaController;
import view.panels.KassaPane;

public class test {

    public static void main(String[] args) {

    Winkel winkel = new Winkel();
    Winkelwagen winkelwagen = new Winkelwagen(winkel);
    Verkoop verkoop = new Verkoop(winkel);

    }
}