package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Carte extends Spell{
    public Carte() { super(new SpellComponent[] {new Damage((int) Math.floor(Math.random() * 200))}, 3, 1,0, new Texture("Carte.png"));}
}
