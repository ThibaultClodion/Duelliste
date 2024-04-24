package Spells;

import com.badlogic.gdx.graphics.Texture;

public class DoubleDague extends Spell{
    public DoubleDague(){super(new SpellComponent[] {new Damage(300)}, 1, 3, new Texture("classSquare1.JPG"));}
}
