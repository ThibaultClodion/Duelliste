package Spells;

import Game.PlayerController;
import com.badlogic.gdx.graphics.Texture;

public class Spell
{
    SpellComponent[] components;
    int pa;
    int range;
    public Texture image;


    public Spell(SpellComponent[] components, int pa, int range, Texture image)
    {
        this.components = components;
        this.pa = pa;
        this.range = range;
        this.image = image;
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
