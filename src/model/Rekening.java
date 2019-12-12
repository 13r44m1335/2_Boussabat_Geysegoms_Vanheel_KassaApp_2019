package model;

import controller.KassaController;
import controller.KlantController;
import javafx.util.Pair;
import view.KlantView;

import java.util.ArrayList;

import static controller.KlantController.artikelsToPair;

/**
 * Deze klasse wordt gebruikt om een rekening voor te stellen van onze kassa app.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class Rekening extends RekeningAbstract {

    private Winkel winkel;
    private String description;
    private double korting = 0;

    /**
     * Deze methode maakt een instance aan van de klasse.
     * @param winkel de winkel waarin de bon gemaakt wordt.
     * @author Andreas Geysegoms
     */
    public Rekening(Winkel winkel) {
        this.winkel = winkel;
        ArrayList<Artikel> artikels = winkel.getCurrent().getAll();
        this.description = "";
        createKassaBon(artikels);
    }

    /**
     * Deze methode schrijft de beschrijving van de artikels.
     * @param all alle artikels.
     * @author Andreas Geysegoms
     */
    private void createKassaBon(ArrayList<Artikel> all) {
        this.description += String.format("%-15s","Omschrijving") + String.format("%14s","Aantal") +String.format("%8s","Prijs") +"\n";
        this.description += "*************************************\n";
        ArrayList<Pair<Artikel, Integer>> paren = KlantController.artikelsToPair(all);
        for (Pair<Artikel, Integer> paar : paren) {
            String res = String.format("%-15s",paar.getKey().getOmschrijving()) + String.format("%11s",paar.getValue()) + String.format("%11s",String.format("%.2f", paar.getKey().getVerkoopprijs())) +"\n";
            this.description += res;
        }
        this.description += "*************************************\n";
        if (!winkel.getProperties().get("footerKorting").equals("")) {
            korting = Double.parseDouble((String) winkel.getProperties().get("footerKorting"));
        }
        try {
            this.description += String.format("Betaald (inclusief korting): € %.2f ", Double.parseDouble((String) winkel.getProperties().get("totaal")) - korting);
        } catch (NumberFormatException ignored) {

        }

    }

    /**
     * Deze methode haalt de beschrijving op.
     * @return de beschrijving.
     * @author Andreas Geysegoms
     */
    public String getDescription() {
        return description;
    }
}
