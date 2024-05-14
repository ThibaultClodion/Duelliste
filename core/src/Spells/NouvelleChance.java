package Spells;

import com.badlogic.gdx.graphics.Texture;

public class NouvelleChance extends Spell{
    public NouvelleChance(){super(new SpellComponent[] {new EchangePAPM()},0,0,4,new Texture("NouvelleChance.png"),"Nouvelle chance est un sort qui échange les points d'actions en points de mouvement");}
}
