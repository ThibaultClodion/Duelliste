package Spells;

import com.badlogic.gdx.graphics.Texture;

public class NoWall extends Spell {
    public NoWall() {
        super(new SpellComponent[] {new Deplacement()}, 3, 3, 2, new Texture("noWall.png"), "Le NoWall permet de rendre les murs franchissables pour se déplacer de 3 cases");
    }
}
