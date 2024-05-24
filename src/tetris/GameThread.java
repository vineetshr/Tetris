package tetris;

public class GameThread extends Thread {

    private GameArea gameArea;

    public GameThread(GameArea ga) {
        this.gameArea = ga;
    }

    @Override
    public void run() {

        while (true) {

            gameArea.spawnBlock();
            while (gameArea.moveBlockDown()) {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (gameArea.isOutOfBounds()) {
                System.out.println("Game Over");
                break;
            }

            gameArea.moveBlockToBackground();
            gameArea.clearLines();
        }
    }
}


