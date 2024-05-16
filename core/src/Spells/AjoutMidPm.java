package Spells;

import Game.PlayerController;

public class AjoutMidPm implements SpellComponent {
    public void execute(int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer) {
        myPlayer.AjoutMidPm();
    }
}
