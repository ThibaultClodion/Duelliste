package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Coup_d_epee extends Spell{
    public Coup_d_epee()
    {
        super(new SpellComponent[] {new Damage(200)}, 3, 1,0, new Texture("coupDepee.png"));
    }
}
