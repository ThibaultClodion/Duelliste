package Scenes;

import Game.GameManager;
import Game.PlayerController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen, InputProcessor
{
    private final SpriteBatch batch = new SpriteBatch();
    private final Texture background = new Texture(Gdx.files.internal("backgroundVF.JPG"));
    private final Texture playAgain = new Texture(Gdx.files.internal("replay_button.png"));
    private final Rectangle playAgainButton = new Rectangle(1600 - 250, (float) 900 / 6 - (float) 100 / 2, 100, 100);
    private final BitmapFont victoire = new BitmapFont();
    private String gagnant = "";
    private final BitmapFont gagnantFont = new BitmapFont();
    private String perdant = "";
    private final BitmapFont perdantFont = new BitmapFont();
    private PlayerController gagnantPlayer;
    private PlayerController perdantPlayer;
    private final GameManager gameManager;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final Vector3 pos = new Vector3();
    private final BitmapFont seedFont = new BitmapFont();

    public EndScreen(GameManager gm) {
        gameManager = gm;
        Gdx.input.setInputProcessor(this);
        camera.setToOrtho(false, 1600, 900);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //Begin the batch
        batch.begin();

        DisplayBackgroundAndButtons();
        DisplayPodium();

        batch.end();

    }

    private void DisplayBackgroundAndButtons()
    {
        batch.draw(background, 0, 0);
        batch.draw(playAgain, playAgainButton.x, playAgainButton.y);
    }

    private void DisplayPodium()
    {
        gagnantFont.draw(batch, gagnant, (float) 1600 /2 - 400, (float) 1600 / 2 - 150);
        gagnantFont.getData().setScale(1.5f);
        perdantFont.draw(batch, perdant, (float) 1600 /2 + 200, (float) 1600 / 2 - 150);
        perdantFont.getData().setScale(1.5f);
        String seed = "Utiliser cette seed pour rejouer sur la même carte : ";
        seedFont.draw(batch, seed + gameManager.getGameScreen().getMap().getSeed(), 150, 150);
        seedFont.getData().setScale(1.5f);

        batch.draw(gagnantPlayer.getCharacter().GetImage(), (float) 1600 /2 - 400, (float) 900 /2 - (float) 200 /2, 200, 200);
        batch.draw(perdantPlayer.getCharacter().GetImage(), (float) 1600 /2 + 200, (float) 900 /2 - (float) 200 /2, 200, 200);
    }

    public void setPodium(String gg, String looser, PlayerController winnerPlayer, PlayerController looserPlayer) {
        gagnant = "GAGNANT : " + gg;
        perdant = "PERDANT : " + looser;
        gagnantPlayer = winnerPlayer;
        perdantPlayer = looserPlayer;
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        playAgain.dispose();
        batch.dispose();
        gagnantFont.dispose();
        perdantFont.dispose();
        victoire.dispose();
        seedFont.dispose();

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int i2, int i3) {
        pos.set(screenX, screenY, 0);
        camera.unproject(pos);

        if(playAgainButton.contains(pos.x, pos.y)) {
            gameManager.setSelectionScreen();
        }
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
