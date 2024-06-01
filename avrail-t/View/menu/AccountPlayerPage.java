package View.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.Controller;
import Model.model.Player;
import Model.model.GameBoard;
import View.music.MusicBackGround;

public class AccountPlayerPage extends JFrame {
    private JLabel lab;
    private int currentPlayer = 1;
    private int totalPlayers;
    private static final String path = "View/menu/.accountSave.txt";
    private String account = "";
    private JButton val;
    private JTextField textFieldName = new JTextField("nom", 16);
    private JTextField textFieldFirstName = new JTextField("prénom", 16);
    String name;
    String firstName;
    String Account;
    int currentAccount = 1;
    boolean fullNameExist;
    private Controller controller;
    private GameBoard gameBoard;

    public AccountPlayerPage(int nbPlayer, Controller controller, GameBoard gameBoard) {
        setName("Création de compte pour le joueur " + currentPlayer);
        totalPlayers = nbPlayer;
        this.controller = controller;
        this.gameBoard = gameBoard;
        showCurrentPage();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resetAccountSave();
            }
        });
    }

    private void showCurrentPage() {
        JButton mus = new JButton();
        ImageIcon icon = new ImageIcon("Resources/music/music.png");
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

        val = new JButton("Enregistrer");
        textFieldName.setHorizontalAlignment(JTextField.CENTER);

        textFieldName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldName.getText().equals("nom")) {
                    textFieldName.setText("");
                }
            }


            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldName.getText().isEmpty()) {
                    textFieldName.setText("nom");
                }
            }
        });

        textFieldName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldName.getText().equals("nom")) {
                    textFieldName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldName.getText().isEmpty()) {
                    textFieldName.setText("nom");
                }
            }
        });

        textFieldFirstName.setHorizontalAlignment(JTextField.CENTER);
        textFieldFirstName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldFirstName.getText().equals("prénom")) {
                    textFieldFirstName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldFirstName.getText().isEmpty()) {
                    textFieldFirstName.setText("prénom");
                }
            }
        });
        textFieldFirstName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldFirstName.getText().equals("prénom")) {
                    textFieldFirstName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldFirstName.getText().isEmpty()) {
                    textFieldFirstName.setText("prénom");
                }
            }
        });

        textFieldName.setForeground(Color.GRAY);
        textFieldName.setBounds(400, 250, 300, 20);

        textFieldFirstName.setForeground(Color.GRAY);
        textFieldFirstName.setBounds(400, 200, 300, 20);
        JLabel labText = new JLabel("JOUEUR N°" + currentAccount, SwingConstants.CENTER);
        labText.setForeground(Color.WHITE);
        labText.setBounds(50, 150, 1000, 20);
        lab.add(labText);
        lab.add(textFieldName);
        lab.add(textFieldFirstName);
        lab.setBounds(200, 300, 1000, 20);

        if (currentPlayer < totalPlayers) {
            val.setBounds(510, 370, 90, 20);
            currentAccount++;
            val.addActionListener(e -> {
                setVisible(false);
                currentPlayer++;
                textFieldFirstName.setForeground(Color.BLACK);
                textFieldName.setForeground(Color.BLACK);
                textFieldFirstName.setText(textFieldFirstName.getText());
                textFieldName.setText(textFieldName.getText());
                String tmp = textFieldName.getText() + " " + textFieldFirstName.getText();
                for (int i = 0; i < controller.getGameBoard().getPlayers().size(); i++) {
                    if (tmp.equals(controller.getGameBoard().getPlayers().get(i).getName())) {
                        fullNameExist = true;
                    }
                }
                if (!fullNameExist) {
                    controller.addPlayer(new Player(textFieldName.getText() + " " + textFieldFirstName.getText()));
                } else {
                    controller.addPlayer(new Player("No Name"));
                    fullNameExist = false;
                }

                writeAccountSave(textFieldName.getText(), textFieldFirstName.getText());
                showCurrentPage();
            });
        } else {
            val.setBounds(510, 370, 90, 20);
            val.addActionListener(e -> {
                setVisible(false);
                textFieldFirstName.setText(textFieldFirstName.getText());
                textFieldName.setText(textFieldName.getText());
                String tmp = textFieldName.getText() + " " + textFieldFirstName.getText();
                for (int i = 0; i < controller.getGameBoard().getPlayers().size(); i++) {
                    if (tmp.equals(controller.getGameBoard().getPlayers().get(i).getName())) {
                        fullNameExist = true;
                    }
                }
                if (!fullNameExist) {
                    controller.addPlayer(new Player(textFieldName.getText() + " " + textFieldFirstName.getText()));
                } else {
                    controller.addPlayer(new Player("No Name"));
                    fullNameExist = false;
                }
                writeAccountSave(textFieldName.getText(), textFieldFirstName.getText());
                new modeChoice(controller, gameBoard);
            });
        }
        mus.setBorderPainted(false);
        mus.setBorderPainted(false);
        mus.setContentAreaFilled(false);
        mus.setFocusPainted(false);
        mus.setOpaque(false);
        lab.add(mus);
        lab.add(val);
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

    public static void resetAccountSave() {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readAccountSave() {
        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = bufferedReader.readLine();
            account += line;
            reader.close();
            return account;

        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public void writeAccountSave(String n, String f) {
        if (account == null) {
            account = "";
        }
        account = n + "  " + f;
        if (n.equals("nom") || f.equals("prénom")) {
            account += "\n";
        } else {
            textFieldName.setText("nom");
            textFieldFirstName.setText("prénom");
            try {
                FileWriter fileWriter = new FileWriter(path, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(account);
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
