package Interfaces;

public interface Observable {
    public void addObserver(Observer obj);

    public void deleteObserver(Observer obj);

    public void notifyObservers();

}
