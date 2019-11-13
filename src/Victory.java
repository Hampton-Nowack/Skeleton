


import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.util.List;

import edu.utc.game.Game;
import edu.utc.game.GameObject;
import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;
import edu.utc.game.Sound;
import edu.utc.game.Text;
import edu.utc.game.XYPair;


public class Victory extends Game implements Scene { 
	SkeletonGame.Score score; 
	SimpleMenu menu = new SimpleMenu();
	Target t;
	Text time; 
	double timeOut;
	Text victory = new Text(145,50,80,80, "Victory"); 
	SkeletonGame game;
	Sound yaaa;
	
	public Victory(SkeletonGame game) {
		t = new Target(50, 200, "Return To Main Menu");
		this.game = game;
		yaaa = new Sound("res/YEAAAYEAAA.wav"); 
		
	}
	
	public void setScore(SkeletonGame.Score score, double timeOut){
		menu = new SimpleMenu();
		menu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
		menu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		menu.select(0);
		this.score = score;
		this.timeOut = timeOut;
		time = new Text(50,300,50,50, "Time: " + String.valueOf(timeOut) + " seconds"); 
		yaaa.play(); 
		yaaa.setLoop(true);
	}	
	public class Target extends GameObject {
		public int width;
		public int height; 
		public Text textBox; 
		
		public Target (int x, int y, String text) {
			textBox = new Text(x, y, 50, 50, text);  
			hitbox.setSize(text.length() * 50, 50);
			hitbox.setLocation(x, y); 
		}
		public boolean isClicked() {
			if(Game.ui.mouseButtonIsPressed(0) == true) {
				XYPair<Integer> mouse = Game.ui.getMouseLocation(); 
				return (hitbox.contains(mouse.x, mouse.y)); 
			}
			else {
				return false; 
			}
		}

		
		
	}
	
	public Scene drawFrame(int delta)
	{
		glClearColor(.0f, .0f, .0f, .0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
	
		victory.draw(); 
		
		score.scoreText.draw(); 
		time.draw();
		if(t.isClicked()) {
			score.reset();
			yaaa.stop();
			return menu; 
		}

		t.textBox.draw(); 
		
		return this;

	}

}
