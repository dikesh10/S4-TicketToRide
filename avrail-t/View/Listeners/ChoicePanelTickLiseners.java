package View.Listeners;

import java.awt.event.MouseEvent;
import Controller.Controller;
import Model.model.GameBoard;

import java.awt.event.MouseAdapter;

public class ChoicePanelTickLiseners extends MouseAdapter {
    private Controller controller;

    public ChoicePanelTickLiseners(Controller controller) {
        this.controller = controller;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameBoard gameBoard = controller.getGameBoard();

        if (gameBoard.getCurrentPlayer().getGameState() >= 2) {

            gameBoard.getCurrentPlayer().setGameState(4);
            gameBoard.notifyObservers();

            gameBoard.getDestinationMaps().ChargePanelDestinations();
            gameBoard.changePlayer();

            if (gameBoard.getCurrentPlayer() == gameBoard.getPlayers().get(0)) {
                gameBoard.changePlayersState(5);
                gameBoard.notifyObservers();

            }

            gameBoard.notifyObservers();

        }
    }

}
