package Model.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import Model.cities.City;
import Model.cities.CityType;
import java.awt.Point;

public class Graph {
    private ArrayList<Route> map = new ArrayList<>();



    public Graph getCopy() {
        Graph g = new Graph();
        g.map = (ArrayList<Route>) map.clone();
        return g;
    }


    public void populateRoutes(HashMap<Integer, ArrayList<Integer>> cityConnections) {
        System.out.println(cityConnections.keySet());
        for (Integer sourceCityType : cityConnections.keySet()) {
            System.out.println("ma valeur city type: " + sourceCityType);
            City sourceCity = new City(CityType.values()[sourceCityType]);
            for (Integer destinationCityType : cityConnections.get(sourceCityType)) {
                City destinationCity = new City(CityType.values()[destinationCityType]);
                Route r = new Route(sourceCity, destinationCity);
                r.setColor(selectRandomColor());
                map.add(r);
            }
        }
    }



 public int selectRandomColor() {
        Random random = new Random();
        int randomNumber;
        do {

            randomNumber = random.nextInt(9);
        } while (randomNumber == 5);
        return randomNumber;

    }

    private HashMap<Integer, ArrayList<Integer>> generatePlanarConnectedGraph() {
        CityType[] cityTypes = CityType.values();
        int numberOfCities = cityTypes.length;
        HashMap<Integer, ArrayList<Integer>> connections = new HashMap<>();

        /* Initialize connections in the map */
        for (int i = 0; i < numberOfCities; i++) {
            connections.put(i, new ArrayList<>());
        }

        /* Connect each city to its closest neighbors to ensure planarity */
        for (int i = 0; i < numberOfCities; i++) {
            Point currentCityPoint = cityTypes[i].getCoordinates();
            Integer nearestCity = null;
            double minDistance = Double.MAX_VALUE;

            for (int j = 0; j < numberOfCities; j++) {
                if (i != j) {
                    Point testCityPoint = cityTypes[j].getCoordinates();
                    double distance = currentCityPoint.distance(testCityPoint);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestCity = j;
                    }
                }
            }

            /* Connect to the nearest city */
            if (nearestCity != null && !connections.get(i).contains(nearestCity)) {
                connections.get(i).add(nearestCity);
                connections.get(nearestCity).add(i);
            }
        }

        return connections;
    }

    /*********************************************************************
     * Getters and setters
     *********************************************************************/

    public ArrayList<Route> getMap() {
        return map;
    }

    public Graph() {
        map = new ArrayList<>();
    }


    //destaintion deux sens
    public void addRoute(Route route) {
        map.add(route);        
        map.add(new Route(route.getDestinationCity(), route.getSourceCity()));
        Route r= map.get(map.size()-1);
        int num=r.getSourceCity().getCityNumber();
        int num1=r.getDestinationCity().getCityNumber();
        String name =CityType.values()[num].name();
        String name1 =CityType.values()[num1].name();

        System.out.println(name +"---------LES VILLES MISES DENDANS-----------" +name1);

     //  System.out.println(hasPath(r.getSourceCity().getName(), r.getDestinationCity().getName()) +" est ceque il y bien des routes ou pas ");
    }

    public boolean hasPath(String  source, String destination) {
       // Set<City> visited = new HashSet<>();
        Set<String> visited = new HashSet<>();
        return depthFirstSearch(source, destination, visited);
    }

    private boolean depthFirstSearch(String source, String destination, Set<String> visited) {
        if (source.equals(destination)) {
            return true;
        }
        visited.add(source);
        for (Route route : map) {
            if (route.getSourceCity().getName().equals(source)) {
                String neighbor = route.getDestinationCity().getName();
                if (!visited.contains(neighbor)) {
                    if (depthFirstSearch(neighbor, destination, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    

    public static void main(String[] args) {

        Graph graph = new Graph();

        City paris = new City(CityType.PARIS);
        City MONTPELLIER = new City(CityType.MONTPELLIER);
        City calais = new City(CityType.CALAIS);
        City DIJON = new City(CityType.DIJON);
        City BECHAR = new City(CityType.BECHAR);
        City ALGER = new City(CityType.ALGER);
        City OKHATSK = new City(CityType.OKHATSK);
        City Kiev = new City(CityType.KIEV);

        /// -------------------------------------//

        City NORMANDIE = new City(CityType.NORMANDIE);
        City PERPINION = new City(CityType.PERPINION);

        Route route1 = new Route(paris, MONTPELLIER);
        Route route2 = new Route(MONTPELLIER, calais);
        Route route3 = new Route(calais, DIJON);
        Route route4 = new Route(MONTPELLIER, BECHAR);
        Route route5 = new Route(BECHAR, ALGER);
        Route route6 = new Route(DIJON, OKHATSK);
        Route route7 = new Route(Kiev, OKHATSK);
        Route route8 = new Route(NORMANDIE, PERPINION);
        Route route9 = new Route(OKHATSK, NORMANDIE);

        graph.addRoute(route1);
        graph.addRoute(route2);
        graph.addRoute(route3);
        graph.addRoute(route4);
        graph.addRoute(route5);
        graph.addRoute(route6);
        graph.addRoute(route7);
        graph.addRoute(route8);
        graph.addRoute(route9);

        System.out.println("paris <-----> ALGER " + graph.hasPath(paris.getName(), ALGER.getName()));
        System.out.println("ALGER <-----> paris " + graph.hasPath(ALGER.getName(), paris.getName()));
        System.out.println("DIJON <-----> ALGER " + graph.hasPath(DIJON.getName(), ALGER.getName()));
        System.out.println("ALGER <-----> DIJON " + graph.hasPath(ALGER.getName(), DIJON.getName()));

        // ----------------------------------------------------------------//
        System.out.println("ALGER <-----> PARIS-->>>>>> " + graph.hasPath("ALGER", paris.getName()));
        System.out.println("OKHATSK <-----> ALGER " + graph.hasPath(OKHATSK.getName(), ALGER.getName()));
        System.out.println("ALGER <-----> ALGER " + graph.hasPath(paris.getName(), ALGER.getName()));
        System.out.println("ALGER <-----> ALGER " + graph.hasPath(ALGER.getName(), paris.getName()));

        // ----------------------------------------------------------------//

        System.out.println("paris <-----> kiev " + graph.hasPath(paris.getName(), Kiev.getName()));
        System.out.println("kiev <-----> paris " + graph.hasPath(Kiev.getName(), paris.getName()));

        System.out.println("kiev <-----> Alger " + graph.hasPath(Kiev.getName(), ALGER.getName()));
        System.out.println("Alger <-----> kiev " + graph.hasPath(ALGER.getName(), Kiev.getName()));

        // --------------------------------------------------------------------///

        System.out.println("NORMANDIE <-----> PERPINION " + graph.hasPath(PERPINION.getName(), NORMANDIE.getName()));
        System.out.println("PERPINION <-----> NORMANDIE " + graph.hasPath(NORMANDIE.getName(), PERPINION.getName()));
        System.out.println("paris <-----> NORMANDIE " + graph.hasPath(paris.getName(), NORMANDIE.getName()));
        System.out.println("Kiev <-----> NORMANDIE " + graph.hasPath(Kiev.getName(), NORMANDIE.getName()));
        System.out.println("NORMANDIE <-----> paris " + graph.hasPath(NORMANDIE.getName(), paris.getName()));
        System.out.println("KNORMANDIE <-----> Kiev " + graph.hasPath(NORMANDIE.getName(), Kiev.getName()));

    }

}
