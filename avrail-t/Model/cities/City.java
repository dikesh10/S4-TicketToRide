package Model.cities;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class City {
    private CityType cityType;
    private Point coordinates;
    private Integer distance = Integer.MAX_VALUE;
    Map adjacentCity = new HashMap<>();

    public City(CityType c) {
        cityType = c;
        coordinates = c.getCoordinates();
    }

    public void addDestination(City destination, int distance) {
        adjacentCity.put(destination, distance);
    }

    /*********************************************************************
     * Getters and setters
     *********************************************************************/
    public int getCityNumber() {
        return cityType.ordinal();
    }

    public Point getCoordinates() {

        return cityType.getCoordinates();
    }

    public void setCoordinates(int x, int y) {
        cityType.setCoordinates(x, y);
    }

    public void setDistance(int x) {
        distance = x;
    }

    public String getName() {
        return cityType.name();
    }

}
