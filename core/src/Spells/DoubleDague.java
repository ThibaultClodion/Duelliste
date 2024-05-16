package Spells;

import com.badlogic.gdx.graphics.Texture;

public class DoubleDague extends Spell{
    public DoubleDague(){super(new SpellComponent[] {new Damage(4000)}, 5, 3, 4, new Texture("doubleDague.png"),"Double Dague est une attaque qui inflige 400 dégats");}
}
