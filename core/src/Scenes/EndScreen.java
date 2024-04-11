package Scenes;

import Class.Aleator;
import Class.Goblin;
import Class.Character;
import Class.Lamenpeine;
import Class.Rageux;
import Class.Creationiste;
import Game.GameManager;
import Game.PlayerController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen, InputProcessor {
    private SpriteBatch batch;
    Texture background;
    Texture playAgain;
    Rectangle playAgainButton;
    BitmapFont victoire;
    String gagnant;
    BitmapFont gagnantFont;
    PlayerController gagnantPlayer;
    String perdant;
    BitmapFont perdantFont;
    PlayerController perdantPlayer;
    GameManager gameManager;
    OrthographicCamera camera;
    Vector3 pos;
    BitmapFont seedFont;
    String seed;
    public EndScreen(GameManager gm) {
        gameManager = gm;
        background = new Texture(Gdx.files.internal("backgroundV2.jpeg"));
        playAgain = new Texture(Gdx.files.internal("validation.JPG"));
        playAgainButton = new Rectangle(1600 / 2 - 100 / 2, 900 / 6 - 100 / 2, 100, 100);

        victoire = new BitmapFont();
        gagnantFont = new BitmapFont();
        perdantFont = new BitmapFont();
        gagnant = "";
        perdant = "";

        Gdx.input.setInputProcessor(this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

        pos = new Vector3();

        batch = new SpriteBatch();

        seedFont = new BitmapFont();
        seed = "Seed de la map : ";
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

        batch.draw(background, 0, 0);
        batch.draw(playAgain, playAgainButton.x, playAgainButton.y);

        gagnantFont.draw(batch, gagnant, 5 * 900 / 6 - 100, 1600 / 2 - 100);
        seedFont.draw(batch, seed + gameManager.getGameScreen().getMap().getSeed(), 1000, 100);

        batch.draw(gagnantPlayer.getCharacter().GetImage(), 1600/2 - 100/2, 900/2 - 100/2, 100, 100);

        batch.end();

    }

    public void setPodium(String gg, String looser, PlayerController winnerPlayer, PlayerController looserPlayer) {
        gagnant = "Winner : " + gg;
        perdant = "Looser : " + looser;
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
