package Spells;

import Game.PlayerController;

import java.util.Arrays;

public class Damage implements SpellComponent
{
    private int damage;

    public Damage(int dmg)
    {
        this.damage =dmg;
    }

    @Override
    public void execute(int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer)
    {
        if(Arrays.equals(mapPos, otherPlayer.getCurrentPosition()))
        {
            otherPlayer.Hit(damage);
        }
    }
}
