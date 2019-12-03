package controller;

import database.LoadSave;
import model.*;
import view.panels.InstellingenPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Deze klasse fungeert als controller tussen de instellingen tab en de winkel.
 *
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class InstellingenController {

    private Winkel winkel;
    private InstellingenPane view;
    private KassaController kassaController;

    public void setKassaController(final KassaController kassaController) {
        this.kassaController = kassaController;
    }

    /**
     * Deze methode maakt een instantie aan van een isntellingencontroller ahv de winkel.
     *
     * @param winkel de winkel.
     * @author Andreas Geysegoms
     */
    public InstellingenController(Winkel winkel) {
        this.winkel = winkel;
    }

    /**
     * Deze methode stelt de input in als txt.
     * @author Andreas Geysegoms
     */
    public void setTxt() {
        Properties properties = winkel.getProperties();
        FileOutputStream os = null;
        try {
            File prop = new File("src/bestanden/instellingen.xml");
            os = new FileOutputStream(prop);
            properties.setProperty("input", "txt");
            properties.storeToXML(os, "");
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deze methode stelt de input in als een excel file.
     *
     * @author Andreas Geysegoms
     */
    public void setExcel() {
        Properties properties = winkel.getProperties();
        FileOutputStream os = null;
        try {
            File prop = new File("Startcode/src/bestanden/instellingen.xml");
            os = new FileOutputStream(prop);
            properties.setProperty("input", "xls");
            properties.storeToXML(os, "");
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deze methode stelt de radiobutton in ahv de properties.
     * @author Andreas Geysegoms
     */
    public void setStandard() {
        Object inputObj = winkel.getProperties().get("input");
        String input = (String) inputObj;
        SoortOplslag soortOplslag = SoortOplslag.valueOf(input);
        String beschrijving = soortOplslag.getOmschrijving();
        if (beschrijving.equals("Excel")) {
            view.setExcelStandard();
        } else if (beschrijving.equals("Text")) {
            view.setTxtStandard();
        }
    }

    /**
     * Deze methode stelt de view in.
     * @param instellingenPane de view.
     * @author Andreas Geysegoms
     */
    public void setView(InstellingenPane instellingenPane) {
        this.view = instellingenPane;
    }


    public void createKorting(final String type, final String percent, String additional) {
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
