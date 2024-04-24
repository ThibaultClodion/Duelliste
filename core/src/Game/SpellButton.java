package Game;

import Spells.Spell;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SpellButton {
    public Rectangle rectangle;
    private Spell spell;

    public SpellButton(float x, float y, float width, float height, Spell spell)
    {
        rectangle = new Rectangle(x, y, width, height);
        this.spell = spell;
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(new Texture(spell.image.toString()), rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public boolean isClicked(float x, float y) {
        return rectangle.contains(x, y);
    }

    public Texture getTexture() { return spell.image;}
    public Spell getSpell() {return spell;}
}
