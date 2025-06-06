package nepriatelia;

import fri.shapesge.Obrazok;
/**
 * Trieda reprezentujúca Rytiera – stredne rýchleho nepriateľa so stredným počtom životov a útocnou animáciou.
 * Po dosiahnutí konca cesty prepne na sekvenciu útocných obrázkov.
 */
public class Rytier extends Nepriatel {
    private int animacia;
    private int pocitadloAnimacie;
    private int utocnaAnimacia;
    private boolean dorazilNaKoniec;
    /**
     * Konštruktor, ktorý inicializuje Rytiera so 500 životmi a rýchlosťou 2 ticky na krok.
     * Nastaví počiatočný obrázok a umiestni ho na aktuálnu pozíciu.
     */
    public Rytier() {
        super(500, 2);
        Obrazok obrazok = new Obrazok("rytier_0.png");
        obrazok.zmenPolohu(getX() - 25, getY() - 75);
        obrazok.zobraz();
        setObrazok(obrazok);
        this.animacia = 0;
        this.pocitadloAnimacie = 0;
        this.utocnaAnimacia = 0;
        this.dorazilNaKoniec = false;
    }
    /**
     * Aktualizuje stav Rytiera každým tickom:
     * – pohybuje sa po ceste a strieda pásmovú animáciu (každých 17 tickov),
     * – po dosiahnutí konca cesty prepne na útocnú animáciu striedajúcu tri obrázky.
     */
    @Override
    public void aktualizuj() {
        this.pocitadloAnimacie++;
        getObrazok().skry();
        getObrazok().zobraz();
        pohybujSa();

        if (!this.dorazilNaKoniec) {


            if (getIndexBodu() >= getCesta().getVelkost() - 1) {
                this.dorazilNaKoniec = true;
            }

            if (this.pocitadloAnimacie % 17 == 0) {
                if (this.animacia == 0) {
                    getObrazok().zmenObrazok("rytier_1.png");
                    this.animacia = 1;
                } else {
                    getObrazok().zmenObrazok("rytier_0.png");
                    this.animacia = 0;
                }
            }


        } else {
            if (this.pocitadloAnimacie % 17 == 0) {
                switch (this.utocnaAnimacia) {
                    case 0:
                        getObrazok().zmenObrazok("rytierUtoci_0.png");
                        this.utocnaAnimacia = 1;
                        break;
                    case 1:
                        getObrazok().zmenObrazok("rytierUtoci_1.png");
                        this.utocnaAnimacia = 2;
                        break;
                    case 2:
                        getObrazok().zmenObrazok("rytierUtoci_2.png");
                        this.utocnaAnimacia = 0;
                        break;
                }
            }
        }
    }
    /**
     * Vracia silu útoku Rytiera (50 bodov poškodenia).
     *
     * @return hodnota 50
     */
    @Override
    public int dajSiluUtoku() {
        return 50;
    }
    /**
     * Určuje, či Rytier môže zaútočiť (po uplynutí 30 tickov od posledného útoku).
     *
     * @return true, ak uplynulo aspoň 30 tickov, inak false
     */
    @Override
    public boolean mozeUtok() {
        return this.dajPocitadloUtoku() >= 30;
    }

}
