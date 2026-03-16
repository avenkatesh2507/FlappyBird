import javax.swing.JFrame;

public class GameFrame extends JFrame {

    GamePanel panel;

    GameFrame(){

        panel = new GamePanel();

        this.add(panel);

        this.setTitle("Flappy Bird");
        this.setSize(400, 600);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);

        this.setResizable(false);

        this.setVisible(true);

    }

}
