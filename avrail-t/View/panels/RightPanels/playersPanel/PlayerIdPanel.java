package View.panels.RightPanels.playersPanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Model.*;
import Model.model.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

public class PlayerIdPanel extends JPanel {
    private JLabel score;
    private JLabel wagon;
    private JLabel nbCard;
    private JLabel destination;
    private Image backgroundImage;

    public PlayerIdPanel(double width, Player player) {
        backgroundImage = new ImageIcon("Resources/profiles/" + player.getId() + ".png").getImage();
        setPreferredSize(new Dimension((int) width - 30, 100));
        setLayout(null);

        // JLabel pour le pseudo du joueur
        JLabel pseudo = new JLabel(player.getName());
        pseudo.setForeground(Color.WHITE);
        pseudo.setBounds(49, 4, 160, 12);
        pseudo.setFont(new Font(pseudo.getFont().getName(), Font.BOLD, pseudo.getFont().getSize()));
        add(pseudo);

        // JLabel pour le score du joueur
        score = new JLabel(player.getScore() + "");
        score.setForeground(Color.WHITE);
        score.setBounds(50, 23, 32, 12);
        score.setFont(new Font(score.getFont().getName(), Font.BOLD, score.getFont().getSize()));
        add(score);

        // JLabel pour le nombre de destinations du joueur
        destination = new JLabel(player.getDestinationCards().size() + "");
        destination.setForeground(Color.WHITE);
        destination.setFont(new Font(destination.getFont().getName(), Font.BOLD, destination.getFont().getSize()));
        destination.setBounds(178, 65, 37, 12);
        add(destination);

        // JLabel pour le nombre de wagon du joueur
        wagon = new JLabel(player.getNbWagons() + "");
        wagon.setForeground(Color.BLACK);
        wagon.setFont(new Font(wagon.getFont().getName(), Font.BOLD, wagon.getFont().getSize()));
        wagon.setBounds(21, 73, 37, 12);
        add(wagon);

        // JLabel pour le nombre de carte du joueur
        nbCard = new JLabel(player.getNbCards() + "");
        nbCard.setForeground(Color.WHITE);
        nbCard.setFont(new Font(nbCard.getFont().getName(), Font.BOLD, nbCard.getFont().getSize()));
        nbCard.setBounds(96, 65, 37, 12);
        add(nbCard);
        repaint();
    }

    public void setScore(int score) {
        this.score.setText(score + "");
    }

    public void setDestination(int dest) {
        this.destination.setText(dest + "");
    }

    public void setNbWagon(int nbw) {
        this.wagon.setText(nbw + "");

    }

    public String getNbWagon() {
        return wagon.getText();
    }

    public void setNbCards(int nbc) {
        this.nbCard.setText(nbc + "");

    }

    // Méthode pour compter le nombre de cartes dans le paquet du joueur
    public int compterNbCards(HashMap<Integer, Integer> map) {
        int cp = 0;
        for (int key : map.keySet()) {
            cp += map.get(key);
        }
        return cp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
