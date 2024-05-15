package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Explosion extends Spell{
    public Explosion(){super(new SpellComponent[] {new Damage(200)},2,2,2,new Texture("explosion.png"),"Explosion est une attaque qui inflige 200 dégats");}
}
