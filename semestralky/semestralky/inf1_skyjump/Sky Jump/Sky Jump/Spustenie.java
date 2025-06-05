import java.io.IOException;

/**
 * Túto triedu som celú prevzal zo svojej minuloročnej semestrálnej práce.
 * Táto trieda s názvom Spustenie slúži ako vstupný bod hry.
 * Inicializuje inštanciu triedy NacitanieUdajov a spúšťa hru.
 */
public class Spustenie {
    /**
     * Hlavná metóda programu, ktorá sa spustí pri spustení hry.
     * Inicializuje načítanie údajov pre hru
     * @param args
     * @throws IOException ak dôjde k chybe pri práci so súborom
     */
    public static void main(String[] args) throws IOException {
        NacitanieUdajov nacitanieUdajov = new NacitanieUdajov();
    }
}
