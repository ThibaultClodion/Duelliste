package ClassSelection;

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

public class Selection implements Screen {
    public GameManager game;
    OrthographicCamera camera;
    public int classNumber;
    public Texture backgroundImage;
    public Texture spellSquareImage;
    public Texture rectangleImage; // for PV, PA, PM, and spells
    public Texture classSquare1Image;
    public Texture classSquare2Image;
    public Texture classSquare3Image;
    public Texture classSquare4Image;
    public Texture classSquare5Image;
    public Texture validationImage;
    public Music menuSound;
    public Rectangle spellSquare1;
    public Rectangle spellSquare2;
    public Rectangle spellSquare3;
    public Rectangle spellSquare4;
    public Rectangle spellSquare5;
    public Rectangle spellSquare6;
    public Rectangle spellSquare7;
    public Rectangle spellSquare8;
    public Rectangle spellSquare9;
    public Rectangle spellSquare10;
    public Rectangle rectangle;
    public Rectangle classSquare1;
    public Rectangle classSquare2;
    public Rectangle classSquare3;
    public Rectangle classSquare4;
    public Rectangle classSquare5;
    public BitmapFont spellTextFont;
    public Rectangle validation;
    public String spellText;

    public Selection(final GameManager game)
    {

        this.classNumber = 0;

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // load the images
        backgroundImage = new Texture(Gdx.files.internal("background.jpeg"));
        spellSquareImage = new Texture(Gdx.files.internal("spellSquare.JPG"));
        classSquare1Image = new Texture(Gdx.files.internal("classSquare.JPG"));
        classSquare2Image = new Texture(Gdx.files.internal("classSquare.JPG"));
        classSquare3Image = new Texture(Gdx.files.internal("classSquare.JPG"));
        classSquare4Image = new Texture(Gdx.files.internal("classSquare.JPG"));
        classSquare5Image = new Texture(Gdx.files.internal("classSquare.JPG"));
        rectangleImage = new Texture(Gdx.files.internal("rectangle.JPG"));
        validationImage = new Texture(Gdx.files.internal("validation.JPG")); // 100x100

        // load the background sound in the menu
        menuSound = Gdx.audio.newMusic(Gdx.files.internal("pkm.mp3"));

        // start the playback of the background music immediately

        spellText = "Cliquez sur un sort";
        spellTextFont = new BitmapFont();

        rectangle = new Rectangle();
        rectangle.x = 1280 / 2 - 1100 / 2; // the rectangle will be 1000 pixel long.
        rectangle.y = 0;
        rectangle.width = 1000;
        rectangle.height = 720 * 2 / 3;

        // classSquare seront de 200x200 dans un rectangle de 1280x240 espacés de 40 pixels

        classSquare1 = new Rectangle();
        classSquare1.x = 20 + 100 - 200 / 2; // 0 must be replaced by the height of the square
        classSquare1.y = 480 + 20;
        classSquare1.width = 200;
        classSquare1.height = 200;

        classSquare2 = new Rectangle();
        classSquare2.x = 20 + 100 + 200 + 60 - 200 / 2;
        classSquare2.y = 480 + 20;
        classSquare2.width = 200;
        classSquare2.height = 200;

        classSquare3 = new Rectangle();
        classSquare3.x = 20 + 100 + 200*2 + 60*2 - 200 / 2;
        classSquare3.y = 480 + 20;
        classSquare3.width = 200;
        classSquare3.height = 200;

        classSquare4 = new Rectangle();
        classSquare4.x = 20 + 100 + 200*3 + 60*3 - 200 / 2;
        classSquare4.y = 480 + 20;
        classSquare4.width = 200;
        classSquare4.height = 200;

        classSquare5 = new Rectangle();
        classSquare5.x = 20 + 100 + 200*4 + 60*4 - 200 / 2; // début + les autres carrés + les espacements
        classSquare5.y = 480 + 20;
        classSquare5.width = 200;
        classSquare5.height = 200;

        // Les carrés seront espacés de 10 pixels et compris dans un rectangle de 160 de hauteur et 1100 de long

        spellSquare1 = new Rectangle(); // carré de 100x100
        spellSquare1.x = 180 / 2 + 5;
        spellSquare1.y = 160 + 30;
        spellSquare1.width = 100;
        spellSquare1.height = 100;

        spellSquare2 = new Rectangle(); // carré de 100x100
        spellSquare2.x = 180 / 2 + 5 + 100 + 10; // same thing as classSquare
        spellSquare2.y = 160 + 30;
        spellSquare2.width = 100;
        spellSquare2.height = 100;

        spellSquare3 = new Rectangle(); // carré de 100x100
        spellSquare3.x = 180 / 2 + 5 + 100*2 + 10*2;
        spellSquare3.y = 160 + 30;
        spellSquare3.width = 100;
        spellSquare3.height = 100;

        spellSquare4 = new Rectangle(); // carré de 100x100
        spellSquare4.x = 180 / 2 + 5 + 100*3 + 10*3;
        spellSquare4.y = 160 + 30;
        spellSquare4.width = 100;
        spellSquare4.height = 100;

        spellSquare5 = new Rectangle(); // carré de 100x100
        spellSquare5.x = 180 / 2 + 5 + 100*4 + 10*4;
        spellSquare5.y = 160 + 30;
        spellSquare5.width = 100;
        spellSquare5.height = 100;

        spellSquare6 = new Rectangle(); // carré de 100x100
        spellSquare6.x = 180 / 2 + 5 + 100*5 + 10*5;
        spellSquare6.y = 160 + 30;
        spellSquare6.width = 100;
        spellSquare6.height = 100;

        spellSquare7 = new Rectangle(); // carré de 100x100
        spellSquare7.x = 180 / 2 + 5 + 100*6 + 10*6;
        spellSquare7.y = 160 + 30;
        spellSquare7.width = 100;
        spellSquare7.height = 100;

        spellSquare8 = new Rectangle(); // carré de 100x100
        spellSquare8.x = 180 / 2 + 5 + 100*7 + 10*7;
        spellSquare8.y = 160 + 30;
        spellSquare8.width = 100;
        spellSquare8.height = 100;

        spellSquare9 = new Rectangle(); // carré de 100x100
        spellSquare9.x = 180 / 2 + 5 + 100*8 + 10*8;
        spellSquare9.y = 160 + 30;
        spellSquare9.width = 100;
        spellSquare9.height = 100;

        spellSquare10 = new Rectangle(); // carré de 100x100
        spellSquare10.x = 180 / 2 + 5 + 100*9 + 10*9;
        spellSquare10.y = 160 + 30;
        spellSquare10.width = 100;
        spellSquare10.height = 100;

        validation = new Rectangle();
        validation.x = 1100 + 180 / 2 + 180 / 2 - 100;
        validation.y = 50; // random
        validation.width = 100;
        validation.height = 100;

    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();

        //Initialize the batch
        SpriteBatch batch = new SpriteBatch();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        /*
		game.batch.draw(backgroundImage, 0, 0);
		game.batch.draw(rectangleImage, rectangle.x, rectangle.y);
		game.batch.draw(classSquare1Image, classSquare1.x, classSquare1.y);
		game.batch.draw(classSquare1Image, classSquare2.x, classSquare2.y);
		game.batch.draw(classSquare1Image, classSquare3.x, classSquare3.y);
		game.batch.draw(classSquare1Image, classSquare4.x, classSquare4.y);
		game.batch.draw(classSquare1Image, classSquare5.x, classSquare5.y);
		game.batch.draw(validationImage, validation.x, validation.y);
		*/

        if (this.classNumber == 1) {
            //Class1() class = new Class1()
            //int nbSort = class.nbSort
            //for(int i = 0; i < nbSort; i++) {
                //game.batch.draw(classSpellImage, 180/2 + 5 + 100 * i + 10 * i, 160 + 30)
            //}
        }

        batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if((touchPos.x <= classSquare1.x - classSquare1.width && touchPos.x >= classSquare1.x) && (touchPos.y <= classSquare1.y + classSquare1.height && touchPos.y >= classSquare1.y)) {
                // En gros faire classText = "Pa : X            PM : X              PV : X" + System.currentTimeMillis(), séparer en 3 éventuellement
                this.classNumber = 1;
            }
            if((touchPos.x <= classSquare2.x + classSquare2.width && touchPos.x >= classSquare2.x) && (touchPos.y <= classSquare2.y + classSquare2.height && touchPos.y >= classSquare2.y)) {
                this.classNumber = 2;

            }
            if((touchPos.x <= classSquare3.x + classSquare3.width && touchPos.x >= classSquare3.x) && (touchPos.y <= classSquare3.y + classSquare3.height && touchPos.y >= classSquare3.y)) {
                this.classNumber = 3;

            }
            if((touchPos.x <= classSquare4.x + classSquare4.width && touchPos.x >= classSquare4.x) && (touchPos.y <= classSquare4.y + classSquare4.height && touchPos.y >= classSquare4.y)) {
                this.classNumber = 4;

            }
            if((touchPos.x <= classSquare5.x + classSquare5.width && touchPos.x >= classSquare5.x) && (touchPos.y <= classSquare5.y + classSquare5.height && touchPos.y >= classSquare5.y)) {
                this.classNumber = 5;

            }

            // Il faut rajouter un si on clic sur validation alors on définit player1.class et on remet classNumber à 0, on incrémente countPlayer
            // puis quand countPlayer vaut 2 : on switch sur la map de combat
        }

    }

    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}
