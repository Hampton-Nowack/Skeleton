import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import edu.utc.game.*;
public class SkeletonGame extends Game implements Scene {
	//Initialize score and scoreText 
	public static int score; 
	Text scoreText;
	public static Score sc; 
	
	
	public static void main(String[] args) {
		SkeletonGame game = new SkeletonGame(); 
		game.registerGlobalCallbacks();
		
		
		SimpleMenu menu = new SimpleMenu();
		menu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
		menu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		menu.select(0);
		
		// construct a SkeletonGame object and launch the game loop
		game.setScene(menu); 
		game.gameLoop();
	}
	
	public SkeletonGame() {
		initUI(640, 480, "Skeleton");
		scoreText = new Text(100, 150, 30,30, "test"); 
		glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		score = 0; 
		sc = new Score("res/Zero.jpg"); 

	}
	public class Player extends GameObject {
		
	}
	public static class Score extends GameObject {
		private Texture texture = null; 
		public Score(String skin) {
			texture = new Texture(skin); 
			this.hitbox.setSize(20, 30);
			this.hitbox.setLocation(10, 4);
			this.setColor(0,0,0); 
		}
		public void setTexture(String picture) {
			texture = new Texture(picture); 
		}
		public void draw() {
			texture.draw(this);
		}
		public void update(int delta) { 
			if (score == 0) {
				sc.setTexture("res/Zero.jpg");
			}
			if (score == 1) {
				sc.setTexture("res/one.png");
			}
			if (score == 2) {
				sc.setTexture("res/two.png");
			}
			if (score == 3) {
				sc.setTexture("res/three.jpg");
			}
			if (score == 4) {
				sc.setTexture("res/four.png");
			}
			if (score == 5) {
				sc.setTexture("res/five.jpg");
			}
			if (score == 6) {
				sc.setTexture("res/six.png");
			}
			if (score == 7) {
				sc.setTexture("res/seven.jpg");
			}
			if (score == 8) {
				sc.setTexture("res/eight.png");
			}
			if (score == 9) {
				sc.setTexture("res/nine.jpg");
			}
			if (score == 10) {
				sc.setTexture("res/ten.jpg");
			}
		}
	} 
	
	
	public Scene drawFrame(int delta) {
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		sc.draw();
		sc.update(delta);
		
		
		return this; 
	}
	

}
