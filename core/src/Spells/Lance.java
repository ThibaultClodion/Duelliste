package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Lance extends Spell{
    public Lance(){super(new SpellComponent[] {new Damage(200)}, 2, 3, new Texture("classSquare1.JPG"));}
}
