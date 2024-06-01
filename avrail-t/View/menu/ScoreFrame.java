package View.menu;

import Controller.Controller;

import Model.model.GameBoard;
import Model.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScoreFrame extends JFrame {
    JLabel lab;
    private JTable table;
    private Controller controller;
    private GameBoard gameBoard;

    private Image image;
    private final String path = "Resources/classement/winner1.jpg";
    private List<Player> players;
    private int tmpLonguestPath;

    Point playerNamePosition = new Point(70, 135);
    Point playerScorePosition = new Point(70, 175);
    private int horizontalSpacing = 200;

    public ScoreFrame(Controller controller, GameBoard gameBoard) {
        this.controller = controller;
        this.gameBoard = gameBoard;
        this.players = gameBoard.getPlayers();
        setSize(500, 500);
        setTitle("Classement des joueurs");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Charger l'image
        this.image = new ImageIcon(path).getImage();

        // Panneau pour l'image
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 400, 400, this);
                g.setColor(Color.RED); // Couleur du texte
                g.setFont(new Font("Arial", Font.BOLD, 20)); // Police du texte

                // players.sort((p1, p2) -> p2.getScore() - p1.getScore());
                int max = players.get(0).getScore();
                Player tmp = players.get(0);
                int[] scores = new int[players.size()];
                for (int i = 0; i < players.size(); i++) {
                    Player player = players.get(i);
                    int score = player.getScore();
                    for (int j = 0; j < player.getDestinationCards().size(); j++) {
                        if (player.hasPath(player.getDestinationCards().get(j).getVille1().getName(),
                                player.getDestinationCards().get(j).getVille2().getName())) {
                            score += player.getDestinationCards().get(j).getPoints() + player.longestPathAlgo();
                        } else {
                            score -= player.getDestinationCards().get(j).getPoints() + player.longestPathAlgo();
                        }
                    }
                    scores[i] = score;
                    tmpLonguestPath = player.longestPathAlgo();
                    if (tmpLonguestPath > max) {
                        max = tmpLonguestPath;
                        tmp = player;
                        System.out.println(tmp.getName()+ " a la distance la plus longue qui est: " + max);
                    }

                }
                scores[tmp.getId()] += max;
                for (int i = 0; i < players.size(); i++) {
                    Player player = players.get(i);

                    int x = playerNamePosition.x + horizontalSpacing * i;
                    g.drawString(player.getName(), x, playerNamePosition.y);
                    g.drawString("Score: " + scores[i], x, playerScorePosition.y);
                    if (i == 0) {

                        Image pin = new ImageIcon("Resources/classement/1.png").getImage();

                        g.drawImage(pin, x, 550, this);
                    }
                }
                tmp.setScore(tmp.getScore() + tmpLonguestPath);
            }
        };

        add(imagePanel, BorderLayout.CENTER);
        setAlwaysOnTop(true);
        setVisible(true);
    }

}
