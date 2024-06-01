package View.menu.continents;

import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;

import Controller.Controller;
import Model.model.GameBoard;
import View.View;
import View.menu.AccountPlayerPage;
import View.music.MusicBackGround;

/**
 * Choix du pays lié au choix de l'Amerique
 * 
 * @param args
 */
public class EuropeCountryPage extends JFrame {
    GameBoard gameBoard;
    Controller controller;

    public EuropeCountryPage(Controller controller, GameBoard gameBoard) {
        this.controller = controller;
        this.gameBoard = gameBoard;
        JButton mus = new JButton();
        ImageIcon iconMus = new ImageIcon("Resources/music/music.png");
        Image img = iconMus.getImage();
        Image newImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        mus.setIcon(new ImageIcon(newImg));
        mus.setBounds(900, 30, 100, 100);
        mus.addActionListener(e -> {
            new MusicBackGround();
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AccountPlayerPage.resetAccountSave();
            }
        });
        setTitle("Choix du pays d'Europe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon background = new ImageIcon("Resources/backgroundMaps/BackGroundSelectedMaps.jpeg");
        Image backgroundResized = background.getImage().getScaledInstance(1024, 1000, Image.SCALE_SMOOTH);
        background = new ImageIcon(backgroundResized);
        JLabel label = new JLabel(background);
        label.setLayout(new FlowLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelFrance = new JLabel("France");
        labelFrance.setBounds(180, 150, 250, 250);
        ImageIcon icon = new ImageIcon("Resources/backgroundMaps/villes-europe/mapFrance.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton France = new JButton(new ImageIcon(newImg));
        France.setBounds(100, 300, 250, 250);

        JLabel labelRussie = new JLabel("Russie");
        labelRussie.setBounds(480, 150, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/villes-europe/mapRussie.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton Russie = new JButton(new ImageIcon(newImg));
        Russie.setBounds(400, 300, 250, 250);

        JLabel labelFinlande = new JLabel("Finlande");
        labelFinlande.setBounds(800, 150, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/villes-europe/mapFinlande.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton Finlande = new JButton(new ImageIcon(newImg));
        Finlande.setBounds(700, 300, 250, 250);

        Border border = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE);
        France.setBorder(border);
        Russie.setBorder(border);
        Finlande.setBorder(border);

        Font boldFont = new Font("Courier New", Font.BOLD, 20);
        labelFrance.setFont(boldFont);
        labelRussie.setFont(boldFont);
        labelFinlande.setFont(boldFont);
        labelFrance.setForeground(Color.WHITE);
        labelRussie.setForeground(Color.WHITE);
        labelFinlande.setForeground(Color.WHITE);

        label.add(labelFrance);
        label.add(labelRussie);
        label.add(labelFinlande);
        label.add(France);
        label.add(Russie);
        label.add(Finlande);
        mus.setBorderPainted(false);
        mus.setContentAreaFilled(false);
        mus.setFocusPainted(false);
        mus.setOpaque(false);
        label.add(mus);
        setContentPane(label);
        getContentPane().setLayout(new BorderLayout());
        France.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("france");
            new View(gameBoard, controller);
        });

        Russie.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("russie");
            new View(gameBoard, controller);
        });

        Finlande.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("finlande");
            new View(gameBoard, controller);
        });

        setLayout(null);
        pack();
        setSize(1024, 1000);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
