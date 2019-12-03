package model;

public abstract class Observer
{
    private Winkel subject;

    public Observer(final Winkel subject) {
        this.subject = subject;
    }

    public abstract void update(final Object p0);
}