package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Bond extends Spell{
    public Bond(){super(new SpellComponent[] {new Deplacement()},4,5,2,new Texture("bond.png"),"Le bond est un sort permettant de se déplacer de 5 cases");}
}
