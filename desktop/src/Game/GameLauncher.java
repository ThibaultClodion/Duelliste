package Game;

import Map.Map;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class GameLauncher {
	public static void main (String[] arg)
	{
		//Create the config
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		//Window Settings
		config.setTitle("Duelliste");
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setWindowedMode(1280, 720);

		new Lwjgl3Application(new GameManager(), config);
	}
}
