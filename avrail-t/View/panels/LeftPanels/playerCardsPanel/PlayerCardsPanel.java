package View.panels.LeftPanels.playerCardsPanel;

import javax.swing.JPanel;

import Controller.Controller;
import Interfaces.Observable;
import Interfaces.Observer;
import Model.model.GameBoard;
import Model.model.Player;
import View.Listeners.PlayerCardsPanelListener;

import java.awt.Color;
import java.awt.Dimension;

public class PlayerCardsPanel extends JPanel implements Observer {

    private Observable observable;
    private Controller controller;

    private CardPanel[] cardImages;

    public PlayerCardsPanel(int height, Observable o, Controller controller) {
        this.controller = controller;
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), height));
        // setPreferredSize(new Dimension(400, height));
        addMouseListener(new PlayerCardsPanelListener(controller));
        cardImages = new CardPanel[9];
        loadCardImages(height);
        for (int i = 0; i < cardImages.length; i++) {
            add(cardImages[i]);
        }
        setObservable(o);
        ((GameBoard) observable).addObserver(this);
    }

    private void loadCardImages(int height) {
        for (int i = 0; i < cardImages.length; i++) {
            cardImages[i] = new CardPanel(i, height, 0, controller);
        }
    }

    /*************************************************************************
     * Observer Methods
     *************************************************************************/

    @Override
    public void update() {
        GameBoard gameBoard = ((GameBoard) observable);
        Player currentPlayer = gameBoard.getCurrentPlayer();
        if (currentPlayer != null && currentPlayer.getPackCard() != null) {
            for (int i = 0; i <= cardImages.length; i++) {
                if (currentPlayer.getPackCard().size() > i && currentPlayer.getPackCard().get(i) != null) {
                    cardImages[i].setQuantity(currentPlayer.getPackCard().get(i).intValue());
                }
            }
        }
    }

    @Override
    public void setObservable(Observable o) {
        observable = o;
    }

}
