package View.menu.continents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
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
 * Choix du pays lié au choix de l'Asie
 * 
 * @param args
 */
public class AsiaCountryPage extends JFrame {
    GameBoard gameBoard;
    Controller controller;

    public AsiaCountryPage(Controller controller, GameBoard gameBoard) {
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

        setTitle("Choix du pays d'Asie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon background = new ImageIcon("Resources/backgroundMaps/BackGroundSelectedMaps.jpeg");
        Image backgroundResized = background.getImage().getScaledInstance(1024, 1000, Image.SCALE_SMOOTH);
        background = new ImageIcon(backgroundResized);
        JLabel label = new JLabel(background);
        label.setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelInde = new JLabel("Inde");
        labelInde.setBounds(200, 150, 250, 250);
        ImageIcon icon = new ImageIcon("Resources/backgroundMaps/villes-asie/mapInde.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton Inde = new JButton(new ImageIcon(newImg));
        Inde.setBounds(100, 300, 250, 250);

        JLabel labelChine = new JLabel("Chine");
        labelChine.setBounds(500, 150, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/villes-asie/mapChine.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton Chine = new JButton(new ImageIcon(newImg));
        Chine.setBounds(400, 300, 250, 250);

        JLabel labelArabieSaoudite = new JLabel("Arabie Saoudite");
        labelArabieSaoudite.setBounds(740, 150, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/villes-asie/mapArabieSaoudite.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton ArabieSaoudite = new JButton(new ImageIcon(newImg));
        ArabieSaoudite.setBounds(700, 300, 250, 250);

        Border border = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE);
        Inde.setBorder(border);
        Chine.setBorder(border);
        ArabieSaoudite.setBorder(border);

        Font boldFont = new Font("Courier New", Font.BOLD, 20);
        labelInde.setFont(boldFont);
        labelChine.setFont(boldFont);
        labelArabieSaoudite.setFont(boldFont);
        labelInde.setForeground(Color.WHITE);
        labelChine.setForeground(Color.WHITE);
        labelArabieSaoudite.setForeground(Color.WHITE);

        label.add(labelInde);
        label.add(labelChine);
        label.add(labelArabieSaoudite);
        label.add(Inde);
        label.add(Chine);
        label.add(ArabieSaoudite);
        mus.setBorderPainted(false);
        mus.setContentAreaFilled(false);
        mus.setFocusPainted(false);
        mus.setOpaque(false);
        add(mus);
        label.add(mus);
        setContentPane(label);
        getContentPane().setLayout(new BorderLayout());
        Inde.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("inde");
            controller.getGameBoard().notifyObservers();

            new View(gameBoard, controller);
        });

        Chine.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("chine");
            controller.getGameBoard().notifyObservers();

            new View(gameBoard, controller);
        });

        ArabieSaoudite.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("arabie saoudite");
            controller.getGameBoard().notifyObservers();
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
