package View.panels.RightPanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import Controller.Controller;
import Interfaces.Observable;
import View.panels.RightPanels.playersPanel.PlayersPanel;

public class RightPanel extends JPanel {

    public RightPanel(int width, Observable obs, Controller controller) {

        PlayersPanel playersPanel = new PlayersPanel(((2 * width) / 3), obs);
        PackCardsPanel packCardsPanel = new PackCardsPanel((width / 3), obs, controller);

        setPreferredSize(new Dimension(width, (int) getPreferredSize().getHeight()));
        setLayout(new BorderLayout());
        add(packCardsPanel, BorderLayout.CENTER);
        add(playersPanel, BorderLayout.EAST);
        setVisible(true);
        controller.addPlayersPanel(playersPanel);
    }

}
