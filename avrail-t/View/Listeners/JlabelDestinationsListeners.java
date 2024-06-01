package View.Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Controller.Controller;

public class JlabelDestinationsListeners extends MouseAdapter {

        public Controller controller;
        public int index;
        public int countimageremoved = 0;
        private static int count = 0;
        private int[] tab = new int[3];

        public JlabelDestinationsListeners(Controller controller, int index) {
                this.controller = controller;
                this.index = index;
                if (count < tab.length) {
                        tab[count] = index;
                        count++;
                } else {
                        count = 0;
                }

        }

        public void mouseClicked(MouseEvent e) {

                if (possibletoremove(index)) {
                        int PlayerGameStateAFTERCLICK = controller.getGameBoard().getCurrentPlayer().getGameState() + 1;
                        controller.getGameBoard().getCurrentPlayer().setGameState(PlayerGameStateAFTERCLICK);
                        controller.removeDestinationLabel(index);

                        tab[index] = -1;
                }
        }

        public boolean possibletoremove(int index) {
                return tab[index] != -1;
        }

}
