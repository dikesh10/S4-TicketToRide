package View.Listeners;

import java.awt.event.MouseAdapter;

import Controller.Controller;

public class PlayerCardsPanelListener extends MouseAdapter {
    private Controller controller;

    public PlayerCardsPanelListener(Controller c) {
        controller = c;
    }

}
