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
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.Input.TextInputListener;

public class SeedScreen implements Screen, InputProcessor  {
    Texture background;
    Texture random;
    Texture validation;
    Rectangle randomButton;
    Rectangle validationButton;
    TextField seedField;
    String seed;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 pos;
    private Skin skin;
    public SeedScreen(GameManager GM) {
        background = new Texture(Gdx.files.internal("backgroundSeed.jpg"));
        random = new Texture(Gdx.files.internal("random.jpg"));
        validation = new Texture(Gdx.files.internal("validation.JPG"));
        randomButton = new Rectangle(1600 / 2 - 400 / 2, 900 / 2 - 200 / 2, 400, 200 );
        validationButton = new Rectangle(1600 / 2 - 100 / 2, 900 / 4 - 100 / 2, 100, 100);

        // Charge le skin par défaut
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = skin.getFont("default-font");
        textFieldStyle.fontColor = skin.getColor("text");
        textFieldStyle.cursor = skin.getDrawable("cursor");
        textFieldStyle.selection = skin.getDrawable("selection");
        textFieldStyle.background = skin.getDrawable("textfield");

        seedField = new TextField("Enter your seed", textFieldStyle);
        seed = "";

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

        Gdx.input.setInputProcessor(this);
        pos = new Vector3();

        this.seedField.setPosition(24,73);
        this.seedField.setSize(88, 14);

        //https://stackoverflow.com/questions/45014420/using-textfield-with-libgdx
    }



    @Override
    public void show() {

    }

    public void render(float v) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(validation, validationButton.x, validationButton.y);
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
    public void dispose() {
        background.dispose();
        random.dispose();
        validation.dispose();
        batch.dispose();
        skin.dispose();

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
        pos.set(i, i1, 0);
        camera.unproject(pos);
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
