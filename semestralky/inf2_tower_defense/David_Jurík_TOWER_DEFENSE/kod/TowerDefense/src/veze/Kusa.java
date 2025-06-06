package veze;

import nepriatelia.Nepriatel;
import fri.shapesge.Obrazok;
import veze.projektily.Hviezda;
import veze.projektily.Projektil;
/**
 * Trieda reprezentujúca kušovú vežu.
 * Má malý dosah, nízke poškodenie, ale rýchly cooldown.
 * Vystreľuje hviezdičkové projektily s rotačnou animáciou.
 */
public class Kusa extends Veza {
    private Obrazok obrazok;
    /**
     * Vytvorí kušu na zadaných súradniciach so
     * dosahom 130px, poškodením 3 a cooldownom 10 tickov.
     * Načíta a zobrazí počiatočný obrázok kuše.
     *
     * @param x vodorovná súradnica stredu veže
     * @param y zvislá súradnica stredu veže
     */
    public Kusa(int x, int y) {
        super(x, y, 130, 3, 10);
        this.obrazok = new Obrazok("kusa.png");
        this.obrazok.zmenPolohu(x - 30, y - 41);
        this.obrazok.zobraz();
    }
    /**
     * Vystrelí hviezdičkový projektil na aktuálneho cieľa.
     * Projektily sú pridané do zoznamu projektilov veže.
     */
    @Override
    public void vystrel() {
        Nepriatel ciel = getCiel();
        if (ciel != null) {
            Projektil p = new Hviezda(getX(), getY(), ciel, getDamage());
            pridajProjektil(p);
        }
    }
    /**
     * Zobrazí grafický objekt kuše.
     */
    @Override
    public void zobraz() {
        this.obrazok.zobraz();
    }
    /**
     * Skryje grafický objekt kuše (napr. pri ukončení hry).
     */
    @Override
    public void zanikni() {
        this.obrazok.skry();
    }

}
