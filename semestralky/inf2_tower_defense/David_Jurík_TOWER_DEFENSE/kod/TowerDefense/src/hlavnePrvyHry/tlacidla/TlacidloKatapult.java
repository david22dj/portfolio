package hlavnePrvyHry.tlacidla;

/**
 * Trieda reprezentujúca tlačidlo výberu katapultu.
 * Rozširuje základné správanie tlačidla triedy Tlacidlo so
 * špecifickou pozíciou a ikonou katapultu.
 */
public class TlacidloKatapult extends Tlacidlo {

    /**
     * Vytvorí tlačidlo katapultu na pevne definovanej pozícii (870, 695)
     * a načíta obrázok "katapult_0.png".
     */
    public TlacidloKatapult() {
        super(870, 695, "katapult_0.png");
    }
}
