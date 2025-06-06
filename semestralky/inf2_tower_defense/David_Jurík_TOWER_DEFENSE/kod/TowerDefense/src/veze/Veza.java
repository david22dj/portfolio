package veze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nepriatelia.Nepriatel;
import veze.projektily.Projektil;
/**
 * Abstraktná trieda reprezentujúca obrannú vežu.
 * Spravuje hľadanie cieľa v dosahu, strieľanie projektilov, cooldown
 * a aktualizáciu existujúcich projektilov.
 */
public abstract class Veza {
    private int x;
    private int y;
    private int range;
    private int damage;
    private int cooldown;
    private int aktualnyCooldown;
    private Nepriatel ciel;
    private ArrayList<Projektil> projektily;
    /**
     * Vytvorí vežu na pozícii (x, y) so zadaným dosahom, poškodením a cooldownom medzi výstrelmi.
     *
     * @param x        vodorovná súradnica veže
     * @param y        zvislá súradnica veže
     * @param range    dosah veže (v pixeloch)
     * @param damage   poškodenie, ktoré projektily aplikujú na cieľ
     * @param cooldown počet tickov medzi dvoma výstrelmi
     */
    public Veza(int x, int y, int range, int damage, int cooldown) {
        this.x = x;
        this.y = y;
        this.range = range;
        this.damage = damage;
        this.cooldown = cooldown;
        this.aktualnyCooldown = 0;
        this.ciel = null;
        this.projektily = new ArrayList<>();
    }
    /**
     * Skryje a odstráni všetky aktívne projektily tejto veže.
     */
    public void vezaPremazProjektily() {
        for (Projektil p : this.projektily) {
            p.skry();
        }
        this.projektily.clear();
    }
    /**
     * Hlavná aktualizačná metóda, volaná každý tick:
     * – ak nemá platný cieľ alebo cieľ nie je nažive/dosahu, nájde nový,
     * – ak cooldown vypršal, vystrelí projektil,
     * – updatne všetky existujúce projektily a odstráni tie, ktoré zasiahli alebo sú neaktívne.
     *
     * @param nepriatelia zoznam všetkých nepriateľov v hre
     */
    public void aktualizuj(List<Nepriatel> nepriatelia) {
        if (this.ciel == null || !this.ciel.jeNazive() || !this.jeVDosahu(this.ciel)) {
            if (this.ciel != null && !this.ciel.jeNazive()) {
                for (Projektil p : this.projektily) {
                    p.skry();
                }
                this.projektily.clear();
            }
            this.najdiCiel(Collections.unmodifiableList(nepriatelia));
        }

        if (this.ciel != null) {
            if (this.aktualnyCooldown <= 0) {
                this.vystrel();
                //System.out.println("Veza striela!");
                this.aktualnyCooldown = this.cooldown;
            }
        }

        if (this.aktualnyCooldown > 0) {
            this.aktualnyCooldown--;
        }

        ArrayList<Projektil> nove = new ArrayList<>();
        for (Projektil projektil : this.projektily) {
            projektil.aktualizuj();
            if (projektil.jeAktivny()) {
                nove.add(projektil);
            }
        }

        this.projektily = nove;
    }

    /**
     * Nastaví ako cieľ najbližšieho živého nepriateľa v dosahu.
     *
     * @param nepriatelia zoznam nepriateľov, podľa ktorého sa vyhľadáva
     */
    private void najdiCiel(List<Nepriatel> nepriatelia) {
        Nepriatel najblizsi = null;
        double min = Double.MAX_VALUE;

        for (Nepriatel nepriatel : nepriatelia) {
            if (!nepriatel.jeNazive()) {
                continue;
            }
            double vzdialenost = this.vzdialenost(nepriatel);
            if (vzdialenost <= this.range && vzdialenost < min) {
                min = vzdialenost;
                najblizsi = nepriatel;
            }
        }

        this.ciel = najblizsi;
    }
    /**
     * Vypočíta euklidovskú vzdialenosť medzi vežou a nepriateľom.
     *
     * @param nepriatel cieľový nepriateľ
     * @return vzdialenosť v pixeloch
     */
    private double vzdialenost(Nepriatel nepriatel) {
        int dx = this.x - nepriatel.getX();
        int dy = this.y - nepriatel.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * Skontroluje, či je nepriateľ v dosahu veže.
     *
     * @param n nepriateľ na kontrolu
     * @return true, ak je nepriateľ v dosahu, inak false
     */
    private boolean jeVDosahu(Nepriatel n) {
        return this.vzdialenost(n) <= this.range;
    }
    /**
     * @return vodorovnú súradnicu stredu veže
     */
    protected int getX() {
        return this.x;
    }
    /**
     * @return zvislú súradnicu stredu veže
     */
    protected int getY() {
        return this.y;
    }
    /**
     * @return poškodenie, ktoré veža spôsobí každým výstrelom
     */
    protected int getDamage() {
        return this.damage;
    }
    /**
     * @return aktuálne vybraného nepriateľa ako cieľ (alebo null, ak žiadny)
     */
    protected Nepriatel getCiel() {
        return this.ciel;
    }
    /**
     * Pridá nový projektil do zoznamu aktívnych projektilov veže.
     *
     * @param p projektil na pridanie
     */
    protected void pridajProjektil(Projektil p) {
        this.projektily.add(p);
    }
    /**
     * Vystrelí jeden projektil na aktuálneho cieľa.
     * Implementujú konkrétne typy veží.
     */
    public abstract void vystrel();
    /**
     * Zobrazí grafický objekt veže.
     */
    public abstract void zobraz();
    /**
     * Skrýva vežu a uvoľní jej prostriedky (napr. pri jej zničení).
     */
    public abstract void zanikni();


}
