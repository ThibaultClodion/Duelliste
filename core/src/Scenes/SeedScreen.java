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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class SeedScreen implements Input.TextInputListener {
    Texture background;
    Texture random;
    Texture validation;
    Rectangle randomButton;
    Rectangle validationButton;
    TextField seed;
    public SeedScreen(GameManager GM) {
        background = new Texture(Gdx.files.internal("backgroundSeed.JPG"));
        random = new Texture(Gdx.files.internal("random.JPG"));
        validation = new Texture(Gdx.files.internal("validation.JPG"));
        randomButton = new Rectangle(1600 / 2 - 400 / 2, 900 / 2 - 200 / 2, 400, 200 );
        validationButton = new Rectangle(1600 / 2 - 100 / 2, 900 / 4 - 100 / 2, 100, 100);
        //seed = new TextField("Enter your seed", );
    }

    public void input(String seed) {
    }

    public void canceled() {

    }
}
