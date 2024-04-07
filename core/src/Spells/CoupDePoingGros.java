package Spells;

public class CoupDePoingGros extends Spell {
    public CoupDePoingGros() {
        super (new SpellComponent[]{new Damage(150)}, 2, 1);
    }
}
