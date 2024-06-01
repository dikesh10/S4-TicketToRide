package View.panels.LeftPanels.mapPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class Conversions extends MouseAdapter {
    private static int panelHeight;
    private static int panelWidth;
    private static int cellHeight;
    private static int cellWidth;

    public Conversions() {
        panelHeight = 615;
        panelWidth = 888;
        cellHeight = panelHeight / 19;
        cellWidth = panelWidth / 29;
    }

    public static Point convertCityCooToViewCoo(int x, int y) {
        return new Point(cellWidth * x, cellHeight * y);
    }

    public static Point convertViewCooToCityCoo(int x, int y) {
        return new Point(x / cellWidth, y / cellHeight);
    }

    public static double convertRouteLength(double n) {
        return n * cellWidth;
    }

    public static Point convertCooToViewCoo(double x, double y) {
        return new Point((int) (x * cellWidth), (int) (y * cellHeight));
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

}
