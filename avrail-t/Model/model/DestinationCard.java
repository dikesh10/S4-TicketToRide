package Model.model;

import Model.cities.City;
import Model.cities.CityType;

public class DestinationCard {

    private City ville1, ville2;
    private int points;
    private boolean finish;
    private int id;

    public DestinationCard(City v1, City v2, int point, int id) {
        ville1 = v1;
        ville2 = v2;
        this.points = point;
        this.finish = false;
        this.id = id;
    }

    public DestinationCard() {

    }

    public DestinationCard(int id) {
        this.id = id;
    }

    public DestinationCard(City v1, City v2) {
        ville1 = v1;
        ville2 = v2;
        this.finish = false;
    }

    public City getVille1() {
        return ville1;
    }

    public void setVille1(City ville1) {
        this.ville1 = ville1;
    }

    public City getVille2() {
        return ville2;
    }

    public void setVille2(City ville2) {
        this.ville2 = ville2;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {

        DestinationCard a = new DestinationCard();
        City one = new City(CityType.PARIS);
        City two = new City(CityType.LYON);
        int numeberOfpointsGained = 10;

    }

}