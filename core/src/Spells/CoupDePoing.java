package Spells;

import com.badlogic.gdx.graphics.Texture;

public class CoupDePoing extends Spell {
    public CoupDePoing () {
        super (new SpellComponent[]{new Damage(50)}, 1, 1, new Texture("classSquare1.JPG"));
    }
}