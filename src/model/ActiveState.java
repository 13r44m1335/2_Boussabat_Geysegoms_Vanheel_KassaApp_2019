package model;

public class ActiveState implements VerkoopState{

    private Verkoop verkoop;

    public ActiveState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void setToActive() {
        System.out.println("staat al op actief");
    }

    @Override
    public void cancelVerkoop() {
        System.out.println("verkoop geannuleerd");
        verkoop.setCurrent(verkoop.getCancelledState());
    }

    @Override
    public void setToOnHold() {
        System.out.println("verkoop on hold gezet");
        verkoop.setCurrent(verkoop.getOnHoldState());
    }

    @Override
    public void sold() {
        System.out.println("verkoop compleet");
        verkoop.setCurrent(verkoop.getSoldState());
    }
}
