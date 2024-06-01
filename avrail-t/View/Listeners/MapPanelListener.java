package View.Listeners;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import Controller.Controller;
import Model.model.Player;
import Model.model.Route;
import Model.model.GameBoard;

import View.panels.LeftPanels.mapPanel.Conversions;
import View.panels.LeftPanels.mapPanel.MapPanel;

public class MapPanelListener extends MouseAdapter {
    private Controller controller;
    private GameBoard gameBoard;
    private MapPanel mapPanel;

    public MapPanelListener(Controller c, GameBoard gameBoard, MapPanel mapPanel) {
        controller = c;
        this.gameBoard = gameBoard;
        this.mapPanel = mapPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        isRouteClicked(e.getX(), e.getY());
    }

    public boolean isRouteClicked(int x, int y) {
        Conversions conversions = new Conversions();

        int halfCellHeight = conversions.getCellHeight() / 2;
        int halfCellWidth = conversions.getCellWidth() / 2;

        for (Route r : gameBoard.getGraph().getMap()) {
            Point point1 = r.getSourceCity().getCoordinates();
            Point point2 = r.getDestinationCity().getCoordinates();

            point1 = convertCityCooToViewCooForRoute(point1, conversions, halfCellWidth, halfCellHeight);
            point2 = convertCityCooToViewCooForRoute(point2, conversions, halfCellWidth, halfCellHeight);

            if (!r.getIsOccupied() && isPointOnLineSegment(new Point(x, y), point1, point2)) {

                if (currentPlayerHasCards(r)) {
                    controller.setIsOccupied(r, true);
                    controller.addRouteToPlayerGraph(r, gameBoard.getCurrentPlayer());
                    Player currPlayer = gameBoard.getCurrentPlayer();
                    controller.addRoute(r, gameBoard.getCurrentPlayer());
                    int id = gameBoard.getCurrentPlayer().getId() + 1;
                    Player newCurrentPlayer = gameBoard.getPlayers().get(id %
                            gameBoard.getPlayers().size());
                    controller.setCurrentPlayer(newCurrentPlayer);
                    mapPanel.addWagonsToRoute(r);
                    gameBoard.notifyObservers();
                    // Chemin.size = 1 --> +1 pts
                    // Chemin.size = 2 --> +2 pts
                    // Chemin.size = 3 --> +4 pts
                    // Chemin.size = 4 --> +7 pts
                    // Chemin.size = 5 --> +10 pts
                    // Chemin.size = 6 --> +15 pts
                    if ((int) r.getLength() - 1 == 2) {
                        controller.setScore(currPlayer, currPlayer.getScore() + (int) (r.getLength() - 1));
                        controller.setNbWagon(currPlayer, currPlayer.getNbWagons() - 2);
                    } else if ((int) r.getLength() - 1 == 3) {
                        controller.setScore(currPlayer, currPlayer.getScore() + (int) (r.getLength() - 1) + 1);
                        controller.setNbWagon(currPlayer, currPlayer.getNbWagons() - 3);
                    } else if ((int) r.getLength() - 1 == 4) {
                        controller.setScore(currPlayer, currPlayer.getScore() + (int) (r.getLength() - 1) + 3);
                        controller.setNbWagon(currPlayer, currPlayer.getNbWagons() - 4);
                    } else if ((int) r.getLength() - 1 == 5) {
                        controller.setScore(currPlayer, currPlayer.getScore() + (int) (r.getLength() - 1) + 5);
                        controller.setNbWagon(currPlayer, currPlayer.getNbWagons() - 5);
                    } else if ((int) r.getLength() - 1 == 6) {
                        controller.setScore(currPlayer, currPlayer.getScore() + (int) (r.getLength() - 1) + 9);
                        controller.setNbWagon(currPlayer, currPlayer.getNbWagons() - 6);
                    } else if ((int) r.getLength() - 1 == 1) {
                        controller.setScore(currPlayer, currPlayer.getScore() + (int) r.getLength() - 1);
                        controller.setNbWagon(currPlayer, currPlayer.getNbWagons() - 1);
                    } else {
                    }
                    return true;
                }

            }

        }

        return false;
    }

    public boolean currentPlayerHasCards(Route r) {
        Player currentP = gameBoard.getCurrentPlayer();
        int color = r.getColor();
        int length = (int) r.getLength() - 1;
        /* Soit il a assez de cartes de cette couleur */
        int cards = currentP.getPackCards().get(color);
        if (cards >= length) {
            currentP.removeCards(color, length);
            return true;
        }
        /* Soit le choix c'est la carte locomotive */
        int nbLocomotiveCards = currentP.getPackCards().get(5);

        if (cards + nbLocomotiveCards >= length) {
            currentP.removeCards(color, length);
            currentP.removeCards(5, length);
            return true;
        }
        return false;
    }

    private Point convertCityCooToViewCooForRoute(Point point, Conversions conversions, int halfCellWidth,
            int halfCellHeight) {
        point = Conversions.convertCityCooToViewCoo((int) point.getX(), (int) point.getY());
        return new Point((int) point.getX() + halfCellWidth, (int) point.getY() + halfCellHeight);
    }

    private boolean isPointOnLineSegment(Point point, Point start, Point end) {
        /* We calculate the squared distance between the debut and end points */
        double lineLengthSquared = Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2);

        /*
         * We Calculate the dot product of the vector from start to point and start to
         * end
         */
        double dotProduct = ((point.x - start.x) * (end.x - start.x) + (point.y - start.y) * (end.y - start.y))
                / lineLengthSquared;

        /* Calculate the closest point on the line segment to the clicked point */
        double closestX = start.x + dotProduct * (end.x - start.x);
        double closestY = start.y + dotProduct * (end.y - start.y);

        /* Check if the closest point is within the line segment */
        if (dotProduct >= 0 && dotProduct <= 1) {
            /*
             * Calculate the distance between the clicked point and the closest point on the
             * line segment
             */
            double distanceSquared = Math.pow(point.x - closestX, 2) + Math.pow(point.y - closestY, 2);

            /* Define a tolerance d'une distance (5 pixels de chaque cote) */
            double toleranceSquared = 25.0;

            return distanceSquared <= toleranceSquared;
        }

        return false;
    }
}
