package Class;
import Spells.*;
import com.badlogic.gdx.graphics.Texture;

public class Creationiste extends Character {
    public Creationiste() {
        super((float) 2000 + Math.round(Math.random() * (2770 - 2000)), (int) ((int) 1 + Math.round(Math.random())), (int) (4 + Math.round(Math.random() * 4)), new Texture("Creationiste.png"), new Spell[]{});
    }
}
