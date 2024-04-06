package Spells;

public class VolDesorganise extends Spell{
    public VolDesorganise() {super(new SpellComponent[] {new Heal((int) (Math.random() * 6 + 1) * 50),new Damage((int) (Math.random() * 6 + 1) * 50)} ,6,2);}
}
