package model;

public enum SoortOpslag {
    xls("Excel", "database.ExcelAdapter"),
    txt("Text", "database.ArtikelTekstLoadSave");

    private final String omschrijving;
    private final String klasseNaam;

    private SoortOpslag(String omschrijving, String klasseNaam) {
        this.omschrijving = omschrijving;
        this.klasseNaam = klasseNaam;
    }

    public String getOmschrijving() {
        return this.omschrijving;
    }

    public String getKlasseNaam() {
        return this.klasseNaam;
    }
}