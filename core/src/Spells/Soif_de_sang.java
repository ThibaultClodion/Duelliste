package Spells;

import com.badlogic.gdx.graphics.Texture;

public class Soif_de_sang extends Spell{
    public Soif_de_sang(){super(new SpellComponent[] {new AjoutPmPa()},1,0,6,new Texture("soifDeSang.png"),"Soif de Sang est un sort qui transforme les Pm restants en PA");}
}
