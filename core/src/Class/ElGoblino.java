package Class;

import Spells.*;
import com.badlogic.gdx.graphics.Texture;

public class ElGoblino extends Character
{
    public ElGoblino(){
        super(1500f, 4, 5, new Texture("goblin64x64.png"), new Texture("goblinRectangle.JPG"), new Spell[] {new Morsure(), new Lance(),new DoubleDague(),new Patate(),new Flash()});
    }
}
