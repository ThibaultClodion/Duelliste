package Game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SpellButton {
    private Texture iconTexture;
    private Rectangle rectangle;

    public SpellButton(Texture iconTexture, float x, float y, float width, float height) {
        this.iconTexture = iconTexture;
        rectangle = new Rectangle(x, y, width, height);
    }

    public void render(SpriteBatch batch) {
        batch.draw(iconTexture, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public boolean isClicked(float x, float y) {
        return rectangle.contains(x, y);
    }

    public Texture getTexture() { return iconTexture;}
}
