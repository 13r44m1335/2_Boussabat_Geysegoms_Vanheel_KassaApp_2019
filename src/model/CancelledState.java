package model;

public class CancelledState implements VerkoopState {

    private Verkoop verkoop;

    public CancelledState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void setToActive() {
        System.out.println("op actief zetten niet mogelijk, al ge annuleerd");
    }

    @Override
    public void cancelVerkoop() {
        System.out.println("annuleren niet mogelijk, al ge annuleerd");
    }

    @Override
    public void setToOnHold() {
        System.out.println("on hold zetten niet mogelijk, al ge annuleerd");

    }

    @Override
    public void sold() {
        System.out.println("kan niet verkocht worden, al ge annuleerd");
    }
}
