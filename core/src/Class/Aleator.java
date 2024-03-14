package Class;

import Spells.Des;
import Spells.Spell;
import com.badlogic.gdx.graphics.Texture;

public class Aleator extends Character
{
    public Aleator()
    {
        super(1500f, 3, 6, new Texture("aleator.png"), new Spell[] {new Des(), new Des(),
        new Des(), new Des(),new Des(),new Des(),new Des(),new Des(),new Des(),new Des()});

    }

}
