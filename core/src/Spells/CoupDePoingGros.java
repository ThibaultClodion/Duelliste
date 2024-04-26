package Spells;

import com.badlogic.gdx.graphics.Texture;

public class CoupDePoingGros extends Spell {
    public CoupDePoingGros() {
        super (new SpellComponent[]{new Damage(150)}, 2, 1,0, new Texture("classSquare1.JPG"));
    }
}
