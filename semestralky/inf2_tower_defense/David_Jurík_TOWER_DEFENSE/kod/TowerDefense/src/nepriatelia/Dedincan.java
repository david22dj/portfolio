package nepriatelia;

import fri.shapesge.Obrazok;
/**
 * Trieda reprezentujúca Dedinčana – základného nepriateľa s nízkou rýchlosťou a životmi.
 * Má jednoduchú animáciu striedania dvoch obrázkov každých 17 tickov.
 */
public class Dedincan extends Nepriatel {
    private int animacia;
    private int pocitadloAnimacie;
    /**
     * Konštruktor, ktorý inicializuje Dedinčana so 50 životmi a rýchlosťou 1 tick na krok.
     * Nastaví počiatočný obrázok a umiestni ho na aktuálnu pozíciu.
     */
    public Dedincan() {
        super(50, 1);
        Obrazok obrazok = new Obrazok("dedincan_0.png");
        obrazok.zmenPolohu(getX() - 25, getY() - 75);
        obrazok.zobraz();
        setObrazok(obrazok);
        this.animacia = 0;
        this.pocitadloAnimacie = 0;
    }
    /**
     * Aktualizuje stav Dedinčana každým tickom:
     * – pohybuje sa po ceste,
     * – obnovuje zobrazenie obrázka,
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
                getObrazok().zmenObrazok("dedincan_1.png");
                this.animacia = 1;
            } else {
                getObrazok().zmenObrazok("dedincan_0.png");
                this.animacia = 0;
            }
        }


    }
    /**
     * Vracia útočnú silu Dedinčana (1 bod poškodenia).
     *
     * @return hodnota 1
     */
    @Override
    public int dajSiluUtoku() {
        return 1;
    }
    /**
     * Určuje, či Dedinčan môže zaútočiť (po uplynutí 10 tickov od posledného útoku).
     *
     * @return true, ak uplynulo aspoň 10 tickov, inak false
     */
    @Override
    public boolean mozeUtok() {
        return this.dajPocitadloUtoku() >= 10;
    }

}
