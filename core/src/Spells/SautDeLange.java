package Spells;

public class SautDeLange extends Spell{
    public SautDeLange()
    {
        super(new SpellComponent[] {new Deplacement()}, 3, 5);
    }
}
