package Spells;

public class SoinDouteux extends Spell{
    public SoinDouteux() {super(new SpellComponent[] {new Heal((int) Math.round(Math.random())*300)},3,0);}
    }

