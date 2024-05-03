package Spells;

import com.badlogic.gdx.graphics.Texture;

public class SoinDouteux extends Spell{
    public SoinDouteux() {super(new SpellComponent[] {new Heal((int) Math.round(Math.random())*300)},3,0,3, new Texture("SoinDouteux.png"));}
    }

