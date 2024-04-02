package Spells;

public class Dechiquetage extends Spell{
    public Dechiquetage()
    {
            super(new SpellComponent[] {new Damage(400)}, 4, 1);
    }
}
