import fri.shapesge.Obrazok;

/**
 * Túto triedu som celú prevzal zo svojej minuloročnej semestrálnej práce.
 * Trieda Pozadie reprezentuje pozadie v hre.
 * Trieda využíva knižnicu Shapesge.
 */
public class Pozadie {
    private Obrazok obrazok;
    private TypPozadia typPozadia = TypPozadia.POZADIE;
    private int x;
    private int y = -2400;
    private int noveX;
    private int noveY = -2400;
    
    /**
     * Konštruktor pre vytvorenie objektu Pozadie a inicializáciu zobrazených textových informácií.
     * Spúšťa metódu zobrazPozadie.
     */
    public Pozadie() {
        this.x = x;
        this.y = y;
        this.noveX = noveX;
        this.noveY = noveY;
        
        this.zobrazPozadie();
    }
    
    /**
     * Metóda na zobrazenie pozadia.
     */
    public void zobrazPozadie() {
        this.typPozadia = this.typPozadia.POZADIE;
        this.obrazok = new Obrazok(this.typPozadia.getLink(), this.x, this.y);
        this.obrazok.zobraz();
    }
    
    /**
     * Metóda na posunutie pozadia.
     */
    public void posunPozadie() {
        this.noveY += 100;
        this.obrazok.zmenPolohu(this.noveX, this.noveY);
    }
}
 