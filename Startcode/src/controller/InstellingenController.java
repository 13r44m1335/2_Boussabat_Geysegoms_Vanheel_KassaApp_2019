package controller;

import model.Duurstekorting;
import model.DrempelKorting;
import model.Groepkorting;
import model.SoortOpslag;
import java.util.Properties;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import view.panels.InstellingenPane;
import model.Winkel;

public class InstellingenController
{
    private Winkel winkel;
    private InstellingenPane view;
    private KassaController kassaController;

    public void setKassaController(final KassaController kassaController) {
        this.kassaController = kassaController;
    }

    public InstellingenController(final Winkel winkel) {
        this.winkel = winkel;
    }

    public void setTxt() {
        final Properties properties = this.winkel.getProperties();
        FileOutputStream os = null;
        try {
            final File prop = new File("src/bestanden/instellingen.xml");
            os = new FileOutputStream(prop);
            properties.setProperty("input", "txt");
            properties.storeToXML(os, "");
            os.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void setExcel() {
        final Properties properties = this.winkel.getProperties();
        FileOutputStream os = null;
        try {
            final File prop = new File("src/bestanden/instellingen.xml");
            os = new FileOutputStream(prop);
            properties.setProperty("input", "xls");
            properties.storeToXML(os, "");
            os.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void setStandard() {
        final Object inputObj = this.winkel.getProperties().get("input");
        final String input = (String)inputObj;
        final SoortOpslag soortOplslag = SoortOpslag.valueOf(input);
        final String beschrijving = soortOplslag.getOmschrijving();
        if (beschrijving.equals("Excel")) {
            this.view.setExcelStandard();
        }
        else if (beschrijving.equals("Text")) {
            this.view.setTxtStandard();
        }
    }

    public void setView(final InstellingenPane instellingenPane) {
        this.view = instellingenPane;
    }

    public void createKorting(final String type, final String percent, final String additional) {
        this.winkel.setKorting(type);
        this.winkel.getKorting().setPercent(Double.parseDouble(percent));
        if (this.winkel.getKorting() instanceof Groepkorting) {
            this.winkel.getKorting().setAdditional((Object)additional);
        }
        else if (this.winkel.getKorting() instanceof DrempelKorting) {
            this.winkel.getKorting().setAdditional((Object)Double.parseDouble(additional));
        }
        else if (this.winkel.getKorting() instanceof Duurstekorting) {
            this.winkel.getKorting().setAdditional((Object)this.kassaController.getDuursteInKar());
        }
    }
}