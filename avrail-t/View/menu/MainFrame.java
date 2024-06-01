package View.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Controller.Controller;
import Model.model.GameBoard;
import View.panels.LeftPanels.LeftPanel;
import View.panels.RightPanels.RightPanel;

public class MainFrame extends JFrame{
    public MainFrame(GameBoard gameBoard, Controller controller, String map) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AccountPlayerPage.resetAccountSave();
            }
        });
        setPreferredSize(new Dimension(1300, 800));
        RightPanel rightPanel = new RightPanel(400, gameBoard, controller);
        LeftPanel leftPanel = new LeftPanel(900, gameBoard, controller, map);
        this.setLayout(new BorderLayout());
        add(rightPanel, BorderLayout.EAST);
        add(leftPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        pack();
    }
    
}
