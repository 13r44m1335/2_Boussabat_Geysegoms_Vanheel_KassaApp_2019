package model;

public enum SoortKorting {
    Groepkorting("Groepkorting", "model.Groepkorting"),
    Drempelkorting("Drempelkorting", "model.Drempelkorting"),
    Duurstekorting("Duurstekorting", "model.Duurstekorting");

    private final String omschrijving;
    private final String klasseNaam;

    private SoortKorting(String omschrijving, String klasseNaam) {
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