import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.lang.Thread;

public class Game extends JPanel {
	int player_size = 20;
	public static int h = 800;
	public static int w = 800;
	public static int tick = 0;
	Player snake_head = new Player(this,h/2-player_size,w/2-player_size);
	Apple apple = new Apple(this);
	Lava lava = new Lava(this);
	Lava lava2 = new Lava(this);
//Make the Panel Background	
	public Game () {
		this.setBackground(Color.black);
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				snake_head.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(1);
				}
			}
		});
		setFocusable(true);
	}
//Paint everything here
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		snake_head.draw(g);
		//g.drawString(""+snake_head.body_piece_count, 380, 20);
		apple.draw(g);
		//lava.draw(g);
		//lava2.draw(g);
	}
//Move function	
	 void move() {
		snake_head.move();
	}
	 void hit() {
		apple.hit(snake_head);
		snake_head.gameOver();
	}
	 Boolean gameOver() {
		 return snake_head.gameOver();
	 }
//Make the JFrame	
	public static JFrame makeFrame(Game game) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)size.getWidth();
		int height = (int)size.getHeight();
		Point middleOfScreen = new Point((width/2)-h/2,(height/2)-h/2);
		
		JFrame f = new JFrame("SNAKETIME");
		f.setSize(h,w);
		f.setLocation(middleOfScreen);
		f.getContentPane().add(game);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return f;
	}
	
//Main Game	
	public static void main(String []args) throws InterruptedException {

		//Game game = new Game();
		//JFrame f = makeFrame(game);
		//f.setVisible(true);
		//int tick = 0;
		
		
		while(true) {
			Game game = new Game();
			JFrame f = makeFrame(game);
			tick = 0;
			while(!game.gameOver()) {
				
				//60 fps
				
				Thread.sleep(1000/60);
				//Move
				//if (tick % 5 == 0) {
				game.move();
				game.hit();
				//Repaint
				//f.getContentPane().repaint();	
				game.repaint();
				f.setVisible(true);
				//}
				tick++;
			}	
		}

	}
}
