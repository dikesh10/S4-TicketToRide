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
public class SouthAmericaCountryPage extends JFrame {
    GameBoard gameBoard;
    Controller controller;

    public SouthAmericaCountryPage(Controller controller, GameBoard gameBoard) {
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
        setTitle("Choix du pays d'Amerique du Sud");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon background = new ImageIcon("Resources/backgroundMaps/BackGroundSelectedMaps.jpeg");
        Image backgroundResized = background.getImage().getScaledInstance(1024, 1000, Image.SCALE_SMOOTH);
        background = new ImageIcon(backgroundResized);
        JLabel label = new JLabel(background);
        label.setLayout(new FlowLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border border = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE);

        JLabel labelBresil = new JLabel("Bresil");
        labelBresil.setBounds(480, 150, 250, 250);
        ImageIcon icon = new ImageIcon("Resources/backgroundMaps/villes-Samerique/mapBresil.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton Bresil = new JButton(new ImageIcon(newImg));
        Bresil.setBounds(400, 300, 250, 250);

        Bresil.setBorder(border);

        Font boldFont = new Font("Courier New", Font.BOLD, 20);
        labelBresil.setFont(boldFont);
        labelBresil.setForeground(Color.WHITE);

        label.add(labelBresil);
        label.add(Bresil);
        mus.setBorderPainted(false);
        mus.setContentAreaFilled(false);
        mus.setFocusPainted(false);
        mus.setOpaque(false);
        label.add(mus);
        setContentPane(label);
        getContentPane().setLayout(new BorderLayout());
        Bresil.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("bresil");
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
