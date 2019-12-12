package controller;

import model.*;
import view.panels.KassaPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static model.SoortObserver.*;

/**
 * Deze klasse is de controller tussen winkel en view.
 */
public class KassaController extends Observer {

    private Winkel winkel;
    private KassaPane view;

    /**
     * Deze methode maakt een isntantie aan van een kassaController.
     *
     * @param winkel de winkel die gebruikt wordt.
     * @author Andreas Geysegoms
     */
    public KassaController(Winkel winkel) {
        super(winkel);
        setWinkel(winkel);
        winkel.registerObserver(this, SoortObserver.ARTIKELINSCANNEN);
        winkel.registerObserver(this, DELETEARTIKEL);
    }

    /**
     * Deze methode stelt de winkel in.
     *
     * @param winkel de winkel.
     * @author Andreas Geysegoms
     */
    public void setWinkel(Winkel winkel) {
        this.winkel = winkel;
    }

    /**
     * Deze methode stelt de view in.
     *
     * @param view de view.
     * @author Andreas Geysegoms
     */
    public void setView(KassaPane view) {
        this.view = view;
    }

    /**
     * Deze methode stelt het totale bedrag in.
     *
     * @param totaal het totale bedrag
     * @author Andreas Geysegoms
     */
    public void setTotaal(double totaal) {
        winkel.getCurrent().setTotaal(totaal);
    }

    /**
     * Deze methode haalt het totale bedrag op.
     *
     * @return het totale bedrag.
     * @author Andreas Geysegoms
     */
    public double getTotaal() {
        return winkel.getCurrent().getTotaal();
    }


    /**
     * Deze methode scant een item in in de winkel.
     *
     * @param code de code van het artikel dat ingescand wordt.
     * @author Andreas Geysegoms
     */
    public void scan(String code) {
        winkel.getCurrent().scan(code);
        view.getArtikelCodeField().clear();
    }


    /**
     * Deze methode updatet de view van kassa.
     *
     * @param artikels de artikels die doorgegeven worden.
     * @author Andreas Geysegoms
     */
    @Override
    public void update(ArrayList<Artikel> artikels, Enum soort) {
        try {
            if (artikels == null) {
                throw new NullPointerException("Dit artikel bestaat niet.");
            }
            double totaalS = 0;

            if (soort.equals(ARTIKELINSCANNEN)) {
                totaalS = this.berekenTotaal(artikels);
                this.view.setArtikels(artikels);

            }
            else if (soort.equals(DELETEARTIKEL)) {
                totaalS = this.berekenTotaal(getAll());
                this.view.setArtikels(getAll());

            }
            Properties properties = winkel.getProperties();
            this.setTotaal(totaalS);
            if (this.getKorting() != null) {
                double korting = this.getKorting().berekenKorting(this.getAll());
                totaalS = totaalS - korting;
                properties.setProperty("footerKorting",String.valueOf(korting));
            }
            totaalS = (double) Math.round(totaalS * 100.0) / 100.0;
            this.view.setTotaal(totaalS);
            properties.setProperty("totaal",String.valueOf(this.berekenTotaal(winkel.getCurrent().getAll())));
            FileOutputStream os = null;
            try {
                File prop = new File("src/bestanden/instellingen.xml");
                os = new FileOutputStream(prop);
                properties.storeToXML(os, "");
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            winkel.setProperties(properties);
        } catch (NullPointerException e) {
            this.view.getError().setText(e.getMessage());
            this.view.getError().setVisible(true);
        }
    }

    /**
     * Deze methode berekent het totaal van de producten zonder korting in rekening te brengen van de huidige winklewagen.
     * @param artikels de artikels van de huidige winkelwagen.
     * @return de totale prijs van de items in de winkelwagen.
     * @author Andreas Geysegoms
     */
    private double berekenTotaal(ArrayList<Artikel> artikels) {
        double res = 0;
        for (Artikel a : artikels) {
            res += a.getVerkoopprijs();
        }
        return res;
    }

    /**
     * Deze methode haalt de korting op.
     *
     * @return de korting
     * @author Andreas Geysegoms
     */
    public Korting getKorting() {
        return this.winkel.getKorting();
    }

    /**
     * Deze methode haalt alle artikels uit de winkelmand op.
     *
     * @return een ArrayList van Artikels.
     * @author Andreas Geysegoms
     */
    public ArrayList<Artikel> getAll() {
        return winkel.getCurrent().getAll();
    }

    /**
     * Deze methode haalt de view op.
     *
     * @return de view.
     * @author Andreas Geysegoms
     */
    public KassaPane getView() {
        return view;
    }

    /**
     * Deze methode zet een verkoop op hold.
     *
     * @author Andreas Geysegoms
     */
    public void putOnHold() {
        this.winkel.putOnHold();
        view.reset();
        view.setTotaal(winkel.getCurrent().getTotaal());
        winkel.notifyObservers(HOLD,new ArrayList<>(0));
    }

    /**
     * Deze methode de verkoop van hold terug op de voorgrond.
     *
     * @author Andreas Geysegoms
     */
    public void resume() {
        this.winkel.resume();
        view.resume(winkel.getCurrent().getAll());
        double totaal = winkel.getCurrent().getTotaal();
        Properties properties = winkel.getProperties();
        if (this.getKorting() != null) {
            double korting = this.getKorting().berekenKorting(this.getAll());
            totaal = totaal - korting;
            properties.setProperty("footerKorting",String.valueOf(korting));
        }
        totaal = (double) Math.round(totaal * 100.0) / 100.0;
        winkel.setHold(new Winkelwagen(winkel));
        this.view.setTotaal(totaal);
        winkel.notifyObservers(RESUME, winkel.getCurrent().getAll());
        properties.setProperty("totaal",String.valueOf(this.berekenTotaal(winkel.getCurrent().getAll())));
        FileOutputStream os = null;
        try {
            File prop = new File("src/bestanden/instellingen.xml");
            os = new FileOutputStream(prop);
            properties.storeToXML(os, "");
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        winkel.setProperties(properties);
    }

    /**
     * Thomas
     *
     * @param code
     */
    public void verwijderArtikel(Artikel code) {
        winkel.getCurrent().deleteArtikel(code);
        view.getArtikelCodeField().clear();
    }

    /**
     * Deze methode verwijdert een artikel uit de winkelwagen ahv het invoerveld.
     *
     * @param artikelCode de code van het artikel dat verwijderd dient te worden.
     * @author Andreas Geysegoms
     */
    public void verwijderArtikelByInput(String artikelCode) {
        try {
            Artikel a = winkel.getCurrent().get(artikelCode);
            verwijderArtikel(a);
        } catch (IllegalArgumentException e) {
            view.getError().setText(e.getMessage());
            view.getError().setVisible(true);
        }
    }

    /**
     * Deze methode geeft door aan de winkel om de rekening te printen.
     * @author Andreas Geysegoms
     */
    public void print() {
        winkel.printRekening();
    }
}
