package Spells;

public class VolDesorganise extends Spell{
    public VolDesorganise() {super(new SpellComponent[] {new Heal((int) (Math.random() * 5 + 1) * 100),new Damage((int) (Math.random() * 5 + 1) * 100)} ,6,2);}
}
