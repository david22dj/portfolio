import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Trieda Pamat spravuje jednoduché ukladanie a načítanie údajov o nastúpaných metroch 
 * do/z textového súboru.
 */
public class Pamat {
    private String nazovSuboru;
    
    /**
     * Konštruktor, ktorý inicializuje názov súboru na ukladanie údajov.
     */
    public Pamat() {
        this.nazovSuboru = "pamat.txt";
    }
    
    /**
     * Načíta nastúpané metre zo súboru.
     * Ak súbor obsahuje číslo, vráti jeho hodnotu. Ak súbor neobsahuje platné údaje,
     * vráti predvolenú hodnotu 0.0.
     *
     * @return Nastúpané metre ako desatinné číslo.
     * @throws IOException Ak nastane problém s čítaním súboru.
     */
    public double nacitajMetreZPamate() throws IOException {
        File subor = new File(this.nazovSuboru);
        Scanner citac = new Scanner(subor);
        if (citac.hasNextDouble()) {
            double nastupaneMetre = citac.nextDouble();
            citac.close();
            return nastupaneMetre;
        } else {
            citac.close();
            return 0.0;
        }
    }
    
    /**
     * Uloží nové nastúpané metre do súboru.
     * Pred zápisom sa vymažú staré údaje v súbore.
     *
     * @param noveNastupaneMetre Nové nastúpané metre, ktoré sa majú uložiť.
     * @throws IOException Ak nastane problém so zápisom do súboru.
     */
    public void ulozMetreDoPamate(double noveNastupaneMetre) throws IOException {
        this.vymazStaryRecord();
        File subor = new File(this.nazovSuboru);
        PrintWriter zapisovac = new PrintWriter(subor);
        
        zapisovac.format("%f", noveNastupaneMetre);
        
        zapisovac.close();
    }
    
    /**
     * Vymaže všetky existujúce údaje zo súboru.
     *
     * @throws IOException Ak nastane problém pri manipulácii so súborom.
     */
    public void vymazStaryRecord() throws IOException {
        File subor = new File(this.nazovSuboru);
        PrintWriter zapisovac = new PrintWriter(subor);
        zapisovac.close();
    }
}
