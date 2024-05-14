package Spells;

import com.badlogic.gdx.graphics.Texture;

public class VolDesorganise extends Spell{
    public VolDesorganise() {super(new SpellComponent[] {new Heal((int) (Math.random() * 4 + 2) * 100),new Damage((int) (Math.random() * 4 + 2) * 100)} ,6,2,4, new Texture("VolDesorganise.png"),"Vol désorganisé est un sort qui vole entre 200 et 600 points de vie à l'adversaire");}
}
