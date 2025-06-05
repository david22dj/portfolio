import java.util.Random;

/**
 * Túto triedu som celú prevzal zo svojej minuloročnej semestrálnej práce.
 * Trieda Platformy predstavuje pole platforiem v hre.
 * Poskytuje metódy na správu platforiem, ako ich pridávanie, načítanie a zistenie počtu.
 */
public class Platformy {
    private Platforma[] platformy;
    private Random random;
    private int poradie = 0;
    private Platforma platforma;
    private int nahodneX;
    private int nahodneY;
    
    /**
     * Konštruktor inicializuje pole platforiem a pridá pomocou metódy pridajPlatformu
     * platformy do poľa.
     */
    public Platformy() {
        
        this.platformy = new Platforma[5];
        this.random = new Random();
        
        this.pridajPlatformu(this.random.nextInt(401), 720);
        
        this.pridajPlatformu(this.random.nextInt(401), 572);
        this.pridajPlatformu(this.random.nextInt(401), 394);
        
        this.pridajPlatformu(this.random.nextInt(401), 216);
        this.pridajPlatformu(this.random.nextInt(401), 37);
        
        
        
    }
    
    /**
     * Pridá novú platformu na základe zadaných súradníc.
     * 
     * @param x Súradnica x novej platformy.
     * @param y Súradnica y novej platformy.
     */
    public void pridajPlatformu(int x, int y) {
        if (this.poradie < this.platformy.length) {
            this.platformy[this.poradie] = new Platforma(x, y);
            this.poradie++;
        }
    }
    
    /**
     * Vráti i-ty prvok poľa platforiem.
     * 
     * @param i Index prvku, ktorý má byť vrátený.
     * @return i-ty prvok poľa platforiem alebo null, ak index nie je platný.
     */
    public Platforma getPlatforma(int i) {
        if (i >= 0 && i < this.poradie) {
            return this.platformy[i];
        }
        return null;
    }
    
    /**
     * Vráti počet platforiem v poli.
     * 
     * @return Počet platforiem v poli.
     */
    public int getPocetPlatforiem() {
        return this.poradie;
    }
    
}
