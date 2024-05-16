package Spells;

import com.badlogic.gdx.graphics.Texture;

public class CoupDePoingGros extends Spell {
    public CoupDePoingGros() {
        super (new SpellComponent[]{new Damage(200)}, 2, 1,0, new Texture("coupDeGrosPoing.png"),"Gros coup de poing qui inflige 200 dégats");
    }
}
