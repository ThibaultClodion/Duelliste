import Spells.Spell;

public class PlayerController
{
    private Character character;
    private int[] currentPosition;
    private int pm;
    private int pa;

    public PlayerController(Character character)
    {
        this.character = character;
    }

    public void newRound()
    {
        //Reset the PM and PA
        pm = character.getPm();
        pa = character.getPa();
    }

    //region <Spells>
    private boolean CanUseSpell(Spell spell)
    {
        return (pa - spell.getPa() >= 0);
    }

    private void UseSpell(Spell spell)
    {
        if(CanUseSpell(spell))
        {
            //Use the spell
            spell.Launch();

            //Decrease PA
            pa -= spell.getPa();

        }
    }
    //endregion

    //region <Movement>
    public boolean CanMove(int[] position)
    {
        //Determine if a player can access a position
        return (pm - NbMove(position)) >= 0;
    }

    public int NbMove(int[] position)
    {
        //Get the number of move to do on x-axis and y-axis
        int xMove = Math.abs(position[0] - currentPosition[0]);
        int yMove = Math.abs(position[1] - currentPosition[1]);

        return xMove + yMove;
    }

    public void Move(int[] position)
    {
        //Move the player to the position if it's possible and decrease PM
        if(CanMove(position))
        {
            currentPosition = position;
            pm = NbMove(position);
        }
    }
    //endregion
}
