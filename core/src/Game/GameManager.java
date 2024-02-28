package Game;

import Scenes.GameScreen;
import Scenes.SelectionScreen;
import Class.Aleator;
import Class.Lamenpeine;
import Spells.Des;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;

public class GameManager extends Game
{
    //Screens
    private GameScreen gameScreen;
    private SelectionScreen selectionScreen;

    //Players
    public PlayerController player1;
    public PlayerController player2;
    private PlayerController actualPlayer;

    @Override
    public void create()
    {
        //Initialize the two Players with however class
        player1 = new PlayerController(new Aleator(), new int[]{0,0});
        player2 = new PlayerController(new Lamenpeine(), new int[]{1,0});

        //Tell which player begin
        actualPlayer = player1;

        //At the beginning we start with this screen (for test purposes)
        setGameScreen();
    }

    //region <Player Management>

    public void LaunchSpell(int[] position)
    {
        actualPlayer.UseSpell(position, new Des(), GetOtherPlayer());
    }

    public PlayerController GetOtherPlayer()
    {
        if(actualPlayer == player1)
        {
            return player2;
        }
        else
        {
            return player1;
        }
    }

    //endregion

    //region <Screen Management>

    private void setGameScreen()
    {
        gameScreen = new GameScreen(this , player1, player2);
        setScreen(gameScreen);
    }

    private void setSelectionScreen()
    {
        selectionScreen = new SelectionScreen(this);
        setScreen(selectionScreen);
    }

    @Override
    public void dispose()
    {
        super.dispose();
    }

    @Override
    public void render()
    {
        super.render();
    }

    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);
    }

    @Override
    public void pause()
    {
        super.pause();
    }

    @Override
    public void resume()
    {
        super.resume();
    }
    //endregion
}
