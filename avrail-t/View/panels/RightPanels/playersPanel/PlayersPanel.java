package View.panels.RightPanels.playersPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import Interfaces.Observable;
import Interfaces.Observer;
import Model.model.GameBoard;

public class PlayersPanel extends JPanel implements Observer {

    private Observable observable;
    private PlayerIdPanel[] childrens;

    public void addPlayers(int n) {

        childrens = new PlayerIdPanel[n];

        for (int i = 0; i < n; i++) {
            PlayerIdPanel a = new PlayerIdPanel(getPreferredSize().getWidth(),
                    ((GameBoard) observable).getPlayers().get(i));

            childrens[i] = a;
            this.add(a);
            repaint();
        }
    }

    public PlayersPanel(int width, Observable obs) {
        setObservable(obs);
        ((GameBoard) observable).addObserver(this);
        Color myColor = new Color(204, 102, 0);
        setBackground(myColor);

        setPreferredSize(new Dimension(width, (int) getPreferredSize().getHeight()));
        setVisible(true);
        addPlayers(((GameBoard) observable).getPlayers().size());

    }

    /*************************************************************************
     * Observer Methods
     *************************************************************************/
    @Override
    public void update() {
        GameBoard gameBoard = (GameBoard) observable;

        for (int i = 0; i < childrens.length; i++) {
            childrens[i].setScore(gameBoard.getPlayers().get(i).getScore());
            childrens[i].setNbWagon(gameBoard.getPlayers().get(i).getNbWagons());
            childrens[i].setNbCards(gameBoard.getPlayers().get(i).getNbCards());
            childrens[i].setDestination(gameBoard.getPlayers().get(i).getDestinationCards().size());
            /*
             * if (childrens[i].getNbWagon().equals("2")) {
             * return;
             * }
             */
        }
    }

    @Override
    public void setObservable(Observable obs) {
        observable = obs;
    }
}
