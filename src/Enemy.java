import java.awt.Graphics;
import java.awt.Color;

public class Enemy {

    public double x,y;
    public int width, height;
    public int points = 0;
    public double difficult = 0.2;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 50;
    }

    public void tick(){
        y+= (Game.ball.y - y) * difficult;
        if(y+ (Game.ball.y - y) <= 0 )
            y=0;
        if(y+ (Game.ball.y - y) >= Game.HEIGHT * Game.SCALE - 50)
            y=Game.HEIGHT * Game.SCALE - 50;

    }
    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, width,height);
    }
}
