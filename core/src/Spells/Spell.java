package Spells;

public abstract class Spell
{
    private int pa;

    public Spell(int pa)
    {
        this.pa = pa;
    }

    public int getPa()
    {
        return pa;
    }

    public abstract void Launch();
}
