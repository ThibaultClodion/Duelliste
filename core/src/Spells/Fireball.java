package Spells;

public class Fireball extends Spell
{
    public Fireball()
    {
        //The fireball use 3 PA
        super(3);
    }

    @Override
    public void Launch()
    {
        System.out.println("Lancer la boule de feu");
    }
}
