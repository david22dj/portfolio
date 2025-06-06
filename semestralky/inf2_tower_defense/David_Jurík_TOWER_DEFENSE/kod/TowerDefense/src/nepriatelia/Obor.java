package nepriatelia;

import fri.shapesge.Obrazok;
/**
 * Trieda reprezentujúca Obra – pomalého, no veľmi odolného nepriateľa s vysokou silou útoku.
 * Má animáciu striedania dvoch obrázkov každých 17 tickov.
 */
public class Obor extends Nepriatel {
    private int animacia;
    private int pocitadloAnimacie;
    /**
     * Konštruktor, ktorý inicializuje Obra so 1000 životmi a rýchlosťou 3 ticky na krok.
     * Nastaví počiatočný obrázok umiestnený vzhľadom na jeho veľkosť.
     */
    public Obor() {
        super(1000, 3);
        Obrazok obrazok = new Obrazok("obor_0.png");
        obrazok.zmenPolohu(getX() - 36, getY() - 87);
        obrazok.zobraz();
        setObrazok(obrazok);
        this.animacia = 0;
        this.pocitadloAnimacie = 0;
    }
    /**
     * Aktualizuje stav Obra každým tickom:
     * – pohybuje sa po ceste,
     * – obnovuje zobrazenie obrázka pre plynulý pohyb,
     * – strieda obrázky animácie každých 17 tickov.
     */
    @Override
    public void aktualizuj() {
        pohybujSa();
        getObrazok().skry();
        getObrazok().zobraz();
        this.pocitadloAnimacie++;

        if (this.pocitadloAnimacie % 17 == 0) {
            if (this.animacia == 0) {
                getObrazok().zmenObrazok("obor_1.png");
                this.animacia = 1;
            } else {
                getObrazok().zmenObrazok("obor_0.png");
                this.animacia = 0;
            }
        }

    }
    /**
     * Vracia obrovskú silu útoku Obra (400 bodov poškodenia).
     *
     * @return hodnota 400
     */
    @Override
    public int dajSiluUtoku() {
        return 400;
    }
    /**
     * Určuje, či Obor môže zaútočiť (po uplynutí 120 tickov od posledného útoku).
     *
     * @return true, ak uplynulo aspoň 120 tickov, inak false
     */
    @Override
    public boolean mozeUtok() {
        return this.dajPocitadloUtoku() >= 120;
    }


}
