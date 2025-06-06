package hlavnePrvyHry.tlacidla;

import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
/**
 * Abstraktná základná trieda pre tlačidlá.
 * Spravuje vykreslenie rámika a obrázka,
 * ako aj stav „stlačené“/„nestlačené“.
 */
public abstract class Tlacidlo {
    private Obrazok obrazok;
    private Obdlznik obdlznik;
    private Obdlznik stlaceny;
    private int xObdlzika;
    private int yObdlzika;

    /**
     * Vytvorí nové tlačidlo na pozícii (x, y) a načíta obrázok zo zadanej cesty.
     * Inicializuje biely rámik a čierny „stlačený“ rámik o 5 px väčší.
     *
     * @param x              vodorovná súradnica ľavého horného rohu tlačidla
     * @param y              zvislá súradnica ľavého horného rohu tlačidla
     * @param cestaObrazka   cesta k súboru s obrázkom tlačidla
     */
    public Tlacidlo(int x, int y, String cestaObrazka) {
        this.xObdlzika = x;
        this.yObdlzika = y;
        this.obdlznik = new Obdlznik();
        this.obdlznik.zmenPolohu(this.xObdlzika, this.yObdlzika);
        this.obdlznik.zmenStrany(60, 83);
        this.obdlznik.zmenFarbu("white");
        this.obdlznik.zobraz();

        this.stlaceny = new Obdlznik();
        this.stlaceny.zmenPolohu(this.xObdlzika - 5, this.yObdlzika - 5);
        this.stlaceny.zmenStrany(70, 93);
        this.stlaceny.zmenFarbu("black");

        this.obrazok = new Obrazok(cestaObrazka);
        this.obrazok.zmenPolohu(this.xObdlzika, this.yObdlzika);
        this.obrazok.zobraz();
    }
    /**
     * Prepne tlačidlo do stlačeného stavu.
     * Skryje pôvodný biely rámik a obrázok, zobrazí čierny rámik
     * a následne opäť biely rámik s obrázkom (pre vizuálnu spätnú väzbu).
     */
    public void stlacene() {
        this.obdlznik.skry();
        this.obrazok.skry();
        this.stlaceny.zobraz();
        this.obdlznik.zobraz();
        this.obrazok.zobraz();
    }
    /**
     * Vráti tlačidlo do normálneho (nestlačeného) stavu.
     * Skryje čierny „stlačený“ rámik.
     */
    public void nestlacene() {
        this.stlaceny.skry();
    }

    /**
     * Overí, či daný bod (x, y) leží v rámci tlačidla.
     *
     * @param x  vodorovná súradnica bodu
     * @param y  zvislá súradnica bodu
     * @return   true, ak bod leží vo vnútri tlačidla (vrátane okrajov), inak false
     */
    public boolean obsahujeBod(int x, int y) {
        return x >= this.xObdlzika && x <= this.xObdlzika + 60 &&
                y >= this.yObdlzika && y <= this.yObdlzika + 83;
    }
}
