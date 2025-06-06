package hlavnePrvyHry;

import fri.shapesge.Obdlznik;
/**
 * Trieda reprezentujúca grafický ukazovateľ životov (HP bar).
 * Zobrazuje čierne pozadie a červené naplnenie podľa aktuálneho zostatku životov.
 */
public class HpBar {
    private Obdlznik pozadie;
    private Obdlznik naplnenie;
    private int maxHp;
    private int sirka;
    private int vyska;
    /**
     * Vytvorí nový HP bar s definovanou maximálnou hodnotou životov a rozmermi.
     *
     * @param maxHp maximálny počet životov (100 % naplnenia)
     * @param sirka šírka HP baru v pixeloch
     * @param vyska výška HP baru v pixeloch
     */
    public HpBar(int maxHp, int sirka, int vyska) {
        this.maxHp = maxHp;
        this.sirka = sirka;
        this.vyska = vyska;

        this.pozadie = new Obdlznik();
        this.pozadie.zmenStrany(sirka, vyska);
        this.pozadie.zmenFarbu("black");

        this.naplnenie = new Obdlznik();
        this.naplnenie.zmenStrany(sirka, vyska);
        this.naplnenie.zmenFarbu("red");
    }

    /**
     * Nastaví pozíciu HP baru na obrazovke a zobrazí ho.
     *
     * @param x vodorovná súradnica ľavého horného rohu
     * @param y zvislá súradnica ľavého horného rohu
     */
    public void setPozicia(int x, int y) {
        this.pozadie.zmenPolohu(x, y);
        this.naplnenie.zmenPolohu(x, y);
        this.pozadie.zobraz();
        this.naplnenie.zobraz();
    }

    /**
     * Upravi šírku naplnenia podľa zostávajúcich životov.
     * Pomer aktuálnych životov k maximálnym sa prepočíta na novú šírku červeného pruhu.
     *
     * @param zostatok aktuálny počet životov (môže byť aj 0)
     */
    public void upravHp(int zostatok) {
        double pomer = Math.max(0, (double)zostatok / this.maxHp);
        int novaSirka = (int)(this.sirka * pomer);
        this.naplnenie.zmenStrany(novaSirka, this.vyska);
    }
    /**
     * Skryje celý HP bar (pozadie aj naplnenie).
     */
    public void skry() {
        this.pozadie.skry();
        this.naplnenie.skry();
    }
}
