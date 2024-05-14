package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Morsure extends Spell
{
    public Morsure()
    {
        super(new SpellComponent[] {new Damage(150)}, 1, 2, 2, new Texture("morsure.png"),"Morsure est une attaque infligeant 150 dégats");
    }
}
