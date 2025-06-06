package hlavnePrvyHry;
/**
 * Trieda reprezentujúca jeden level hry.
 * Uchováva číslo levelu a počty rôznych typov nepriateľov, ktorí sa v ňom spawnu.
 */
public class Level {
    private int cislo;
    private int pocetDedicnanov;
    private int pocetRytierov;
    private int pocetObrov;
    /**
     * Vytvorí nový level s daným číslom a počtami nepriateľov.
     *
     * @param cislo           poradové číslo levelu
     * @param pocetDedicnanov počet Dedinčanov, ktorí sa majú spawnuť
     * @param pocetRytierov   počet Rytierov, ktorí sa majú spawnuť
     * @param pocetObrov      počet Obrov, ktorí sa majú spawnuť
     */
    public Level(int cislo, int pocetDedicnanov, int pocetRytierov, int pocetObrov) {
        this.cislo = cislo;
        this.pocetDedicnanov = pocetDedicnanov;
        this.pocetRytierov = pocetRytierov;
        this.pocetObrov = pocetObrov;
    }


    /**
     * Vracia počet Dedinčanov, ktorí sa spawnu v tomto leveli.
     *
     * @return počet Dedinčanov
     */
    public int getPocetDedicnanov() {
        return this.pocetDedicnanov;
    }
    /**
     * Vracia počet Rytierov, ktorí sa spawnu v tomto leveli.
     *
     * @return počet Rytierov
     */
    public int getPocetRytierov() {
        return this.pocetRytierov;
    }
    /**
     * Vracia počet Obrov, ktorí sa spawnu v tomto leveli.
     *
     * @return počet Obrov
     */
    public int getPocetObrov() {
        return this.pocetObrov;
    }


}
