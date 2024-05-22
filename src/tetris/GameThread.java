package tetris;

public class GameThread extends Thread {

    private GameArea gameArea;

    public GameThread(GameArea ga) {
        this.gameArea = ga;
    }

    @Override
    public void run() {

        while (true) {
            try {
                gameArea.moveBlockDown();
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


