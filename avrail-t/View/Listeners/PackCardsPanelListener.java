package View.Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Controller.Controller;
import Model.model.GameBoard;

public class PackCardsPanelListener extends MouseAdapter {

    private Controller controller;
    private int index;

    public PackCardsPanelListener(int index, Controller c) {
        this.controller = c;
        this.index = index;

    }

    public void mouseClicked(MouseEvent e) {

        GameBoard gb = controller.getGameBoard();
        int PlayerGameState = gb.getCurrentPlayer().getGameState();

        if (index == 1001) {
            controller.randomCardGamePlayer();
            return;
        }

        if (index == 6 && gb.getplayerActionPoints() == 0) {
            /* On ne fait pas appel a update */
            gb.chargePanelDestinations();
            /* On fait appel a packcardpanel */
            seeDestaintionSeeWagons();
            return;
        }

        if (PlayerGameState == 7) {
            removeDestinationCard();
            return;
        }
        if ((PlayerGameState == 6 && index != 6)) {
            if (gb.getCardPack().getCards()[index] == null)
                return;
            int cardColor = gb.getCardPack().getCards()[index].getColor();

            if (cardColor % 10 == 5 && gb.getplayerActionPoints() == 0) {
                /* On fait appel a packcardpanel dans gameboard */
                clickLocomotive();
                addImagesCard();
                return;
            }

            if (gb.getplayerActionPoints() >= 0 && !(cardColor % 10 == 5)) {
                int color = gb.getCardPack().getCards()[index].getColor();
                color = vividToNormal(color);
                clickWagon(color);
                addImagesCard();
                return;
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (index == 1001)
            return;

        int PlayerGameState = controller.getGameBoard().getCurrentPlayer().getGameState();
        if (PlayerGameState == 7)
            return;
        if (index == 6)
            return;
        GameBoard gb = controller.getGameBoard();
        if (gb.getCardPack().getCards()[index] == null)
            return;
        controller.makeVivid(index);

    }

    public void mouseExited(MouseEvent e) {
        if (index == 1001)
            return;

        int PlayerGameState = controller.getGameBoard().getCurrentPlayer().getGameState();
        if (PlayerGameState == 7)
            return;
        if (index == 6)
            return;
        GameBoard gb = controller.getGameBoard();
        if (controller.getGameBoard().getCardPack().getCards()[index] == null)
            return;
        controller.makeNormalImage(index);
    }

    public int vividToNormal(int i) {
        if (i == 123) {
            return 0;
        } else {
            return i / 10;
        }

    }

    public void seeDestaintionSeeWagons() {
        GameBoard gb = controller.getGameBoard();
        int PlayerGameState = gb.getCurrentPlayer().getGameState();
        if (PlayerGameState == 6) {

            controller.changeCurrentPlayerState(7);
            controller.getGameBoard().updatePackCardPanel();
        } else if (PlayerGameState == 7) {
            controller.changeCurrentPlayerState(6);
            controller.getGameBoard().updatePackCardPanel();
        }
    }

    public void removeDestinationCard() {
        if (index == 1 || index == 2 || index == 3) {
            controller.removeDestinationI(index);
            controller.changePlayer();
            controller.updatePackCardPanel();
            ;
            return;
        }

    }

    public void clickLocomotive() {
        controller.addCardCurrentPlayer(5);
        controller.getGameBoard().setplayerActionPoints(controller.getGameBoard().getplayerActionPoints() + 2);
        /* On fait appel a packcardpanel */
        controller.RemoveCard(index);
        return;
    }

    public void clickWagon(int color) {
        controller.addCardCurrentPlayer(color);
        controller.getGameBoard().setplayerActionPoints(controller.getGameBoard().getplayerActionPoints() + 1);
        controller.RemoveCard(index);
        return;

    }

    public void addImagesCard() {
        if (controller.getGameBoard().getplayerActionPoints() >= 2) {
            controller.changePlayer();
            controller.addImagesCard();

        }

    }

}