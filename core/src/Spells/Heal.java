package Spells;

import Game.PlayerController;
import java.util.Arrays;

public class Heal implements SpellComponent
{
    private int heal;

    public Heal(int heal)
    {
        this.heal = heal;
    }

    @Override
    public void execute(int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer)
    {
        if(Arrays.equals(mapPos, otherPlayer.getCurrentPosition()))
        {
            myPlayer.Hit(-heal);
        }
    }
}
