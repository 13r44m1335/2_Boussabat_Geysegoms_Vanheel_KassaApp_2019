package model;

public class SoldState implements VerkoopState{

    private Verkoop verkoop;

    public SoldState(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    @Override
    public void setToActive() {
        System.out.println("al ge verkocht");
    }

    @Override
    public void cancelVerkoop() {
        System.out.println("al ge verkocht");
    }

    @Override
    public void setToOnHold() {
        System.out.println("al ge verkocht");

    }

    @Override
    public void sold() {
        System.out.println("al ge verkocht");
    }
}
