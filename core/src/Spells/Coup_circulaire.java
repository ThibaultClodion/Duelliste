package Spells;

public class Coup_circulaire extends Spell{
    public Coup_circulaire()
    {
      super(new SpellComponent[] {new Damage(150)}, 3, 3);
    }
}
