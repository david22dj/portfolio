import fri.shapesge.Obrazok;

/**
 * Túto triedu som celú prevzal zo svojej minuloročnej semestrálnej práce.
 * Trieda Platforma predstavuje platformu v hre.
 * Trieda využíva knižnicu Shapesge.
 */
public class Platforma {
    private Obrazok obrazok;
    private int x;
    private int y;
    private TypPlatformy typPlatformy = TypPlatformy.HLINA;
    
    /**
     * Konštruktor pre vytvorenie platformy so zadanými súradnicami.
     * Spúšta medódu vytvorSa.
     * 
     * @param x Súradnica x platformy.
     * @param y Súradnica y platformy.
     */
    public Platforma(int x, int y) {
        this.x = x;
        this.y = y;
        this.vytvorSa();
        
    }
    
    /**
     * Vytvorí grafické zobrazenie platformy.
     */
    public void vytvorSa() {
        this.obrazok = new Obrazok(this.typPlatformy.getLink(), this.x , this.y);
        this.obrazok.zobraz();
    }
    
    /**
     * Nastaví nové x a y platforme.
     * Platforma zmení polohu.
     *
     * @param x Súradnica x pre novú pozíciu platformy.
     * @param y Súradnica y pre novú pozíciu platformy.
     */
    public void posunPlatformu(int x, int y) {
        this.obrazok.zmenPolohu(x, y);
        this.x = x;
        this.y = y;
    }
    
    /**
     * Zmení typ platformy na trávu.
     */
    public void zmenSaNaTravu() {
        this.typPlatformy = this.typPlatformy.TRAVA;
        this.obrazok.zmenObrazok(this.typPlatformy.getLink());
    }
    
    /**
     * Zmení typ platformy na oblak.
     */
    public void zmenSaNaOblak() {
        this.typPlatformy = this.typPlatformy.OBLAK;
        this.obrazok.zmenObrazok(this.typPlatformy.getLink());
    }
    
    /**
     * Zmení typ platformy na meteorit.
     */
    public void zmenSaNaMeteorit() {
        this.typPlatformy = this.typPlatformy.METEORIT;
        this.obrazok.zmenObrazok(this.typPlatformy.getLink());
    }
    
    /**
     * Vráti súradnicu x platformy.
     * 
     * @return Súradnica x platformy.
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Vráti súradnicu y platformy.
     * 
     * @return Súradnica y platformy.
     */
    public int getY() {
        return this.y;
    }
}
