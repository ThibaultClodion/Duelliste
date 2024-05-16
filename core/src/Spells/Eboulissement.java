package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Eboulissement extends Spell{
    public Eboulissement(){super(new SpellComponent[] {new Damage(400)},3,6,4,new Texture("eblouissement.png"),"Eboulissement est une attaque infligeant 400 dégats");}
}
