package Scenes;

import Game.GameManager;
import Map.Map;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class GameScreen implements Screen
{
    private GameManager gameManagerOld;

    //Batch
    private SpriteBatch batch;

    //Map Data's
    public Map map;

    public GameScreen(GameManager GM)
    {
        this.gameManagerOld = GM;

        //Initialize the batch
        batch = new SpriteBatch();

        //Initialize the map
        Random random = new Random();
        int seed = random.nextInt();
        map = new Map(seed);
    }

    @Override
    public void render(float delta)
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
    public void show()
    {

    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        //Destroy the things created before quit application
        batch.dispose();
        map.Dispose();
    }
}