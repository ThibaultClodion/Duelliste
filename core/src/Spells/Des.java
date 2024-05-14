package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Des extends Spell
{
    public Des() {super(new SpellComponent[] {new Damage((int) (Math.random() * 6 + 1) * 50)}, 4, 2,2, new Texture("des.png"),"Dés est une attaque qui inflige entre 300 et 350 dégats");}
}
