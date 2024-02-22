package Spells;

public class Morsure extends Attack{
    public Morsure()
    {
        super(1,150,1,1);
    }

    @Override
    public void Launch()
    {
        System.out.println("Morsure");
    }
}
