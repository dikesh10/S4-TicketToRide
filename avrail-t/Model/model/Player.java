package Model.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import Model.cities.City;
import View.menu.continents.SouthAmericaCountryPage;

public class Player {
    private int score;
    private int id = 0;
    private Color color;
    private int nbWagons = 45;
    private String name;
    // private ArrayList<DestinationMaps> destinationMaps = new
    // ArrayList<DestinationMaps>();
    private ArrayList<DestinationCard> DestinationCards = new ArrayList<DestinationCard>();

    private static int count = 0;
    private int chosenCard;
    private int GameState = 0;
    private Graph graph = new Graph();
    private Graph destinationMapsGraph = new Graph();

    private int longestPath;

    public Player(String name, int id) {
        this.name = name;
        this.graph = new Graph();
        this.longestPath = longestPathAlgo();
    }

    /*
     ** This Hashmap represents the amount of cards that the player has for
     * each*color*<li><code>Key: Int (Color of the card)</code>--><code> Value :Int
     * (Nb of cards of that color)</li></code>
     */

    private HashMap<Integer, Integer> packCards = new HashMap<Integer, Integer>();

    public Player(String n) {
        longestPath = 0;
        this.name = n;
        score = 0;
        nbWagons = 0;
        this.id = count;
        count++;
        this.nbWagons = 45;
        this.graph = new Graph();
        loadPlayerCard();
    }

    private void loadPlayerCard() {
        for (int i = 0; i <= 8; i++) {
            packCards.put(i, 0);
        }
        Random randomNumbers = new Random();
        for (int i = 0; i < 4; i++) {
            int colorcard = randomNumbers.nextInt(8) + 1;
            packCards.put(colorcard, packCards.get(colorcard) + 1);
        }
    }

    public int longestPathAlgo() {
        int res = 0;
        for (Route r : graph.getMap()) {
            Graph g = graph.getCopy();
            g.getMap().remove(r);
            int tmp1 = (int) r.getLength() - 1 + longestPathAlgoRec(g, r, r.getDestinationCity());
            int tmp2 = (int) r.getLength() - 1 + longestPathAlgoRec(g, r, r.getSourceCity());
            if (tmp1 > res) {
                res = tmp1;
            }
            if (tmp2 > res) {
                res = tmp2;
            }
        }
        System.out.println(
                ">>>>>> " + getName() + " a parcouru " + res
                        + " wagon(s).");
        return res;
    }

    public int longestPathAlgoRec(Graph g, Route r, City c) {
        if (g.getMap().isEmpty() || r == null) {
            return 0;
        }
        int res = 0;
        HashSet<Route> visited = new HashSet<Route>();
        for (Route route : g.getMap()) {
            int tmp1 = 0, tmp2 = 0;
            if (visited.size() == g.getMap().size()) {
                return res;
            }
            if (!visited.contains(route)) {
                Graph g2 = g.getCopy();
                g2.getMap().remove(route);
                if (route.getDestinationCity().getCityNumber() == c.getCityNumber())
                    tmp1 = (int) (route.getLength() - 1 + longestPathAlgoRec(g2, route, route.getSourceCity()));
                if (route.getSourceCity().getCityNumber() == c.getCityNumber())
                    tmp2 = (int) (route.getLength() - 1 + longestPathAlgoRec(g2, route, route.getDestinationCity()));
            }
            if (tmp1 > res) {
                res = tmp1;
            }
            if (tmp2 > res) {
                res = tmp2;
            }
            visited.add(route);
        }
        return res;
    }

    public void addCard(int color) {
        if (packCards.containsKey(color)) {
            int previousNb = packCards.get(color);
            packCards.put(color, previousNb + 1);

        } else {
            packCards.put(color, 1);

        }

    }

    public void removeCard(int color) {
        if (packCards.containsKey(color)) {
            int previousNb = packCards.get(color);
            packCards.put(color, previousNb - 1);
        }
    }

    public void removeCards(int color, int nb) {
        if (packCards.containsKey(color)) {
            int previousNb = packCards.get(color);
            /*
             * If player has enough cards, we remove them, if he doesnt, we remove the max
             */
            if (previousNb >= nb) {
                packCards.put(color, previousNb - nb);
            } else {
                packCards.put(color, 0);

            }
        }
    }

    /*********************************************************************
     * Getters, setters, Add/Remove Methods
     *********************************************************************/

    public int getScore() {
        return score;
    }

    public void setScore(int newScore) {
        score = newScore;
    }

    public int getId() {
        return id;
    }

