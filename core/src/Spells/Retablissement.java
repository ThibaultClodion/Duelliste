package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Retablissement extends Spell{
    public Retablissement(){super(new SpellComponent[] {new Heal(500)},3,0,4,new Texture("soin.png"),"Rétablissement est un soin de 500 points de vie");}
}
