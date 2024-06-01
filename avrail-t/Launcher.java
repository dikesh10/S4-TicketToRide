import Controller.Controller;
import Model.model.GameBoard;
import View.menu.PlayerPage;

public class Launcher {
    public static void main(String[] args) {
        GameBoard gb = new GameBoard();
        Controller controller = new Controller(gb);
        PlayerPage pp = new PlayerPage(controller, gb);

    }
}
