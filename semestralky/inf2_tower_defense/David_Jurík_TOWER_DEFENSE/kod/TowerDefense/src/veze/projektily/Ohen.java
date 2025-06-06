package veze.projektily;

import nepriatelia.Nepriatel;
/**
 * Trieda reprezentujúca ohnivý projektil vrhaný z veže.
 * Má animáciu striedania troch obrázkov ohňa.
 */
public class Ohen extends Projektil {
    private int animacia = 1;
    /**
     * Vytvorí nový projektil typu Oheň na pozícii (x, y) so zadaným cieľom a poškodením.
     * Načíta počiatočný obrázok "ohen_1.png".
     *
     * @param x      počiatočná vodorovná súradnica projektilu
     * @param y      počiatočná zvislá súradnica projektilu
     * @param ciel   cieľ nepriateľa, ktorému projektil uberá životy
     * @param damage množstvo poškodenia aplikované pri zásahu
     */
    public Ohen(int x, int y, Nepriatel ciel, int damage) {
        super(x, y, ciel, damage, "ohen_1.png");
    }
    /**
     * Animácia ohňa počas letu.
     * Každým volaním sa prepne na ďalší z troch obrázkov ("ohen_1.png" až "ohen_3.png").
     */
    @Override
    public void animacia() {
        this.animacia++;
        if (this.animacia > 3) {
            this.animacia = 1;
        }
        getObrazok().zmenObrazok("ohen_" + this.animacia + ".png");
    }
}
