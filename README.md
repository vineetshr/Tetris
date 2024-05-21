# tetris.Tetris
tetris.Tetris game

### Steps

1. **Create tetris grid and draw a tetris.Tetris block on the game Form**
   - Create `GameForm` class (extends `JFrame`)
   - Create `tetris.GameArea` class (extends `JPanel`)
   - use the overloaded method: `protected void paintComponent(Graphics g)` to draw on the `JPanel`. Set bounds on JPanel and create tetris.GameArea object in GameForm class constructor
   - call `super.paintComponent(g)` in paintComponent to see visible background color.
   - the paintComponent method is not called but some other system calls it
   - Create and draw tetris grid with number of rows and columns
   - Adjust JPanel to match grid specification with centre and vertical alignment
   - Create a tetris block using 2-D array (1 = colored, 0 = blank)
   - drawBlock method to draw this tetris block on the grid

2. **Make the block fall down**
   - Create a TetrisBlock class, has 2-D array determining shape and variable for its color
   - spawnBlock method in tetris.GameArea creates a new TetrisBlock object
   - edit the drawBlock method so it uses the getter methods on TetrisBlock class
   - Create variables for x and y direction offset
   - Use these variables to move TetrisBlock down (y++), left (x--) and right (x--).
   - moveBlockDown method, moves TetrisBlock down and calls repaint() method which draws the block in the new position
   - we need the block to move down in regular intervals (create a new GameThread class and assign this task to a new thread)
   - startGame method in GameForm is used to create a new GameThread object. To start a thread use .start() method on the object which is inherited from Thread class.
   - call the startGame method in the GameForm constructor
   - when the start() method is called, another method, run is called
   - in the overriden run method in GameThread, call the moveBlockDown method by first getting reference to the tetris.GameArea object and then passing that reference to the GameThread object
   - Fix the block so it moves down from above the tetris.GameArea
   - Fix the block so it stops moving after touching the bottom



3. **Spawn new blocks**
   - Background = already fallen blocks
   - Foreground = falling blocks


4. **Moving and rotating block on key press**


5. **Clear complete lines**

6. **Adding more block types**


7. **Adding a leaderboard**



8. **Saving/loading leaderboard data**


9. **Adding sounds**







