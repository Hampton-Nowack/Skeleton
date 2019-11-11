
import java.util.List;

import edu.utc.game.*;

public class Pause extends Game implements Scene {
	private Scene returnTo;
	private SkeletonGame.Score score; 
	SimpleMenu menu; 
	public List<Text> boxes; 
	Target t1;
	Target t2; 
	
	//pause constructor takes in the scene that returned pause menu and the current score
	public Pause(Scene returnTo, SkeletonGame.Score score) {
		this.returnTo = returnTo;
		this.score = score; 
		boxes = new java.util.LinkedList<>(); 
		
		//create new selectable text boxes
		t1 = new Target(100, 100, "Continue");
		t2 = new Target(100, 150, "Return to Main Menu"); 
		
	}
	//target class creates selectable text boxes
	public class Target extends GameObject {
		public int width;
		public int height; 
		
		//constructs targets
		public Target (int x, int y, String text) {
			Text textBox = new Text(x, y, 50, 50, text); 
			boxes.add(textBox); 
			hitbox.setSize(text.length() * 50, 50);
			hitbox.setLocation(x, y); 
		}
		
		//determines if the player is clicking on the textbox
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
	
	//draws and updates objects
	public Scene drawFrame(int delta) {
		
		//if the player tries to continue game, setScene to true so clicking can update score and continue the timer
		if (t1.isClicked()) {
			score.setScene(true);
			score.startTime();
			return returnTo; 
		}
		
		//if the player wants to return to the main menu, setScene to true, reset score to zero, and create a new main menu scene
		//and return to it
		if (t2.isClicked()) 
		{
			score.setScene(true);
			score.reset(); 
			menu = new SimpleMenu(); 
			menu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), returnTo);
			menu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
			menu.select(0);
			return menu; 
		}
		//draw the targets
		for (Text b : boxes) {
			b.draw(); 
		}
		
		//draw and update the score
		score.draw(); 
		score.update(delta);
		
		
		return this;
	}
}
