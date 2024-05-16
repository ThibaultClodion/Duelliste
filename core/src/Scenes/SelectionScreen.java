package Scenes;

import Class.Aleator;
import Class.ElGoblino;
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

public class SelectionScreen implements Screen, InputProcessor
{
    Vector3 pos;
    private boolean clicEffectue;
    private int nbClicEffectue;
    //Game Manager
    private GameManager gameManager;
    private Character[] characters;

    //Batch
    private SpriteBatch batch;

    OrthographicCamera camera;
    public int classNumber;
    public int spellNumber;
    public int playerNumber;
    public Texture backgroundImage;
    public Texture spellSquareImage;
    public Texture rectangleImage; // for PV, PA, PM, and spells

    public Texture validationImage;
    private Texture musicImage;
    public Music menuSound;

    //Spells
    public Rectangle[] spellsRectangles = new Rectangle[10];

    public Rectangle rectangle;

    //Class Rectangle
    public Rectangle[] classRectangles = new Rectangle[5];

    public BitmapFont spellTextFont;
    public Rectangle validation;
    private Rectangle music;
    public String spellText;
    public Rectangle spellTextRectangle;
    private String classText;
    private BitmapFont classTextFont;
    private Rectangle classTextRectangle;

    public SelectionScreen(GameManager GM)
    {
        this.gameManager = GM;
        this.characters = new Character[] {new Aleator(), new ElGoblino(), new Lamenpeine(), new Creationiste(), new Rageux()};

        pos = new Vector3();
        clicEffectue = false;
        nbClicEffectue = 0;
        Gdx.input.setInputProcessor(this);


        //Initialize the batch
        batch = new SpriteBatch();

        this.classNumber = 5;
        this.playerNumber = 1;
        this.spellNumber = 10;

        //Initialize the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900);

        //Load the Images
        backgroundImage = new Texture(Gdx.files.internal("backgroundVF.JPG"));
        spellSquareImage = new Texture(Gdx.files.internal("spellSquare.JPG"));

        //Create the spells and class Rectangle
        CreateSpellsRectangle();
        CreateClassRectangles();

        //Rectangle and Validation
        rectangleImage = new Texture(Gdx.files.internal("SpellInformationBackground.png"));
        validationImage = new Texture(Gdx.files.internal("validation.JPG")); // 100x100
        musicImage = new Texture(Gdx.files.internal("musique.jpg"));
        rectangle = new Rectangle(1600 /2 - 1200/2, 50, 1200, 600);
        validation = new Rectangle(1450, 50, 100, 100);
        music = new Rectangle(50, 50, 100, 100);
        spellTextRectangle = new Rectangle(1600 / 2 - 1200 / 2 + 50, 250, 1200, 200 );

        // load the background sound in the menu
        menuSound = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusique.mp3"));
        menuSound.setLooping(true);
        menuSound.play();

        // start the playback of the background music immediately

        // Create texts

        spellText = "Cliquez sur un sort";
        spellTextFont = new BitmapFont();
        classText = "Choisissez une classe";
        classTextFont = new BitmapFont();
        classTextRectangle = new Rectangle(1600 / 2 - 550, 540, 1200, 200 );
    }

    private void CreateClassRectangles()
    {
        // classSquare seront de 200x200 dans un rectangle de 1280x240 espacés de 40 pixels
        for(int i = 0; i < classRectangles.length; i++)
        {
            Rectangle newClassRectangle = new Rectangle(100 + 200 * i + 100 * i,650,200,200);
            classRectangles[i] = newClassRectangle;
        }
    }

