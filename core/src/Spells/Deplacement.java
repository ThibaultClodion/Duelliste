package Spells;

import Game.PlayerController;

public class Deplacement implements SpellComponent{
    public void execute(int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer)
    {
        myPlayer.Move(mapPos, false);
    }
}
