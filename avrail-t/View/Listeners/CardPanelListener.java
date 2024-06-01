package View.Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Controller.Controller;
import View.panels.LeftPanels.playerCardsPanel.CardPanel;

public class CardPanelListener extends MouseAdapter {

    private Controller controller;
    private CardPanel panel;
    private boolean isHighlighted;

    public CardPanelListener(CardPanel panel) {
        this.panel = panel;
        this.isHighlighted = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /* Changez l'apparence du JPanel lorsque la souris entre */
        isHighlighted = true;
        panel.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /* Restaurez l'apparence du JPanel lorsque la souris sort */
        isHighlighted = false;
        panel.repaint();
    }

    ///////////////////////////////// A MODIFIER ///////////////////////////////////
    public void mouseClicked(MouseEvent e) {
        // Lorsqu'on clique sur l'Image
        panel.getController().setChosenCard(panel.getColor());
    }

    public void removeHighlighted() {
        this.isHighlighted = false;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }
}
