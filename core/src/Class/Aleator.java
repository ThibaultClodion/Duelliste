package Class;

import Spells.*;
import com.badlogic.gdx.graphics.Texture;

public class Aleator extends Character
{
    public Aleator()
    {
        super(1500f, 3, 6, new Texture("aleator.png"), new Texture("aleatorRectangle.JPG"), new Spell[] {new Morsure(),new VolDesorganise(),new Des(),new Carte(),new SoinDouteux(),new RouletteRusse(),new NouvelleChance()});

    }

}
