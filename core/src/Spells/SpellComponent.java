package Spells;

import Game.PlayerController;

public interface SpellComponent
{
    public abstract void execute(int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer);
}
