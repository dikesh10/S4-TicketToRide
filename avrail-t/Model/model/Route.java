package Model.model;

import java.awt.geom.Point2D;

import Model.cities.City;

public class Route {
    private City source;
    private City destination;
    private double length;
    private double angle;
    private int color = 0;
    private boolean isOccupied;

    public Route(City source, City destination) {
        this.source = source;
        this.destination = destination;
        length = calculateDistance(source, destination);
        angle = calculateAngle(source, destination);
        isOccupied = false;
    }



    public double calculateDistance(City source, City destination) {
        double x1 = source.getCoordinates().getX() + 0.5;
        double y1 = source.getCoordinates().getY() + 0.5;
        double x2 = destination.getCoordinates().getX() + 0.5;
        double y2 = destination.getCoordinates().getY() + 0.5;
        Point2D point1 = new Point2D.Double(x1, y1);
        Point2D point2 = new Point2D.Double(x2, y2);
        return point1.distance(point2);
    }

    public double calculateAngle(City Source, City destination) {
        double x1 = source.getCoordinates().getX() + 0.5;
        double y1 = source.getCoordinates().getY() + 0.5;
        double x2 = destination.getCoordinates().getX() + 0.5;
        double y2 = destination.getCoordinates().getY() + 0.5;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        return Math.toDegrees(angle);
    }

    /*********************************************************************
     * Getters, setters, Add/Remove Methods
     *********************************************************************/

    public City getSourceCity() {
        return source;
    }

    public City getDestinationCity() {
        return destination;
    }

    public double getLength() {
        return length;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int n) {
        color = n;
    }

    public double getAngle() {
        return angle;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean b) {
        isOccupied = b;
    }
}
