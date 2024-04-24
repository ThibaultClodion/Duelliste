package Spells;

import Game.PlayerController;

public class EchangePAPAM implements SpellComponent
{
    public void execute (int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer)
    {
        myPlayer.Echange();
    }
}
