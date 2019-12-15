package model;

public class Verkoop {

    private Winkelwagen winkelwagen;

    VerkoopState onHoldState;
    VerkoopState soldState;
    VerkoopState cancelledState;
    VerkoopState activeState;

    VerkoopState current;

    public Verkoop(Winkelwagen winkelwagen) {
        onHoldState = new OnHoldState(this);
        soldState = new SoldState(this);
        cancelledState = new CancelledState(this);
        activeState = new ActiveState(this);
        current = activeState;

        this.winkelwagen = winkelwagen;
    }

    public VerkoopState getOnHoldState() {
        return onHoldState;
    }

    public VerkoopState getSoldState() {
        return soldState;
    }

    public VerkoopState getCancelledState() {
        return cancelledState;
    }

    public VerkoopState getActiveState() {
        return activeState;
    }

    public void setCurrent(VerkoopState current) {
        this.current = current;
    }

    public void setToActive(){
        current.setToActive();
    }

    public void cancelVerkoop(){
        current.cancelVerkoop();
    }

    public void setToOnHold(){
        current.setToOnHold();
    }

    public void sold(){
        current.sold();
    }



}
