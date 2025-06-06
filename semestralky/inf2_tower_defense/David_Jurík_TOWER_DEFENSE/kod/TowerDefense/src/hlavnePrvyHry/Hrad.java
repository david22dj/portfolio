package hlavnePrvyHry;
/**
 * Trieda reprezentujúca hrad s určitým počtom životov.
 * Zobrazuje grafický ukazovateľ životov (HP bar) na pevnej pozícii.
 */
public class Hrad {
    private int x;
    private int y;
    private int zivot;
    private HpBar ukazovatelZivota;
    /**
     * Vytvorí nový hrad s daným počtom životov.
     * Inicializuje HP bar so šírkou 200px a výškou 15px na prednastavenej pozícii.
     *
     * @param zivot počiatočný počet životov hradu
     */
    public Hrad(int zivot) {
        this.x = 979;
        this.y = 34;
        this.zivot = zivot;
        this.ukazovatelZivota = new HpBar(this.zivot, 200, 15);
        this.ukazovatelZivota.setPozicia(this.x, this.y);
    }
    /**
     * Odoberie hradu zadaný počet životov a aktualizuje HP bar.
     *
     * @param kolko počet životov, o ktoré sa má hrad znížiť
     */
    public void uberZivot(int kolko) {
        this.zivot -= kolko;
        this.ukazovatelZivota.upravHp(this.zivot);
    }
    /**
     * Vráti aktuálny počet zostávajúcich životov hradu.
     *
     * @return aktuálna hodnota života
     */
    public int dajZivot() {
        return this.zivot;
    }
}
