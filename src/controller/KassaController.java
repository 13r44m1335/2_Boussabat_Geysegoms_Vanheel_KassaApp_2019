package controller;

import model.Korting;
import model.Duurstekorting;
import model.Drempelkorting;
import model.Groepkorting;
import java.util.ArrayList;
import model.SoortObserver;
import model.Artikel;
import view.panels.KassaPane;
import model.Winkel;
import model.Observer;

public class KassaController extends Observer
{
    private Winkel winkel;
    private KassaPane view;
    private double totaal;
    private Artikel duurste;
    private String groep;
    private double hogerDanBedrag;

    public KassaController(final Winkel winkel) {
        super(winkel);
        this.totaal = 0.0;
        this.duurste = null;
        this.setWinkel(winkel);
        winkel.registerObserver((Observer)this, SoortObserver.ARTIKELINSCANNEN);
    }

    public void setWinkel(final Winkel winkel) {
        this.winkel = winkel;
    }

    public void setView(final KassaPane view) {
        this.view = view;
    }

    public void setTotaal(final double totaal) {
        this.totaal = totaal;
    }

    public double getTotaal() {
        return this.totaal;
    }

    public void update(final Object artikels) {
        try {
            final ArrayList<Artikel> artikels2 = (ArrayList<Artikel>)artikels;
            final Artikel artikel = artikels2.get(0);
            final double totaalS = this.getTotaal() + artikel.getVerkoopprijs();
            this.view.setTotaal(totaalS);
            this.setTotaal(totaalS);
            this.view.addArtikel(artikel);
            if (this.duurste == null) {
                this.duurste = artikel;
            }
            else if (this.duurste.getVerkoopprijs() < artikel.getVerkoopprijs()) {
                this.duurste = artikel;
            }
        }
        catch (NullPointerException e) {
            this.view.getError().setVisible(true);
        }
    }

    public void scan(final String code) {
        this.winkel.scan(code);
    }

    public Artikel getDuursteInKar() {
        return this.duurste;
    }

    public void setDetails(final double percent, final Object additional) {
        if (this.winkel.getKorting() instanceof Groepkorting) {
            final String s = (String)additional;
        }
        else if (this.winkel.getKorting() instanceof Drempelkorting) {
            (double)additional;
        }
        else if (this.winkel.getKorting() instanceof Duurstekorting) {
            final Artikel artikel = (Artikel)additional;
        }
    }

    public Korting getKorting() {
        return this.winkel.getKorting();
    }
}