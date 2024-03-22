package Spells;

public class Des extends Spell{
    public Des() {super(new SpellComponent[] {new Damage((int) (Math.random() * 6 + 1) * 50)}, 4, 2);}
}
