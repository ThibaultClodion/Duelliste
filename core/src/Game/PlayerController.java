package Game;

import OldSpells.Spellv1;
import Class.Character;
import Spells.Spell;

public class PlayerController
{
    //Character use by the Player
    public Character character;

    //Combat Data's
    public int[] currentPosition;
    private float hp;
    private int pm;
    private int pa;

    public PlayerController(Character character, int[] beginPosition)
    {
        changeCharacter(character);
        currentPosition = beginPosition;
    }

    //region <Datas Management>
    public void changeCharacter(Character character)
    {
        //Get the character associated to the player
        this.character = character;

        //Initialize his data's
        this.hp = character.getHp();
        this.pm = character.getPm();
        this.pa = character.getPa();
    }

    public void NewRound()
    {
        //Reset the PM and PA
        pm = character.getPm();
        pa = character.getPa();
    }

    public void Hit(int damage)
    {
        this.hp -= damage;
        System.out.println("Il me reste " + hp + " HP");
    }
    //endregion

    //region <Spells>
    public boolean CanUseSpell(int[] position, Spell spell)
    {
        return Distance(position) <= spell.getRange() && pa - spell.getPa() >= 0;
    }

    public void UseSpell(int[] position, Spell spell, PlayerController otherPlayer)
    {
        if(CanUseSpell(position, spell))
        {
            //Use the spell
            spell.Launch(position, otherPlayer,this);

            //Decrease PA
            pa -= spell.getPa();

        }
    }

    //endregion

    //region <Movement>
    public boolean CanMove(int[] position)
    {
        //Determine if a player can access a position
        return (pm - Distance(position)) >= 0;
    }

    public int Distance(int[] position)
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
            pm -= Distance(position);
        }
    }
// Getters
    public int[] getCurrentPosition()
    {
        return currentPosition;
    }
    public float getHp() { return hp; }
    public float getPa() { return pa; }
    public int getPm() {return pm;}

    //endregion
}
