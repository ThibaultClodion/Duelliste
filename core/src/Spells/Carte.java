package Spells;

public class Carte extends Spell{
    public Carte() { super(new SpellComponent[] {new Damage((int) Math.floor(Math.random() * 200))}, 3, 1);}
}
