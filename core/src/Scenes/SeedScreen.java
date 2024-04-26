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
    Texture background;
    Texture random;
    Texture validation;
    Rectangle randomButton;
    Rectangle validationButton;
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
        background = new Texture(Gdx.files.internal("backgroundSeed.jpg"));
        random = new Texture(Gdx.files.internal("random.jpg"));
        validation = new Texture(Gdx.files.internal("validation.JPG"));
        randomButton = new Rectangle(1600 / 2 - 400 / 2, 900 / 2 - 200 / 2, 400, 200 );
        validationButton = new Rectangle(1600 / 2 - 100 / 2, 900 / 4 - 100 / 2, 100, 100);

        // Charge le skin par défaut
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        //TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();

        seedField = new TextField("Enter your seed", skin);
        seed = "";
        seedInfo = "";
        seedEnter = "Entrez une seed";

        seedInfoFont = new BitmapFont();
        seedEnterFont = new BitmapFont();

        aleatoire = new TextButton("Aleatoire", skin);
        playSeed = new TextButton("Lire la seed", skin);

        yes = new TextButton("Yes", skin);
        no = new TextButton("No", skin);
        selection = new Button(skin);
        yesno = new ButtonGroup(yes, no);

        aleatoire.setPosition(1600 / 2 - 100 / 2 - 100 - 25, 100);
        playSeed.setPosition(1600 / 2 - 100 / 2 + 100 + 25, 100);
        aleatoire.setSize(100, 100);
        playSeed.setSize(100, 100);

        yes.setPosition(100, 100);
        no.setPosition(1000, 100);

        this.seedField.setPosition(1600/2 - 200 / 2,900/2 - 50 / 2 + 100);
        this.seedField.setSize(200, 50);

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
                if(isInteger(seed)) {
                    gameManager.setGameScreen(Integer.parseInt(seed));
                }
                else {
                    seedInfo = "La seed n'est pas valide ( seed est un entier )";
                }
            }
        });

        aleatoire.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // Action à effectuer lorsque le bouton est cliqué
                Random random = new Random();
                int seed = random.nextInt();
                gameManager.setGameScreen(seed);
            }
        });

        stage.addActor(playSeed);
        stage.addActor(aleatoire);

        stage.addActor(seedField);
        //stage.addActor(yes);
        //stage.addActor(no);
        //Cas où on utilise les boutons oui / non

        // Sélectionne le premier bouton par défaut
        yesno.setChecked("button1");

        yesno.setUncheckLast(true); // Permet de désélectionner le bouton précédemment sélectionné
        yesno.setMinCheckCount(0); // Permet de désélectionner tous les boutons
        yesno.setMaxCheckCount(1); // Permet de sélectionner un seul bouton à la fois

        //https://stackoverflow.com/questions/45014420/using-textfield-with-libgdx
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

        seedInfoFont.getData().setScale(2f);
        seedInfoFont.draw(batch, seedInfo, 1600 / 2 - 50, 900 / 2 - 20);
        seedEnterFont.draw(batch, seedEnter, 1600 / 2 - 50, 900 / 2 - 20 + 100 + 100 + 100);
        //batch.draw(validation, validationButton.x, validationButton.y);

        batch.end();

        /*if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(pos);
            if(validationButton.contains(pos.x, pos.y)) {
                //System.out.println("ok?");
                //System.out.println("ok");
                //Gdx.input.getTextInput(seedListener, "Entrez votre seed", "", "");
                if(yesno.getChecked() == yes || yesno.getChecked() == no) {
                    selection = yesno.getChecked();
                    if (selection == yes) {
                        System.out.println("ok"); // test
                    } else {
                        seed = seedField.getText();
                        System.out.println(seed); // test
                    }
                    gameManager.setGameScreen();
                }
            }
        }*/

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
        background.dispose();
        random.dispose();
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
        pos.set(i, i1, 0);
        camera.unproject(pos);
        System.out.println("ok?");
        if(validationButton.contains(pos.x, pos.y)) {
            System.out.println("ok");
            //Gdx.input.getTextInput(seedListener, "Entrez votre seed", "", "");
            selection = yesno.getChecked();
            if(selection == yes) {
                System.out.println("ok");
            }
            else {
                seed = seedField.getText();
                System.out.println(seed);
            }
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

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