    private void CreateSpellsRectangle()
    {
        // Les carrés seront espacés de 10 pixels et compris dans un rectangle de 160 de hauteur et 1100 de long
        for(int i = 0; i < spellsRectangles.length; i++)
        {
            Rectangle newSpellRectangle = new Rectangle(250 + 125 * i,325,100,100);
            spellsRectangles[i] = newSpellRectangle;
        }
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //Begin the batch
        batch.begin();

        //Draw the backgrounds
        batch.draw(backgroundImage, 0, 0);
        batch.draw(rectangleImage, rectangle.x, rectangle.y);
        batch.draw(validationImage, validation.x, validation.y);
        batch.draw(musicImage, music.x, music.y, music.width, music.height);

        //Draw the class rectangle
        for(int i = 0; i < classRectangles.length; i++)
        {
            batch.draw(characters[i].GetRectangleImage(), classRectangles[i].x, classRectangles[i].y);
            //batch.draw(characters[i].getImage(), classRectangles[i].x, classRectangles[i].y);
        }

        spellTextFont.getData().setScale(2f);
        classTextFont.getData().setScale(2f);
        classTextFont.draw(batch, classText, classTextRectangle.x, classTextRectangle.y);

        // Display spellSquare & spell texts
        if(classNumber <= 4 && classNumber >= 0) {
            spellTextFont.draw(batch, spellText, spellTextRectangle.x, spellTextRectangle.y);
            for (int j = 0; j < this.characters[classNumber].getNbSpell(); j++) {
                batch.draw(new Texture(this.characters[classNumber].GetSpell(j).image.toString()), spellsRectangles[j].x, spellsRectangles[j].y, 100, 100);
            }
        }

        batch.end();

    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pos.set(screenX, screenY, 0);
        camera.unproject(pos);
        // Check if we have to unproject the camera ( a simple game? ) Yes, for having good coords i think

        if(button == Input.Buttons.LEFT) {
            if(music.contains(pos.x, pos.y)) {
                if(menuSound.isPlaying()) {
                    menuSound.pause();
                }
                else {
                    menuSound.play();
                }
            }

            for (int i = 0; i < classRectangles.length; i++) {
                // Cheking if a classRectangle has been chosen
                if (classRectangles[i].contains(pos.x, pos.y))
                {
                    this.classNumber = i;
                    spellText = "Cliquez sur un sort";
                    classText = "HP :  " + characters[i].GetHp() + "          PA :  " + characters[i].GetPa() + "           PM :  " + characters[i].GetPm();
                }
            }
            if(classNumber <= 4 && classNumber >= 0) {
                for (int j = 0; j < characters[classNumber].getNbSpell(); j++) {
                    if(spellsRectangles[j].contains(pos.x, pos.y)) {
                        spellNumber = j;
                        spellText = "PA = " + characters[classNumber].GetSpell(spellNumber).getPa() + "         Range = " + characters[classNumber].GetSpell(spellNumber).getRange() + "            Cooldown =" + characters[classNumber].GetSpell(spellNumber).getCooldown() + "\n\n" + characters[classNumber].GetSpell(spellNumber).getDescription();
                        Gdx.graphics.requestRendering();
                    }
                }
                // Now looking if validation button is clicked
                if(validation.contains(pos.x, pos.y)/*pos.x <= validation.x + validation.width && pos.x >= validation.x && 900 - pos.y <= validation.y + validation.height && 900 - pos.y >= validation.y*/) {
                    // Check whose player is validating his choice
                    if (this.playerNumber == 1) {
                        gameManager.setPlayer1(new PlayerController(characters[classNumber], new int[] {0, 0}));
                        playerNumber = playerNumber + 1;
                        classNumber = 5;
                        classText = "Choisissez une classe";
                    }
                    else {
                        gameManager.setPlayer2(new PlayerController(characters[classNumber], new int[] {0, 0}));
                        classNumber = 5;
                        // Eventually reset everything here by creating a function reset and call it
                        gameManager.setSeedScreen();
                    }
                }
            }

        }
        return true;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void dispose()
    {
        spellSquareImage.dispose();
        spellTextFont.dispose();

        //Dispose all class images
        for(int i = 0; i < characters.length; i++)
        {
            characters[i].GetRectangleImage().dispose();
        }

        rectangleImage.dispose();
        validationImage.dispose();
        backgroundImage.dispose();
        menuSound.dispose();
        musicImage.dispose();
    }
}
