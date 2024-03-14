package Scenes;

import Game.GameManager;
import Game.PlayerController;
import Map.Map;
import Spells.Spell;
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

import java.util.ArrayList;
import java.util.List;
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
    private Texture rangeTexture;
    private Texture next_Turn_Inactive_Button;
    private Texture next_Turn_Active_Button;

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

        map.DisplayMap();

        //Initialize the offset
        xMapOffset = (1600 - map.width*map.tileWidth)/2;
        yMapOffset = (int) Math.floor((900 - map.tileHeight*map.height)/1.5);

        //Initialize the Textures
        backgroundTexture1 = new Texture("backgroundInformation.png");
        backgroundTexture2 = new Texture("backgroundInformation2.png");
        rangeTexture = new Texture("range.png");
        timer = new ShapeRenderer();
        next_Turn_Inactive_Button = new Texture("next_turn_inactive.png");
        next_Turn_Active_Button = new Texture("next_turn_active.png");

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
        for(int column = 0; column < map.width; column++)
        {
            for(int line = 0; line < map.height; line++)
            {
                batch.draw(map.GetTexture(column, line),
                        column*map.tileWidth + xMapOffset,
                        (map.height - 1 - line)*map.tileHeight + yMapOffset);
            }
        }

        //Draw the range of actual spell
        if(gameManager.GetActualPlayer() != null)
        {
            //For now, we use only the first spell of the character
            List<int[]> rangePositions = getSpellRangePosition(gameManager.GetActualPlayer().character.GetSpell(0));

            for (int[] position: rangePositions)
            {
                //If the position is not a hole or a wall
                batch.draw(rangeTexture, position[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - position[1] * map.tileHeight + yMapOffset);
            }
        }

        //Draw the players
        if(player1 != null && player2 != null)
        {
            batch.draw(player1.character.getImage(), player1.GetCurrentPosition()[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - player1.GetCurrentPosition()[1] * map.tileHeight + yMapOffset);
            batch.draw(player2.character.getImage(), player2.GetCurrentPosition()[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - player2.GetCurrentPosition()[1] * map.tileHeight + yMapOffset);
        }


        //If the timer is over then the round is over too
        if ( clock > 10 )
        {
            clock=0;
            gameManager.EndRound();
        }

        //Timer Renderer
        clock += delta_time;
        timer.begin(ShapeRenderer.ShapeType.Filled);
        timer.setColor(Color.DARK_GRAY);
        Rectangle rectangle = new Rectangle(750, 830, 100-10*clock, 50);
        timer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

        //Draw the background of information
        batch.draw(backgroundTexture1, 0, yMapOffset + map.tileHeight * map.height);
        batch.draw(backgroundTexture2, 0, 0);

        //Next Turn Button
        if ( 850 < Gdx.input.getX() && Gdx.input.getX() < 900 && 20 < Gdx.input.getY() && Gdx.input.getY() < 70) {
            batch.draw(next_Turn_Active_Button, 860,830,50,50 );
            if (Gdx.input.isTouched()) {
                clock=0;
            }
        }
        else {batch.draw(next_Turn_Inactive_Button, 860,830,50,50 );}

        //end batch
        batch.end();
        timer.end();
    }

    @Override
    public void dispose()
    {
        //Destroy the things created before quit application
        batch.dispose();
        map.Dispose();
    }

    public List<int[]> getSpellRangePosition(Spell spell)
    {
        int[] actualPosition = gameManager.GetActualPlayer().currentPosition;
        List<int[]> positions = new ArrayList<>();

        for(int i = -spell.getRange(); i <= spell.getRange(); i++)
        {
            for(int j = -spell.getRange(); j <= spell.getRange(); j++)
            {
                //If the position is in range of the spell
                if(Math.abs(i) + Math.abs(j) <= spell.getRange())
                {
                    //Verify if the position is on the map
                    if(actualPosition[0] + i >= 0 && actualPosition[0] + i < map.width
                    && actualPosition[1] + j >= 0 && actualPosition[1] + j < map.height)
                    {
                        //Verify is the position is a ground
                        if(map.IsGroundPosition(actualPosition[0] + i, actualPosition[1] + j))
                        {
                            positions.add(new int[]{actualPosition[0] + i, actualPosition[1] + j});
                        }
                    }
                }
            }
        }
        return positions;
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
