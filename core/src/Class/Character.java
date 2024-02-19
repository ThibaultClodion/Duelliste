package Class;

import Spells.Spell;
import com.badlogic.gdx.graphics.Texture;

public class Character
{
    private float hp;
    private int pm;
    private int pa;
    private int nbSpell;
    private Texture image;
    private Spell[] spells;

    public Character(float hp, int pm, int pa, Texture image, Spell[] spells)
    {
        this.hp = hp;
        this.pm = pm;
        this.pa = pa;
        this.image = image;
        this.spells = spells;
    }

    public float getHp()
    {
        return hp;
    }

    public int getPm()
    {
        return pm;
    }

    public int getPa()
    {
        return pa;
    }

    public Texture getImage()
    {
        return image;
    }

    public Spell GetSpell(int index)
    {
        return spells[index];
    }

    public int getNbSpell() {
        return spells.length;
    }
}