    public int getLongestPath() {
        // return la valeur obtenue avec dikjstra
        return longestPath;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setId(int n) {
        id = n;
    }

    public int getNbWagons() {
        return nbWagons;
    }

    public String getName() {
        String namee = "";
        for (int i = name.length() - 1; i >= 0; i--) {
            if (name.charAt(i) == ' ') {
                namee = name.substring(i + 1);
                break;
            }
        }
        return namee;

    }

    public HashMap<Integer, Integer> getPackCard() {
        return packCards;
    }

    public int getNbCards(Integer color) {
        return packCards.get(color);
    }

    public int getNbCards() {
        int tmp = 0;
        for (int i : packCards.keySet()) {
            tmp += getNbCards(i);
        }
        return tmp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNbWagon(int nbWagons) {
        this.nbWagons = nbWagons;
    }

    public void setNbWagons(int nbWagons) {
        this.nbWagons = nbWagons;
    }

    public void setNbCards(int nbWagons) {
        this.nbWagons = nbWagons;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Player.count = count;
    }

    public int getGameState() {
        return GameState;
    }

    public void setGameState(int gameState) {
        GameState = gameState;
    }

    public HashMap<Integer, Integer> getPackCards() {
        return packCards;
    }

    public void setPackCards(HashMap<Integer, Integer> packCards) {
        this.packCards = packCards;
    }

    public ArrayList<DestinationCard> getDestinationCards() {
        return DestinationCards;
    }

    public void setDestinationCards(ArrayList<DestinationCard> destinationCards) {
        DestinationCards = destinationCards;
    }

    public Graph getDestinationMapsGraph() {
        return destinationMapsGraph;
    }

    public void setDestinationMapsGraph(Graph destinationMapsGraph) {
        this.destinationMapsGraph = destinationMapsGraph;
    }

    public void setChosenCard(int t) {
        chosenCard = t;
    }

    public boolean hasPath(String source, String destination) {
        System.out.println(getName() + "     ");
        return destinationMapsGraph.hasPath(source, destination);

    }

    /*
     * 
     * player.hasPath("","");
     */
    public static void main(String[] args) {

        // Player currentplayer = new Player();

        // City paris = new City(CityType.PARIS);
        // City MONTPELLIER = new City(CityType.MONTPELLIER);
        // City calais =new City(CityType.CALAIS);
        // City DIJON =new City(CityType.DIJON);
        // City BECHAR =new City(CityType.BECHAR);
        // City ALGER = new City(CityType.ALGER);
        // City OKHATSK =new City(CityType.OKHATSK);
        // City Kiev = new City(CityType.KIEV);

        // City NORMANDIE = new City(CityType.NORMANDIE);
        // City PERPINION = new City(CityType.PERPINION);

        // currentplayer.getDestinationCards().add(new DestinationCard(paris, ALGER,1));

        // currentplayer.getDestinationCards().add(new DestinationCard(ALGER, DIJON,1));

        // currentplayer.getDestinationCards().add(new DestinationCard(ALGER,
        // OKHATSK,1));

        // currentplayer.getDestinationCards().add(new DestinationCard(ALGER, paris,1));

        // currentplayer.getDestinationCards().add(new DestinationCard(paris, Kiev,1));

        // currentplayer.getDestinationCards().add(new DestinationCard(ALGER, Kiev,1));

        // currentplayer.getDestinationCards().add(new DestinationCard(PERPINION,
        // NORMANDIE,1));

        // currentplayer.getDestinationCards().add(new DestinationCard(paris,
        // NORMANDIE,1));

        // currentplayer.getDestinationCards().add(new DestinationCard(NORMANDIE,
        // Kiev,1));

        // currentplayer.graph.addRoute(new Route(paris, MONTPELLIER));
        // currentplayer.graph.addRoute (new Route(MONTPELLIER, calais));
        // currentplayer.graph.addRoute (new Route(calais, DIJON));

        // currentplayer.graph.addRoute (new Route(MONTPELLIER, BECHAR));
        // currentplayer.graph.addRoute (new Route(BECHAR, ALGER));
        // currentplayer.graph.addRoute (new Route(DIJON, OKHATSK));
        // currentplayer.graph.addRoute (new Route(Kiev,OKHATSK));
        // currentplayer.graph.addRoute (new Route(NORMANDIE,PERPINION));

        // System.out.println("paris <-----> ALGER " +
        // currentplayer.graph.hasPath(paris, ALGER));
        // System.out.println("ALGER <-----> paris " +
        // currentplayer.graph.hasPath(ALGER, paris));
        // System.out.println("DIJON <-----> ALGER " +
        // currentplayer.graph.hasPath(DIJON, ALGER));
        // System.out.println("ALGER <-----> DIJON " +
        // currentplayer.graph.hasPath(ALGER, DIJON));

        // //----------------------------------------------------------------//
        // System.out.println("ALGER <-----> OKHATSK " +
        // currentplayer.graph.hasPath(ALGER, OKHATSK));
        // System.out.println("OKHATSK <-----> ALGER " +
        // currentplayer.graph.hasPath(OKHATSK, ALGER));
        // System.out.println("ALGER <-----> ALGER " +
        // currentplayer.graph.hasPath(paris, ALGER));
        // System.out.println("ALGER <-----> ALGER " +
        // currentplayer.graph.hasPath(ALGER, paris));

        // //----------------------------------------------------------------//

        // System.out.println("paris <-----> kiev " + currentplayer.graph.hasPath(paris,
        // Kiev));
        // System.out.println("kiev <-----> paris " + currentplayer.graph.hasPath(Kiev,
        // paris));

        // System.out.println("kiev <-----> Alger " + currentplayer.graph.hasPath(Kiev,
        // ALGER));
        // System.out.println("Alger <-----> kiev " + currentplayer.graph.hasPath(ALGER,
        // Kiev));

        // //--------------------------------------------------------------------///

        // System.out.println("NORMANDIE <-----> PERPINION " +
        // currentplayer.graph.hasPath(PERPINION, NORMANDIE));
        // System.out.println("PERPINION <-----> NORMANDIE " +
        // currentplayer.graph.hasPath(NORMANDIE, PERPINION));

        // System.out.println("paris <-----> NORMANDIE " +
        // currentplayer.graph.hasPath(paris, NORMANDIE));
        // System.out.println("Kiev <-----> NORMANDIE " +
        // currentplayer.graph.hasPath(Kiev, NORMANDIE));

        // System.out.println("NORMANDIE <-----> paris " +
        // currentplayer.graph.hasPath(NORMANDIE, paris));
        // System.out.println("KNORMANDIE <-----> Kiev " +
        // currentplayer.graph.hasPath(NORMANDIE, Kiev));

    }

}
