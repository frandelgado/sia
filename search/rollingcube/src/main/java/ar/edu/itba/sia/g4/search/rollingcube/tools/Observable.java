package ar.edu.itba.sia.g4.search.rollingcube.tools;

public interface Observable<T extends Observer<?>> {
    public void addObserver(T observer);

    public void removeObserver(T observer);

    public void notifyObservers();
}