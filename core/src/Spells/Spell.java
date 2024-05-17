package Spells;

import Game.PlayerController;
import com.badlogic.gdx.graphics.Texture;

public class Spell
{
    SpellComponent[] components;
    int pa;
    int range;
    int cooldown;
    int currentCooldown;
    String description;
    public Texture image;


    public Spell(SpellComponent[] components, int pa, int range, int cooldown, Texture image, String description)
    {
        this.components = components;
        this.pa = pa;
        this.range = range;
        this.image = image;
        this.cooldown = cooldown;
        this.description = description;
        currentCooldown = 0;
    }

    public String GetDescription(int lineCharLimit)
    {
        String finalDescription = "";
        int nbChar = 0;
        String[] wordInDescriptions = description.split(" ");

        for (String word: wordInDescriptions)
        {
            nbChar += word.length();
            finalDescription += word;
            finalDescription += " ";

            if(nbChar >= lineCharLimit)
            {
                nbChar = 0;
                finalDescription += "\n";
            }
        }

        return finalDescription;
    }



    public void UpdateCooldown()
    {
        if(currentCooldown > 0)
        {
            currentCooldown--;
        }
    }

    public boolean IsReloaded()
    {
        return currentCooldown <= 0;
    }

    public void ResetCooldown()
    {
        currentCooldown = cooldown;
    }


    public int getRange()
    {
        return range;
    }

    public int getPa()
    {
        return pa;
    }

    public int getCooldown() {return cooldown;}

    public void Launch(int[] mapPos, PlayerController otherPlayer, PlayerController myPlayer)
    {
        for (SpellComponent component: components)
        {
            component.execute(mapPos, otherPlayer, myPlayer);
        }
    }
}
