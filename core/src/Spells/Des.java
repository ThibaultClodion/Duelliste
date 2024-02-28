package Spells;

/* Inflicts random damage between 50 and 300
* 4 PA
* 0 reload
* 3 range
 */

public class Des extends Attack
{
    public Des() {
        super(4, (int) (Math.random() * 6 + 1) * 50, 1);
    }
}
