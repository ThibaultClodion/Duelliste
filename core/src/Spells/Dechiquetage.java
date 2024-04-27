package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Dechiquetage extends Spell{
    public Dechiquetage()
    {
            super(new SpellComponent[] {new Damage(400)}, 4, 1,4, new Texture("Dechiquetage.png"));
    }
}
