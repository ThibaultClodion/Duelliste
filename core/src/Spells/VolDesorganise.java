package Spells;

import com.badlogic.gdx.graphics.Texture;

public class VolDesorganise extends Spell{
    public VolDesorganise() {super(new SpellComponent[] {new Heal((int) (Math.random() * 5 + 1) * 100),new Damage((int) (Math.random() * 5 + 1) * 100)} ,6,2,0, new Texture("classSquare1.JPG"));}
}
