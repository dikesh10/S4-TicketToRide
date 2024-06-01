package Model.model;

import Interfaces.Observable;
import Interfaces.Observer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class InfoTime implements Observable {

    private GameBoard gameBoard;
    private ArrayList<Observer> observers = new ArrayList<>();
    private Player player;
    private Timer timer;
    private int secondsLeft = 60;
    private int totalsecond = 0;
    private boolean temp = true;
    private TimerTask task;
    private static int count = 0;

    public InfoTime(GameBoard gameBoardTimer) {

        if (count == 0) {
            this.gameBoard = gameBoardTimer;
            timer = new Timer();
            setupTimerTask();
            this.player = gameBoard.getPlayers().get(0);
            gameBoard.setCurrentPlayer(player);
            callTimer();
            count++;
        }
    }

    public boolean isTemp() {
        return temp;
    }

    private void setupTimerTask() {
        task = new TimerTask() {
            @Override
            public void run() {

                if (totalsecond % 50 == 0) {
                    check();
                }

                if (secondsLeft > 0) {

                    if (player == gameBoard.getCurrentPlayer()) {

                        totalsecond++;
                        secondsLeft--;
                        notifyObservers();

                    } else {
                        secondsLeft = 60;
                        player = gameBoard.getCurrentPlayer();
                        notifyObservers();
                        totalsecond++;
                        secondsLeft--;

                    }

                } else {
                    secondsLeft = 60;
                    gameBoard.changePlayer();
                    player = gameBoard.getCurrentPlayer();
                    gameBoard.addImagesCard();

                }
                notifyObservers();
            }
        };
    }

    public void callTimer() {
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    /**************************************************************************
     * Methods Observable
     *************************************************************************/
    @Override
    public void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException("Null Observer");
        if (!observers.contains(o)) {
            observers.add(o);
            notifyObservers();

        }
    }

    public void check() {
        for (int i = 0; i < gameBoard.getPlayers().size(); i++) {
            Player p = gameBoard.getPlayers().get(i);

            for (int j = 0; j < p.getDestinationCards().size(); j++) {
                String citysource = p.getDestinationCards().get(j).getVille1().getName();
                String citysource2 = p.getDestinationCards().get(j).getVille2().getName();
                System.out.println("    " + p.getName() + "       " + citysource + " <-------------->" + citysource2
                        + "  il reussi a relié ou pas  ?" + totalsecond + p.hasPath(citysource, citysource2));
            }
            System.out.println("#########################");
        }
    }

    @Override
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.get(0).update();

    }

    /******************************************************************************
     * Getters et Setters
     *********************************************************************************/

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public int getTotalsecond() {
        return totalsecond;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public TimerTask getTask() {
        return task;
    }

    public void setTask(TimerTask task) {
        this.task = task;
    }

    public Player getPlayer() {
        return player;
    }
}
