import java.awt.Graphics;
import java.awt.Color;

public class Player {

    public boolean up, down;
    public int x, y, width, height, speed = 3;
    public int points = 0;

    public Player(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 50;
    }

    public void tick(){
        if(up && y > 0) {
            y-=speed;
        }
        if(down && y < Game.HEIGHT * Game.SCALE - 50){
            y+=speed;
        }

    }

    public void render(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width,height);
    }
}
