import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {

    // Bird
    int birdX = 100;
    int birdY = 250;
    int velocity = 0;

    // Pipes
    int pipeX = 360;
    int gapY;

    int pipeWidth = 60;
    int gapHeight = 150;

    // Score
    int score = 0;

    // Elapsed time
    long startTime;

    boolean gameOver = false;

    // Timer
    Timer timer;

    Random random = new Random();

    GamePanel(){

        this.setFocusable(true);
        this.addKeyListener(this);

        startTime = System.currentTimeMillis();

        gapY = random.nextInt(300) + 100;

        timer = new Timer(20, new ActionListener(){

            public void actionPerformed(ActionEvent e){

                // Gravity
                velocity += 1;
                birdY += velocity;

                // Move pipe
                pipeX -= 5;

                // Respawn pipe
                if(pipeX < -pipeWidth){

                    pipeX = 400;
                    gapY = random.nextInt(300) + 100;

                    score++;

                }

                // Skip collisions until panel has a valid size.
                if(getHeight() > 0){

                    // Collision with top/bottom
                    if(birdY < 0 || birdY > getHeight()){
                        endGame();
                    }

                    // Collision with pipe
                    if(birdX + 30 > pipeX && birdX < pipeX + pipeWidth){

                        if(birdY < gapY - gapHeight/2 || birdY + 30 > gapY + gapHeight/2){
                            endGame();
                        }

                    }

                }

                repaint();

            }

        });

        timer.start();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        // Background
        this.setBackground(Color.cyan);

        // Draw bird
        g.setColor(Color.yellow);
        g.fillOval(birdX, birdY, 30, 30);

        // Draw pipes
        g.setColor(Color.green);

        // Top pipe
        g.fillRect(pipeX, 0, pipeWidth, gapY - gapHeight/2);

        // Bottom pipe
        g.fillRect(pipeX, gapY + gapHeight/2, pipeWidth, getHeight() - (gapY + gapHeight/2));

        // Draw score
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 30));

        g.drawString("Score: " + score, 20, 40);

        long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
        g.drawString("Time: " + elapsedSeconds, 220, 40);

        if(gameOver){
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 42));
            g.drawString("GAME OVER", 85, 260);

            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Press R or SPACE to restart", 75, 295);
        }

    }

    void endGame(){
        gameOver = true;
        timer.stop();
    }

    void restartGame(){
        birdX = 100;
        birdY = 250;
        velocity = 0;

        pipeX = 400;
        gapY = random.nextInt(300) + 100;

        score = 0;
        startTime = System.currentTimeMillis();
        gameOver = false;

        timer.start();
    }

    public void keyPressed(KeyEvent e){

        if(gameOver){
            if(e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_SPACE){
                restartGame();
                repaint();
            }
            return;
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE){

            velocity = -13;

        }

    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}
