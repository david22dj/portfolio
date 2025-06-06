package veze;

import fri.shapesge.Obrazok;
import nepriatelia.Nepriatel;
import veze.projektily.Ohen;
import veze.projektily.Projektil;
/**
 * Trieda reprezentujúca plameňometnú vežu.
 * Má stredný dosah, malé poškodenie a mierne dlhší cooldown.
 * Vystreľuje ohnivé projektily so striedanou animáciou plameňa.
 */
public class Plamenomet extends Veza {
    private Obrazok obrazok;
    /**
     * Vytvorí plameňomet na zadaných súradniciach so
     * dosahom 200px, poškodením 5 a cooldownom 20 tickov.
     * Načíta a zobrazí počiatočný obrázok plameňometu.
     *
     * @param x vodorovná súradnica stredu veže
     * @param y zvislá súradnica stredu veže
     */
    public Plamenomet(int x, int y) {
        super(x, y, 200, 5, 20);
        this.obrazok = new Obrazok("plamenomet.png");
        this.obrazok.zmenPolohu(x - 30, y - 41);
        this.obrazok.zobraz();
    }
    /**
     * Vystrelí ohnivý projektil na aktuálneho cieľa.
     * Projektily sú pridané do zoznamu projektilov veže.
     */
    @Override
    public void vystrel() {
        Nepriatel ciel = getCiel();
        if (ciel != null) {
            Projektil p = new Ohen(getX(), getY(), ciel, getDamage());
            pridajProjektil(p);
        }
    }
    /**
     * Zobrazí grafický objekt plameňometu.
     */
    @Override
    public void zobraz() {
        this.obrazok.zobraz();
    }
    /**
     * Skryje grafický objekt plameňometu (napr. pri ukončení hry).
     */
    @Override
    public void zanikni() {
        this.obrazok.skry();
    }

}
