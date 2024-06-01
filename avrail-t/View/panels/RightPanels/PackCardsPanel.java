package View.panels.RightPanels;

import javax.swing.JPanel;
import javax.swing.border.Border;
import Controller.Controller;
import Interfaces.Observable;
import Interfaces.Observer;
import Model.model.GameBoard;
import Model.model.DestinationCard;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import View.Listeners.PackCardsPanelListener;
import View.Listeners.JlabelDestinationsListeners;
import java.awt.*;

public class PackCardsPanel extends JPanel implements Observer {

    private int width;
    private Observable observable;
    private Panelimages[] panels = new Panelimages[7];
    private static final String wagonspaths = "Resources/card/card";
    private static final String DestinationsPaths = "Resources/destination-temp/";
    private static final String DeckDestinationPath = "Resources/card/Des1";// .png"; // Des1 pour vivde
    private static final String DeckwagonsPaths = "Resources/card/cardBack1"; // cardBack1 pour vivid
    private Controller control; // pour listner
    private JLabel[] label = new JLabel[3];
    private int initialiserListenerAdded = 0;

    PackCardsPanel(int width, Observable obs, Controller c) {
        this.control = c;
        this.width = width;
        setObservable(obs);
        setBackground(Color.red);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setPreferredSize(new Dimension(width, (int) getPreferredSize().getHeight()));
        setVisible(true);
        obs.addObserver(this);

    }

    public void addDestinationLabels() {
        GameBoard gb = (GameBoard) observable;
        for (int i = 0; i < 3; i++) {
            int card = gb.getDestinationMaps().getDestinationsCards()[i].getId();
            String paths = DestinationsPaths + card + ".png";
            label[i] = new JLabel();

            setImages(paths, label[i], this.width);
            label[i].addMouseListener(new JlabelDestinationsListeners(control, i));
            add(label[i]);
        }
    }

    public static void setImages(String imagePath, JLabel label, int panelWidth) {
        if (imagePath == null) {
            System.out.println("Not possible to set images ");
            return;
        }
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();

        int desiredWidth = 150;

    // Calculate the scale based on the panel width and desired image width
    double scale = (double) panelWidth / desiredWidth;
    int scaledWidth = (int) (img.getWidth(null) * scale);
    int scaledHeight = (int) (img.getHeight(null) * scale);

        Image scaledImg = img.getScaledInstance(scaledWidth - 20, scaledHeight, Image.SCALE_SMOOTH);

        label.setIcon(new ImageIcon(scaledImg));
        label.setPreferredSize(new Dimension(scaledWidth - 10, scaledHeight));
    }

    public void addCardDeck() {
        String paths = DeckwagonsPaths + ".png";
        panels[0] = new Panelimages(paths);
        panels[0].setPreferredSize(new Dimension(width, 100));
        panels[0].setBackground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        panels[0].setBorder(border);
        add(panels[0]);
        panels[0].addMouseListener(new PackCardsPanelListener((1001), control));
    }

    public void addDestinationDeck() {
        String paths = DeckDestinationPath + ".png";
        panels[panels.length - 1] = new Panelimages(paths);
        panels[panels.length - 1].setPreferredSize(new Dimension(width, 100));
        panels[panels.length - 1].setBackground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        panels[panels.length - 1].setBorder(border);
        add(panels[panels.length - 1]);

    }

  
    public void initialisePanels() {
        addCardDeck();
        for (int i = 1; i < panels.length - 1; i++) {
            panels[i] = new Panelimages();
            panels[i].setPreferredSize(new Dimension(width, 100));
            panels[i].setBackground(Color.WHITE);
            Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
            panels[i].setBorder(border);
            add(panels[i]);
        }
        addDestinationDeck();
    }

    /*************************************************************************
     * Observer Methods
     *************************************************************************/

    @Override
    public void update() {
        GameBoard gb = (GameBoard) observable;
        int state = gb.getCurrentPlayer().getGameState();

        switch (state) {
            case 0:
                addDestinationLabels();
                break;
            case 1:
            case 2:
            case 3:
                JlabelDestinationsRemove();
                break;
            case 4: // REMOVE ALL LA PURGE
                removeAllLabels();
                break;
            case 5:
                /*
                 * affichag des wagons declancer par un des joueurs et ça doit être appeler que
                 * une seul fois
                 */
                gb.updateChoicePanel();
                addIntialiserListener();
                changeImageFromPanelWagons();
                gb.changePlayersState(6);
                break;
            case 6:
                changeImageFromPanelWagons();
                break;
            case 7:
                changeImageFromPanelDestination();
                break;
            default:
                break;
        }
    }

    public void addIntialiserListener() {
        if (initialiserListenerAdded == 1)
            return;
        initialisePanels();
        addListeners();
        initialiserListenerAdded = 1;

    }

    public void changeImageFromPanelWagons() {
        GameBoard gb = (GameBoard) observable;
        for (int i = 1; i < panels.length - 1; i++) {
            if (gb.getCardPack().getCards()[i - 1] == null) {
                panels[i].setImages(null);
                panels[i].setBackground(Color.WHITE);
                revalidate();
                repaint();

            } else {
                int color = gb.getCardPack().getCards()[i - 1].getColor();
                String paths = wagonspaths + color + ".png";
                panels[i].setImages(paths);
                Color coleur = getColor(color);
                panels[i].setBackground(coleur);
                revalidate();
                repaint();
            }
        }

    }

    public Color getColor(int num) {
        if (num == 123) {
            return Color.BLUE;
        }
        int colorIndex = num % 10;
        switch (colorIndex) {
            case 0:
                return Color.BLUE;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.ORANGE;
            case 3:
                return Color.PINK;
            case 4:
                return Color.BLACK;
            case 5:
                return new Color(255, 20, 147);
            case 6:
                return Color.RED;
            case 7:
                return Color.WHITE;
            case 8:
                return Color.YELLOW;
            default:
                return Color.GRAY;
        }
    }

    public void changeImageFromPanelDestination() {
        GameBoard gb = (GameBoard) observable;
        DestinationCard[] destinationCards = gb.getDestinationMaps().getDestinationsCards();

        for (int i = 2; i < panels.length - 2; i++) {
            DestinationCard card = destinationCards[i - 2];
            if (card == null) {
                panels[i].setImages(null);
            } else {
                int index = card.getId();
                String path = DestinationsPaths + index + ".png";
                panels[i].setImages(path);
            }
        }

        RemoveUnWanted();
    }

    public void RemoveUnWanted() {
        for (int i = 0; i < panels.length; i++) {
            panels[i].setBackground(Color.WHITE);
            if (i == 1 || i == 5) {
                panels[i].setImages(null);
                revalidate();
                repaint();

            }
        }
    }

    public void removeAllLabels() {
        for (int i = 0; i < label.length; i++) {
            if (label[i] != null) {
                remove(label[i]);
                revalidate();
                repaint();
                label[i] = null;
            }
        }
    }

    public void JlabelDestinationsRemove() {

        GameBoard gb = (GameBoard) observable;
        for (int i = 0; i < label.length; i++) {
            if (gb.getDestinationMaps().getDestinationsCards()[i] == null) {

                label[i].setIcon(null);
                revalidate();
                repaint();



            };
           
        }
    }

    public void addListeners() {
        for (int i = 1; i < panels.length - 1; i++) {
            panels[i].addMouseListener(new PackCardsPanelListener((i - 1), control));
        }
        panels[panels.length - 1].addMouseListener(new PackCardsPanelListener(panels.length - 1, control));
    }

    @Override
    public void setObservable(Observable obs) {
        observable = obs;

    }

}
