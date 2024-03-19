package Spells;

public class Morsure extends Spell
{
    public Morsure()
    {
        super(new SpellComponent[] {new Damage(150)}, 1, 3);
    }
}
