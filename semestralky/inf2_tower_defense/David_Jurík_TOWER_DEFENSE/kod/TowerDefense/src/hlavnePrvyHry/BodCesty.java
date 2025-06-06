package hlavnePrvyHry;
/**
 * Trieda reprezentujúca jeden bod na ceste pomocou x a y súradníc.
 */
public class BodCesty {
    private int x;
    private int y;
    /**
     * Vytvorí nový bod cesty na zadanej pozícii.
     *
     * @param x  vodorovná súradnica bodu
     * @param y  zvislá súradnica bodu
     */
    public BodCesty(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Vracia vodorovnú (X) súradnicu tohto bodu.
     *
     * @return hodnota x súradnice
     */
    public int getX() {
        return this.x;
    }
    /**
     * Vracia zvislú (Y) súradnicu tohto bodu.
     *
     * @return hodnota y súradnice
     */
    public int getY() {
        return this.y;
    }

}
