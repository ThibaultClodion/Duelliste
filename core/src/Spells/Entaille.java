package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Entaille extends Spell{
    public Entaille(){super(new SpellComponent[] {new Damage(300)},2,1,1,new Texture("entaille.png"),"Entaille est une attaque infligeant 300 dégats");}
}
