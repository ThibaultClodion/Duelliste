package Class;

import Spells.Spell;
import com.badlogic.gdx.graphics.Texture;

public class Goblin extends Character{
    public Goblin(){
        super(1500f, 4, 5, new Texture("goblin.png"), new Spell[10]);
    }
}
