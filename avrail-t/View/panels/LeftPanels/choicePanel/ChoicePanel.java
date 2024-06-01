package View.panels.LeftPanels.choicePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Controller.Controller;
import Interfaces.Observable;
import Interfaces.Observer;
import Interfaces.*;
import Model.model.GameBoard;
import Model.model.InfoTime;
import View.Listeners.ChoicePanelTickLiseners;

public class ChoicePanel extends JPanel implements Observer {
    private Observable observable;
    private Controller controller;
    private JLabel timer = new JLabel();
    private JLabel playerName = new JLabel();
    private JLabel totalSeconds = new JLabel();
    private boolean case5Executed = false;
    private JLabel Tick = new JLabel();

    public static final String Orangecheck = "Resources/check/1.png";
    public static final String Greencheck = "Resources/check/2.png";

    public ChoicePanel(int height, Observable obs, Controller controller) {
        this.observable = obs;
        this.controller = controller;
        setBackground(Color.PINK);
        setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), height));

        Tick = new JLabel();
        addCheckImageOnLabel(Orangecheck);
        Tick.addMouseListener(new ChoicePanelTickLiseners(controller));

        observable.addObserver(this);
        setLayout(new BorderLayout());
        add(Tick, BorderLayout.EAST);

        timer.setPreferredSize(new Dimension(200, 20));
        totalSeconds.setPreferredSize(new Dimension(200, 20));
        playerName.setPreferredSize(new Dimension(200, 20));
        Font font = new Font("Arial", Font.BOLD, 18);
        timer.setFont(font);
        playerName.setFont(font);
        GameBoard gb = (GameBoard) obs;
        gb.setCurrentPlayer(gb.getPlayers().get(0));
    }

    @Override
    public void update() {
        int state = controller.getCurrentPlayer().getGameState();
        switch (state) {
            case 0:
            instructionCaseZeroUpadte();
            case 1:
                addCheckImageOnLabel(Orangecheck);
                break;
            case 2:
            case 3:
            instrcutionCaseTroisUpdate();
                break;
            case 4:
                break;
            case 5:
            instructionCaseCinqUpdate();
                break;
            case 6:
            case 7:
                updatePanelInformationAndNames();
                revalidate();
                repaint();
                break;
            default:
                break;
        }

    }

    @Override
    public void setObservable(Observable obs) {

    }


    public void updatePanelInformationAndNames() {

        if (controller.getInfo() == null || controller.getInfo().getPlayer() == null
                || controller.getInfo().getPlayer().getName() == null) {
            return;
        }

        playerName.setText(controller.getInfo().getPlayer().getName() + "  "
                + findFacePlayer(controller.getInfo().getPlayer().getId()));
        Color fontColor = controller.getInfo().getSecondsLeft() <= 10 ? Color.RED : Color.BLACK;
        timer.setForeground(fontColor);
        timer.setText("Temps Limite: " + controller.getInfo().getSecondsLeft() + " s ");
        totalSeconds.setForeground(Color.BLUE);
        totalSeconds.setText("Temps total: " + controller.getInfo().getTotalsecond() + " s ");

        ShowInformationestinationsPlayer();
        revalidate();
        repaint();
    }

    public void ShowInformationestinationsPlayer() {
        removeAll();
        JPanel playerInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        playerInfoPanel.setBackground(Color.PINK);
        playerInfoPanel.add(playerName);
        playerInfoPanel.add(timer);
        playerInfoPanel.add(totalSeconds);
        JPanel namesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namesPanel.setBackground(Color.PINK);
        JPanel panel = new JPanel(new BorderLayout());

        for (int i = 0; i < controller.getCurrentPlayer().getDestinationCards().size(); i++) {
            // int city1Number =
            // gb.getDestinationMaps().getDestinationsCards()[i].getVille1().getCityNumber();
            int city1Num = controller.getCurrentPlayer().getDestinationCards().get(i).getVille1().getCityNumber();
            // int city2Number =
            // gb.getDestinationMaps().getDestinationsCards()[i].getVille2().getCityNumber();
            int city2Num = controller.getCurrentPlayer().getDestinationCards().get(i).getVille2().getCityNumber();
            int point = controller.getCurrentPlayer().getDestinationCards().get(i).getPoints();

            String city1name = Model.cities.CityType.values()[city1Num].name();
            String city2name = Model.cities.CityType.values()[city2Num].name();
            JLabel nameLabel = new JLabel(city1name + "<->" + city2name +"("+point+"),  ");
            namesPanel.add(nameLabel);
        }

        panel.add(playerInfoPanel, BorderLayout.NORTH);
        panel.add(namesPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
    }

    public String findFacePlayer(int id) {

        switch (id) {
            case 0:
                return "( ͡• ͜ʖ ͡•)";
            case 1:
                return "( ͠° ͟ʖ ͡°)";
            case 2:
                return "( ͡~ ͜ʖ ͡°)";
            case 3:
                return "( ͡o ͜ʖ ͡o)";
            case 4:
                return "( ͡ʘ ͜ʖ ͡ʘ)";
            default:
                return "( ͡°ʖ̯ ͡°)";
        }
    }

    public void setImage(ImageIcon image) {
        Tick.setIcon(image);
    }

    public void addCheckImageOnLabel(String path) {

        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        setImage(icon);
        Tick.setPreferredSize(new Dimension(40, 40));
    }

    public void instructionCaseCinqUpdate(){
        if (!case5Executed) {
            removeAll();
            case5Executed = true;
            revalidate();
            repaint();
            InfoTime infoTime = controller.infoTime();
            infoTime.addObserver(this);
            revalidate();
            controller.notifyObservers();
            controller.changeCurrentPlayerState(6);
            JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            timerPanel.setBackground(Color.PINK);
            timerPanel.add(timer);
            timerPanel.add(playerName);
            timerPanel.add(totalSeconds);
            add(timerPanel, BorderLayout.NORTH);
        }
    }



    public void instrcutionCaseTroisUpdate(){
        Font font = new Font("Arial", Font.BOLD, 16);

        remove(totalSeconds);
        totalSeconds.setFont(font);
        totalSeconds.setText("SATISFAIT? CLIC SUR VERT----->");
        totalSeconds.setForeground(Color.GREEN);
        revalidate();
        repaint();
        add(totalSeconds, BorderLayout.CENTER);
        addCheckImageOnLabel(Greencheck);

    }


    public void instructionCaseZeroUpadte(){
        remove(totalSeconds);
        revalidate();
        repaint();
        playerName.setText(controller.getCurrentPlayer().getName() + "  "
                + findFacePlayer(controller.getCurrentPlayer().getId()));
        add(playerName, BorderLayout.NORTH);
        Font font = new Font("Arial", Font.BOLD, 16);
        totalSeconds.setFont(font);
        totalSeconds.setText("Choisez les cartes destinations");
        totalSeconds.setForeground(Color.MAGENTA);
        revalidate();
        repaint();
        add(totalSeconds, BorderLayout.CENTER);
    }
  


}