package controller;

import model.Winkel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Deze klasse fungeert als controller tussen de instellingen tab en de winkel.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class InstellingenController {

    private Winkel winkel;

    /**
     * Deze methode maakt een instantie aan van een isntellingencontroller ahv de winkel.
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
            properties.setProperty("input","txt");
            properties.storeToXML(os,"");
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deze methode stelt de input in als een excel file.
     * @author Andreas Geysegoms
     */
    public void setExcel() {
        Properties properties = winkel.getProperties();
        FileOutputStream os = null;
        try {
            File prop = new File("src/bestanden/instellingen.xml");
            os = new FileOutputStream(prop);
            properties.setProperty("input","xls");
            properties.storeToXML(os,"");
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
