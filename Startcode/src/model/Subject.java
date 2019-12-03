package model;

import model.Observer;
import model.SoortObserver;

public interface Subject {
    public void registerObserver(Observer var1, SoortObserver var2);

    public void removeObserver(Observer var1);

    public void notifyObservers(SoortObserver var1, Object var2);
}