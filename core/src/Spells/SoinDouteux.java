package Spells;

import com.badlogic.gdx.graphics.Texture;

public class SoinDouteux extends Spell{
    public SoinDouteux() {super(new SpellComponent[] {new Heal((int) Math.round(Math.random())*300)},3,0,0, new Texture("classSquare1.JPG"));}
    }

