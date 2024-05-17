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

    //Textures and text
    private final Texture backgroundTexture1 = new Texture("backgroundInformation.png");
    private final Texture backgroundTexture2 = new Texture("backgroundInformation2.png");
    private final Texture rangeTexture = new Texture("range.png");
    private final Texture selectedTexture = new Texture("selected.png");
    private final Texture next_Turn_Inactive_Button = new Texture("next_turn_inactive.png");
    private final Texture next_Turn_Active_Button = new Texture("next_turn_active.png");
    private final Texture moving_button = new Texture("moving_button.png");
    private final ShapeRenderer timer = new ShapeRenderer();
    private final ShapeRenderer hpBarPlayer1 = new ShapeRenderer();
    private final ShapeRenderer hpBarPlayer2 = new ShapeRenderer();
    private final ShapeRenderer hpBarBackgroundPlayer1 = new ShapeRenderer();
    private final ShapeRenderer hpBarBackgroundPlayer2 = new ShapeRenderer();
    private final BitmapFont player1HP = new BitmapFont();
    private final BitmapFont player2HP = new BitmapFont();
    private final BitmapFont playerPa = new BitmapFont();
    private final BitmapFont playerPm = new BitmapFont();
    private final BitmapFont actualSpellDescription = new BitmapFont();
    private String actualSpellDescriptionText = "Permet de se déplacer via les PM";
    private final Texture coolD = new Texture("spellSquare.JPG");


    //Players Data's
    private final PlayerController player1;
    private final PlayerController player2;
    private List<SpellButton> actualPlayerButtons = new ArrayList<>();
    private boolean isMoving;
    List<int[]> rangePositions;

    //Timer Gestion Ressources
    private float clock=0;
    private final float roundTime = 30f;


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
        xMapOffset = (1600 - map.width*map.tileWidth)/2;
        yMapOffset = (int) Math.floor((900 - map.tileHeight*map.height)/1.5);

        //Change Player positions
        player1.currentPosition = Map.GetInstance().GetFirstPlayerPosition();
        player2.currentPosition = Map.GetInstance().GetSecondPlayerPosition();

        UpdateSpellsButton();
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

        //Draw begin
        batch.begin();

        DrawBackgrounds();
        DrawMap();
        DrawRangePositions();
        DisplaySelectedCell();
        DrawPlayers();
        TimerUpdate();
        CheckTimerButton();
        DrawSpells();
        MovementSpell();

        EndBatch();
    }

    private void DrawBackgrounds()
    {
        batch.draw(backgroundTexture1, 0, yMapOffset + map.tileHeight * map.height);
        batch.draw(backgroundTexture2, 0, 0);
    }

    private void MovementSpell()
    {
        handleInput(Gdx.input.getX(),900-Gdx.input.getY());
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && 220 < Gdx.input.getX()
                && Gdx.input.getX() < 270 && 750 < Gdx.input.getY() && Gdx.input.getY() < 800)
        {
            isMoving = true;
            actualSpellDescriptionText = "Permet de se déplacer via les PM";
            GetRangePosition();
        }
    }

    private void CheckTimerButton()
    {
        if ( 860 < Gdx.input.getX() && Gdx.input.getX() < 910 && 20 < Gdx.input.getY() && Gdx.input.getY() < 70)
        {
            batch.draw(next_Turn_Active_Button, 860,830,50,50 );

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
            {
                EndRound();
            }
        }
        else
        {
            batch.draw(next_Turn_Inactive_Button, 860,830,50,50 );
        }
    }

    private void DrawSpells()
    {
        //Draw actual spell description
        actualSpellDescription.setColor(Color.WHITE);
        actualSpellDescription.getData().setScale(2);
        actualSpellDescription.draw(batch, actualSpellDescriptionText, 650, 130);

        // Dessiner les boutons de sorts
        batch.draw(moving_button, 250,95,50,50 );
        for (SpellButton button : actualPlayerButtons)
        {
            button.render(batch);
            if(! button.getSpell().IsReloaded())
            {
                batch.setColor(1, 1, 1, 0.7f);
                batch.draw(coolD, button.getRectangle().x, button.getRectangle().y, 50, 50);
                batch.setColor(Color.WHITE);
            }
        }
        batch.draw(moving_button, 250,95,0,0 ); //Don't Delete it
    }

    private void DrawPlayers()
    {
        //Draw the players
        if(player1 != null && player2 != null)
        {
            batch.draw(player1.character.GetImage(), player1.currentPosition[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - player1.currentPosition[1] * map.tileHeight + yMapOffset, 64, 64);
            batch.draw(player2.character.GetImage(), player2.currentPosition[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - player2.currentPosition[1] * map.tileHeight + yMapOffset, 64, 64);
        }


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
        Rectangle hp_1 = new Rectangle(100, 830, 200*player1.GetHp()/player1.character.GetHp(), 50); //La largeur de la barre est multipliée par le ratio PVactuerl/PVmax
        Rectangle hp_2 = new Rectangle(1300, 830, 200*player2.GetHp()/player2.character.GetHp(), 50);
        hpBarPlayer1.rect(hp_1.x, hp_1.y, hp_1.width, hp_1.height);
        hpBarPlayer2.rect(hp_2.x, hp_2.y, hp_2.width, hp_2.height);
        //Draw player HP
        player1HP.setColor(Color.WHITE);
        player1HP.getData().setScale(2);
        player1HP.draw(batch, "PV : " + player1.GetHp(), 325, 865);
        player2HP.setColor(Color.WHITE);
        player2HP.getData().setScale(2);
        player2HP.draw(batch, "PV : " + player2.GetHp(), 1130, 865);

        //Draw players icons at the side of health bar
        batch.draw(player1.character.GetImage(), hp_1.x - 80, hp_1.y, 64, 64);
        batch.draw(player2.character.GetImage(), hp_2.x + 200 + 10, hp_2.y, 64, 64);

        //PA
        playerPa.setColor(Color.WHITE);
        playerPa.getData().setScale(3);
        playerPa.draw(batch,"PA :" + gameManager.GetActualPlayer().GetPa(),50,140);

        //PM
        playerPm.setColor(Color.WHITE);
        playerPm.getData().setScale(3);
        playerPm.draw(batch,"PM :" + gameManager.GetActualPlayer().GetPm(),50,60);

    }

    private void TimerUpdate()
    {
        //If the timer is over then the round is over too
        if ( clock > roundTime )
        {
            EndRound();
        }

        //Timer Renderer
        float delta_time = 1 / 60f;
        clock += delta_time;
        timer.begin(ShapeRenderer.ShapeType.Filled);
        timer.setColor(Color.DARK_GRAY);
        Rectangle rectangle = new Rectangle(750, 830, roundTime*3-3*clock, 50);
        timer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    private void DrawMap()
    {
        for(int column = 0; column < map.width; column++)
        {
            for(int line = 0; line < map.height; line++)
            {
                batch.draw(map.GetTexture(column, line),
                        column*map.tileWidth + xMapOffset,
                        (map.height - 1 - line)*map.tileHeight + yMapOffset);
            }
        }
    }

    private void DrawRangePositions()
    {
        for (int[] position: rangePositions)
        {
            batch.draw(rangeTexture, position[0] * map.tileWidth + xMapOffset, (map.height-1)*map.tileHeight - position[1] * map.tileHeight + yMapOffset);
        }
    }

    private void DisplaySelectedCell()
    {
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
    }

    private void EndRound()
    {
        clock=0;
        gameManager.EndRound();
        isMoving = true;
        actualSpellDescriptionText = "Permet de se déplacer via les PM";
        GetRangePosition();
        UpdateSpellsButton();
    }

    private void EndBatch()
    {
        timer.end();
        hpBarBackgroundPlayer1.end();
        hpBarBackgroundPlayer2.end();
        hpBarPlayer1.end();
        hpBarPlayer2.end();
        batch.end();
    }

    private void UpdateSpellsButton()
    {
        actualPlayerButtons = new ArrayList<>();
        for (int i=0; i< gameManager.GetActualPlayer().character.getNbSpell(); i++)
        {
            SpellButton spellButton;
            if ( i%2 == 0) {
                spellButton = new SpellButton(250 + (float) (100 * i) / 2, 20, 50, 50, gameManager.GetActualPlayer().character.GetSpell(i));
            }
            else {
                spellButton = new SpellButton(250 + (float) (100 * (i + 1)) / 2, 95, 50, 50, gameManager.GetActualPlayer().character.GetSpell(i));
            }
            actualPlayerButtons.add(spellButton);
        }
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
        for (SpellButton button : actualPlayerButtons)
        {
            button.getTexture().dispose();
        }
    }
    public void handleInput(float x, float y)
    {
        for (SpellButton button : actualPlayerButtons)
        {
            if (button.isClicked(x, y) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
            {
                System.out.println("Button clicked");
                isMoving=false;
                gameManager.GetActualPlayer().actualSpell = button.getSpell();
                actualSpellDescriptionText = "PA = " + button.getSpell().getPa() + "            Temps de rechargement =" + button.getSpell().getCooldown() + "\n" + button.getSpell().GetDescription(50);
                GetRangePosition();
            }
        }
    }

    public List<int[]> GetSpellRangePosition()
    {
        return GetValidPositions(gameManager.GetActualPlayer().currentPosition, gameManager.GetActualPlayer().actualSpell.getRange());
    }

    private void GetRangePosition()
    {
        //Get spell range
        if(gameManager.GetActualPlayer() != null && !isMoving)
        {
            rangePositions = GetSpellRangePosition();
        }
        //Get movement range
        else if(gameManager.GetActualPlayer() != null)
        {
            rangePositions = GetMovementPosition();
        }
    }

    private List<int[]> GetMovementPosition()
    {
        return GetValidPositions(gameManager.GetActualPlayer().currentPosition, gameManager.GetActualPlayer().GetPm());
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
                    if(gameManager.player1.GetHp() <= 0 || gameManager.player2.GetHp() <= 0)
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
