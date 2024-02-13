package Class;

import Spells.Spell;
import com.badlogic.gdx.graphics.Texture;

public class Aleator extends Character
{

    public Aleator()
    {
        super(1500f, 3, 6, new Texture("hole.png"), new Spell[10]);
    }

    @Override
    public int getPm()
    {
        return 3;
    }

    @Override
    public int getPa() {
        return 6;
    }
}
