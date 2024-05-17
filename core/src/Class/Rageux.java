package Class;
import Spells.*;
import com.badlogic.gdx.graphics.Texture;
public class Rageux extends Character {
    public Rageux () {
        super (1400f, 4, 5, new Texture("rageux.png"), new Texture("rageuxRectangle.JPG"), new Spell[]{new CoupDePoing(), new CoupDePoingGros(), new MidHeal(), new NoWall()});
    }
}
