package ClassSelection;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Selection extends ApplicationAdapter {
    private Texture backgroundImage;
    private Texture spellSquareImage; // potentiellement inutile ( autant faire les carrés directement en amont sur l'image )
    private Texture rectangleImage; // for PV, PA, PM, and spells
    private Texture classSquare1Image;
    private Texture classSquare2Image;
    private Texture classSquare3Image;
    private Texture classSquare4Image;
    private Texture classSquare5Image;
    private Texture validationImage;
    private Music menuSound;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Rectangle spellSquare; // potentiellement inutile
    private Rectangle rectangle;
    private Rectangle classSquare1;
    private Rectangle classSquare2;
    private Rectangle classSquare3;
    private Rectangle classSquare4;
    private Rectangle classSquare5;
    private BitmapFont spellTextFont;
    private String spellText;

    @Override
    public void create() {
        // load the images
        backgroundImage = new Texture(Gdx.files.internal("droplet.png"));
        spellSquareImage = new Texture(Gdx.files.internal("bucket.png"));
        classSquare1Image = new Texture(Gdx.files.internal("droplet.png"));
        classSquare2Image = new Texture(Gdx.files.internal("droplet.png"));
        classSquare3Image = new Texture(Gdx.files.internal("droplet.png"));
        classSquare4Image = new Texture(Gdx.files.internal("droplet.png"));
        classSquare5Image = new Texture(Gdx.files.internal("droplet.png"));
        rectangleImage = new Texture(Gdx.files.internal("bucket.png"));
        validationImage = new Texture(Gdx.files.internal("bucket.png"));

        // load the background sound in the menu
        menuSound = Gdx.audio.newMusic(Gdx.files.internal("drop.wav"));

        // start the playback of the background music immediately
        menuSound.setLooping(true);
        menuSound.play();


        // fix the vision to not be impacted by too big pictures
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        batch = new SpriteBatch();

        spellText = "Cliquez sur un sort";
        spellTextFont = new BitmapFont();

        rectangle = new Rectangle();
        rectangle.x = 1920 / 2 - 1500 / 2; // the rectangle will be 1500 pixel long.
        rectangle.y = 0;
        rectangle.width = 1080 * 2 / 3;
        rectangle.height = 1500;

        classSquare1 = new Rectangle();
        classSquare1.x = 384 - 0 / 2; // 0 must be replaced by the height of the square
        classSquare1.y = 900;
        classSquare1.width = 0;
        classSquare1.height = 0;

        classSquare2 = new Rectangle();
        classSquare2.x = 384 * 2 - 0 / 2;
        classSquare2.y = 900;
        classSquare2.width = 0;
        classSquare2.height = 0;

        classSquare3 = new Rectangle();
        classSquare3.x = 384 * 3 - 0 / 2;
        classSquare3.y = 900;
        classSquare3.width = 0;
        classSquare3.height = 0;

        classSquare4 = new Rectangle();
        classSquare4.x = 384 * 4 - 0 / 2;
        classSquare4.y = 900;
        classSquare4.width = 0;
        classSquare4.height = 0;

        classSquare5 = new Rectangle();
        classSquare5.x = 384 * 5 - 0 / 2;
        classSquare5.y = 900;
        classSquare5.width = 0;
        classSquare5.height = 0;

    }

    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundImage, 0, 0);
        spellTextFont.draw(batch, spellText, rectangle.x, rectangle.y + 180);
        batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if((touchPos.x <= classSquare1.x && touchPos.x >= classSquare1.x - classSquare1.height) && (touchPos.y >= classSquare1.y && touchPos.y >= classSquare1.y - classSquare1.width )) {
                // En gros faire classText = "Pa : X            PM : X              PV : X" + System.currentTimeMillis(), séparer en 3 éventuellement
            }
            if((touchPos.x <= classSquare2.x && touchPos.x >= classSquare2.x - classSquare2.height) && (touchPos.y >= classSquare2.y && touchPos.y >= classSquare2.y - classSquare2.width )) {

            }
            if((touchPos.x <= classSquare3.x && touchPos.x >= classSquare3.x - classSquare3.height) && (touchPos.y >= classSquare3.y && touchPos.y >= classSquare3.y - classSquare3.width )) {

            }
            if((touchPos.x <= classSquare4.x && touchPos.x >= classSquare4.x - classSquare4.height) && (touchPos.y >= classSquare4.y && touchPos.y >= classSquare4.y - classSquare4.width )) {

            }
            if((touchPos.x <= classSquare5.x && touchPos.x >= classSquare5.x - classSquare5.height) && (touchPos.y >= classSquare5.y && touchPos.y >= classSquare5.y - classSquare5.width )) {

            }
        }

        // ajouter encore des if cette fois ci pour les sorts ( mais faut connaitre leur coord sur le rectangle prc je les ai pas def separement )
    }
}
