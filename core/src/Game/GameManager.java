package Game;

import Scenes.GameScreen;
import Scenes.SelectionScreen;
import com.badlogic.gdx.Game;

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
        //Start with the selection screen
        setSelectionScreen();
    }

    //region <Fight Management>
    public void EndRound()
    {
        //The stats are reset
        player1.NewRound();
        player2.NewRound();

        //The actual player switch
        actualPlayer = GetOtherPlayer();
    }

    //endregion

    //region <Player Management>

    public void LaunchSpell(int[] position)
    {
        //Only the actual player can use his spells
        actualPlayer.UseSpell(position, player1.character.GetSpell(0), GetOtherPlayer());
    }

    public void setPlayer1(PlayerController playerController)
    {
            this.player1 = playerController;
    }
    public void setPlayer2(PlayerController playerController)
    {
        this.player2 = playerController;

        //If the second player is selected then we can define who is playing first
        actualPlayer = player1;
    }

    public PlayerController GetActualPlayer()
    {
        return actualPlayer;
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

    public void setGameScreen()
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
