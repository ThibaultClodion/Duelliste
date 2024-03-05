package Spells;

import Game.PlayerController;

import java.util.Arrays;

public class Attack extends Spell
{
    private int damage;

    public Attack(int pa, int dmg, int range)
    {
        super(pa, range);
        this.damage =dmg;
    }
    public int getDamage() {return damage;}

    public void Launch(int[] pos, PlayerController otherPlayer,PlayerController myPlayer)
    {
        if(Arrays.equals(pos, otherPlayer.GetCurrentPosition()))
        {
            otherPlayer.Hit(damage);
        }
    }

}
