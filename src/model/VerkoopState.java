package model;

public interface VerkoopState {

    void setToActive();

    void cancelVerkoop();

    void setToOnHold();

    void sold();
}
