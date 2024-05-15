package Class;

import Spells.*;
import com.badlogic.gdx.graphics.Texture;

public class Lamenpeine extends Character
{

    public Lamenpeine()
    {
        super(1400f, 2, 6, new Texture("lamenpeine62x62.png"), new Texture("lamenpeine.JPG"), new Spell[] {new SautDeLange(),new Coup_circulaire(), new Coup_d_epee(), new Dechiquetage(),new Explosion(),new Soif_de_sang()});
    }
}
