package hlavnePrvyHry.tlacidla;
/**
 * Trieda reprezentujúca tlačidlo výberu plameňometu.
 * Rozširuje základné správanie tlačidla triedy Tlacidlo so
 * špecifickou pozíciou a ikonou plameňometu.
 */
public class TlacidloPlamenomet extends Tlacidlo {
    /**
     * Vytvorí tlačidlo plameňometu na pevne definovanej pozícii (950, 695)
     * a načíta obrázok "plamenomet.png".
     */
    public TlacidloPlamenomet() {
        super(950, 695, "plamenomet.png");
    }
}
