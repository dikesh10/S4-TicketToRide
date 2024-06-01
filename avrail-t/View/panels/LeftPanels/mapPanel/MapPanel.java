package View.panels.LeftPanels.mapPanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Controller.Controller;
import Interfaces.Observable;
import Interfaces.Observer;
import Model.cities.City;
import Model.cities.CityType;
import Model.model.GameBoard;
import Model.model.Player;
import Model.model.Route;
import View.Listeners.MapPanelListener;

public class MapPanel extends JPanel implements Observer {
    private Observable observable;
    private Controller controller;
    private int nbVerticalLines = 30; // ca signifie 29 cases
    private int nbHorizontalLines = 20; // ca signifie 19 cases
    private int wagonWidth = 30;

    public MapPanel(Observable observable, Controller controller, String m) {
        this.controller = controller;
        setLayout(null);
        setVisible(true);
        addMouseListener(new MapPanelListener(controller, ((GameBoard) (observable)), this));
        setObservable(observable);
        addCitiesToBackground();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* We add the background image */
        /*
         * try {
         * Image background = ImageIO.read(new
         * File("Resources/backgroundMaps/villes-afrique/mapAlgerie.jpeg"));
         * g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
         * System.out.println(getHeight() + " width:" + getWidth());
         * System.out.println(getPreferredSize().getHeight() + "P.width:" +
         * getPreferredSize().getWidth());
         * 
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */
        // super.paintComponent(g);
        /* We draw the grid */
        drawGrid(g, getWidth(), getHeight(), nbVerticalLines, nbHorizontalLines);
        GameBoard gb = (GameBoard) observable;
        try {
            Image background = ImageIO.read(new File("Resources/backgroundMaps/villes-europe/mapFrance.jpeg"));
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            System.out.println(getHeight() + " width:" + getWidth());
            System.out.println(getPreferredSize().getHeight() + "P.width:" +
                    getPreferredSize().getWidth());

        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Route> map = gb.getGraph().getMap();
        for (Route r : map) {
            double[] t = calculateMidPosRoute(r);
            // We draw the image
            double rotationAngle = Math.toRadians(t[2]);
            int n = getNbWagons(r);
            ImageIcon imageIcon = new ImageIcon(
                    "Resources/routes/routesWithoutWagons/wagon" + r.getColor() + "" + n + ".png");
            // Draw the image
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.rotate(rotationAngle, t[0], t[1]);
            /* Rendre l'image plus smooth */
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

            g2d.drawImage(imageIcon.getImage(), (int) t[0] - imageIcon.getIconWidth() / 2,
                    (int) t[1] - imageIcon.getIconHeight() / 2, null);
            g2d.dispose();
        }
    }

    public void addWagonsToRoute(Route r) {
        Graphics2D g2d = (Graphics2D) getGraphics();
        double[] t = calculateMidPosRoute(r);
        // We draw the image
        double rotationAngle = Math.toRadians(t[2]);
        int n = getNbWagons(r);
        int id = ((GameBoard) observable).getCurrentPlayer().getId();
        ImageIcon imageIcon = new ImageIcon(
                "Resources/routes/routesWithWagons/wagon" + r.getColor() + "" + n + "" + id + ".png");
        // Draw the image
        g2d.rotate(rotationAngle, t[0], t[1]);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

        g2d.drawImage(imageIcon.getImage(), (int) t[0] - imageIcon.getIconWidth() / 2,
                (int) t[1] - 22, null);
        g2d.dispose();
    }

    /*********************************************************************
     * Methods for initializing the background
     *********************************************************************/
    private void drawGrid(Graphics g, int panelWidth, int panelHeight, int nbVerticalLines, int nbHorizontalLines) {
        int verticalSpacing = panelWidth / (nbVerticalLines - 1);
        int horizontalSpacing = panelHeight / (nbHorizontalLines - 1);

        // Draw vertical lines
        for (int i = -1; i < nbVerticalLines - 1; i++) {
            int x = verticalSpacing * (i + 1);
            g.drawLine(x, 0, x, panelHeight);
        }
        // Draw horizontal lines
        for (int i = -1; i < nbHorizontalLines - 1; i++) {
            int y = horizontalSpacing * (i + 1);
            g.drawLine(0, y, panelWidth, y);
        }
    }

    public void addCitiesToBackground() {
        GameBoard gb = (GameBoard) observable;
        for (Route key : gb.getGraph().getMap()) {
            Point position = key.getSourceCity().getCoordinates();
            Point positionDest = key.getDestinationCity().getCoordinates();

            addDotLocation(position, key.getSourceCity().getName());
            addDotLocation(positionDest, key.getDestinationCity().getName());

        }
    }

    public void addDotLocation(Point position, String city) {
        Conversions conversions = new Conversions();
        Point viewPosition = Conversions.convertCityCooToViewCoo((int) position.getX(), (int) position.getY());
        // ImageIcon icon = new ImageIcon("Resources/DotLocation.png");
        // JLabel label = new JLabel(icon);
        // label.setOpaque(true);
        // label.setBounds((int) viewPosition.getX(), (int) viewPosition.getY(),
        // icon.getIconWidth(),
        // icon.getIconHeight());
        // this.add(label);
        // Crée l'icône
        ImageIcon icon = new ImageIcon("Resources/DotLocation.png");

        // Crée le JLabel avec l'icône et le texte
        JLabel label = new JLabel(city, icon, JLabel.LEFT);

        // Définit la position du texte par rapport à l'icône
        label.setHorizontalTextPosition(JLabel.RIGHT); // Texte à droite de l'icône
        label.setVerticalTextPosition(JLabel.CENTER); // Texte centré verticalement par rapport à l'icône

        // Définit les dimensions du JLabel pour inclure l'icône et le texte
        label.setBounds((int) viewPosition.getX(), (int) viewPosition.getY(),
                icon.getIconWidth() + label.getPreferredSize().width,
                icon.getIconHeight());

        // Ajoute le JLabel au conteneur
        this.add(label);
        repaint();
    }

    /******************************************************************
     * Methods for computing
     ******************************************************************/
    /**
     * t[0]-> posX (milieu de la route en px)
     * t[1]->posY (milieu de la route en px)
     * t[2]->angle
     */
    public double[] calculateMidPosRoute(Route route) {
        Point source = route.getSourceCity().getCoordinates();
        Point des = route.getDestinationCity().getCoordinates();
        double midx = (source.getX() + des.getX() + 1) / 2;
        double midy = (source.getY() + des.getY() + 1) / 2;
        Point viewPosition = Conversions.convertCooToViewCoo(midx, midy);
        double angle = route.getAngle();
        double[] t = { (int) viewPosition.getX(), (int) viewPosition.getY(), angle };
        return t;

    }

    public int getNbWagons(Route r) {
        Conversions conversions = new Conversions();
        double l = Conversions.convertRouteLength(r.getLength() - 1);
        return (int) l / wagonWidth;
    }

    /*************************************************************************
     * Observer Methods
     *************************************************************************/
    @Override
    public void update() {
        GameBoard gb = (GameBoard) observable;
    }

    @Override
    public void setObservable(Observable observable) {
        this.observable = observable;
    }

}
