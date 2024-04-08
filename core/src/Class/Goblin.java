package Class;

import Spells.Spell;
import Spells.Morsure;
import Spells.VolDesorganise;
import com.badlogic.gdx.graphics.Texture;

public class Goblin extends Character
{
    public Goblin(){
        super(1500f, 4, 5, new Texture("goblin64x64.png"), new Texture("goblinRectangle.JPG"), new Spell[] {new Morsure()});
    }
}
