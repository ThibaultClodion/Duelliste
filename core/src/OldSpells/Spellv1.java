package OldSpells;

import Game.PlayerController;

public abstract class Spellv1
{
    private int pa;
    private int range;

    public Spellv1(int pa, int range)
    {
        this.pa = pa;
        this.range = range;
    }

    public int getPa()
    {
        return pa;
    }

    public int getRange(){return range;}

    public abstract void Launch(int[] pos, PlayerController otherPlayer,PlayerController myPlayer);
}
