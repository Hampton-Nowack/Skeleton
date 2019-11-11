import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import org.lwjgl.opengl.GL;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

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
	static Sound click;
	Sound pauseSound; 
	
	
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
	
	//Skeleton Game Constructor
	public SkeletonGame() {
		initUI(640, 480, "Skeleton");
		glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		
		//initialize the score to 0 every time a new SkeletonGame is created
		score = 0; 
		
		//initialize the sounds for clicking and pausing
		pauseSound = new Sound("res/pauseSound.wav");
		click = new Sound("res/click.wav"); 
		sc = new Score(); 

	}
	//The score class keeps track of the total score, time, and whether or not the current scene can add score or not
	public static class Score extends GameObject {
		public boolean pressed = false; 
		public long startTime;
		public long endTime;
		public long sumTime; 
		public Text scoreText = new Text(20, 30, 20,20, "0");
		public boolean scene = true; 
		int counter = 0; 
		
		//score constructor
		public Score() {
			this.hitbox.setSize(20, 30);
			this.hitbox.setLocation(10, 4);
			this.setColor(0,0,0); 
		}
		//starts the timer
		public void startTime() {
			startTime = System.currentTimeMillis(); 
		}
		//stops the timer
		public void stopTime() {
			endTime = System.currentTimeMillis();
			sumTime = sumTime + endTime - startTime; 
		}
		//returns the total time
		public long getTime() {
			return sumTime; 
		}
		//Determines if the mouse button has been released before it can be pressed again
		public boolean mouseButtonIsPressed(int button) {
			if(GLFW.glfwGetMouseButton(Game.ui.getWindow(), button) == GLFW_RELEASE) {
				pressed = true; 
			}
			if(GLFW.glfwGetMouseButton(Game.ui.getWindow(), button) == GLFW_PRESS && pressed) {
				pressed = false;
				return true; 
			}
			else {
				return false;
			}
		}
		
		//resets the timer and the score
		public void reset() {
			sumTime = 0;
			score = 0;
			scoreText = new Text(20, 30, 20,20, String.valueOf(score));
		}
		//Determines if the current scene is able to add to score 
		public void setScene(Boolean scene) {
			this.scene = scene; 
		} 
		//determines if the score should be updated and updates the score and scoreText
		public void update(int delta) {
			if(mouseButtonIsPressed(0) == true && scene) { 
				if (score == 0) {
					startTime(); 
				}
				click.play();
				score++; 
				scoreText = new Text(20, 30, 20,20, String.valueOf(score));
			}
		}
	}
	
	//draws and updates all objects
	public Scene drawFrame(int delta) {
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		sc.scoreText.draw(); 
		sc.update(delta);
		
		//creates and returns new victory screen if the score is 10
		if(score == 10) {
			sc.stopTime(); 
			double timeOut = (double)(sc.getTime() * .001);
			 
			vict.setScore(sc, timeOut);
			return vict; 
		}
		//returns the pause scene and stops the timer
		if (Game.ui.keyPressed(org.lwjgl.glfw.GLFW.GLFW_KEY_P)) {
			pauseSound.play(); 
			Pause pause = new Pause(this, sc); 
			sc.setScene(false); 
			sc.stopTime(); 
			return pause; 
		}
		
		
		return this; 
	}
	

}
