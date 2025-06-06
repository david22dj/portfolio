package hlavnePrvyHry;

import fri.shapesge.Obdlznik;
import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;
/**
 * Trieda zodpovedná za zobrazenie a správu stavu peňazí hráča.
 * Vykresľuje panel s ikonou zlata, aktuálnym stavom mincí
 * a cenami dostupných vylepšení (katapult, plameňomet, kuša).
 */
public class BarPenazi {
    private Obdlznik pozadie;
    private BlokTextu text;
    private Obrazok zlato;
    private int peniaze;

    private static final int ZAKLADNA_CENA_KATAPULTU   = 120;
    private static final int ZAKLADNA_CENA_PLAMENOMETU = 110;
    private static final int ZAKLADNA_CENA_KUSY       = 40;

    private int aktualnaCenaKatapultu;
    private int aktualnaCenaPlamenometu;
    private int aktualnaCenaKusy;

    private BlokTextu cenaKatapult;
    private BlokTextu cenaPlamenomet;
    private BlokTextu cenaKusa;
    /**
     * Inicializuje panel peňazí so zadaným počiatočným počtom mincí.
     * Vykreslí pozadie, ikonku zlata, text so stavom a ceny jednotlivých zbraní.
     *
     * @param pociatocneMince počiatočný počet mincí hráča
     */

    public BarPenazi(int pociatocneMince) {
        this.peniaze = pociatocneMince;


        this.pozadie = new Obdlznik(35, 56);
        this.pozadie.zmenStrany(130, 25);
        this.pozadie.zmenFarbu("brown");
        this.pozadie.zobraz();

        this.text = new BlokTextu("" + this.peniaze, 80, 80);
        this.text.zmenFarbu("yellow");
        this.text.zmenFont("Arial", StylFontu.BOLD, 30);
        this.text.zobraz();

        this.zlato = new Obrazok("zlato.png");
        this.zlato.zmenPolohu(15, 32);
        this.zlato.zobraz();

        this.cenaKatapult = new BlokTextu("120", 885, 685);
        this.cenaKatapult.zmenFont("Arial", StylFontu.BOLD, 20);
        this.cenaKatapult.zmenFarbu("black");
        this.cenaKatapult.zobraz();

        this.cenaPlamenomet = new BlokTextu("110", 970, 685);
        this.cenaPlamenomet.zmenFont("Arial", StylFontu.BOLD, 20);
        this.cenaPlamenomet.zmenFarbu("black");
        this.cenaPlamenomet.zobraz();

        this.cenaKusa = new BlokTextu("40", 1050, 685);
        this.cenaKusa.zmenFont("Arial", StylFontu.BOLD, 20);
        this.cenaKusa.zmenFarbu("black");
        this.cenaKusa.zobraz();
        this.nastavCenyPodlaLevelu(1);
    }
    /**
     * Nastaví ceny jednotiek podľa čísla levelu.
     * Level 1 → základné ceny; každý ďalší level +5.
     *
     * @param level číslo aktuálneho levelu
     */
    public void nastavCenyPodlaLevelu(int level) {
        this.aktualnaCenaKatapultu   = ZAKLADNA_CENA_KATAPULTU   + (level - 1) * 5;
        this.aktualnaCenaPlamenometu = ZAKLADNA_CENA_PLAMENOMETU + (level - 1) * 5;
        this.aktualnaCenaKusy        = ZAKLADNA_CENA_KUSY        + (level - 1) * 5;

        this.cenaKatapult.zmenText("" + this.aktualnaCenaKatapultu);
        this.cenaPlamenomet.zmenText("" + this.aktualnaCenaPlamenometu);
        this.cenaKusa.zmenText("" + this.aktualnaCenaKusy);
    }
    /** @return aktuálna cena katapultu */
    public int dajCenuKatapultu() {
        return this.aktualnaCenaKatapultu;
    }
    /** @return aktuálna cena plameňometu */
    public int dajCenuPlamenometu() {
        return this.aktualnaCenaPlamenometu;
    }
    /** @return aktuálna cena kuše */
    public int dajCenuKusy() {
        return this.aktualnaCenaKusy;
    }

    /**
     * Pridá zadanú sumu mincí do stavu a aktualizuje zobrazený text.
     *
     * @param suma počet mincí, ktoré sa majú pridať
     */
    public void pridaj(int suma) {
        this.peniaze += suma;
        this.aktualizujText();
    }
    /**
     * Odoberie zadanú sumu mincí zo stavu a aktualizuje zobrazený text.
     *
     * @param suma počet mincí, ktoré sa majú odobrať
     */
    public void uber(int suma) {
        this.peniaze -= suma;
        this.aktualizujText();
    }
    /**
     * Vráti aktuálny stav peňazí.
     *
     * @return počet mincí, ktoré hráč momentálne má
     */
    public int dajStav() {
        return this.peniaze;
    }
    /**
     * Interná metóda na prepis textového bloku na novú hodnotu stavu
     * a jeho opätovné zobrazenie.
     */
    private void aktualizujText() {
        this.text.zmenText("" + this.peniaze);
        this.text.zobraz();
    }
    /**
     * Aktualizuje farby textu cien zbraní podľa dostupného stavu mincí:
     * – čierna, ak hráč má dosť peňazí na kúpu,
     * – červená, ak nemá dostatok prostriedkov.
     */
    public void aktualizujFarbyCien() {
        if (this.peniaze >= this.aktualnaCenaKatapultu) {
            this.cenaKatapult.zmenFarbu("black");
            this.text.zobraz();
        } else {
            this.cenaKatapult.zmenFarbu("red");
            this.text.zobraz();
        }

        if (this.peniaze >= this.aktualnaCenaPlamenometu) {
            this.cenaPlamenomet.zmenFarbu("black");
            this.text.zobraz();
        } else {
            this.cenaPlamenomet.zmenFarbu("red");
            this.text.zobraz();
        }

        if (this.peniaze >= this.aktualnaCenaKusy) {
            this.cenaKusa.zmenFarbu("black");
            this.text.zobraz();
        } else {
            this.cenaKusa.zmenFarbu("red");
            this.text.zobraz();
        }
    }
    /**
     * Skryje celý panel peňazí vrátane pozadia, ikony zlata a všetkých cien.
     */
    public void skryPanel() {
        this.pozadie.skry();
        this.text.skry();
        this.zlato.skry();
        this.cenaKatapult.skry();
        this.cenaPlamenomet.skry();
        this.cenaKusa.skry();
    }
    /**
     * Zobrazí (alebo obnoví) celý panel peňazí vrátane
     * pozadia, ikony zlata, textu so sumou a cien.
     */
    public void zobrazPanel() {

        this.pozadie.zobraz();
        this.zlato.zobraz();

        this.text.zmenText("" + this.peniaze);
        this.text.zobraz();

        this.cenaKatapult.zobraz();
        this.cenaPlamenomet.zobraz();
        this.cenaKusa.zobraz();

        this.aktualizujFarbyCien();
    }
}
