package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Carte extends Spell{
    public Carte() { super(new SpellComponent[] {new Damage((int) Math.floor(Math.random() * 200))}, 3, 1, new Texture("classSquare1.JPG"));}
}
