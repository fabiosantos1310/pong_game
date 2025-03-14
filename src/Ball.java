import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Ball {

    public double x,y;
    public int width, height;

    public double dx, dy;
    public double speed = 3;

    public Ball(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 5;
        this.height = 5;

        int angle = new Random().nextInt(359);
        dx = Math.cos(angle);
        dy = Math.sin(angle);
    }

    public void tick(){

        if(y+dy*speed < 0)
            dy*=-1;
        if(y+dy*speed > Game.HEIGHT * Game.SCALE - 5)
            dy*=-1;

        if(x >= Game.WIDTH * Game.SCALE){
            Game.player.points++;
            Game.enemy.difficult += (Game.enemy.difficult / 8);
            Game.ball.x = (double) (Game.WIDTH * Game.SCALE) / 2;
            Game.ball.y = (double) (Game.HEIGHT * Game.SCALE) / 2;
            Game.ball.speed = 3;
            System.out.println("Pontos do jogador: " + Game.player.points);
        } else if(x <= 0){
            Game.enemy.points++;
            Game.ball.x = (double) (Game.WIDTH * Game.SCALE) / 2;
            Game.ball.y = (double) (Game.HEIGHT * Game.SCALE) / 2;
            Game.ball.speed = 3;
            System.out.println("Pontos do inimigo: " + Game.enemy.points);
        }

        Rectangle bounds = new Rectangle((int)(x + (dx*speed)), (int)(y + (dy*speed)), width, height);

        Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
        Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int) Game.enemy.y, Game.enemy.width, Game.enemy.height);

        if(bounds.intersects(boundsPlayer)){
            dx*=-1;
            speed+=0.3;
        } else if (bounds.intersects(boundsEnemy)) {
            dx*=-1;
            speed+=0.3;
        }

        x+=dx*speed;

        y+=dy*speed;

    }
    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, width,height);
    }
}
