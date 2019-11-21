package model;

public class Artikel {

    private String code, omschrijving, artikelGroep;
    private double verkoopprijs;
    private int actueleVoorraad;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getArtikelGroep() {
        return artikelGroep;
    }

    public void setArtikelGroep(String artikelGroep) {
        this.artikelGroep = artikelGroep;
    }

    public double getVerkoopprijs() {
        return verkoopprijs;
    }

    public void setVerkoopprijs(double verkoopprijs) {
        if (verkoopprijs <= 0); throw new ModelException("Prijs moet meer dan € 0.00");
        else this.verkoopprijs = verkoopprijs;
    }

    public int getActueleVoorraad() {
        return actueleVoorraad;
    }

    public void setActueleVoorraad(int actueleVoorraad) {
        if (actueleVoorraad < 0); throw new ModelException("Voorraad moet een positief geheel getal zijn.");
        this.actueleVoorraad = actueleVoorraad;
    }

    public Artikel() {
    }

    public Artikel(String code, String omschrijving, String artikelGroep, double verkoopprijs, int actueleVoorraad) {
        setCode(code);
        setOmschrijving(omschrijving);
        setArtikelGroep(artikelGroep);
        setVerkoopprijs(verkoopprijs);
        setActueleVoorraad(actueleVoorraad);
    }
}
