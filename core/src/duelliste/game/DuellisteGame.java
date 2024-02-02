package duelliste.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class DuellisteGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("tile64x64.png");
	}

	@Override
	public void render ()
	{
		//Set background color
		ScreenUtils.clear(255, 255, 255, 1);
		//Render the grid
		int nbTilesWidth = 15;
		int nbTilesHeight = 10;
		int tileWidth = 64;
		int tileHeight = 64;
		batch.begin();
		for(int line = 0; line < nbTilesWidth; line++)
		{
			for(int column = 0; column < nbTilesHeight; column++)
			{
				batch.draw(img, line*tileWidth, column*tileHeight);
			}
		}
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
