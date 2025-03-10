/*May 22, 2023
**/

package Snake-Game;

import javax.swing.JFrame;

public class SnakeGame extends JFrame{

    public SnakeGame(){
        GamePage panel = new GamePage();
        this.add(panel);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        SnakeGame newGame = new SnakeGame();
    }
    
}
