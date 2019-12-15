package model;

public class OnHoldState implements VerkoopState {

    private Verkoop verkoop;

    public OnHoldState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void setToActive() {
        System.out.println("Terug op ctief gezet");
        verkoop.setCurrent(verkoop.getActiveState());
    }

    @Override
    public void cancelVerkoop() {
        System.out.println("zet eerst op actief, dan cancel mogelijk");
    }

    @Override
    public void setToOnHold() {
        System.out.println("staat al op hold");
    }

    @Override
    public void sold() {
        System.out.println("zet eerst op actief, dan sold mogelijk");
    }
}
