package View.panels.LeftPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import Controller.Controller;
import Interfaces.Observable;
import View.panels.LeftPanels.choicePanel.ChoicePanel;
import View.panels.LeftPanels.mapPanel.MapPanel;
import View.panels.LeftPanels.playerCardsPanel.PlayerCardsPanel;

public class LeftPanel extends JPanel {

    public LeftPanel(int width, Observable obs, Controller controller, String m) {

        setPreferredSize(new Dimension(width, (int) getPreferredSize().getHeight()));
        setLayout(new BorderLayout());
        setBackground(Color.gray);
        /* Creating Panels */
        ChoicePanel choicePanel = new ChoicePanel(50, obs, controller);
        PlayerCardsPanel playerCardsPanel = new PlayerCardsPanel(100, obs, controller);
        MapPanel mapPanel = new MapPanel(obs, controller, m);
        /* Adding Panels */
        add(choicePanel, BorderLayout.NORTH);
        add(playerCardsPanel, BorderLayout.SOUTH);
        add(mapPanel, BorderLayout.CENTER);
        setVisible(true);

    }

}
