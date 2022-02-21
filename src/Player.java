import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class Player {
	
	int x;
	int y;
	int xs;
	int ys;
	int cvar = 1;
	int cvars;
	private BufferedImage SnakeDown;
	private BufferedImage SnakeLeft;
	private BufferedImage SnakeUp;
	private BufferedImage SnakeRight;
	private BufferedImage SnakeSleep;

	
	Point position;
	int player_size = 20;
	int SPEED = 5;
	int speed_ratio = player_size/SPEED;
	private Game game;
	//private Jlabel snake_head_pic = new JLabel(new Image)

	int last_direction;
	int body_piece_count;
	
	ArrayList<Point> last_positions = new ArrayList();

	public Player (Game game, int x, int y){
		this.game = game;
		this.x = x;
		this.y = y;
		this.position = new Point(x,y);
		try{
            SnakeDown = ImageIO.read(new File ("/Users/kevinsangston/eclipse-workspace/021722 Snake/Media/SnakeHeadDown.png"));
            SnakeLeft = ImageIO.read(new File ("/Users/kevinsangston/eclipse-workspace/021722 Snake/Media/SnakeHeadLeft.png"));
            SnakeUp = ImageIO.read(new File ("/Users/kevinsangston/eclipse-workspace/021722 Snake/Media/SnakeHeadUp.png"));
            SnakeRight = ImageIO.read(new File ("/Users/kevinsangston/eclipse-workspace/021722 Snake/Media/SnakeHeadRight.png"));
            SnakeSleep = ImageIO.read(new File ("/Users/kevinsangston/eclipse-workspace/021722 Snake/Media/SnakeHeadSleep.png"));
        } catch(java.io.IOException e){
            System.out.println("load image failed:" + e);
        }
		last_positions.add(new Point(x,y));
	}
	
	public void draw(Graphics g) {
		int size = last_positions.size();
		//g.setColor(new Color(0,150,0));
		//g.fillRoundRect(x, y, player_size, player_size, 10, 10);
		
		//g.drawImage(image, x, y, game);
		//g.fillRect(x, y, player_size, player_size);	
		if(body_piece_count > 0) {
			//for(int i = 0; i < size; i+=speed_ratio) {
			
			g.setColor(new Color(100,100,0));
			for(int i = speed_ratio; i < size; i++) {
				//g.fillOval(last_positions.get(i).x, last_positions.get(i).y, player_size, player_size);
				g.fillRoundRect(last_positions.get(i).x, last_positions.get(i).y, player_size-3, player_size-3, 20, 20);
				
			}
			g.setColor(new Color(0,150,0));
			for(int i = speed_ratio; i < size; i+=speed_ratio) {
				//g.fillOval(last_positions.get(i).x, last_positions.get(i).y, player_size, player_size);
				g.fillRoundRect(last_positions.get(i).x, last_positions.get(i).y, player_size-3, player_size-3, 20, 20);
				
			}
			
		}
		switch (last_direction) {
		case 1: 
			if(ys != 1)
				g.drawImage(SnakeUp, x, y, game);
			else
				g.drawImage(SnakeDown, x, y, game);
			break;
			
		case 2:
			if(ys != -1)
				g.drawImage(SnakeDown, x, y, game);
			else
				g.drawImage(SnakeUp, x, y, game);
			break;
		
		case 3:
			if(xs != 1)
				g.drawImage(SnakeLeft, x, y, game);
			else
				g.drawImage(SnakeRight, x, y, game);
			break;
			
		case 4:
			if(xs != -1)
				g.drawImage(SnakeRight, x, y, game);
			else
				g.drawImage(SnakeLeft, x, y, game);
			break;
		default:
			
			if(cvar == 99) {
				cvars = 1;
			}
			else if (cvar == 1) {
				cvars = -1;
			}
			g.setColor(new Color(255,255,255, cvar-=cvars));
			g.drawString("Zzzz", x+cvar/4, y-5);
			g.drawImage(SnakeSleep, x, y, game);
		}
		//g.drawImage(image, x, y, game);
	}
	
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP: 
			
			last_direction = 1;
			break;
			
		case KeyEvent.VK_DOWN:
			
			last_direction = 2;
			break;
		
		case KeyEvent.VK_LEFT:
			
			last_direction = 3;
			break;
			
		case KeyEvent.VK_RIGHT:
			
			last_direction = 4;
			break;
		}
	}
	
	Boolean checkHitSelf() {
		int size = last_positions.size();
		for(int i = 1; i < size; i++) {
			if(position.equals(last_positions.get(i))) {
				//System.out.println("HIT");
				return true;
			}
		}
		return false;
	}
	
	Boolean checkHitWall() {
		return (position.x < 0 || position.x >= game.w || position.y < 0 || position.y >= game.h-player_size);
	}
	
	Boolean gameOver() {
		if(checkHitSelf() || checkHitWall()) {
			return true;
			//System.exit(0);
		}
		else
			return false;
	}
	
	void move() {

		if(x % player_size == 0 && y % player_size == 0) {
			switch (last_direction) {
			case 1: 
				if(ys == 0)
				ys = -1;
				xs = 0;
				break;
				
			case 2:
				
				last_direction = 2;
				if(ys == 0)
				ys = 1;
				xs = 0;
				break;
			
			case 3:
				
				last_direction = 3;
				if(xs == 0)
				xs = -1;
				ys = 0;
				break;
				
			case 4:
				
				last_direction = 4;
				if(xs == 0)
				xs = 1;
				ys = 0;
				break;
			}
		}
		x += xs * SPEED;
		y += ys * SPEED;
		
		position.x = x;
		position.y = y;
		//body_piece_count > 0 && 
		if(!position.equals(last_positions.get(0))){
			last_positions.add(0,new Point(x,y));
			while(last_positions.size() > body_piece_count*speed_ratio+1) {
			last_positions.remove(last_positions.size()-1);
			}	
		}
		//System.out.println(last_positions.get(0));
		//System.out.println(last_positions.size());
	}
}
