import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;


public class Apple {
	
	
	private BufferedImage apple;
	Random rand = new Random();
	
	int apple_size = 20;
	private Game game;
	//private static Game dim = new Game();
	int x = rand.nextInt(game.h/apple_size)*apple_size;
	int y = rand.nextInt(game.w/apple_size-1)*apple_size;
	
	public Apple (Game game){
		this.game = game;
		try{
            apple = ImageIO.read(new File ("/Users/kevinsangston/eclipse-workspace/021722 Snake/Media/Apple.png"));
        } catch(java.io.IOException e){
            System.out.println("load image failed:" + e);
        }
	}
	
	void draw(Graphics g) {
		g.setColor(Color.white);
		//g.fillRect(x, y, apple_size, apple_size);
		//g.fillRoundRect(x, y+12, apple_size-2, apple_size/2, 10, 10);
		g.drawImage(apple, x, y, game);
		//g.fillRoundRect(x, y, apple_size, apple_size/2, 10, 10);
	}
	
	Boolean hit(Player snake_head) {
		if(snake_head.x == x && snake_head.y == y)
		{
			snake_head.body_piece_count++;
			//System.out.println(snake_head.body_piece_count);
			x = rand.nextInt(game.h/apple_size)*apple_size;
			y = rand.nextInt(game.w/apple_size-1)*apple_size;
			return(true);
		}
		return false;
	}
	
}
