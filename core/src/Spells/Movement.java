package Spells;

import Game.PlayerController;

import java.util.Arrays;

public class Movement implements SpellComponent
{
    private int range;
    public Movement(int range)
    {
        this.range = range;
    }

    @Override
    public void execute(int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer)
    {
    }
}
