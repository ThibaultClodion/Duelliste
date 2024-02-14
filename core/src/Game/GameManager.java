package Game;

import Scenes.GameScreen;
import Scenes.SelectionScreen;
import Class.Aleator;
import com.badlogic.gdx.Game;

public class GameManager extends Game
{
    //Screens
    private GameScreen gameScreen;
    private SelectionScreen selectionScreen;

    //Players
    private PlayerController player1;
    private PlayerController player2;

    @Override
    public void create()
    {
        //At the beginning we start with this screen (for test purposes)
        setGameScreen();

        //Initialize the two Players with however class
        player1 = new PlayerController(new Aleator());
        player2 = new PlayerController(new Aleator());
    }

    //region <Screen Management>

    private void setGameScreen()
    {
        gameScreen = new GameScreen(this);
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
