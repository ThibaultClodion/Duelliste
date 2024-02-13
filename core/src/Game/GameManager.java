package Game;

import ClassSelection.Selection;
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
	//private Selection selection;

	@Override
	public void create ()
	{
		//Initialize the batch
		batch = new SpriteBatch();

		//Initialize the map
		Random random = new Random();
		int seed = random.nextInt();
		map = new Map(seed);

		/*selection = new Selection();

		selection.menuSound.setLooping(true);
		selection.menuSound.play();*/
	}

	@Override
	public void render ()
	{
		//Set background color
		ScreenUtils.clear(255, 255, 255, 1);

		//Render the grid
		batch.begin();

		//Selection Scene Display
		/*
		batch.draw(selection.backgroundImage, 0, 0);
		batch.draw(selection.rectangleImage, selection.rectangle.x, selection.rectangle.y);
		batch.draw(selection.spellSquareImage, selection.spellSquare1.x, selection.spellSquare1.y);
		batch.draw(selection.spellSquareImage, selection.spellSquare2.x, selection.spellSquare2.y);
		batch.draw(selection.spellSquareImage, selection.spellSquare3.x, selection.spellSquare3.y);
		batch.draw(selection.spellSquareImage, selection.spellSquare4.x, selection.spellSquare4.y);
		batch.draw(selection.spellSquareImage, selection.spellSquare5.x, selection.spellSquare5.y);
		batch.draw(selection.spellSquareImage, selection.spellSquare6.x, selection.spellSquare6.y);
		batch.draw(selection.spellSquareImage, selection.spellSquare7.x, selection.spellSquare7.y);
		batch.draw(selection.spellSquareImage, selection.spellSquare8.x, selection.spellSquare8.y);
		batch.draw(selection.spellSquareImage, selection.spellSquare9.x, selection.spellSquare9.y);
		batch.draw(selection.spellSquareImage, selection.spellSquare10.x, selection.spellSquare10.y);
		batch.draw(selection.classSquare1Image, selection.classSquare1.x, selection.classSquare1.y);
		batch.draw(selection.classSquare1Image, selection.classSquare2.x, selection.classSquare2.y);
		batch.draw(selection.classSquare1Image, selection.classSquare3.x, selection.classSquare3.y);
		batch.draw(selection.classSquare1Image, selection.classSquare4.x, selection.classSquare4.y);
		batch.draw(selection.classSquare1Image, selection.classSquare5.x, selection.classSquare5.y);
		batch.draw(selection.validationImage, selection.validation.x, selection.validation.y);
		*/


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

		/*selection.spellSquareImage.dispose();
		selection.classSquare1Image.dispose();
		selection.rectangleImage.dispose();
		selection.validationImage.dispose();
		selection.backgroundImage.dispose();
		selection.menuSound.dispose();
		*/
	}
}
