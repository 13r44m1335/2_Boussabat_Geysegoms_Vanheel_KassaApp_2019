package model;

public class Artikel {

    private String code;
    private String omschrijving;
    private String artikelGroep;
    private String verkoopprijs;

    public Artikel(String code, String omschrijving, String artikelGroep, String verkoopprijs) {
        this.code = code;
        this.omschrijving = omschrijving;
        this.artikelGroep = artikelGroep;
        this.verkoopprijs = verkoopprijs;
    }
}
