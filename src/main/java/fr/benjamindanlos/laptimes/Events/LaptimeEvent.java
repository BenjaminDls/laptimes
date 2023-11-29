package fr.benjamindanlos.laptimes.Events;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public abstract class LaptimeEvent {
    protected String driver;
    protected String track;
    protected String car;
    protected String game;
    protected int laptime;

    public abstract String toString();

    public enum Type{
        SessionBest,
        PersonnalBest
    }

    public abstract Type type();
}
