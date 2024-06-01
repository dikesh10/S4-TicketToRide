package View.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import Controller.Controller;
import Model.model.GameBoard;
import View.music.MusicBackGround;

public class PlayerPage extends JFrame {
    MusicBackGround musique;
    JLabel lab;
    JPanel panel;
    JComboBox<String> box;
    JButton button;
    private static int nbPlayer = 0;
    private Controller controller;
    private GameBoard gameBoard;

    /**
     * Label déroulant et choix du nombre de joueur
     * 
     * @param args
     */
    public PlayerPage(Controller controller, GameBoard gameBoard) {
        this.controller = controller;
        this.gameBoard = gameBoard;
        JButton mus = new JButton();
        ImageIcon icon = new ImageIcon("Resources/music/music.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        mus.setIcon(new ImageIcon(newImg));
        mus.setBounds(900, 30, 100, 100);
        mus.addActionListener(e -> {
            if (musique == null)
                musique = new MusicBackGround();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AccountPlayerPage.resetAccountSave();
            }
        });

        String[] options = { "2", "3", "4", "5" };

        lab = new JLabel("Combien de joueur êtes-vous?", JLabel.CENTER);
        setContentPane(new JLabel(new ImageIcon("Resources/rail.jpeg")));
        lab.setBounds(60, 300, 1000, 20);
        Font style = new Font("Courier New", Font.BOLD | Font.ITALIC, 20);
        lab.setForeground(Color.WHITE);
        lab.setFont(style);

        box = new JComboBox<>(options);
        box.setBounds(460, 300, 200, 100);

        button = new JButton("Appliquer");
        button.addActionListener(e -> {
            setVisible(false);
            Object nbPlayerselected = box.getItemAt(box.getSelectedIndex());
            nbPlayer = Integer.parseInt(String.valueOf(nbPlayerselected));
            new AccountPlayerPage(nbPlayer, controller, gameBoard);
            lab.setText("Tu as choisi " + box.getItemAt(box.getSelectedIndex()) + " joueur(s)");
        });

        button.setBounds(512, 370, 90, 20);
        add(lab);
        mus.setBorderPainted(false);
        mus.setContentAreaFilled(false);
        mus.setFocusPainted(false);
        mus.setOpaque(false);
        add(mus);
        add(button);
        add(box);
        setLayout(null);
        setSize(1024, 1000);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}