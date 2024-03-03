package Spells;

import Game.PlayerController;

public class Deplacement extends Spell{
    private int coutpm;
    public Deplacement(int pa, int coutpm,int range)
    {
        super(pa,range);
        this.coutpm=coutpm;
    }
    public void Launch(int [] pos, PlayerController joueur){
        if (joueur.CanMove(pos)){
            joueur.currentPosition=pos;
        }
    }
}
