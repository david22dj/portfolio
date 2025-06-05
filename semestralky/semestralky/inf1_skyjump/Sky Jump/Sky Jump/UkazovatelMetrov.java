import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;
import java.io.IOException;
/**
 * Túto triedu som celú prevzal zo svojej minuloročnej semestrálnej práce.
 * Trieda UkazovatelMetrov reprezentuje vizuálny ukazovateľ pre zobrazenie prejdených metrov.
 * Trieda umožňuje zobrazovať aktuálny počet prejdených metrov hráča a kontroluje, či sa tento počet
 * stal novým rekordom. Ak je dosiahnutý nový rekord, uloží ho do pamäte.
 * Trieda využíva knižnicu Shapesge.
 */
public class UkazovatelMetrov {
    private String metreString;
    private BlokTextu blokTextu;
    private StylFontu stylFontu = StylFontu.BOLD;
    
    private String recordMetreString;
    private BlokTextu recordBlokTextu;
    
    private Pamat pamat;
    /**
     * Konštruktor pre vytvorenie objektu UkazovatelMetrov a inicializáciu 
     * zobrazovaných textových informácií.
     */
    public UkazovatelMetrov() throws IOException {
        this.blokTextu = new BlokTextu("0.00", 360 , 60);
        this.blokTextu.zmenFarbu("red");
        
        this.blokTextu.zmenFont("Times New Roman", this.stylFontu, 20);
        this.blokTextu.zobraz();
        
        this.pamat = new Pamat();
        double record = this.pamat.nacitajMetreZPamate();
        this.recordMetreString = "Record: " + record;
        this.recordBlokTextu = new BlokTextu(this.recordMetreString, 40 , 60);
        
        this.recordBlokTextu.zmenFarbu("red");
        
        this.recordBlokTextu.zmenFont("Times New Roman", this.stylFontu, 20);
        this.recordBlokTextu.zobraz();
    }
    
    /**
     * Metóda pre aktualizáciu zobrazených metrov.
     * 
     * @param metre Dĺžka, ktorú hráč prešiel.
     */
    public void zmenMetre(double metre) {
        this.metreString = Double.toString(metre);
        this.blokTextu.zmenText(this.metreString + " " + "metrov");
    }
    
    /**
     * Metóda na zmenu zobrazených metrov a kontrolu, či sa prekonal rekord.
     * Ak je nový rekord, uloží ho do pamäte.
     * 
     * @param metre Dĺžka, ktorú hráč prešiel, v metroch.
     * @throws IOException Ak dôjde k chybe pri ukladaní nového rekordu.
     */
    public void zmenRecord(double metre) throws IOException {
        this.metreString = Double.toString(metre);
        this.blokTextu.zmenText(this.metreString + " " + "metrov");
        if (metre > this.pamat.nacitajMetreZPamate()) {
            this.pamat.ulozMetreDoPamate(metre);
        }
    }
}
