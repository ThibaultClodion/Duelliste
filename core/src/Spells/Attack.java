package Spells;

public class Attack extends Spell
{
    private int dmg;
    private int zone;

    public Attack(int pa, int dmg, int zone, int range)
    {
        super(pa, range);
        this.dmg=dmg;
        this.zone=zone;
    }
    public int getDmg() {return dmg;}
    public int getPortee() {return zone;}

    public void Launch(int[] pos){System.out.println("Lance une attaque");}

}
