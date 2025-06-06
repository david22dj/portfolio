
package hlavnePrvyHry;
/**
 * Výnimka signalizujúca pokus o umiestnenie veže príliš blízko hernej cesty.
 * Vyhodená v prípade, že zvolená pozícia kríži alebo je príliš blízko ceste.
 */
public class PrilisBlizkoKCesteException extends Exception {
    /**
     * Základný konštruktor s prednastavenou správou o príliš blízkej pozícii.
     */
    public PrilisBlizkoKCesteException() {
        super("Miesto je príliš blízko cesty na umiestnenie veže!");
    }
}
