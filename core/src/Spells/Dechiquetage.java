package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Dechiquetage extends Spell{
    public Dechiquetage()
    {
            super(new SpellComponent[] {new Damage(400)}, 4, 1,0, new Texture("classSquare1.JPG"));
    }
}
