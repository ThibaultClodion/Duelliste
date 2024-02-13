package Class;

import Spells.Spell;
import com.badlogic.gdx.graphics.Texture;

public abstract class Character
{
    private float pv;
    private int pm;
    private int pa;
    private Texture image;
    private Spell[] spells;

    public Character(float pv, int pm, int pa, Texture image, Spell[] spells)
    {
        this.pv = pv;
        this.pm = pm;
        this.pa = pa;
        this.image = image;
        this.spells = spells;
    }

    public Spell GetSpell(int index)
    {
        return spells[index];
    }

    public abstract int getPm();

    public abstract int getPa();
}
