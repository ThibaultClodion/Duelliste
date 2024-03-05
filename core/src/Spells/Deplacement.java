package Spells;

import Game.PlayerController;

public class Deplacement extends Spell{
    public Deplacement(int pa,int range)
    {
        super(pa,range);
    }
    public void Launch(int [] pos,PlayerController otherPlayer,PlayerController myPlayer)
    {
        if(myPlayer.CanUseSpell(pos,this)){
            myPlayer.currentPosition=pos;
        }
    }
}
