package veze.projektily;

import nepriatelia.Nepriatel;
/**
 * Trieda reprezentujúca kamenný projektil vrhaný z veže.
 * Doplňuje základné správanie projektilu o pomalú rotáciu.
 */
public class Kamen extends Projektil {
    private int uhol = 0;
    /**
     * Vytvorí nový projektil typu Kamen na pozícii (x, y) so zadaným cieľom a poškodením.
     *
     * @param x      počiatočná vodorovná súradnica projektilu
     * @param y      počiatočná zvislá súradnica projektilu
     * @param ciel   cieľ nepriateľa, ktorému projektil uberá životy
     * @param damage množstvo poškodenia aplikované pri zásahu
     */
    public Kamen(int x, int y, Nepriatel ciel, int damage) {
        super(x, y, ciel, damage, "kamen.png");
    }
    /**
     * Animácia rotácie kamenného projektilu počas letu.
     * Každým volaním sa uhol otočenia zvýši o 10° (mod 360).
     */
    @Override
    public void animacia() {
        this.uhol = (this.uhol + 10) % 360;
        getObrazok().zmenUhol(this.uhol);
    }
}
