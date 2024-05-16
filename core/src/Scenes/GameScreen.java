package Scenes;

import Game.GameManager;
import Game.PlayerController;
import Game.SpellButton;
import Map.Map;
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

import java.util.*;

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
    private final Texture selectedTexture;
    private final Texture next_Turn_Inactive_Button;
    private final Texture next_Turn_Active_Button;
    private final Texture moving_button;
    private final ShapeRenderer timer;
    private final ShapeRenderer hpBarPlayer1;
    private final ShapeRenderer hpBarPlayer2;
    private final ShapeRenderer hpBarBackgroundPlayer1;
    private final ShapeRenderer hpBarBackgroundPlayer2;
    private final BitmapFont player1HP;
    private final BitmapFont player2HP;
    private final BitmapFont playerPa;
    private final BitmapFont playerPm;
    private final List<SpellButton> spellButtonsPlayer1;
    private final List<SpellButton> spellButtonsPlayer2;
    private final Texture coolD;

    //Players Data's
    private final PlayerController player1;
    private final PlayerController player2;
    private boolean isMoving;
    List<int[]> rangePositions;

    //Timer Gestion Ressources
    private float clock=0;
    private final float delta_time=1/60f;
    private float roundTime = 30f;


    public GameScreen(GameManager GM, PlayerController player1, PlayerController player2, int seed)
    {
        this.gameManager = GM;
        this.player1 = player1;
        this.player2 = player2;

        //Initially the player is moving
        isMoving = true;
        rangePositions = new ArrayList<>();

        //Initialize the batch
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

        //Initialize the map
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
        selectedTexture = new Texture("selected.png");
        moving_button = new Texture("moving_button.png");
        player1HP = new BitmapFont();
        player2HP = new BitmapFont();
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
        coolD = new Texture("spellSquare.JPG");

        for (int i=0; i< player1.character.getNbSpell(); i++)
        {
            if ( i%2 == 0) {
                SpellButton spellButton = new SpellButton(250 + 100*i/2, 20, 50, 50, player1.character.GetSpell(i));
                spellButtonsPlayer1.add(spellButton);
            }
            else {
                SpellButton spellButton = new SpellButton(250 + 100*(i+1)/2, 95, 50, 50, player1.character.GetSpell(i));
                spellButtonsPlayer1.add(spellButton);
            }
        }

        for (int i=0; i< player2.character.getNbSpell(); i++)
        {
            if ( i%2 == 0) {
                SpellButton spellButton = new SpellButton(250 + 100*i/2, 20, 50, 50, player2.character.GetSpell(i));
                spellButtonsPlayer2.add(spellButton);
            }
            else {
                SpellButton spellButton = new SpellButton(250 + 100*(i+1)/2, 95, 50, 50, player2.character.GetSpell(i));
                spellButtonsPlayer2.add(spellButton);
            }
        }

        GetRangePosition(); //Display the cell in range

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

        for (int[] position: rangePositions)
        {
            batch.draw(rangeTexture, position[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - position[1] * map.tileHeight + yMapOffset);
        }

        //Display the case selected by the current player
        int screenX = Gdx.input.getX();
        int screenY = Gdx.input.getY();
        if (IsValidMapPosition(screenX, screenY))
        {
            //Get touch position on the map
            int[] position = new int[2];
            position[0] = (screenX - xMapOffset) / map.tileWidth;
            position[1] = (screenY - (900 - map.height * map.tileHeight - yMapOffset)) / map.tileHeight;
            if(Map.GetInstance().IsGroundPosition(position[0], position[1]))
            {
                batch.draw(selectedTexture, position[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - position[1] * map.tileHeight + yMapOffset);
            }
        }

        //Draw the players
        if(player1 != null && player2 != null)
        {
            batch.draw(player1.character.GetImage(), player1.getCurrentPosition()[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - player1.getCurrentPosition()[1] * map.tileHeight + yMapOffset, 64, 64);
            batch.draw(player2.character.GetImage(), player2.getCurrentPosition()[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - player2.getCurrentPosition()[1] * map.tileHeight + yMapOffset, 64, 64);
        }


        //If the timer is over then the round is over too
        if ( clock > roundTime )
        {
            clock=0;
            gameManager.EndRound();
            isMoving = true;
            GetRangePosition();
        }

        //Timer Renderer
        clock += delta_time;
        timer.begin(ShapeRenderer.ShapeType.Filled);
        timer.setColor(Color.DARK_GRAY);
        Rectangle rectangle = new Rectangle(750, 830, roundTime*3-3*clock, 50);
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
                isMoving = true;
                GetRangePosition();
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
        //Draw player HP
        player1HP.setColor(Color.WHITE);
        player1HP.getData().setScale(2);
        player1HP.draw(batch, "PV : " + player1.getHp(), 325, 865);
        player2HP.setColor(Color.WHITE);
        player2HP.getData().setScale(2);
        player2HP.draw(batch, "PV : " + player2.getHp(), 1130, 865);

        //Draw players icons at the side of health bar
        if(player1 != null && player2 != null)
        {
            batch.draw(player1.character.GetImage(), hp_1.x - 80, hp_1.y, 64, 64);
            batch.draw(player2.character.GetImage(), hp_2.x + 200 + 10, hp_2.y, 64, 64);
        }

        //PA
        playerPa.setColor(Color.WHITE);
        playerPa.getData().setScale(3);
        playerPa.draw(batch,"PA :" + gameManager.GetActualPlayer().getPa(),50,140);

        //PM
        playerPm.setColor(Color.WHITE);
        playerPm.getData().setScale(3);
        playerPm.draw(batch,"PM :" + gameManager.GetActualPlayer().getPm(),50,60);


        // Dessiner tous les boutons de sorts
        batch.draw(moving_button, 250,95,50,50 );
        if ( gameManager.GetActualPlayer()==player1 )
        {
            for (SpellButton button : spellButtonsPlayer1)
            {
                button.render(batch);
                if(! button.getSpell().IsReloaded()) {
                    batch.setColor(1, 1, 1, 0.7f);
                    batch.draw(coolD, button.getRectangle().x, button.getRectangle().y, 50, 50);
                    batch.setColor(Color.WHITE);
                }
            }
        }
        else
        {
            for (SpellButton button : spellButtonsPlayer2)
            {
                button.render(batch);
                if(! button.getSpell().IsReloaded())
                {
                    batch.setColor(1, 1, 1, 0.7f);
                    batch.draw(coolD, button.getRectangle().x, button.getRectangle().y, 64, 64);
                    batch.setColor(Color.WHITE);
                }
            }
        }
        handleInput(Gdx.input.getX(),900-Gdx.input.getY());
        batch.draw(moving_button, 250,95,0,0 ); //Don't Delete it

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && 220 < Gdx.input.getX() && Gdx.input.getX() < 270 && 750 < Gdx.input.getY() && Gdx.input.getY() < 800) {
            isMoving = true;
            GetRangePosition();
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

    private boolean IsValidMapPosition(int screenX, int screenY)
    {
        return screenX >= xMapOffset && screenX <= 1600-xMapOffset && screenY >= 900 - (yMapOffset + map.tileHeight * map.height) && screenY <= 900-yMapOffset;
    }

    @Override
    public void dispose()
    {
        //Destroy the things created before quit application
        batch.dispose();
        moving_button.dispose();
        map.Dispose();
        timer.dispose();
        hpBarPlayer1.dispose();
        hpBarPlayer2.dispose();
        hpBarBackgroundPlayer1.dispose();
        hpBarBackgroundPlayer2.dispose();
        playerPa.dispose();
        playerPm.dispose();
        coolD.dispose();
    }
    public void handleInput(float x, float y)
    {
        if(GameManager.getInstance().GetActualPlayer() == player1)
        {
            for (SpellButton button : spellButtonsPlayer1)
            {
                if (button.isClicked(x, y) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
                {
                    System.out.println("Button clicked");
                    isMoving=false;
                    player1.actualSpell = button.getSpell();
                    GetRangePosition();
                }
            }
        }
        else
        {
            for (SpellButton button : spellButtonsPlayer2)
            {
                if (button.isClicked(x, y) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
                {
                    isMoving = false;
                    player2.actualSpell = button.getSpell();
                    GetRangePosition();
                }
            }
        }
    }

    public List<int[]> GetSpellRangePosition()
    {
        return GetValidPositions(gameManager.GetActualPlayer().getCurrentPosition(), gameManager.GetActualPlayer().actualSpell.getRange());
    }

    private void GetRangePosition()
    {
        //Get spell range
        if(gameManager.GetActualPlayer() != null && !isMoving)
        {
            rangePositions = GetSpellRangePosition();
        }
        //Get movement range
        else if(gameManager.GetActualPlayer() != null && isMoving)
        {
            rangePositions = GetMovementPosition();
        }
    }

    private List<int[]> GetMovementPosition()
    {
        return GetValidPositions(gameManager.GetActualPlayer().currentPosition, gameManager.GetActualPlayer().getPm());
    }

    private List<int[]> GetValidPositions(int[] initialPosition, int range)
    {
        List<int[]> validPositions = new ArrayList<>();

        //Make a depth-first search to get the ground cases accessible
        Set<List<Integer>> visited = new HashSet<>();
        Queue<int[]> toVisit = new ArrayDeque<>();
        Queue<Integer> distanceToInitialPosition =  new ArrayDeque<>();

        toVisit.add(initialPosition);
        distanceToInitialPosition.add(0);

        while(!toVisit.isEmpty())
        {
            int[] s = toVisit.poll();
            int distance = distanceToInitialPosition.poll();

            //Transform s to a list for Set Contains test
            List<Integer> sToList = new ArrayList<>();
            sToList.add(s[0]);
            sToList.add(s[1]);

            if(distance > range)
            {
                return validPositions;
            }

            if(!visited.contains(sToList) && Map.GetInstance().IsGroundPosition(s[0], s[1]))
            {

                visited.add(sToList);
                validPositions.add(s);

                Set<int[]> neighbors = Map.GetInstance().GetNeighborPosition(s);

                for(int[] t : neighbors)
                {
                    //Transform t to a list for Set Contains test
                    List<Integer> tToList = new ArrayList<>();
                    tToList.add(t[0]);
                    tToList.add(t[1]);

                    if(!visited.contains(tToList))
                    {
                        toVisit.add(t);
                        distanceToInitialPosition.add(distance + 1);
                    }
                }
            }
        }

        return validPositions;
    }

    private boolean IsARangedPosition(int[] position)
    {
        for (int[] rangePosition: rangePositions)
        {
            if(rangePosition[0] == position[0] && rangePosition[1] == position[1])
            {
                return true;
            }
        }

        return false;
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

            if(position[0] >= 0 && position[0] < map.tileWidth && position[1] >= 0 && position[1] < map.tileHeight
            && IsARangedPosition(position))
            {
                if (isMoving)
                {
                    gameManager.Move(position);
                    GetRangePosition();
                }
                else
                {
                    //Use the spell
                    gameManager.LaunchSpell(position);
                    GetRangePosition();

                    //End the game
                    if(gameManager.player1.getHp() <= 0 || gameManager.player2.getHp() <= 0)
                    {
                        gameManager.setEndScreen();
                    }
                }
                return true;
            }
        }
        return false;
    }

    public Map getMap() {
        return map;
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
