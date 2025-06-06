package hlavnePrvyHry.tlacidla;
/**
 * Trieda reprezentujúca tlačidlo výberu kuše.
 * Rozširuje základné správanie tlačidla triedy Tlacidlo so
 * špecifickou pozíciou a ikonou kuše.
 */
public class TlacidloKusa extends Tlacidlo {
    /**
     * Vytvorí tlačidlo kuše na pevne definovanej pozícii (1030, 695)
     * a načíta obrázok "kusa.png".
     */
    public TlacidloKusa() {
        super(1030, 695, "kusa.png");
    }
}
