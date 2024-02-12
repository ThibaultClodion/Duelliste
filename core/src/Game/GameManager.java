package Game;

import Map.Map;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class GameManager extends ApplicationAdapter
{
	//Batch
	private SpriteBatch batch;

	//Map Datas
	public Map map;

	@Override
	public void create ()
	{
		//Initialize the batch
		batch = new SpriteBatch();

		//Initialize the map
		Random random = new Random();
		int seed = random.nextInt();
		map = new Map(seed);
	}

	@Override
	public void render ()
	{
		//Set background color
		ScreenUtils.clear(255, 255, 255, 1);

		//Render the grid
		batch.begin();
		for(int line = 0; line < map.width; line++)
		{
			for(int column = 0; column < map.height; column++)
			{
				batch.draw(map.GetTexture(line, column), line*map.tileWidth, column*map.tileHeight);
			}
		}
		batch.end();
	}

	@Override
	public void dispose ()
	{
		//Destroy the things created before quit application
		batch.dispose();
		map.Dispose();
	}
}
