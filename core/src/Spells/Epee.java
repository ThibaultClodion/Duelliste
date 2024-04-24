package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Epee extends Spell{
    public Epee() { super(new SpellComponent[] {new Damage(200)}, 2, 2, new Texture("classSquare1.JPG")); }
}
