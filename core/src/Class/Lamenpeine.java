package Class;

import com.badlogic.gdx.graphics.Texture;
import Spells.Spell;
import Spells.Morsure;

public class Lamenpeine extends Character
{

    public Lamenpeine()
    {
        super(1400f, 2, 5, new Texture("goblin64x64.png"), new Spell[] {new Morsure()});
    }
}
