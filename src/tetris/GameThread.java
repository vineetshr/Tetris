package tetris;

public class GameThread extends Thread {

    private GameArea gameArea;
    private int score;
    private GameForm gameForm;

    public GameThread(GameArea gameArea, GameForm gameForm) {
        this.gameArea = gameArea;
        this.gameForm = gameForm;
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
            score += gameArea.clearLines();
            gameForm.updateScore(score);
        }
    }
}


