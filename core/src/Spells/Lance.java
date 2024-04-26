package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Lance extends Spell{
    public Lance(){super(new SpellComponent[] {new Damage(200)}, 2, 3,0, new Texture("lance.png"));}
}
