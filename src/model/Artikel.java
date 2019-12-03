package model;

public class Artikel implements Comparable<Artikel>
{
    private String code;
    private String omschrijving;
    private String artikelGroep;
    private double verkoopprijs;
    private int actueleVoorraad;
    private final String AANTAL = "1";

    public String getAANTAL() {
        return "1";
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getOmschrijving() {
        return this.omschrijving;
    }

    public void setOmschrijving(final String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getArtikelGroep() {
        return this.artikelGroep;
    }

    public void setArtikelGroep(final String artikelGroep) {
        this.artikelGroep = artikelGroep;
    }

    public double getVerkoopprijs() {
        return this.verkoopprijs;
    }

    public void setVerkoopprijs(final double verkoopprijs) {
        if (verkoopprijs <= 0.0) {
            throw new IllegalArgumentException("Prijs moet meer dan \u20ac 0.00");
        }
        this.verkoopprijs = verkoopprijs;
    }

    public int getActueleVoorraad() {
        return this.actueleVoorraad;
    }

    public void setActueleVoorraad(final int actueleVoorraad) {
        if (actueleVoorraad < 0) {
            throw new IllegalArgumentException("Voorraad moet een positief geheel getal zijn.");
        }
        this.actueleVoorraad = actueleVoorraad;
    }

    public Artikel(final String code, final String omschrijving, final String artikelGroep, final double verkoopprijs, final int actueleVoorraad) {
        this.setCode(code);
        this.setOmschrijving(omschrijving);
        this.setArtikelGroep(artikelGroep);
        this.setVerkoopprijs(verkoopprijs);
        this.setActueleVoorraad(actueleVoorraad);
    }

    @Override
    public int compareTo(final Artikel o) {
        return this.getOmschrijving().compareTo(o.getOmschrijving());
    }

    @Override
    public boolean equals(final Object o) {
        final Artikel a = (Artikel)o;
        return a.getOmschrijving().equals(this.getOmschrijving());
    }
}