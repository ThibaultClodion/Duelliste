package Spells;

import com.badlogic.gdx.graphics.Texture;

public class SautDeLange extends Spell{
    public SautDeLange()
    {
        super(new SpellComponent[] {new Deplacement()}, 3, 5, 0, new Texture("classSquare1.JPG"));
    }
}
