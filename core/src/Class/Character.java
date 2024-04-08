package Class;

import Spells.Spell;
import com.badlogic.gdx.graphics.Texture;

public class Character
{
    private float hp;
    private int pm;
    private int pa;
    private Texture image;
    private Texture imageRectangle;
    private Spell[] spells;

    public Character(float hp, int pm, int pa, Texture image, Texture imageRectangle, Spell[] spells)
    {
        this.hp = hp;
        this.pm = pm;
        this.pa = pa;
        this.image = image;
        this.imageRectangle = imageRectangle;
        this.spells = spells;
    }

    public float GetHp()
    {
        return hp;
    }

    public int GetPm()
    {
        return pm;
    }

    public int GetPa()
    {
        return pa;
    }

    public Texture GetImage()
    {
        return image;
    }
    public Texture GetRectangleImage()
    {
        return imageRectangle;
    }

    public Spell GetSpell(int index)
    {
        return spells[index];
    }

    public int getNbSpell() {
        return spells.length;
    }
}
