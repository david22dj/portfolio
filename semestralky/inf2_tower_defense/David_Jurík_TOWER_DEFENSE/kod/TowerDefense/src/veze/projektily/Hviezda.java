package veze.projektily;

import nepriatelia.Nepriatel;
/**
 * Trieda reprezentujúca hviezdičkový projektil vrhaný z veže.
 * Doplňuje základné správanie projektilu o otáčavú animáciu.
 */
public class Hviezda extends Projektil {
    private int uhol = 0;
    /**
     * Vytvorí nový projektil typu Hviezda na pozícii (x, y) so zadaným cieľom a poškodením.
     *
     * @param x       počiatočná vodorovná súradnica projektilu
     * @param y       počiatočná zvislá súradnica projektilu
     * @param ciel    cieľ nepriateľa, ktorému projektil uberá životy
     * @param damage  množstvo poškodenia aplikované pri zásahu
     */
    public Hviezda(int x, int y, Nepriatel ciel, int damage) {
        super(x, y, ciel, damage, "hviezda.png");
    }
    /**
     * Animácia rotácie hviezdy počas letu.
     * Každým volaním sa uhol otočenia zvýši o 25° (mod 360).
     */
    @Override
    public void animacia() {
        this.uhol = (this.uhol + 25) % 360;
        getObrazok().zmenUhol(this.uhol);
    }
}
