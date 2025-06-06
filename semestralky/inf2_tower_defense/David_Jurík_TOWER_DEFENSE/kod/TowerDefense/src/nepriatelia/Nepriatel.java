package nepriatelia;

import fri.shapesge.Obrazok;
import hlavnePrvyHry.Cesta;
import hlavnePrvyHry.HpBar;
/**
 * Abstraktná trieda predstavujúca nepriateľa postupujúceho po ceste.
 * Spravuje pohyb, životy, HP bar a stav útoku na hrad.
 */
public abstract class Nepriatel {
    private int x;
    private int y;
    private int rychlost;
    private int zivot;
    private Cesta cesta;
    private int indexBodu;
    private Obrazok obrazok;
    private int pocitadlo;
    private HpBar ukazovatelZivota;
    private static final int SIRKA_BARU = 50;
    private static final int VYSKA_BARU = 5;

    private boolean aktivnyNaUtok;
    private int pocitadloUtoku;


    /**
     * Konštruktor, ktorý inicializuje neprajateľské parametre a umiestni ho
     * na prvý bod cesty, nastaví HP bar nad jeho pozíciu.
     *
     * @param zivot    počiatočný počet životov nepriateľa
     * @param rychlost počet tickov potrebných na posun na ďalší bod
     */
    public Nepriatel(int zivot, int rychlost) {
        this.cesta = new Cesta();
        this.zivot = zivot;
        this.rychlost = rychlost;
        this.indexBodu = 0;
        this.pocitadlo = 0;

        if (this.cesta.getVelkost() > 0) {
            this.x = this.cesta.getBod(this.indexBodu).getX();
            this.y = this.cesta.getBod(this.indexBodu).getY();
        }

        this.ukazovatelZivota = new HpBar(this.zivot, SIRKA_BARU, VYSKA_BARU);
        this.ukazovatelZivota.setPozicia(this.x - SIRKA_BARU / 2, this.y - 80);
    }
    /**
     * Odoberie nepriateľovi zadaný počet životov a aktualizuje jeho HP bar.
     *
     * @param kolko počet životov, o ktoré sa má znížiť
     */
    public void uberZivot(int kolko) {
        this.zivot -= kolko;
        this.ukazovatelZivota.upravHp(this.zivot);
    }
    /**
     * Skontroluje, či je nepriateľ stále nažive (životy > 0).
     *
     * @return true, ak má ešte životy, inak false
     */
    public boolean jeNazive() {
        return this.zivot > 0;
    }
    /**
     * Vráti aktuálnu vodorovnú súradnicu nepriateľa.
     *
     * @return hodnota X
     */
    public int getX() {
        return this.x;
    }
    /**
     * Vráti aktuálnu zvislú súradnicu nepriateľa.
     *
     * @return hodnota Y
     */
    public int getY() {
        return this.y;
    }
    /**
     * Získa referenciu na cestu, po ktorej sa nepriateľ pohybuje.
     *
     * @return inštancia Cesta
     */
    public Cesta getCesta() {
        return this.cesta;
    }
    /**
     * Vráti index aktuálneho bodu na ceste, ku ktorému je nepriateľ priradený.
     *
     * @return index bodu (0-based)
     */
    public int getIndexBodu() {
        return this.indexBodu;
    }
    /**
     * Nastaví grafický objekt (obrázok), ktorý reprezentuje nepriateľa.
     *
     * @param obrazok inštancia Obrazok používaná na zobrazenie
     */
    public void setObrazok(Obrazok obrazok) {
        this.obrazok = obrazok;
    }
    /**
     * Posunie nepriateľa pozdĺž cesty podľa jeho rýchlosti.
     * Ak dôjde na koniec, aktivuje stav útoku.
     */
    public void pohybujSa() {
        this.pocitadlo++;
        if (this.pocitadlo >= this.rychlost) {
            this.pocitadlo = 0;
            this.indexBodu++;

            if (this.indexBodu < this.cesta.getVelkost()) {
                this.x = this.cesta.getBod(this.indexBodu).getX();
                this.y = this.cesta.getBod(this.indexBodu).getY();
                this.obrazok.zmenPolohu(this.x - 25, this.y - 75);
                this.ukazovatelZivota.setPozicia(this.x - SIRKA_BARU / 2, this.y - 80);
            } else {
                this.aktivnyNaUtok = true;
            }
        }
    }
    /**
     * Vráti grafický objekt používaný na zobrazenie nepriateľa.
     *
     * @return inštancia Obrazok
     */
    public Obrazok getObrazok() {
        return this.obrazok;
    }
    /**
     * Abstraktná metóda na aktualizáciu stavu nepriateľa každým tickom.
     * Implementujú ju konkrétne triedy.
     */
    public abstract void aktualizuj();
    /**
     * Skryje nepriateľa a jeho HP bar z hernej plochy.
     */
    public void zanikni() {
        this.getObrazok().skry();
        this.ukazovatelZivota.skry();
    }
    /**
     * Vráti aktuálny počet životov nepriateľa.
     *
     * @return počet životov
     */
    public int getZivot() {
        return this.zivot;
    }
    /**
     * Skontroluje, či je nepriateľ pripravený na útok hradu.
     *
     * @return true, ak došiel na koniec cesty, inak false
     */
    public boolean jeAktivnyNaUtok() {
        return this.aktivnyNaUtok;
    }
    /**
     * Abstraktná metóda vracajúca silu útoku nepriateľa.
     *
     * @return hodnota útocnej sily
     */
    public abstract int dajSiluUtoku();
    /**
     * Vráti aktuálne počítadlo medzi útokmi nepriateľa.
     *
     * @return počet tickov od posledného útoku
     */
    public int dajPocitadloUtoku() {
        return this.pocitadloUtoku;
    }
    /**
     * Nastaví počítadlo útokov na zadanú hodnotu.
     *
     * @param hodnota nová hodnota počítadla útokov
     */
    public void nastavPocitadloUtoku(int hodnota) {
        this.pocitadloUtoku = hodnota;
    }
    /**
     * Inkrementuje počítadlo útokov o jeden tick.
     */
    public void tikUtok() {
        this.pocitadloUtoku++;
    }
    /**
     * Abstraktná metóda určujúca, či môže nepriateľ v danom čase zaútočiť.
     *
     * @return true, ak sú splnené podmienky na útok, inak false
     */
    public abstract boolean mozeUtok();

}
