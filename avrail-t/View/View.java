package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import Controller.Controller;
import Model.model.GameBoard;
import Model.model.Player;
import View.menu.AccountPlayerPage;
import View.music.MusicBackGround;
import View.panels.LeftPanels.LeftPanel;
import View.panels.RightPanels.RightPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;

public class View extends JFrame {
    // PlayerPage pp;
    GameBoard gameBoard;
    Controller controller;

    public View(GameBoard gameBoard, Controller controller) {
        this.gameBoard = gameBoard;
        this.controller = controller;
        JButton mus = new JButton();
        ImageIcon icon = new ImageIcon("Resources/music/music.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        mus.setIcon(new ImageIcon(newImg));
        mus.setBounds(1200, 30, 100, 100);
        mus.addActionListener(e -> {
            new MusicBackGround();
        });
        mus.setBorderPainted(false);
        mus.setContentAreaFilled(false);
        mus.setFocusPainted(false);
        mus.setOpaque(false);
        add(mus);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AccountPlayerPage.resetAccountSave();
            }
        });

        setPreferredSize(new Dimension(1300, 800));
        RightPanel rightPanel = new RightPanel(400, gameBoard, controller);
        LeftPanel leftPanel = new LeftPanel(900, gameBoard, controller, controller.getGameBoard().getPays());
        this.setLayout(new BorderLayout());
        add(rightPanel, BorderLayout.EAST);
        add(leftPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // System.out.println("il va changer cette structre plus tard ");
        // ça doit être changer car il faut setter le jouer avant meme que joueur soi
        // présent depuis accountpage(ou on ecrit les info sur joueurs)
        // gameBoard.setCurrentPlayer(p1);

        gameBoard.notifyObservers();
        setVisible(true);
        pack();
        controller.setView(this);
    }
}
