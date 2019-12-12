package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Deze klasse wordt gebruikt als een factory om de rekening te maken.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class RekeningFactory {
    private static RekeningFactory ourInstance = new RekeningFactory();
    private Winkel winkel;

    /**
     * Deze methode haalt de enige instance op van de klasse.
     * @return de enige instance van de klasse.
     * @author Andreas Geysegoms
     */
    public static RekeningFactory getInstance() {
        return ourInstance;
    }

    /**
     * Deze methode maakt een instance aan van de klasse.
     * @author Andreas Geysegoms
     */
    private RekeningFactory() {
    }

    /**
     * Deze methode stelt de winkel in.
     * @param winkel de winkel.
     * @author Andreas Geysegoms
     */
    public void setWinkel(Winkel winkel) {
        this.winkel = winkel;
    }

    /**
     * Deze methode maakt een rekening ahv de properties.
     * @param properties de properties.
     * @return de rekening.
     * @author Andreas Geysegoms
     */
    public RekeningAbstract create(Properties properties) {
        RekeningAbstract rekening = new Rekening(winkel);
        try {
            if (!properties.get("headerCustom").equals("")) {
                rekening = new CustomHeader(rekening, (String) properties.get("headerCustom"));
            }
            if (!properties.get("headerTijd").equals("")) {
                rekening = new HeaderTijd(rekening);
            }
            if (!properties.get("footerKorting").equals("")) {
                rekening = new FooterZonderKorting(rekening, Double.parseDouble((String) properties.get("footerKorting")), Double.parseDouble((String) properties.get("totaal")));
            }
            if (!properties.get("footerBTW").equals("") && winkel.getKorting() != null) {
                double totaal = Double.parseDouble((String) properties.get("totaal")) - Double.parseDouble((String) properties.get("footerKorting"));
                rekening = new FooterZonderBTW(rekening, totaal);
            } else if (!properties.get("footerBTW").equals("")) {
                rekening = new FooterZonderBTW(rekening, Double.parseDouble((String) properties.get("totaal")));
            }
            if (!properties.get("footerAfsluitlijn").equals("")) {
                rekening = new FooterBericht(rekening);
            }
            return rekening;
        } catch (NumberFormatException ignored) {
            return rekening;
        }
    }
}