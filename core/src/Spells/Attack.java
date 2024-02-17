package Spells;

public class Attack extends Spell
{
    private int pa;
    private int dmg;
    private int portee;

    public void dmg(int dmg) {this.dmg=dmg;}
    public int getDmg() {return dmg;}
    public void portee(int portee) {this.portee=portee;}
    public int getPortee() {return portee;}

    public Attack(int pa){super(pa);}
    public void Launch(){System.out.println("Lance une attaque");}

}
