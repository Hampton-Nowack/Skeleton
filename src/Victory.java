

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import edu.utc.game.Game;
import edu.utc.game.Scene;
import edu.utc.game.Text;


public class Victory extends Game implements Scene {
	Text victory = new Text(150,200,80,80, "Victory"); 
	SkeletonGame.Score sc = new SkeletonGame.Score("res/ten.jpg"); 
	public Victory() {
		
	}
	
	public Scene drawFrame(int delta)
	{
		glClearColor(.0f, .0f, .0f, .0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
		
		victory.draw(); 
		sc.draw(); 

		return this;

	}

}
