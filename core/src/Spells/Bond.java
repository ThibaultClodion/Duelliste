package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Bond extends Spell{
    public Bond(){super(new SpellComponent[] {new Deplacement()},4,5,2,new Texture("classSquare1.JPG"));}
}
