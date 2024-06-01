package View.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Controller.Controller;
import Model.model.GameBoard;
import View.View;
import View.menu.continents.ContinentPage;
import View.music.MusicBackGround;
import java.util.Random;

public class modeChoice extends JFrame {
    private JLabel lab;
    private ImageIcon icon;
    private Controller controller;
    private GameBoard gameBoard;

    public modeChoice(Controller controller, GameBoard gameBoard) {
        this.controller = controller;
        this.gameBoard = gameBoard;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AccountPlayerPage.resetAccountSave();
            }
        });
        setName("Veux-tu choisir ta map ou laisse-tu le jeu choisir pour toi?");
        JButton mus = new JButton();
        icon = new ImageIcon("Resources/music/music.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        mus.setIcon(new ImageIcon(newImg));
        mus.setBounds(900, 30, 100, 100);
        mus.addActionListener(e -> {
            new MusicBackGround();
        });
        ImageIcon background = new ImageIcon("Resources/backgroundMaps/BackGroundSelectedMaps.jpeg");
        Image backgroundResized = background.getImage().getScaledInstance(1024, 1000, Image.SCALE_SMOOTH);
        background = new ImageIcon(backgroundResized);
        lab = new JLabel(background);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 900);
        lab.setBounds(200, 300, 1000, 20);

        JLabel labelOption1 = new JLabel("CHOOSE YOUR MAP");
        labelOption1.setBounds(250, 150, 250, 250);

        JButton option1 = new JButton();
        icon = new ImageIcon("Resources/check/a.png");
        Image whichWayImg = icon.getImage();
        Image newWhichWayImg = whichWayImg.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
        option1.setIcon(new ImageIcon(newWhichWayImg));
        option1.setBounds(200, 300, 300, 400);
        option1.addActionListener(e -> {
            setVisible(false);
            new ContinentPage(controller, gameBoard);
        });

        JLabel labelOption2 = new JLabel("RANDOM");
        labelOption2.setBounds(710, 150, 250, 250);

        JButton option2 = new JButton();
        icon = new ImageIcon("Resources/check/interrogationPoint.jpeg");
        Image interrImg = icon.getImage();
        Image newInterrImg = interrImg.getScaledInstance(400, 500, Image.SCALE_SMOOTH);
        option2.setIcon(new ImageIcon(newInterrImg));
        option2.setBounds(600, 300, 300, 400);
        option2.addActionListener(e -> {
            setVisible(false);
            gameBoard.randomMode(controller);
            new View(gameBoard, controller);
        });

        mus.setBorderPainted(false);
        mus.setContentAreaFilled(false);
        mus.setFocusPainted(false);
        mus.setOpaque(false);
        lab.add(mus);

        Font boldFont = new Font("Courier New", Font.BOLD, 20);
        labelOption1.setFont(boldFont);
        labelOption2.setFont(boldFont);
        labelOption1.setForeground(Color.WHITE);
        labelOption2.setForeground(Color.WHITE);
        lab.add(option1);
        lab.add(option2);
        lab.add(labelOption1);
        lab.add(labelOption2);
        lab.setBounds(200, 300, 1000, 20);
        setContentPane(lab);
        getContentPane().setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        setResizable(false);
        pack();
        setSize(1024, 1000);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
