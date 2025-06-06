package hlavnePrvyHry;

import java.util.HashMap;
import java.util.Map;
/**
 * Trieda spravujúca kolekciu všetkých dostupných levelov hry.
 * Internálne ukladá mapu číslo levelu → inštancia Level.
 */
public class Levely {
    private Map<Integer, Level> levely;
    /**
     * Konštruktor inicializujúci mapu všetkých levelov
     * s pevne preddefinovanými parametrami pre každý level.
     */
    public Levely() {
        this.levely = new HashMap<>();


        this.levely.put(1, new Level(1, 4, 1, 0));
        this.levely.put(2, new Level(2, 5, 3, 2));
        this.levely.put(3, new Level(3, 7, 3, 2));
        this.levely.put(4, new Level(4, 9, 4, 3));
        this.levely.put(5, new Level(5, 12, 5, 3));
        this.levely.put(6, new Level(6, 15, 7, 4));
        this.levely.put(7, new Level(7, 19, 9, 5));
        this.levely.put(8, new Level(8, 24, 11, 6));
        this.levely.put(9, new Level(9, 25, 14, 8));
        this.levely.put(10, new Level(10, 26, 18, 10));
    }
    /**
     * Získa level podľa jeho čísla.
     *
     * @param cislo poradové číslo požadovaného levelu
     * @return inštancia Level pri úspešnom nájdení, inak null
     */
    public Level dajLevel(int cislo) {
        return this.levely.get(cislo);
    }
}