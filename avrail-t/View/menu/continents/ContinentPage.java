package View.menu.continents;

import javax.swing.*;
import javax.swing.border.Border;

import Controller.Controller;
import Model.model.GameBoard;
import View.menu.AccountPlayerPage;
import View.music.MusicBackGround;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

/**
 * Choix du continent
 * 
 * @param args
 */
public class ContinentPage extends JFrame {
    private Controller controller;
    private GameBoard gameBoard;
    private ImageIcon icon;

    public ContinentPage(Controller controller, GameBoard gameBoard) {
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
        setTitle("Choix du continent");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon background = new ImageIcon("Resources/backgroundMaps/BackGroundSelectedMaps.jpeg");
        Image backgroundResized = background.getImage().getScaledInstance(1024, 1000, Image.SCALE_SMOOTH);
        background = new ImageIcon(backgroundResized);
        JLabel label = new JLabel(background);
        label.setLayout(new FlowLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelAfrica = new JLabel("AFRIQUE");
        labelAfrica.setBounds(180, 50, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/mapAfrique.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JButton Africa = new JButton(new ImageIcon(newImg));
        Africa.setBounds(100, 200, 250, 250);

        JLabel labelSouthAmerica = new JLabel("SOUTH-AMERICA");
        labelSouthAmerica.setBounds(440, 50, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/mapAmeriqueSud.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JButton SouthAmerica = new JButton(new ImageIcon(newImg));
        SouthAmerica.setBounds(400, 200, 250, 250);

        JLabel labelAsia = new JLabel("ASIE");
        labelAsia.setBounds(800, 50, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/mapAsie.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JButton Asia = new JButton(new ImageIcon(newImg));
        Asia.setBounds(700, 200, 250, 250);

        JLabel labelNorthAmerica = new JLabel("NORTH-AMERICA");
        labelNorthAmerica.setBounds(150, 350, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/mapAmeriqueNord.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JButton NorthAmerica = new JButton(new ImageIcon(newImg));
        NorthAmerica.setBounds(100, 500, 250, 250);

        JLabel labelEurope = new JLabel("EUROPE");
        labelEurope.setBounds(480, 350, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/mapEurope.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JButton Europe = new JButton(new ImageIcon(newImg));
        Europe.setBounds(400, 500, 250, 250);

        JLabel labelOceanie = new JLabel("OCEANIE");
        labelOceanie.setBounds(780, 350, 250, 250);
        icon = new ImageIcon("Resources/backgroundMaps/mapOceanie.jpeg");
        img = icon.getImage();
        newImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JButton Oceanie = new JButton(new ImageIcon(newImg));
        Oceanie.setBounds(700, 500, 250, 250);

        Border border = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE);
        Africa.setBorder(border);
        Asia.setBorder(border);
        Europe.setBorder(border);
        NorthAmerica.setBorder(border);
        Oceanie.setBorder(border);
        SouthAmerica.setBorder(border);

        Font boldFont = new Font("Courier New", Font.BOLD, 20);
        labelAfrica.setFont(boldFont);
        labelSouthAmerica.setFont(boldFont);
        labelAsia.setFont(boldFont);
        labelOceanie.setFont(boldFont);
        labelNorthAmerica.setFont(boldFont);
        labelEurope.setFont(boldFont);
        labelAfrica.setForeground(Color.WHITE);
        labelSouthAmerica.setForeground(Color.WHITE);
        labelAsia.setForeground(Color.WHITE);
        labelOceanie.setForeground(Color.WHITE);
        labelNorthAmerica.setForeground(Color.WHITE);
        labelEurope.setForeground(Color.WHITE);

        label.add(labelAfrica);
        label.add(labelSouthAmerica);
        label.add(labelAsia);
        label.add(labelNorthAmerica);
        label.add(labelEurope);
        label.add(labelOceanie);

        label.add(Africa);
        label.add(SouthAmerica);
        label.add(Asia);
        label.add(NorthAmerica);
        label.add(Europe);
        label.add(Oceanie);
        mus.setBorderPainted(false);
        mus.setContentAreaFilled(false);
        mus.setFocusPainted(false);
        mus.setOpaque(false);
        add(mus);
        label.add(mus);
        setContentPane(label);
        getContentPane().setLayout(new BorderLayout());
        Africa.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setContinent("afrique");
            new AfricaCountryPage(controller, gameBoard);
        });

        SouthAmerica.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setContinent("ameriquesud");
            new SouthAmericaCountryPage(controller, gameBoard);

        });
        /*
         * public class LabelListener extends MouseAdapter{
         * Label lab;
         * public LabelListener(Label l){
         * lab=l;
         * }
         * public static void mouseclicked(Event e){
         * if(nom.equals("Europe")||nom.equals("Asie")||nom.equals("Afrique")){
         * controller.getGameBoard().setContinent(nom);
         * }else{
         * controller.getGameBoard().setpays(nom);
         * }
         * }
         * //Penser a faire un switch
         * }
         * 
         * public class Label extends JLabel{
         * String nom;
         * Controller controller;
         * GameBoard gameboard;
         * Label(String n){
         * nom=n;
         * addMouseListener(new LabelListener())
         * }
         * 
         * }
         * 
         * //dans ContinentPage
         * Label l1=new Label("Europe");
         * Label l2=new Label("Asie");
         * Label l3=new Label("France");
         * Label l4=new Label("Chine");
         * Label l5=new Label("Italie");
         * add(l1);
         * add(l2);
         * 
         */

        Asia.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setContinent("asie");
            new AsiaCountryPage(controller, gameBoard);
        });

        NorthAmerica.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setContinent("ameriquenord");
            new NorthAmericaCountryPage(controller, gameBoard);
        });

        Europe.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setContinent("europe");
            new EuropeCountryPage(controller, gameBoard);
        });

        Oceanie.addActionListener(e -> {
            setVisible(false);
            controller.getGameBoard().setContinent("oceanie");
            new OceanieCountryPage(controller, gameBoard);
        });

        setLayout(null);
        setResizable(false);
        pack();
        setSize(1024, 1000);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
