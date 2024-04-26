package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Flash extends Spell{
    public Flash(){super(new SpellComponent[] {new Deplacement()},2,4,0,new Texture("classSquare1.JPG"));}
}
