package Class;
import Spells.*;
import com.badlogic.gdx.graphics.Texture;

public class Creationiste extends Character {
    public Creationiste() {
        super((float) 2000 + Math.round(Math.random() * (2770 - 2000)), (int) ((int) 1 + Math.round(Math.random())), (int) (4 + Math.round(Math.random() * 4)), new Texture("creationniste64x64.PNG"), new Texture("creationnisteRectangle.JPG"), new Spell[]{new Eboulissement(),new Entaille(),new Bond(),new Souffle(),new Retablissement()});
    }
}
