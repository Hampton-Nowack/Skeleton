
import edu.utc.game.*;

public class Pause extends Game implements Scene {
	private Scene returnTo;
	private int score; 
	SkeletonGame.Score sc = new SkeletonGame.Score("res/Zero.jpg"); 
	Text cont = new Text(100,100,50,50,"Continue Game");
	Text exit = new Text(100,150,50,50, "Exit Game"); 
	
	public Pause(Scene returnTo, int score) {
		this.returnTo = returnTo;
		this.score = score; 
	}
	

	public Scene drawFrame(int delta) {
		cont.draw();
		exit.draw(); 
		sc.draw(); 
		sc.update(delta);
		
		
		return this;
	}
}
