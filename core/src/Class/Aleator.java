package Class;

import Spells.Spell;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Aleator extends Character
{
    public Aleator()
    {
        super(1500f, 3, 6, new Texture("aleator.png"), new Spell[10]);
    }

}
