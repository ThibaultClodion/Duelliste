package Scenes;

import Game.GameManager;
import Game.PlayerController;
import Game.SpellButton;
import Map.Map;
import Spells.Spell;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    OrthographicCamera camera;

    //Map Data's
    public Map map;
    private final int xMapOffset;
    private final int yMapOffset;

    //Textures
    private final Texture backgroundTexture1;
    private final Texture backgroundTexture2;
    private final Texture rangeTexture;
    private final Texture next_Turn_Inactive_Button;
    private final Texture next_Turn_Active_Button;
    private final ShapeRenderer timer;
    private final ShapeRenderer hpBarPlayer1;
    private final ShapeRenderer hpBarPlayer2;
    private final ShapeRenderer hpBarBackgroundPlayer1;
    private final ShapeRenderer hpBarBackgroundPlayer2;
    private final BitmapFont playerPa;
    private final BitmapFont playerPm;
    private List<SpellButton> spellButtonsPlayer1;
    private List<SpellButton> spellButtonsPlayer2;
    //Players Data's
    private final PlayerController player1;
    private final PlayerController player2;
    private boolean isMoving;

    //Timer Gestion Ressources
    private float clock=0;
    private final float delta_time=1/60f;


    public GameScreen(GameManager GM, PlayerController player1, PlayerController player2)
    {
        this.gameManager = GM;
        this.player1 = player1;
        this.player2 = player2;

        //Initially the player is moving
        isMoving = false;

        //Initialize the batch
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

        //Initialize the map
        Random random = new Random();
        int seed = random.nextInt();
        map = new Map(seed);

        //Change Player positions
        player1.currentPosition = Map.GetInstance().GetFirstPlayerPosition();
        player2.currentPosition = Map.GetInstance().GetSecondPlayerPosition();


        //Initialize the offset
        xMapOffset = (1600 - map.width*map.tileWidth)/2;
        yMapOffset = (int) Math.floor((900 - map.tileHeight*map.height)/1.5);

        //Initialize the Textures
        backgroundTexture1 = new Texture("backgroundInformation.png");
        backgroundTexture2 = new Texture("backgroundInformation2.png");
        rangeTexture = new Texture("range.png");
        playerPa = new BitmapFont();
        playerPm = new BitmapFont();
        timer = new ShapeRenderer();
        hpBarPlayer1 = new ShapeRenderer();
        hpBarPlayer2 = new ShapeRenderer();
        hpBarBackgroundPlayer1 = new ShapeRenderer();
        hpBarBackgroundPlayer2 = new ShapeRenderer();
        next_Turn_Inactive_Button = new Texture("next_turn_inactive.png");
        next_Turn_Active_Button = new Texture("next_turn_active.png");
        spellButtonsPlayer1 = new ArrayList<>();
        spellButtonsPlayer2 = new ArrayList<>();
        for (int i=0; i< player1.character.getNbSpell(); i++) {
            Texture spellTexture = new Texture("classSquare" + i + ".JPG");
            SpellButton spellButton = new SpellButton(spellTexture, 200+100*i, 100, 50, 50);
            spellButtonsPlayer1.add(spellButton);
        }
        for (int i=0; i< player2.character.getNbSpell(); i++) {
            Texture spellTexture = new Texture("classSquare" + i + ".JPG");
            SpellButton spellButton = new SpellButton(spellTexture, 200+100*i, 100, 50, 50);
            spellButtonsPlayer2.add(spellButton);
        }

        //Initialize the input
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta)
    {
        //Set background color
        ScreenUtils.clear(255, 255, 255, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

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
        if(gameManager.GetActualPlayer() != null && !isMoving)
        {
            //For now, we use only the first spell of the character
            List<int[]> rangePositions = getSpellRangePosition(gameManager.GetActualPlayer().character.GetSpell(0));

            for (int[] position: rangePositions)
            {
                //If the position is not a hole or a wall
                batch.draw(rangeTexture, position[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - position[1] * map.tileHeight + yMapOffset);
            }
        }
        else if(gameManager.GetActualPlayer() != null && isMoving)
        {
            //Get the possible movement position
            List<int[]> MovementPositions = getMovementPosition();

            for (int[] position: MovementPositions)
            {
                //If the position is not a hole or a wall
                batch.draw(rangeTexture, position[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - position[1] * map.tileHeight + yMapOffset);
            }
        }

        //Draw the players
        if(player1 != null && player2 != null)
        {
            batch.draw(player1.character.GetImage(), player1.getCurrentPosition()[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - player1.getCurrentPosition()[1] * map.tileHeight + yMapOffset);
            batch.draw(player2.character.GetImage(), player2.getCurrentPosition()[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - player2.getCurrentPosition()[1] * map.tileHeight + yMapOffset);
        }


        //If the timer is over then the round is over too
        if ( clock > 10 )
        {
            clock=0;
            //isMoving = true;
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
        if ( 860 < Gdx.input.getX() && Gdx.input.getX() < 910 && 20 < Gdx.input.getY() && Gdx.input.getY() < 70) {
            batch.draw(next_Turn_Active_Button, 860,830,50,50 );
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                clock=0;
                gameManager.EndRound();
            }
        }
        else {batch.draw(next_Turn_Inactive_Button, 860,830,50,50 );}

        //Player Info Display
        //HP Bar Background
        hpBarBackgroundPlayer1.begin(ShapeRenderer.ShapeType.Filled);
        hpBarBackgroundPlayer2.begin(ShapeRenderer.ShapeType.Filled);
        hpBarBackgroundPlayer1.setColor(Color.BLACK);
        hpBarBackgroundPlayer2.setColor(Color.BLACK);
        Rectangle hp_background_1 = new Rectangle(100, 830, 200, 50);
        Rectangle hp_background_2 = new Rectangle(1300, 830, 200, 50);
        hpBarBackgroundPlayer1.rect(hp_background_1.x, hp_background_1.y, hp_background_1.width, hp_background_1.height);
        hpBarBackgroundPlayer2.rect(hp_background_2.x, hp_background_2.y, hp_background_2.width, hp_background_2.height);
        //HP Bar Foreground
        hpBarPlayer1.begin(ShapeRenderer.ShapeType.Filled);
        hpBarPlayer2.begin(ShapeRenderer.ShapeType.Filled);
        hpBarPlayer1.setColor(Color.RED);
        hpBarPlayer2.setColor(Color.RED);
        Rectangle hp_1 = new Rectangle(100, 830, 200*player1.getHp()/player1.character.GetHp(), 50); //La largeur de la barre est multipliée par le ratio PVactuerl/PVmax
        Rectangle hp_2 = new Rectangle(1300, 830, 200*player2.getHp()/player2.character.GetHp(), 50);
        hpBarPlayer1.rect(hp_1.x, hp_1.y, hp_1.width, hp_1.height);
        hpBarPlayer2.rect(hp_2.x, hp_2.y, hp_2.width, hp_2.height);
        //PA
        playerPa.setColor(Color.WHITE);
        playerPa.getData().setScale(3);
        playerPa.draw(batch,"PA :" + gameManager.GetActualPlayer().getPa(),50,140);
        //PM
        playerPm.setColor(Color.WHITE);
        playerPm.getData().setScale(3);
        playerPm.draw(batch,"PM :" + gameManager.GetActualPlayer().getPm(),50,60);
        // Dessiner tous les boutons de sorts
        if ( gameManager.GetActualPlayer()==player1 ) {
            for (SpellButton button : spellButtonsPlayer1) {
                button.render(batch);
            }
        }
        else {
            for (SpellButton button : spellButtonsPlayer2) {
                button.render(batch);
            }
        }
        //end batch
        timer.end();
        hpBarBackgroundPlayer1.end();
        hpBarBackgroundPlayer2.end();
        hpBarPlayer1.end();
        hpBarPlayer2.end();
        for (SpellButton button : spellButtonsPlayer1) {
            button.getTexture().dispose();
        }
        for (SpellButton button : spellButtonsPlayer2) {
            button.getTexture().dispose();
        }
        batch.end();
    }

    @Override
    public void dispose()
    {
        //Destroy the things created before quit application
        batch.dispose();
        map.Dispose();
        timer.dispose();
        hpBarPlayer1.dispose();
        hpBarPlayer2.dispose();
        hpBarBackgroundPlayer1.dispose();
        hpBarBackgroundPlayer2.dispose();
        playerPa.dispose();
        playerPm.dispose();
    }
    public void handleInput(float x, float y)
    {
        for (SpellButton button : spellButtonsPlayer1)
        {
            if (button.isClicked(x, y))
            {
                // clic sur le bouton
            }
        }
    }

    public List<int[]> getSpellRangePosition(Spell spell)
    {
        return getRangePosition(spell.getRange());
    }

    public List<int[]> getMovementPosition()
    {
        return getRangePosition(gameManager.GetActualPlayer().getPm());
    }

    public List<int[]> getRangePosition(int range)
    {
        int[] actualPosition = gameManager.GetActualPlayer().currentPosition;
        List<int[]> positions = new ArrayList<>();

        for(int i = -range; i <= range; i++)
        {
            for(int j = -range; j <= range; j++)
            {
                //If the position is in range of the spell
                if(Math.abs(i) + Math.abs(j) <= range)
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
        if (button == Input.Buttons.LEFT && IsValidMapPosition(screenX, screenY))
        {
            //Get touch position on the map
            int[] position = new int[2];
            position[0] = (screenX-xMapOffset)/map.tileWidth;
            position[1] = (screenY-(900 - map.height* map.tileHeight - yMapOffset))/map.tileHeight;

            if(position[0] >= 0 && position[0] < map.tileWidth && position[1] >= 0 && position[1] < map.tileHeight)
            {
                if (isMoving)
                {
                    gameManager.Move(position);
                }
                else if (!isMoving)
                {
                    //Use the spell
                    gameManager.LaunchSpell(position);
                }
                return true;
            }
        }
        return false;
    }

    private boolean IsValidMapPosition(int screenX, int screenY)
    {
        return screenX >= xMapOffset && screenX <= 1600-xMapOffset && screenY >= 900 - (yMapOffset + map.tileHeight * map.height) && screenY <= 900-yMapOffset;
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
