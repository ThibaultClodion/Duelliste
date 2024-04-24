package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Morsure extends Spell
{
    public Morsure()
    {
        super(new SpellComponent[] {new Damage(150)}, 1, 2, new Texture("classSquare1.JPG"));
    }
}
