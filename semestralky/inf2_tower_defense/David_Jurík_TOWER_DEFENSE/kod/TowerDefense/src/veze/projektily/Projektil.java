package veze.projektily;

import fri.shapesge.Obrazok;
import nepriatelia.Nepriatel;
/**
 * Abstraktná trieda reprezentujúca projektil vystrelený z veže.
 * Spravuje jeho pohyb smerom k cieľu, zobrazenie a aplikáciu damage pri zásahu.
 */
public abstract class Projektil {
    private int x;
    private int y;
    private final int rychlost = 10;
    private final int damage;
    private final Nepriatel ciel;
    private boolean zasiahol = false;
    private Obrazok obrazok;
    /**
     * Inicializuje projektil na pozícii (x, y), so zadaným cieľom a poškodením,
     * načíta a zobrazí jeho obrázok.
     *
     * @param x                      počiatočná vodorovná súradnica
     * @param y                      počiatočná zvislá súradnica
     * @param ciel                   cieľ, ktorému projektil uberá životy
     * @param damage                 množstvo poškodenia aplikované pri zásahu
     * @param nazovSuboruObrazka     názov súboru s obrázkom projektilu
     */
    public Projektil(int x, int y, Nepriatel ciel, int damage, String nazovSuboruObrazka) {
        this.x = x;
        this.y = y;
        this.ciel = ciel;
        this.damage = damage;
        this.obrazok = new Obrazok(nazovSuboruObrazka);
        this.obrazok.zmenPolohu(x, y);
        this.obrazok.zobraz();
    }
    /**
     * Posunie projektil smerom k cieľu podľa svojej rýchlosti,
     * kontroluje kolíziu a aplikuje damage, prípadne skrýva projektil pri zásahu.
     */
    public void aktualizuj() {
        //this.animacia();// toto funguje ale dost som seka ked je to aktivne
        if (this.zasiahol || this.ciel == null || !this.ciel.jeNazive()) {
            this.obrazok.skry();
            this.zasiahol = true;
            return;
        }


        if (this.zasiahol || this.ciel == null || !this.ciel.jeNazive()) {
            return;
        }

        int dx = this.ciel.getX() - this.x;
        int dy = this.ciel.getY() - this.y;
        double dlzka = Math.sqrt(dx * dx + dy * dy);

        if (dlzka < this.rychlost) {
            this.x = this.ciel.getX();
            this.y = this.ciel.getY();
        } else {
            this.x += (int)(this.rychlost * dx / dlzka);
            this.y += (int)(this.rychlost * dy / dlzka);
        }

        this.obrazok.skry();
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();

        int rozdielX = this.ciel.getX() - this.x;
        int rozdielY = this.ciel.getY() - this.y;
        if (rozdielX * rozdielX + rozdielY * rozdielY <= 25) {
            this.ciel.uberZivot(this.damage);
            //System.out.println("Zasah! Ciel: " + this.ciel + ", Zivot: " + this.ciel.getZivot());
            this.obrazok.skry();
            this.zasiahol = true;
        }
    }
    /**
     * Skontroluje, či projektil ešte nezasiahol cieľ.
     *
     * @return true, ak projektil je stále aktívny, inak false
     */
    public boolean jeAktivny() {
        return !this.zasiahol;
    }
    /**
     * Skryje grafický objekt projektilu z hernej plochy.
     */
    public void skry() {
        this.obrazok.skry();
    }
    /**
     * Vracia grafický objekt projektilu pre prípadné animácie alebo modifikácie v subtriedach.
     *
     * @return inštancia Obrazok reprezentujúca projektil
     */
    protected Obrazok getObrazok() {
        return this.obrazok;
    }
    /**
     * Abstraktná metóda definujúca dodatočnú animáciu projektilu,
     * ktorú implementujú konkrétne typy projektilov.
     */
    public abstract void animacia();
}
