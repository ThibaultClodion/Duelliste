package Spells;

import com.badlogic.gdx.graphics.Texture;

public class RouletteRusse extends Spell{
    public RouletteRusse() {super(new SpellComponent[] {new Damage((int) Math.round(Math.random())*800),new Heal((int) -Math.round(Math.random())*800)},6,1, new Texture("classSquare1.JPG"));}
}
