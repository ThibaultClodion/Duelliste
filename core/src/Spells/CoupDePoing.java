package Spells;

import com.badlogic.gdx.graphics.Texture;

public class CoupDePoing extends Spell {
    public CoupDePoing () {
        super (new SpellComponent[]{new Damage(50)}, 1, 1,0, new Texture("coupDePoing.png"),"Coup de poing infligeant 150 dégats");
    }
}