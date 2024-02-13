package Game;

import ClassSelection.Selection;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gamer extends Game {
    public SpriteBatch batch;

    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new Selection(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
    }

}


