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
    private final Vector3 pos = new Vector3();
    //Game Manager
    private final GameManager gameManager;
    private final Character[] characters = new Character[] {new Aleator(), new ElGoblino(), new Lamenpeine(), new Creationiste(), new Rageux()};

    //Batch
    private final SpriteBatch batch  = new SpriteBatch();

    private final OrthographicCamera camera =  new OrthographicCamera();
    private int classNumber = 5;
    private int playerNumber = 1;
    private final Texture backgroundImage = new Texture(Gdx.files.internal("backgroundVF.JPG"));
    private final Texture spellSquareImage = new Texture(Gdx.files.internal("spellSquare.JPG"));
    private final Texture rectangleImage = new Texture(Gdx.files.internal("SpellInformationBackground.png"));

    private final Texture validationImage = new Texture(Gdx.files.internal("validation.JPG"));
    private final Texture musicImage = new Texture(Gdx.files.internal("musique.jpg"));
    private final Music menuSound;

    private final Rectangle[] spellsRectangles = new Rectangle[10];
    private final Rectangle rectangle = new Rectangle((float) 1600 /2 - (float) 1200 /2, 50, 1200, 600);
    private final Rectangle[] classRectangles = new Rectangle[5];

    private final BitmapFont spellTextFont = new BitmapFont();
    private final BitmapFont classTextFont = new BitmapFont();
    private final Rectangle validation = new Rectangle(1450, 50, 100, 100);
    private final Rectangle music = new Rectangle(50, 50, 100, 100);
    private String spellText = "Cliquez sur un sort";
    private final Rectangle spellTextRectangle = new Rectangle((float) 1600 / 2 - (float) 1200 / 2 + 50, 250, 1200, 200 );
    private String classText = "Choisissez une classe";
    private final Rectangle classTextRectangle = new Rectangle((float) 1600 / 2 - 550, 540, 1200, 200 );


    public SelectionScreen(GameManager GM)
    {
        this.gameManager = GM;
        Gdx.input.setInputProcessor(this);


        //Initialize the camera and batch
        camera.setToOrtho(false, 1600, 900);
        batch.setProjectionMatrix(camera.combined);

        //Create the spells and class Rectangle
        CreateSpellsRectangle();
        CreateClassRectangles();

        //Menu Sound Management
        menuSound = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusique.mp3"));
        menuSound.setLooping(true);
        //menuSound.play();
    }

    private void CreateClassRectangles()
    {
        for(int i = 0; i < classRectangles.length; i++)
        {
            Rectangle newClassRectangle = new Rectangle(100 + 200 * i + 100 * i,650,200,200);
            classRectangles[i] = newClassRectangle;
        }
    }

    private void CreateSpellsRectangle()
    {
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

        //Begin the batch
        batch.begin();
        DisplayBackgroundAndButtons();
        DisplaySpellsAndClass();
        batch.end();

    }

    private void DisplayBackgroundAndButtons()
    {
        batch.draw(backgroundImage, 0, 0);
        batch.draw(rectangleImage, rectangle.x, rectangle.y);
        batch.draw(validationImage, validation.x, validation.y);
        batch.draw(musicImage, music.x, music.y, music.width, music.height);
    }

    private void DisplaySpellsAndClass()
    {
        spellTextFont.getData().setScale(2f);
        classTextFont.getData().setScale(2f);
        classTextFont.draw(batch, classText, classTextRectangle.x, classTextRectangle.y);

        //Draw the class rectangle
        for(int i = 0; i < classRectangles.length; i++)
        {
            batch.draw(characters[i].GetRectangleImage(), classRectangles[i].x, classRectangles[i].y);
        }

        //Draw the spells
        if(classNumber <= 4 && classNumber >= 0)
        {
            spellTextFont.draw(batch, spellText, spellTextRectangle.x, spellTextRectangle.y);
            for (int j = 0; j < this.characters[classNumber].getNbSpell(); j++) {
                batch.draw(new Texture(this.characters[classNumber].GetSpell(j).image.toString()), spellsRectangles[j].x, spellsRectangles[j].y, 100, 100);
            }
        }
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

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        pos.set(screenX, screenY, 0);
        camera.unproject(pos);

        if(button == Input.Buttons.LEFT)
        {
            //Check if music button is toogle
            if(music.contains(pos.x, pos.y))
            {
                if(menuSound.isPlaying())
                {
                    menuSound.pause();
                }
                else
                {
                    menuSound.play();
                }
            }

            // Cheking if a classRectangle has been chosen
            for (int i = 0; i < classRectangles.length; i++)
            {
                if (classRectangles[i].contains(pos.x, pos.y))
                {
                    this.classNumber = i;
                    spellText = "Cliquez sur un sort";
                    classText = "Point de vie :  " + characters[i].GetHp() + "          Point d'action :  " + characters[i].GetPa() + "           Point de déplacement :  " + characters[i].GetPm();
                }
            }

            //Verify if a class has been choosen
            if(classNumber <= 4 && classNumber >= 0)
            {
                //Check if a spell buttons is toogle
                for (int j = 0; j < characters[classNumber].getNbSpell(); j++)
                {
                    if(spellsRectangles[j].contains(pos.x, pos.y))
                    {
                        spellText = "Point d'action = " + characters[classNumber].GetSpell(j).getPa() + "         Portée = " + characters[classNumber].GetSpell(j).getRange() + "            Temps de rechargement =" + characters[classNumber].GetSpell(j).getCooldown() + "\n\n" + characters[classNumber].GetSpell(j).getDescription();
                        Gdx.graphics.requestRendering();
                    }
                }

                // Checking if validation button is clicked
                if(validation.contains(pos.x, pos.y))
                {
                    if (this.playerNumber == 1)
                    {
                        gameManager.setPlayer1(new PlayerController(characters[classNumber], new int[] {0, 0}));
                        playerNumber = playerNumber + 1;
                        classNumber = 5;
                        classText = "Choisissez une classe";
                    }
                    else
                    {
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
        for (Character character : characters)
        {
            character.GetRectangleImage().dispose();
        }

        rectangleImage.dispose();
        validationImage.dispose();
        backgroundImage.dispose();
        menuSound.dispose();
        musicImage.dispose();
    }
}
