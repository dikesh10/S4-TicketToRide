package Model.model;

import Model.cities.City;
import Model.cities.CityType;
import View.panels.RightPanels.imageCreation.CreateTicketImages;

import java.util.*;

public class DestinationMaps {
    private ArrayList<DestinationCard> listofDestinationsCards = new ArrayList<DestinationCard>();

    private DestinationCard destinationsCards[] = new DestinationCard[3];
    private int[][] Destinations;

    public DestinationMaps() {

        addCardToArray();
    }

    public DestinationMaps(int[][] Destinations, String country) {
        this.Destinations = Destinations;
        String pays = country;
        System.out.println(pays + "----------------LE PAYS CHOSED---------------------");
        createDesination(pays);
    }

    public void createDesination(String pays) {
        int[] points = paysPoints(pays);
        int idimage = 1;
        for (int i = 0; i < Destinations.length; i++) {
            int ville1 = Destinations[i][0];
            int ville2 = Destinations[i][1];
            int pointspays = points[i];

            City city1 = new City(CityType.values()[ville1]);
            City city2 = new City(CityType.values()[ville2]);

           // System.out.println(CityType.values()[ville1].name() + "<----->" + CityType.values()[ville2].name());
            DestinationCard destinationCard = new DestinationCard(city1, city2, pointspays, idimage);
            destinationCard.setId(idimage);
            listofDestinationsCards.add(destinationCard);

            String ville1name = Model.cities.CityType.values()[ville1].name();
            String ville2name = Model.cities.CityType.values()[ville2].name();
            CreateTicketImages ticketImages = new CreateTicketImages(ville1name, ville2name, pointspays,
                    destinationCard.getId());
            idimage++;

        }
        Collections.shuffle(listofDestinationsCards);

        addCardToArray();

    }

    private static int[] paysPoints(String pays) {
        switch (pays) {
            case "algerie":
                return Model.cities.DestinationPoints.algerie;
            case "soudan":
                return Model.cities.DestinationPoints.soudan;
            case "mali":
                return Model.cities.DestinationPoints.mali;
            case "bresil":
                return Model.cities.DestinationPoints.bresil;
            case "inde":
                return Model.cities.DestinationPoints.inde;
            case "chine":
                return Model.cities.DestinationPoints.chine;
            case "arabie saoudite":
                return Model.cities.DestinationPoints.arabie_saoudite;
            case "mexique":
                return Model.cities.DestinationPoints.mexique;
            case "france":
                return Model.cities.DestinationPoints.france;
            case "canada":
                return Model.cities.DestinationPoints.canada;
            case "us":
                return Model.cities.DestinationPoints.us;
            case "russie":
                return Model.cities.DestinationPoints.russie;
            case "finlande":
                return Model.cities.DestinationPoints.finlande;
            case "australie":
                return Model.cities.DestinationPoints.australie;
            default:
                return Model.cities.DestinationPoints.france;

        }
    }

    public void addCardToArray() {

        for (int i = 0; i < 3; i++) {
            DestinationCard carte = listofDestinationsCards.get(listofDestinationsCards.size() - 1);
            destinationsCards[i] = (carte);
            listofDestinationsCards.remove(listofDestinationsCards.size() - 1);
        }

    }

    public void removeDestinationLabel(int index) {
        this.destinationsCards[index] = null;
    }

    public void removeDestinationI(int index) {
        index = index - 1;
        this.destinationsCards[index] = null;

    }

    public void ChargePanelDestinations() {
        for (int i = 0; i < destinationsCards.length; i++) {
            if (destinationsCards[i] == null) {
                if (!listofDestinationsCards.isEmpty()) {
                    DestinationCard num = listofDestinationsCards.get(listofDestinationsCards.size() - 1);
                    destinationsCards[i] = num;
                    listofDestinationsCards.remove(listofDestinationsCards.size() - 1);
                } else {
                    System.err.println("No more Destination Cards available.");

                }
            }
        }
    }

    /*********************************************************************
     * Getters, setters, add/Remove Methods
     *********************************************************************/
    public DestinationCard[] getDestinationsCards() {
        return destinationsCards;
    }

    public void setDestinationsCards(DestinationCard[] destinations) {
        this.destinationsCards = destinations;
    }

    public ArrayList<DestinationCard> getListofDestinationsCards() {
        return listofDestinationsCards;
    }

    public void setListofDestinationsCards(ArrayList<DestinationCard> listofDestinationsCards) {
        this.listofDestinationsCards = listofDestinationsCards;
    }
}
