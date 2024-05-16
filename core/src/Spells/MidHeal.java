package Spells;

import com.badlogic.gdx.graphics.Texture;
import Game.PlayerController;


public class MidHeal extends Spell {
    public MidHeal() {
        super(new SpellComponent[]{new AjoutMidPm()}, 4, 0, 4, new Texture("soin.png"), "MidHeal est un sort qui soigne le minimum entre PV + PVmax/2 et PVmax");
    }
}
