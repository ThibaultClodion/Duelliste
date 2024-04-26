package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Explosion extends Spell{
    public Explosion(){super(new SpellComponent[] {new Damage(100)},2,2,0,new Texture("classSquare1.JPG"));}
}
