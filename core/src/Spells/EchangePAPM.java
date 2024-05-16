package Spells;

import Game.PlayerController;

public class EchangePAPM implements SpellComponent
{
    public void execute (int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer)
    {
        myPlayer.SwitchPaPm();
    }
}
