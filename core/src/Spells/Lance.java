package Spells;

public class Lance extends Attack {
    public Lance(){
        super(3,100,1,5);
    }

    @Override
    public void Launch(int[] pos) {
        System.out.println("Je lance une lance");
    }
}
