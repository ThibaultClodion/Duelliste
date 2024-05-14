package Scenes;

import Game.GameManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import java.util.Random;

public class SeedScreen implements Screen, InputProcessor  {
    private GameManager gameManager;
    private Texture backgroundTexture;
    Texture validation;
    TextButton playSeed;
    TextField seedField;
    String seed;
    String seedInfo;
    BitmapFont seedInfoFont;
    String seedEnter;
    BitmapFont seedEnterFont;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 pos;
    private Skin skin;
    private Stage stage;
    private TextInputListener seedListener;
    private TextButton yes;
    private TextButton no;
    private TextButton aleatoire;
    private ButtonGroup yesno;
    private Button selection;
    public SeedScreen(GameManager GM) {
        gameManager = GM;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        backgroundTexture = new Texture("backgroundVF.JPG");
        validation = new Texture(Gdx.files.internal("validation.JPG"));

        // Charge le skin par défaut
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().setScale(1.5f);


        seedField = new TextField("", skin);
        seed = "";
        seedInfo = "";
        seedEnter = "Enter a Map Generation Seed";

        seedInfoFont = new BitmapFont();
        seedEnterFont = new BitmapFont();
        seedInfoFont.getData().setScale(1.5f);
        seedEnterFont.getData().setScale(1.5f);

        aleatoire = new TextButton("Random", skin);
        playSeed = new TextButton("Play", skin);

        yes = new TextButton("Yes", skin);
        no = new TextButton("No", skin);
        selection = new Button(skin);
        yesno = new ButtonGroup(yes, no);

        aleatoire.setPosition(1600 / 2 - 100 / 2 - 225, 100);
        playSeed.setPosition(1600 / 2 - 100 / 2 + 225, 100);
        aleatoire.setSize(200, 100);
        playSeed.setSize(200, 100);

        yes.setPosition(100, 100);
        no.setPosition(1000, 100);

        this.seedField.setPosition(1600/2 - 200/2,900/2 - 100 / 2);
        this.seedField.setSize(300, 100);

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

        pos = new Vector3();

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
                    seedInfo = "Seed must be an int";
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

        stage.addActor(playSeed);
        stage.addActor(aleatoire);

        stage.addActor(seedField);

        // Sélectionne le premier bouton par défaut
        yesno.setChecked("button1");

        yesno.setUncheckLast(true); // Permet de désélectionner le bouton précédemment sélectionné
        yesno.setMinCheckCount(0); // Permet de désélectionner tous les boutons
        yesno.setMaxCheckCount(1); // Permet de sélectionner un seul bouton à la fois
    }



    @Override
    public void show() {

    }

    public void render(float v) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        ScreenUtils.clear(255, 255, 255, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(backgroundTexture, 0, 0);
        seedInfoFont.draw(batch, seedInfo, 1600 / 2 - 50, 900 / 2 - 100);
        seedEnterFont.draw(batch, seedEnter, 1600 / 2 - 95, 900 / 2 + 100);

        batch.end();

        stage.draw();

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            System.out.println(Gdx.input.getX());
            System.out.println(Gdx.input.getY());
        }


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
    public void dispose() {
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
