package Game;

import Spells.Attack;
import Spells.Spell;
import Class.Character;

public class PlayerController
{
    //Character use by the Player
    public Character character;

    //Combat Data's
    private int[] currentPosition;
    private float hp;
    private int pm;
    private int pa;

    public PlayerController(Character character)
    {
        changeCharacter(character);
    }

    public void changeCharacter(Character character)
    {
        //Get the character associated to the player
        this.character = character;

        //Initialize his datas
        this.hp = character.getHp();
        this.pm = character.getPm();
        this.pa = character.getPa();
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
    private boolean IsInRange(int[] posennemi, Attack attack)
    {
        if ( Math.abs(currentPosition[0] - posennemi[0])<= attack.portee && Math.abs(currentPosition[1] - posennemi[1])<= attack.portee)
        {
            return true;
        }
        else{return false;}
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
            pm -= NbMove(position);
        }
    }
    //endregion
}
