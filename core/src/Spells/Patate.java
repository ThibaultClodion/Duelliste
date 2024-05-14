package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Patate extends Spell{
    public Patate() { super(new SpellComponent[] {new Damage(320)}, 2, 1, 2, new Texture("patate.png"),"Patate est une attaque infligeant 320 dégats"); }
}
