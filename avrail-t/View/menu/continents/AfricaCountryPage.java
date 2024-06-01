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
 * Choix du pays lié au choix de l'Afrique
 * 
 * @param args
 */
public class AfricaCountryPage extends JFrame {
    GameBoard gameBoard;
    Controller controller;

    public AfricaCountryPage(Controller controller, GameBoard gameBoard) {
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
        setTitle("Choix du pays d'Afrique");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon background = new ImageIcon("Resources/backgroundMaps/BackGroundSelectedMaps.jpeg");
        Image backgroundResized = background.getImage().getScaledInstance(1024, 1000, Image.SCALE_SMOOTH);
        background = new ImageIcon(backgroundResized);
        JLabel label = new JLabel(background);
        label.setLayout(new FlowLayout());
        JLabel labelAlgerie = new JLabel("Algerie");
        labelAlgerie.setBounds(180, 150, 250, 250);
        ImageIcon icon = new ImageIcon("Resources/backgroundMaps/villes-afrique/mapAlgerie.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton Algerie = new JButton(new ImageIcon(newImg));
        Algerie.setBounds(100, 300, 250, 250);

        JLabel labelSoudan = new JLabel("Soudan");
        labelSoudan.setBounds(480, 150, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/villes-afrique/mapSoudan.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton Soudan = new JButton(new ImageIcon(newImg));
        Soudan.setBounds(400, 300, 250, 250);

        JLabel labelMali = new JLabel("Mali");
        labelMali.setBounds(800, 150, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/villes-afrique/mapMali.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
        JButton Mali = new JButton(new ImageIcon(newImg));
        Mali.setBounds(700, 300, 250, 250);

        Border border = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE);
        Algerie.setBorder(border);
        Soudan.setBorder(border);
        Mali.setBorder(border);

        Font boldFont = new Font("Courier New", Font.BOLD, 20);
        labelAlgerie.setFont(boldFont);
        labelSoudan.setFont(boldFont);
        labelMali.setFont(boldFont);
        labelAlgerie.setForeground(Color.WHITE);
        labelSoudan.setForeground(Color.WHITE);
        labelMali.setForeground(Color.WHITE);

        label.add(labelAlgerie);
        label.add(labelSoudan);
        label.add(labelMali);
        label.add(Algerie);
        label.add(Soudan);
        label.add(Mali);
        mus.setBorderPainted(false);
        mus.setContentAreaFilled(false);
        mus.setFocusPainted(false);
        mus.setOpaque(false);
        label.add(mus);
        setContentPane(label);
        getContentPane().setLayout(new BorderLayout());
        Algerie.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("algerie");
            new View(gameBoard, controller);
        });

        Soudan.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("soudan");
            new View(gameBoard, controller);
        });

        Mali.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setPays("mali");
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
