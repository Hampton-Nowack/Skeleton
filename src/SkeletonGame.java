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
	public Score sc; 
	public static Scene game; 
	public static Scene pause;
	public static Scene menu; 
	public static Victory vict; 
	
	
	public static void main(String[] args) {
		SkeletonGame game = new SkeletonGame(); 
		game.registerGlobalCallbacks();
		
		
		SimpleMenu menu = new SimpleMenu();
		
		vict = new Victory(game); 
		menu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
		menu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		menu.select(0);
		
		// construct a SkeletonGame object and launch the game loop
		game.setScene(menu);
		game.gameLoop();
	}
	
	public SkeletonGame() {
		initUI(640, 480, "Skeleton");
		glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		score = 0; 
		sc = new Score(); 

	}
	
	public static class Score extends GameObject {
		public long startTime;
		public long endTime;
		public long sumTime; 
		public Text scoreText = new Text(20, 30, 20,20, "0");
		public boolean scene = true; 
		int counter = 0; 
		public Score() {
			this.hitbox.setSize(20, 30);
			this.hitbox.setLocation(10, 4);
			this.setColor(0,0,0); 
		}
		public void startTime() {
			startTime = System.currentTimeMillis(); 
		}
		public void stopTime() {
			endTime = System.currentTimeMillis();
			sumTime = sumTime + endTime - startTime; 
		}
		
		public long getTime() {
			return sumTime; 
		}
		
		public void reset() {
			sumTime = 0;
			score = 0;
			scoreText = new Text(20, 30, 20,20, String.valueOf(score));
		}
	
		public void setScene(Boolean scene) {
			this.scene = scene; 
		} 
		public void update(int delta) {
			if(Game.ui.mouseButtonIsPressed(0) == true && counter == 0 && scene) { 
				if (score == 0) {
					startTime(); 
				}
				score++; 
				scoreText = new Text(20, 30, 20,20, String.valueOf(score));
			}
			
			if (counter <= 5) {
				counter++;
			}
			else {
				counter= 0; 
			}
		}
	}
	
	
	public Scene drawFrame(int delta) {
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		sc.scoreText.draw(); 
		sc.update(delta);
		if(score == 10) {
			sc.stopTime(); 
			double timeOut = (double)(sc.getTime() * .001);
			 
			vict.setScore(sc, timeOut);
			return vict; 
		}
		if (Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_P)) {
			Pause pause = new Pause(this, sc); 
			sc.setScene(false); 
			sc.stopTime(); 
			return pause; 
		}
		
		
		return this; 
	}
	

}
