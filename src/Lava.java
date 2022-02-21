import java.awt.Color;
import java.awt.Graphics;

public class Lava extends Apple{

	
	int x = 10;
	int y = 10;
	int i = 0;
	int lava_size = 40;
	int lava_count = 1;
	private Game game;
	
	public Lava(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	void draw(Graphics g) {


		
		g.setColor(new Color(i,0,0));
		
		if(i<200) {
			g.drawRect(x, y, lava_size*5, lava_size*5);
			i++;
		}
		else if(i>=200 && i < 250){
			g.fillRect(x, y, lava_size*5, lava_size*5);
			i++;
		}
		else {
			i = 0;
			x = rand.nextInt(game.h/lava_size)*lava_size;
			y = rand.nextInt(game.w/lava_size-1)*lava_size;	
		}
		
		//g.fillRoundRect(x, y, apple_size, apple_size, 10, 10);
	}

}
