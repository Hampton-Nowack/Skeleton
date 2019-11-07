
import java.util.List;

import edu.utc.game.*;

public class Pause extends Game implements Scene {
	private Scene returnTo;
	private SkeletonGame.Score score; 
	public List<Text> boxes; 
	Target t1;
	Target t2; 
	
	public Pause(Scene returnTo, SkeletonGame.Score score) {
		this.returnTo = returnTo;
		this.score = score; 
		boxes = new java.util.LinkedList<>(); 
		t1 = new Target(100, 100, "Continue");
		t2 = new Target(100, 150, "Exit"); 
		
	}
	public class Target extends GameObject {
		public int width;
		public int height; 
		
		public Target (int x, int y, String text) {
			Text textBox = new Text(x, y, 50, 50, text); 
			boxes.add(textBox); 
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
	

	public Scene drawFrame(int delta) {
		if (t1.isClicked()) {
			score.setScene(true);
			score.startTime();
			return returnTo; 
		}
		if (t2.isClicked()) {
			return null; 
		}
		for (Text b : boxes) {
			b.draw(); 
		}
		score.draw(); 
		score.update(delta);
		
		
		return this;
	}
}
