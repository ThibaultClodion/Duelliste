package Spells;

import Game.PlayerController;

public class AjoutPmPa implements SpellComponent{
    @Override
    public void execute(int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer)
    {
        myPlayer.AjoutPmrPa();
    }
}
