package Spells;

public class Attack extends Spell
{
    private int dmg;
    private int portee;

    private int range;

    public void dmg(int dmg) {this.dmg=dmg;}
    public int getDmg() {return dmg;}
    public void portee(int portee) {this.portee=portee;}
    public int getPortee() {return portee;}
    public void range(int range){this.range=range;}
    public int getRange(){return range}

    public Attack(int pa){super(pa);}
    public void Launch(){System.out.println("Lance une attaque");}

}
