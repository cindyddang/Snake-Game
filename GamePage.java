package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePage extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;
    
    //declaring static
    static final int WIDTH = 500;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 20;
    static final int UNIT_NUMBER = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
        
    //food coordinates
    int foodX;
    int foodY;
    
    //snake coordinates
    final int x[] = new int[UNIT_NUMBER];
    final int y[] = new int[UNIT_NUMBER];
    
    //initial variables
    Random random;
    Timer timer;
    boolean playing = false;
    int foodEaten = 0;
    int length = 3;
    char direction = 'D';
    
        
    public GamePage()
    {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //set page dimension
        this.setBackground(new Color(175,215,247));
        this.setFocusable(true);
        this.addKeyListener(new keyAdapter());
        play();
    }  
    
    public void play()
    {
        newFood();
        playing = true;
        
        timer = new Timer(80, this);
        timer.start();
    }
    
    //create new food on screen
    public void newFood(){
       foodX = random.nextInt((int)(WIDTH/UNIT_SIZE))*UNIT_SIZE;
       foodY = random.nextInt((int)(HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    
    //when snake eats food
    public void eatFood(){
        if(x[0] == foodX && y[0] == foodY)
        {
            length++;
            foodEaten++;
            newFood();
        }
    }
    
    //when snake hits himself
    public void loseGame(){
        //hits himself
        for(int i = length; i > 0; i--)
        {
            if(x[0] == x[i] && y[0] == y[i])
            {
                playing = false;
            }
        }
        
        //hits wall
        if( x[0] < 0 || x[0] > WIDTH || y[0] < 0 || y[0] > HEIGHT){
            playing = false;
        }
        
        if(playing == false)
        {
            timer.stop();
        }
    }
    
    public void move(){
        for (int i = length; i>0; i--)
        {
           x[i] = x[i-1];
           y[i] = y[i-1];
        }
        
        if(direction == 'L'){
            x[0] = x[0] - UNIT_SIZE;
        }
        if(direction == 'R')
        {
            x[0] = x[0] + UNIT_SIZE;
        }
        if(direction == 'U')
        {
            y[0] = y[0] - UNIT_SIZE;
        }
        else if(direction == 'D')
        {
            y[0] = y[0] + UNIT_SIZE;
        }
    }
    
    //paint page
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);
    }
    
    public void draw(Graphics graphics)
    {
        if(playing)
        {
            //paint food
            graphics.setColor(new Color (121,136,202));
            graphics.fillOval(foodX, foodY, UNIT_SIZE,  UNIT_SIZE);
            
//            graphics.setColor(Color.BLUE);
//            graphics.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);
            
            //paint snake
            for (int i = 0; i<length; i++){
                graphics.setColor( new Color (200,180,225));
                graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            graphics.setColor(new Color (230,201,225));
            graphics.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);  
            
            //text
            graphics.setColor(new Color(121,136,202));
            graphics.setFont(new Font("Arial", BOLD, 30));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, graphics.getFont().getSize());
	}
        
        else{
            graphics.setColor(new Color(121,136,202));
            graphics.setFont(new Font("Arial", BOLD, 80));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, HEIGHT/2-80);
            graphics.drawString("GAME OVER", (WIDTH - metrics.stringWidth("GAME OVER")) / 2, HEIGHT/2);
            }
    }
    
    public void actionPerformed(ActionEvent e) {
        if(playing){
            move();
            eatFood();
            loseGame();
        }
        
        repaint();
    }
    
    public class keyAdapter extends KeyAdapter
    {
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (direction != 'R')
                        direction = 'L';
                    break;
                
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L')
                        direction = 'R';
                    break;
                
                case KeyEvent.VK_UP:
                    if (direction != 'D')
                        direction = 'U';
                    break;
                
                case KeyEvent.VK_DOWN:
                    if (direction != 'U')
                        direction = 'D';
                    break;
                
            }
        }
    }
//    
//    public static void main(String[] args) {
//        GamePage newGame = new GamePage();
//    }
}
