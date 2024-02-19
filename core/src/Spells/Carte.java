package Spells;

/* Inflicts a random damage between 0 and 200
* 3 PA
* 0 reload
* 4 range
 */

public class Carte extends Attack {
    public Carte() {
        super(3, (int) Math.floor(Math.random() * 200), 4);
    }
    @Override
    public void Launch() {
        System.out.println("Lance la Carte");
    }
}
