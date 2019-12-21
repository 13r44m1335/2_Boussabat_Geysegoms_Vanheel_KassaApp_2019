package model;

public class OnHoldState extends VerkoopState {

    private Verkoop verkoop;

    public OnHoldState(Verkoop verkoop) {
        super(verkoop);
        this.verkoop = verkoop;
    }

    @Override
    public void setToActive() {
        System.out.println("Terug op ctief gezet");
        verkoop.setCurrent(verkoop.getActiveState());
    }

}
