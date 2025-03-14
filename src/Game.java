import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 120;
    public static int HEIGHT = 80;
    public static int SCALE = 3;

    public  static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public BufferedImage layer = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);

    public Game(){

        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE  ));
        this.addKeyListener(this);
        player = new Player(-1,  HEIGHT * SCALE/2 - 25);
        enemy = new Enemy(WIDTH * SCALE - 10, HEIGHT * SCALE/2 - 25);
        ball = new Ball(WIDTH * SCALE / 2, HEIGHT * SCALE/2);
    }

    public void initFrame(){
        Game game = new Game();
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {
        while(true){
            requestFocus();
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void tick(){
        player.tick();
        enemy.tick();
        ball.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = layer.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH * SCALE,HEIGHT * SCALE);
        player.render(g);
        enemy.render(g);
        ball.render(g);

        g = bs.getDrawGraphics();
        g.drawImage(layer,0,0,null);


        bs.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = true;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = false;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = false;
        }
    }
}
