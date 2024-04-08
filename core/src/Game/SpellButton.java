package Game;

import Spells.Spell;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SpellButton {
    private Texture iconTexture;
    private Rectangle rectangle;
    private Spell spell;

    public SpellButton(Texture iconTexture, float x, float y, float width, float height, Spell spell) {
        this.iconTexture = iconTexture;
        rectangle = new Rectangle(x, y, width, height);
        this.spell = spell;
    }

    public void render(SpriteBatch batch) {
        batch.draw(iconTexture, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public boolean isClicked(float x, float y) {
        return rectangle.contains(x, y);
    }

    public Texture getTexture() { return iconTexture;}
    public Spell getSpell() {return spell;}
}
