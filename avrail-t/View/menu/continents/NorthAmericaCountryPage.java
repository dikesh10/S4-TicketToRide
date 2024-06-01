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
public class NorthAmericaCountryPage extends JFrame {
    GameBoard gameBoard;
    Controller controller;

    public NorthAmericaCountryPage(Controller controller, GameBoard gameBoard) {
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
        setTitle("Choix du pays d'Amerique du Nord");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon background = new ImageIcon("Resources/backgroundMaps/BackGroundSelectedMaps.jpeg");
        Image backgroundResized = background.getImage().getScaledInstance(1024, 1000, Image.SCALE_SMOOTH);
        background = new ImageIcon(backgroundResized);
        JLabel label = new JLabel(background);
        label.setLayout(new FlowLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelMexique = new JLabel("Mexique");
        labelMexique.setBounds(180, 150, 250, 250);
        ImageIcon icon = new ImageIcon("Resources/backgroundMaps/villes-Namerique/mapMexique.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton Mexique = new JButton(new ImageIcon(newImg));
        Mexique.setBounds(100, 300, 250, 250);

        JLabel labelUS = new JLabel("US");
        labelUS.setBounds(480, 150, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/villes-Namerique/mapUS.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton US = new JButton(new ImageIcon(newImg));
        US.setBounds(400, 300, 250, 250);

        JLabel labelCanada = new JLabel("Canada");
        labelCanada.setBounds(800, 150, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/villes-Namerique/mapCanada.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton Canada = new JButton(new ImageIcon(newImg));
        Canada.setBounds(700, 300, 250, 250);

        Border border = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE);
        Mexique.setBorder(border);
        US.setBorder(border);
        Canada.setBorder(border);

        Font boldFont = new Font("Courier New", Font.BOLD, 20);
        labelMexique.setFont(boldFont);
        labelUS.setFont(boldFont);
        labelCanada.setFont(boldFont);
        labelMexique.setForeground(Color.WHITE);
        labelUS.setForeground(Color.WHITE);
        labelCanada.setForeground(Color.WHITE);

        label.add(labelMexique);
        label.add(labelUS);
        label.add(labelCanada);
        label.add(Mexique);
        label.add(US);
        label.add(Canada);
        mus.setBorderPainted(false);
        mus.setContentAreaFilled(false);
        mus.setFocusPainted(false);
        mus.setOpaque(false);
        label.add(mus);
        setContentPane(label);
        getContentPane().setLayout(new BorderLayout());
        Mexique.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("mexique");
            new View(gameBoard, controller);
        });

        US.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("us");
            new View(gameBoard, controller);
        });

        Canada.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("canada");
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
