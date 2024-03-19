package Spells;

import Game.PlayerController;

import java.util.List;

public class Spell
{
    SpellComponent[] components;
    int pa;
    int range;

    public Spell(SpellComponent[] components, int pa, int range)
    {
        this.components = components;
        this.pa = pa;
        this.range = range;
    }

    public int getRange()
    {
        return range;
    }

    public int getPa()
    {
        return pa;
    }

    public void Launch(int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer)
    {
        for (SpellComponent component: components)
        {
            component.execute(mapPos, otherPlayer, myPlayer);
        }
    }
}
