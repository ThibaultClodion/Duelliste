package Game;

import Scenes.GameScreen;
import Scenes.SelectionScreen;
import com.badlogic.gdx.Game;

public class GameManager extends Game
{
    private GameScreen gameScreen;
    private SelectionScreen selectionScreen;

    @Override
    public void create()
    {
        //At the beginning we start with this screen (for test purposes)
        setGameScreen();
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
