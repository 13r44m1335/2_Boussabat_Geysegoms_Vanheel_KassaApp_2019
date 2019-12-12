package model;

import controller.InstellingenController;
import controller.KassaController;

/**
 * Deze klasse wordt gebruikt om een custom header te printen.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class CustomHeader extends RekeningDecorator {

    private RekeningAbstract rekening;
    private String text;

    /**
     * Deze methode maakt een instantie van deze klasse ahv de originele rekening en de text.
     * @param rekening de origninele rekening.
     * @param text de header tekst.
     * @author Andreas Geysegoms
     */
    public CustomHeader(RekeningAbstract rekening, String text) {
        this.rekening = rekening;
        this.text = text;
    }

    /**
     * Deze methode haalt de beschrijving op van de header.
     * @return de aangepaste header.
     * @author Andreas Geysegoms
     */
    @Override
    public String getDescription() {
        return text + "\n" + rekening.getDescription();
    }
}
