package model;

/**
 * Deze klasse slaat alle soorten opslag op, alsook hun beschrijving en klasseNaam.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public enum SoortOplslag {
    xls ("Excel", "database.ExcelAdapter"),
    txt ("text", "database.ArtikelTekstLoadSave");

    private final String omschrijving;
    private final String klasseNaam;

    /**
     * Deze methode stelt de omschrijving en klassenaam in.
     * @param omschrijving omschrijving.
     * @param klasseNaam klasse naam.
     * @author Andreas Geysegoms
     */
    SoortOplslag(String omschrijving, String klasseNaam) {
        this.omschrijving = omschrijving;
        this.klasseNaam = klasseNaam;
    }

    /**
     * Deze methode returnt de beschrijving van de opslag.
     * @return de beschrijving.
     * @author Andreas Geysegoms
     */
    public String getOmschrijving() { return omschrijving; }

    /**
     * Deze methode returnt de klasse naam van de opslag.
     * @return de klasse naam.
     * @author Andreas Geysegoms
     */
    public String getKlasseNaam() { return klasseNaam; }
}
