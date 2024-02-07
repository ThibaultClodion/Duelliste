package Game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class GameLauncher {
	public static void main (String[] arg)
	{
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		//Window Settings
		config.setTitle("Duelliste");

		int nbTilesWidth = 15;
		int nbTilesHeight = 10;
		int tileWidth = 64;
		int tileHeight = 64;
		config.setWindowedMode(tileWidth*nbTilesWidth, tileHeight*nbTilesHeight);

		config.useVsync(true);
		config.setForegroundFPS(60);

		new Lwjgl3Application(new GameManager(), config);
	}
}
