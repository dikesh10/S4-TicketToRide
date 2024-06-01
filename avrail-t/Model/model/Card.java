package Model.model;

public class Card {
    /**
     * 0-> blue
     * 1-> green
     * 2 ->orange
     * 3 -> pink
     */
    private int color;

    Card(int color) {
        this.color = color;
    }

    /*********************************************************************
     * Getters and setters
     *********************************************************************/

    public int getColor() {
        return color;
    }

    public void setColor(int c) {
        color = c;
    }
}
