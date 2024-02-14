package Scenes;

import Game.GameManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class SelectionScreen implements Screen
{
    //Game Manager
    private GameManager gameManager;
    private Character characterSelected;

    //Batch
    private SpriteBatch batch;

    OrthographicCamera camera;
    public int classNumber;
    public int playerNumber;
    public Texture backgroundImage;
    public Texture spellSquareImage;
    public Texture rectangleImage; // for PV, PA, PM, and spells

    //Class Image
    public Texture[] classImages = new Texture[5];

    public Texture validationImage;
    public Music menuSound;

    //Spells
    public Rectangle[] spellsRectangles = new Rectangle[10];

    public Rectangle rectangle;

    //Class Rectangle
    public Rectangle[] classRectangles = new Rectangle[5];

    public BitmapFont spellTextFont;
    public Rectangle validation;
    public String spellText;

    public SelectionScreen(GameManager GM)
    {
        this.gameManager = GM;

        //Initialize the batch
        batch = new SpriteBatch();

        this.classNumber = 0;
        this.playerNumber = 1;

        //Initialize the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        //Load the Images
        backgroundImage = new Texture(Gdx.files.internal("background.jpeg"));
        spellSquareImage = new Texture(Gdx.files.internal("spellSquare.JPG"));
        LoadClassImages();

        //Create the spells and class Rectangle
        CreateSpellsRectangle();
        CreateClassRectangles();

        //Rectangle and Validation
        rectangleImage = new Texture(Gdx.files.internal("rectangle.JPG"));
        validationImage = new Texture(Gdx.files.internal("validation.JPG")); // 100x100
        rectangle = new Rectangle(1280 /2 - 1100/2, 0, 1000, 720* 2 / 3);
        validation = new Rectangle(1100 + 180 / 2 + 180 / 2 - 100, 50, 100, 100);

        // load the background sound in the menu
        menuSound = Gdx.audio.newMusic(Gdx.files.internal("pkm.mp3"));

        // start the playback of the background music immediately

        spellText = "Cliquez sur un sort";
        spellTextFont = new BitmapFont();

    }

    private void CreateClassRectangles()
    {
        // classSquare seront de 200x200 dans un rectangle de 1280x240 espacés de 40 pixels
        for(int i = 0; i < classRectangles.length; i++)
        {
            Rectangle newClassRectangle = new Rectangle(20 + 260 * i,500,200,200);
            classRectangles[i] = newClassRectangle;
        }
    }

    private void LoadClassImages()
    {
        for(int i = 0; i < classImages.length; i++)
        {
            classImages[i] = new Texture(Gdx.files.internal("classSquare" + i + ".JPG"));
        }
    }

    private void CreateSpellsRectangle()
    {
        // Les carrés seront espacés de 10 pixels et compris dans un rectangle de 160 de hauteur et 1100 de long
        for(int i = 0; i < spellsRectangles.length; i++)
        {
            Rectangle newSpellRectangle = new Rectangle(180 / 2 + 5 + 110 * i,160+30,100,100);
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

        //Begin the batch
        batch.begin();

        //Draw the backgrounds
        batch.draw(backgroundImage, 0, 0);
        batch.draw(rectangleImage, rectangle.x, rectangle.y);
        batch.draw(validationImage, validation.x, validation.y);

        //Draw the spells rectangle
        for(int i = 0; i < spellsRectangles.length; i++)
        {
            batch.draw(spellSquareImage, spellsRectangles[i].x, spellsRectangles[i].y);
        }

        //Draw the class rectangle
        for(int i = 0; i < classRectangles.length; i++)
        {
            batch.draw(classImages[i], classRectangles[i].x, classRectangles[i].y);
        }

        if (this.classNumber == 1)
        {
            //this.characterSelected = new Aleator();
            //int nbSort = this.characterSelected.nbSort;
            //for(int i = 0; i < nbSort; i++) {
            //game.batch.draw(classSpellImage, 180/2 + 5 + 100 * i + 10 * i, 160 + 30)
            //}
        }


        if(Gdx.input.isTouched())
        {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            for (int i = 0; i < classRectangles.length; i++)
            {
                if ((touchPos.x <= classRectangles[i].x - classRectangles[i].width && touchPos.x >= classRectangles[i].x)
                        && (touchPos.y <= classRectangles[i].y + classRectangles[i].height && touchPos.y >= classRectangles[i].y)) {
                    // En gros faire classText = "Pa : X            PM : X              PV : X" + System.currentTimeMillis(), séparer en 3 éventuellement
                    this.classNumber = i;
                }
            }

            /*if (touchPos.x >= validation.x && touchPos.x <= validation.x + validation.width && touchPos.y >= validation.y && touchPos.y <= validation.y + validation.height)
            {
                this.classNumber = 0;
                if (playerNumber == 1)
                {
                    this.gameManager.player1.character = this.characterSelected;
                    this.playerNumber = 2;
                }
                else 
            }*/

            // Il faut rajouter un si on clic sur validation alors on définit player1.class et on remet classNumber à 0, on incrémente countPlayer
            // puis quand countPlayer vaut 2 : on switch sur la map de combat
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
    public void dispose()
    {
        spellSquareImage.dispose();

        //Dispose all class images
        for(int i = 0; i < classImages.length; i++)
        {
            classImages[i].dispose();
        }

        rectangleImage.dispose();
        validationImage.dispose();
        backgroundImage.dispose();
        menuSound.dispose();
    }
}
