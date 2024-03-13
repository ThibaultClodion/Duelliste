package Scenes;

import Game.GameManager;
import Game.PlayerController;
import Map.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class GameScreen implements Screen, InputProcessor
{
    //Game Manager access
    private final GameManager gameManager;

    //Batch
    private final SpriteBatch batch;

    //Map Data's
    public Map map;
    private final int xMapOffset;
    private final int yMapOffset;

    //Textures
    private Texture backgroundTexture1;
    private Texture backgroundTexture2;

    //Players Data's
    private final PlayerController player1;
    private final PlayerController player2;
    //Timer Gestion Ressources
    private ShapeRenderer timer;
    private float clock=0;
    private float delta_time=1/60f;


    public GameScreen(GameManager GM, PlayerController player1, PlayerController player2)
    {
        this.gameManager = GM;
        this.player1 = player1;
        this.player2 = player2;

        //Initialize the batch
        batch = new SpriteBatch();

        //Initialize the map
        Random random = new Random();
        int seed = random.nextInt();
        map = new Map(seed);

        //Initialize the offset
        xMapOffset = (1600 - map.width*map.tileWidth)/2;
        yMapOffset = (int) Math.floor((900 - map.tileHeight*map.height)/1.5);

        //Initialize the Textures
        backgroundTexture1 = new Texture("backgroundInformation.png");
        backgroundTexture2 = new Texture("backgroundInformation2.png");
        timer = new ShapeRenderer();

        //Initialize the input
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta)
    {
        //Set background color
        ScreenUtils.clear(255, 255, 255, 1);

        //Render the grid
        batch.begin();

        //Draw the map
        for(int line = 0; line < map.width; line++)
        {
            for(int column = 0; column < map.height; column++)
            {
                batch.draw(map.GetTexture(line, column), line*map.tileWidth + xMapOffset, column*map.tileHeight + yMapOffset);
            }
        }

        //Draw the players
        if(player1 != null && player2 != null)
        {
            batch.draw(player1.character.getImage(), player1.GetCurrentPosition()[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - player1.GetCurrentPosition()[1] * map.tileHeight + yMapOffset);
            batch.draw(player2.character.getImage(), player2.GetCurrentPosition()[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - player2.GetCurrentPosition()[1] * map.tileHeight + yMapOffset);
        }

        //Timer
        clock += delta_time;
        if ( clock > 10 ) { clock=0;}
        timer.begin(ShapeRenderer.ShapeType.Filled);
        timer.setColor(Color.DARK_GRAY);
        Rectangle rectangle = new Rectangle(750, 830, 100-10*clock, 50);
        timer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

        //Draw the background of information
        batch.draw(backgroundTexture1, 0, yMapOffset + map.tileHeight * map.height);
        batch.draw(backgroundTexture2, 0, 0);

        //end batch
        batch.end();
        timer.end();
    }

    //region <Useless ScreenManagement

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
    //endregion

    @Override
    public void dispose()
    {
        //Destroy the things created before quit application
        batch.dispose();
        map.Dispose();
    }


    // region <InputManagement>
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        //Left mouse button action (for now just on the map)
        if (button == Input.Buttons.LEFT)
        {
            //Get touch position on the map
            int[] position = new int[2];
            position[0] = (screenX-xMapOffset)/map.tileWidth;
            position[1] = (screenY-(900 - map.height* map.tileHeight - yMapOffset))/map.tileHeight;

            //Use the spell
            gameManager.LaunchSpell(position);
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
    //endregion
}
