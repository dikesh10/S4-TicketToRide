package View.panels.LeftPanels.playerCardsPanel;

import javax.swing.*;

import Controller.Controller;

import java.awt.*;
import View.Listeners.CardPanelListener;

public class CardPanel extends JPanel {

    private Image backgroundImage;
    private int quantity, color;
    private CardPanelListener cardPanelListener;
    private JLabel label;
    private Controller controller;

    public CardPanel(int color, int height, int quantity, Controller controller) {
        this.quantity = quantity;
        this.color = color;
        this.controller = controller;
        /* Chargez l'image de fond depuis le fichier */
        backgroundImage = new ImageIcon("Resources/cardsTheo/card" + color + ".png").getImage();
        int imageWidth = backgroundImage.getWidth(null);
        setPreferredSize(new Dimension(imageWidth - 30, height - 10));

        label = new JLabel(Integer.toString(quantity));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("SansSerif", Font.BOLD, 25)); // Police et taille du texte
        add(label);

        cardPanelListener = new CardPanelListener(this);
        addMouseListener(cardPanelListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        if (cardPanelListener.isHighlighted()) {
            g.setColor(new Color(255, 255, 255, 100)); // Couleur de surbrillance semi-transparente (blanc avec opacité)
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if (quantity > 1) {
            label.setText(Integer.toString(quantity));
            this.setVisible(true);
            label.setVisible(true);
        } else if (quantity == 1) {
            this.setVisible(true);
            label.setVisible(false);
        } else {
            this.setVisible(false);
        }
    }

    /*****************************************************************************
     * Getters et setters
     ********************************************************************************/
    public int getColor() {
        return color;
    }

    public Controller getController() {
        return controller;
    }
}
