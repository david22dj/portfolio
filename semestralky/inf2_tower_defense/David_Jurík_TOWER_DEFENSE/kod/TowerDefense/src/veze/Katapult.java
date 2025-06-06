package veze;

import fri.shapesge.Obrazok;
import nepriatelia.Nepriatel;
import veze.projektily.Kamen;
import veze.projektily.Projektil;
/**
 * Trieda reprezentujúca katapultovú vežu.
 * Má veľký dosah, vyššie poškodenie a animáciu pred vystrelením.
 */
public class Katapult extends Veza {
    private Obrazok obrazok;
    private int animacia;
    /**
     * Vytvorí katapult na zadaných súradniciach so
     * dosahom 400px, poškodením 60 a cooldownom 60 tickov.
     * Načíta a zobrazí počiatočný obrázok.
     *
     * @param x vodorovná súradnica stredu veže
     * @param y zvislá súradnica stredu veže
     */
    public Katapult(int x, int y) {
        super(x, y, 400, 60, 60);
        this.obrazok = new Obrazok("katapult_0.png");
        this.obrazok.zmenPolohu(x - 30, y - 41);
        this.obrazok.zobraz();
        this.animacia = 0;
    }
    /**
     * Vystrelí kamen; animácia sa skladá z 5 snímkov,
     * projektil sa vystrie len na treťom (index 3) snímku animácie.
     */
    @Override
    public void vystrel() {
        if (this.animacia == 3) {
            Nepriatel ciel = getCiel();
            if (ciel != null) {
                Projektil p = new Kamen(getX(), getY(), ciel, getDamage());
                pridajProjektil(p);
            }
        }

        this.animacia = (this.animacia + 1) % 5;
        this.obrazok.zmenObrazok("katapult_" + this.animacia + ".png");
    }
    /**
     * Zobrazí grafický objekt katapultu.
     */
    @Override
    public void zobraz() {
        this.obrazok.zobraz();
    }
    /**
     * Skryje katapult (napr. pri ukončení hry).
     */
    @Override
    public void zanikni() {
        this.obrazok.skry();
    }

}
