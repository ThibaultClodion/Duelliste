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

public class Selection {
    private Texture backgroundImage;
    private Texture spellSquareImage; // potentiellement inutile ( autant faire les carrés directement en amont sur l'image )
    private Texture rectangleImage; // for PV, PA, PM, and spells
    private Texture classSquare1Image;
    private Texture classSquare2Image;
    private Texture classSquare3Image;
    private Texture classSquare4Image;
    private Texture classSquare5Image;
    private Texture validationImage;
    public Music menuSound;
    public Rectangle spellSquare; // potentiellement inutile
    public Rectangle rectangle;
    public Rectangle classSquare1;
    public Rectangle classSquare2;
    public Rectangle classSquare3;
    public Rectangle classSquare4;
    public Rectangle classSquare5;
    public BitmapFont spellTextFont;
    public Rectangle validation;
    public String spellText;

    public Selection() {
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
}
