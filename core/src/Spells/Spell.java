package Spells;

import Game.PlayerController;

public abstract class Spell
{
    private int pa;
    private int range;

    public Spell(int pa, int range)
    {
        this.pa = pa;
        this.range = range;
    }

    public int getPa()
    {
        return pa;
    }

    public int getRange(){return range;}

    public abstract void Launch(int[] pos);
}
