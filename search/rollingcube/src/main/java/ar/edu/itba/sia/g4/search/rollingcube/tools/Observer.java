package ar.edu.itba.sia.g4.search.rollingcube.tools;

public interface Observer<T extends Observable<?>>{

    public void Update(T data);
}