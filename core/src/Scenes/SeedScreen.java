package Scenes;

import Game.GameManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SeedScreen implements Screen, InputProcessor  {
    private final GameManager gameManager;
    private final Texture backgroundTexture = new Texture("backgroundVF.JPG");
    private final Texture validation = new Texture(Gdx.files.internal("validation.JPG"));
    TextButton playSeed;
    TextField seedField;
    private String seed = "";
    private String seedInfo = "";
    private final BitmapFont seedInfoFont = new BitmapFont();
    private final String seedEnter = "Entrez une graine de génération de Map";
    private final BitmapFont seedEnterFont = new BitmapFont();
    private final SpriteBatch batch = new SpriteBatch();
    private final OrthographicCamera camera = new OrthographicCamera();
    private final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private final Stage stage = new Stage();
    private TextButton aleatoire;

    public SeedScreen(GameManager GM)
    {
        gameManager = GM;
        Gdx.input.setInputProcessor(stage);
        camera.setToOrtho(false, 1600, 900);
        batch.setProjectionMatrix(camera.combined);

        UIInit();
        ButtonsListenerInit();
        StageInit();
    }

    private void UIInit()
    {
        skin.getFont("default-font").getData().setScale(1.5f);
        seedField = new TextField("", skin);
        seedInfoFont.getData().setScale(1.5f);
        seedEnterFont.getData().setScale(1.5f);
        aleatoire = new TextButton("Aleatoire", skin);
        playSeed = new TextButton("Jouer", skin);
        aleatoire.setPosition((float) 1600 / 2 - (float) 100 / 2 - 225, 100);
        playSeed.setPosition((float) 1600 / 2 - (float) 100 / 2 + 225, 100);
        aleatoire.setSize(200, 100);
        playSeed.setSize(200, 100);
        this.seedField.setPosition((float) 1600 /2 - (float) 200 /2, (float) 900 /2 - (float) 100 / 2);
        this.seedField.setSize(300, 100);
    }

    private void ButtonsListenerInit()
    {
        playSeed.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // Action à effectuer lorsque le bouton est cliqué
                seed = seedField.getText();
                System.out.println(seed);// test
                if(isInteger(seed))
                {
                    gameManager.setGameScreen(Integer.parseInt(seed));
                }
                else {
                    seedInfo = "La graine doit être un nombre entier";
                }
            }
        });

        aleatoire.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // Action à effectuer lorsque le bouton est cliqué
                Random random = new Random();
                int seed = random.nextInt();
                seedField.setText(String.valueOf(seed));
            }
        });
    }

    private void StageInit()
    {
        stage.addActor(playSeed);
        stage.addActor(aleatoire);
        stage.addActor(seedField);
    }


    @Override
    public void show() {

    }

    public void render(float v) {

        ScreenUtils.clear(255, 255, 255, 1);
        camera.update();

        batch.begin();

        batch.draw(backgroundTexture, 0, 0);
        seedInfoFont.draw(batch, seedInfo, (float) 1600 / 2 - 110, (float) 900 / 2 - 100);
        seedEnterFont.draw(batch, seedEnter, (float) 1600 / 2 - 130, (float) 900 / 2 + 100);
        stage.draw();

        batch.end();
    }
    public void resize(int i, int i1) {

    }
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        backgroundTexture.dispose();
        validation.dispose();
        batch.dispose();
        skin.dispose();
        stage.dispose();
        seedEnterFont.dispose();
        seedInfoFont.dispose();

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
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
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

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
