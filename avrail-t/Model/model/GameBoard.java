package Model.model;

import java.util.ArrayList;
import java.util.Random;

import Controller.Controller;
import Interfaces.Observable;
import Interfaces.Observer;
import Model.parsers.MapsParser;

public class GameBoard implements Observable {

    private String continent;
    private String pays;
    private Graph graph;
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private DestinationMaps destinationMaps;
    private CardPack cardPack = new CardPack();
    private ArrayList<Observer> observers = new ArrayList<>();
    private InfoTime info;
    private int playerActionPoints = 0;

    public int getplayerActionPoints() {
        return playerActionPoints;
    }

    public void setplayerActionPoints(int playerActionPoints) {
        this.playerActionPoints = playerActionPoints;
    }

    public void removeCard(Card c) {
        cardPack.removeCardOfPack(c);
    }

    public void addCard(Card c) {
        cardPack.addCardToPack(c);
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void RemoveCard(int i) {
        if (cardPack.getCards()[i] == null) {
            return;
        }
        cardPack.RemoveCard(i);
        updatePackCardPanel();
    }

    public void makeVivid(int i) {
        if (cardPack.getCards()[i] == null) {
            return;
        }
        int color = cardPack.getCards()[i].getColor();
        if (this.playerActionPoints >= 1 && color % 10 == 5) {
            return;
        }
        cardPack.makeVivid(i);
        updatePackCardPanel();
    }

    public void makeNormalImage(int i) {
        if (cardPack.getCards()[i] == null) {
            return;
        }
        cardPack.makeNormalImage(i);
        updatePackCardPanel();
    }

    public void addImagesCard() {
        cardPack.addImagesCard();
        updatePackCardPanel();
        playerActionPoints = 0;

    }

    public void removeDestinationLabel(int index) {

        DestinationCard destinationCard = destinationMaps.getDestinationsCards()[index];
        currentPlayer.getDestinationCards().add(destinationCard);
        destinationMaps.removeDestinationLabel(index);
        notifyObservers();

    }

    public void addCardCurrentPlayer(int color) {
        currentPlayer.addCard(color);
        notifyObservers();
    }

    public void removeDestinationI(int index) {
        if (destinationMaps.getDestinationsCards()[index - 1] == null)
            return;
        DestinationCard destinationCard = destinationMaps.getDestinationsCards()[index - 1];
        currentPlayer.getDestinationCards().add(destinationCard);
        destinationMaps.removeDestinationI(index);
        updateChoicePanel();

    }

    public void chargePanelDestinations() {

        this.destinationMaps.ChargePanelDestinations();

    }

    /*************************************************************************
     * Getters and Setters
     *************************************************************************/
    public String getContinent() {
        return continent;
    }

    public void setChosenCard(int color) {
        currentPlayer.setChosenCard(color);
        notifyObservers();
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }

    public Graph getGraph() {
        return graph;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player p) {
        currentPlayer = p;
    }

    public String getPays() {
        return pays;
    }

    public void setContinent(String n) {
        continent = n;

    }

    public void setPays(String n) {
        pays = n;
        MapsParser mapsParser = new MapsParser(continent, pays);
        graph = new Graph();
        graph.populateRoutes(mapsParser.getMap());
        this.destinationMaps = new DestinationMaps(mapsParser.getCityNuM(), this.getPays());

    }

    public CardPack getCardPack() {
        return cardPack;
    }

    public void setCardPack(CardPack cardPack) {
        this.cardPack = cardPack;
    }

    public DestinationMaps getDestinationMaps() {
        return destinationMaps;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setDestinationMaps(DestinationMaps destinationMaps) {
        this.destinationMaps = destinationMaps;
    }

    public InfoTime getInfo() {
        return info;
    }

    public void setInfo(InfoTime info) {
        this.info = info;
    }

    /*****************************************************************************
     * Methods related to Observable/Observer interfaces
     *****************************************************************************/
    @Override
    public void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException("Null Observer");
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {

            o.update();

        }
    }

    public InfoTime infoTime() {
        this.info = new InfoTime(this);
        return this.info;

    }

    public void changePlayer() {

        changeCurrentPlayerState(6);
        int Nextplayerid = this.getCurrentPlayer().getId() + 1;
        int fulllength = this.getPlayers().size();
        int nextplayer = Nextplayerid % fulllength;
        this.setCurrentPlayer(this.getPlayers().get(nextplayer));
        updatePlayerCardsPanel();

    }

    public void changePlayersState(int state) {

        for (int i = 0; i < this.players.size(); i++) {
            players.get(i).setGameState(state);
        }

    }

    public void randomCardGamePlayer() {
        if (cardPack.getPackCards().size() == 0)
            return;
        int color = cardPack.getPackCards().get(cardPack.getPackCards().size() - 1).getColor();
        cardPack.getPackCards().remove(cardPack.getPackCards().size() - 1);
        if (color % 10 == 5 && getplayerActionPoints() == 0) {
            this.setplayerActionPoints(2);
            addCardCurrentPlayer(5);
            changePlayer();
            this.setplayerActionPoints(0);
            return;
        }

        this.setplayerActionPoints(getplayerActionPoints() + 1);
        addCardCurrentPlayer(color);
        if (this.getplayerActionPoints() >= 2) {
            changePlayer();
            this.setplayerActionPoints(0);

        }
        return;
    }

    public void changeCurrentPlayerState(int state) {
        currentPlayer.setGameState(state);
    }

    public void updateChoicePanel() {
        for (int i = 0; i < observers.size(); i++) {
            if (observers.get(i) instanceof View.panels.LeftPanels.choicePanel.ChoicePanel) {
                observers.get(i).update();
            }
        }
    }

    public void updatePackCardPanel() {

        for (int i = 0; i < observers.size(); i++) {
            if (observers.get(i) instanceof View.panels.RightPanels.PackCardsPanel) {
                observers.get(i).update();
            }
        }

    }

    public void updatePlayerCardsPanel() {
        for (int i = 0; i < observers.size(); i++) {
            if (observers.get(i) instanceof View.panels.LeftPanels.playerCardsPanel.PlayerCardsPanel) {
                observers.get(i).update();
            }
        }
    }

    public boolean randomMode(Controller controller) {
        Random random = new Random();
        int n = random.nextInt(16);
        System.out.println(n);
        switch (n) {
            case 0:
                controller.getGameBoard().setContinent("afrique");
                controller.getGameBoard().setPays("algerie");
                break;
            case 1:
                controller.getGameBoard().setContinent("afrique");
                controller.getGameBoard().setPays("soudan");
                break;
            case 2:
                controller.getGameBoard().setContinent("afrique");
                controller.getGameBoard().setPays("mali");
                break;
            case 3:
                controller.getGameBoard().setContinent("ameriquenord");
                controller.getGameBoard().setPays("mexique");
                break;
            case 4:
                controller.getGameBoard().setContinent("ameriquenord");
                controller.getGameBoard().setPays("us");
                break;
            case 5:
                controller.getGameBoard().setContinent("ameriquenord");
                controller.getGameBoard().setPays("canada");
                break;
            case 6:
                controller.getGameBoard().setContinent("ameriquesud");
                controller.getGameBoard().setPays("bresil");
                break;
            case 7:
                controller.getGameBoard().setContinent("ameriquesud");
                controller.getGameBoard().setPays("perou");
                break;
            case 8:
                controller.getGameBoard().setContinent("ameriquesud");
                controller.getGameBoard().setPays("argentine");
                break;
            case 9:
                controller.getGameBoard().setContinent("asie");
                controller.getGameBoard().setPays("inde");
                break;
            case 10:
                controller.getGameBoard().setContinent("asie");
                controller.getGameBoard().setPays("chine");
                break;
            case 11:
                controller.getGameBoard().setContinent("asie");
                controller.getGameBoard().setPays("arabie saoudite");
                break;
            case 12:
                controller.getGameBoard().setContinent("europe");
                controller.getGameBoard().setPays("france");
                break;
            case 13:
                controller.getGameBoard().setContinent("europe");
                controller.getGameBoard().setPays("russie");
                break;
            case 14:
                controller.getGameBoard().setContinent("europe");
                controller.getGameBoard().setPays("finlande");
                break;
            case 15:
                controller.getGameBoard().setContinent("oceanie");
                controller.getGameBoard().setPays("australie");
                break;
            case 16:
                controller.getGameBoard().setContinent("oceanie");
                controller.getGameBoard().setPays("nouvellezelande");
                break;
            default:
                controller.getGameBoard().setContinent("oceanie");
                controller.getGameBoard().setPays("fidji");

        }

        return true;
    }
}
