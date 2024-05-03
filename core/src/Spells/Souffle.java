package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Souffle extends Spell{
    public Souffle(){super(new SpellComponent[] {new Damage(150)},2,3,1,new Texture("classSquare1.JPG"));}
}
