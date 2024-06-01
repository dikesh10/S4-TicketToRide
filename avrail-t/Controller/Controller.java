package Controller;

import java.awt.BorderLayout;

import Model.model.DestinationMaps;
import Model.model.GameBoard;
import Model.model.InfoTime;
import Model.model.Player;
import Model.model.Route;
import View.View;
import View.Listeners.PackCardsPanelListener;
import View.menu.ScoreFrame;
import View.panels.RightPanels.playersPanel.PlayersPanel;

public class Controller {
    private GameBoard gameBoard;
    private PlayersPanel playersPanel;
    private View view;

    public Controller(GameBoard gb) {
        this.gameBoard = gb;
    }

    public void addPlayer(Player p) {
        gameBoard.addPlayer(p);
    }

    public void addPlayersPanel(PlayersPanel pp) {
        playersPanel = pp;
    }

    public void addCard(Player player, int c) {
        player.addCard(c);
    }

    public void addCardCurrentPlayer(int color) {
        gameBoard.addCardCurrentPlayer(color);

    }

    public void removeCard(Player player, int c) {
        player.removeCard(c);
    }

    public void RemoveCard(int i) {
        gameBoard.RemoveCard(i);

        // if (PackCardsPanelListener.countImageRemoved == 0) {
        // gameBoard.RemoveCard(i);
        // }
        // if (PackCardsPanelListener.countImageRemoved > 0) {
        // if (gameBoard.getCardPack().getCards()[i].getColor()%10==5) {
        // return;
        // }

        // }

    }

    public void makeVivid(int i) {

        gameBoard.makeVivid(i);

    }

    public void makeNormalImage(int i) {

        gameBoard.makeNormalImage(i);
    }

    public void addImagesCard() {
        gameBoard.addImagesCard();
    }

    public void removeDestinationLabel(int index) {
        gameBoard.removeDestinationLabel(index);
    }

    public void removeDestinationI(int index) {

        gameBoard.removeDestinationI(index);
    }

    public void changeCurrentPlayerState(int changstate) {
        gameBoard.changeCurrentPlayerState(changstate);
    }

    public void notifyObservers() {
        gameBoard.notifyObservers();
    }

    public InfoTime infoTime() {
        return gameBoard.infoTime();
    }

    public void randomCardGamePlayer() {
        gameBoard.randomCardGamePlayer();
    }

    public void changePlayer() {
        gameBoard.changePlayer();
    }

    public void updatePackCardPanel() {
        gameBoard.updatePackCardPanel();
    }

    public void addRouteToPlayerGraph(Route r, Player p) {
        p.getGraph().addRoute(r);
    }

    public void addRoute(Route r, Player p) {
        p.getDestinationMapsGraph().addRoute(r);
    }

    // public boolean hasPath(City source , City destination, Player p) {
    // return p.getDestinationMapsGraph().hasPath(source.getName(),
    // destination.getName());
    // }

    /**************************************************************************
     * Getters et Setters
     **************************************************************************/
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Player getCurrentPlayer() {
        return gameBoard.getCurrentPlayer();
    }

    public InfoTime getInfo() {
        return gameBoard.getInfo();
    }

    public void setPays(String st) {
        gameBoard.setPays(st);
    }

    public void setContinent(String st) {
        gameBoard.setContinent(st);
    }

    public void setIsOccupied(Route r, boolean b) {
        r.setIsOccupied(b);
    }

    public void setCurrentPlayer(Player newCurrentPlayer) {
        gameBoard.setCurrentPlayer(newCurrentPlayer);
    }

    public void setChosenCard(int color) {
        gameBoard.setChosenCard(color);
    }

    public void changePlayersState(int num) {
        gameBoard.changeCurrentPlayerState(num);
    }

    public void setScore(Player player, int c) {
        player.setScore(c);
        if (playersPanel != null)
            playersPanel.update();
    }

    public void setNbWagon(Player p, int c) {
        p.setNbWagons(c);
        if (playersPanel != null) {
            playersPanel.update();
        }
        if (p.getNbWagons() <= 2) {
            ScoreFrame scoreFrame = new ScoreFrame(this, gameBoard);
        }
    }

    public void setView(View v) {
        this.view = v;
    }

}
