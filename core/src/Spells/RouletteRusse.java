package Spells;

import com.badlogic.gdx.graphics.Texture;

public class RouletteRusse extends Spell{
    public RouletteRusse() {super(new SpellComponent[] {new Damage((int) Math.round(Math.random())*800),new Heal((int) -Math.round(Math.random())*800)},6,1,6, new Texture("RouletteRusse.png"),"Roulette Russe est une attaque qui 1 chance sur 2 d'infliger à l'adversaire 800 dégats \n ou de s'auto-infliger 800 dégats");}
}
