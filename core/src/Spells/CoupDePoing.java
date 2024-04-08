package Spells;

public class CoupDePoing extends Spell {
    public CoupDePoing () {
        super (new SpellComponent[]{new Damage(50)}, 1, 1);
    }
}