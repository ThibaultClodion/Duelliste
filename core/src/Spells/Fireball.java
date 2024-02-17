package Spells;

public class Fireball extends Attack
{
    public Fireball()
    {
        //The fireball use 3 PA
        super(3);
        this.dmg(30);
        this.portee(100);
        this.range(150);
    }

    @Override
    public void Launch()
    {
        System.out.println("Lancer la boule de feu");
    }
}
