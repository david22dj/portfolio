
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Trieda PamatPreHracov spravuje zoznam hráčov a ich rekordy. 
 * Poskytuje funkcie na pridanie nových hráčov, aktualizáciu ich rekordov 
 * a získanie tabuľky hráčov.
 */
public class PamatPreHracov {
    private String nazovSuboru;
    private ArrayList<String> zoznamHracovMena;
    private ArrayList<Double> zoznamHracovRecord;
    private String menoPrihlasenehoHraca;
    
    /**
     * Konštruktor, ktorý inicializuje triedu s menom aktuálne prihlasovaného hráča.
     * Ak hráč neexistuje v pamäťovom súbore, pridá sa s predvoleným rekordom 0.0.
     *
     * @param menoPrihlasenehoHraca Meno hráča, ktorý sa prihlasuje.
     * @throws IOException Ak nastane problém s prístupom k pamäťovému súboru.
     */
    public PamatPreHracov(String menoPrihlasenehoHraca) throws IOException {
        this.nazovSuboru = "pamatPreHracov.txt";
        this.zoznamHracovMena = new ArrayList<String>();
        this.zoznamHracovRecord = new ArrayList<Double>();
        this.nacitajHracovZPamate();
        this.ulozNovehoHraca(menoPrihlasenehoHraca);
        this.menoPrihlasenehoHraca = menoPrihlasenehoHraca;
    }
    
    /**
     * Konštruktor, ktorý inicializuje triedu bez prihlasovaného hráča.
     * Načíta zoznam hráčov zo súboru.
     *
     * @throws IOException Ak nastane problém s prístupom k pamäťovému súboru.
     */
    public PamatPreHracov() throws IOException {
        this.nazovSuboru = "pamatPreHracov.txt";
        this.zoznamHracovMena = new ArrayList<String>();
        this.zoznamHracovRecord = new ArrayList<Double>();
        this.nacitajHracovZPamate();
    }
    
    /**
     * Načíta zoznam hráčov a ich rekordy z pamäťového súboru.
     *
     * @throws IOException Ak súbor neexistuje alebo ho nie je možné načítať.
     */
    public void nacitajHracovZPamate() throws IOException {
        File subor = new File(this.nazovSuboru);
        Scanner citac = new Scanner(subor);
        
        while (citac.hasNextLine()) {
            String riadok = citac.nextLine().trim();
            String[] casti = riadok.split(" ");
            String meno = casti[0];
            double record = Double.parseDouble(casti[1].replace(',', '.'));
            
            this.zoznamHracovMena.add(meno);
            this.zoznamHracovRecord.add(record);
        }
        citac.close();
    }
    
    /**
     * Pridá nového hráča do zoznamu, ak ešte neexistuje, a uloží zoznam do súboru.
     *
     * @param novyHrac Meno nového hráča.
     * @throws IOException Ak nastane problém so zápisom do súboru.
     */
    public void ulozNovehoHraca(String novyHrac) throws IOException {
        
        for (String hrac : this.zoznamHracovMena) {
            if (novyHrac.equals(hrac)) {
                return;
            }
        }
        
        this.zoznamHracovMena.add(novyHrac);
        this.zoznamHracovRecord.add(0.0);
        
        File subor = new File(this.nazovSuboru);
        PrintWriter zapisovac = new PrintWriter(subor);
        
        for (int i = 0; i < this.zoznamHracovMena.size(); i++) {
            String meno = this.zoznamHracovMena.get(i);
            double record = this.zoznamHracovRecord.get(i);
            zapisovac.printf("%s %f%n", meno, record);
        }
    
        zapisovac.close();
    }
    
    /**
     * Vymaže všetkých hráčov zo zoznamu aj zo súboru.
     *
     * @throws IOException Ak nastane problém pri manipulácii so súborom.
     */
    public void vymazVsetkychHracov() throws IOException {
        File subor = new File(this.nazovSuboru);
        PrintWriter zapisovac = new PrintWriter(subor);
        zapisovac.close();
    }
    
    /**
     * Vytvorí a vráti tabuľku hráčov vo forme textového reťazca. 
     * Pred vytvorením tabuľky aktualizuje rekord aktuálneho hráča, 
     * ak nový rekord je lepší než existujúci.
     *
     * @param novyRecord Nový rekord aktuálneho hráča.
     * @return Tabuľka hráčov vo forme textového reťazca.
     * @throws IOException Ak nastane problém pri manipulácii so súborom.
     */
    public String vytvorTabulkuHracov(double novyRecord) throws IOException {
        this.upravHracovRecord(novyRecord);
        StringBuilder tabulka = new StringBuilder();
        tabulka.append("Tabuľka hráčov:\n");
        for (int i = 0; i < this.zoznamHracovMena.size(); i++) {
            String meno = this.zoznamHracovMena.get(i);
            double record = this.zoznamHracovRecord.get(i);
            tabulka.append(String.format("               %d. %s    record: %.2f%n", i + 1, meno, record));
        }
        return tabulka.toString();
    }
    
    /**
     * Aktualizuje rekord aktuálneho hráča, ak nový rekord je lepší než existujúci.
     * Zmeny sa zároveň ukladajú do súboru.
     *
     * @param novyRecord Nový rekord aktuálneho hráča.
     * @throws IOException Ak nastane problém pri ukladaní do súboru.
     */
    public void upravHracovRecord(double novyRecord) throws IOException  {
        int index = -1;
        
        for (int i = 0; i < this.zoznamHracovMena.size(); i++) {
            String meno = this.zoznamHracovMena.get(i);
            if (meno != null && meno.equals(this.menoPrihlasenehoHraca)) {
                index = i;
                break;
            }
        }   
    
        if (index != -1) {
            if (this.zoznamHracovRecord.get(index) < novyRecord) {
                this.zoznamHracovRecord.set(index, novyRecord);
            }
        }
        
        File subor = new File(this.nazovSuboru);
        PrintWriter zapisovac = new PrintWriter(subor);
        
        for (int i = 0; i < this.zoznamHracovMena.size(); i++) {
            String meno = this.zoznamHracovMena.get(i);
            double record = this.zoznamHracovRecord.get(i);
            zapisovac.printf("%s %f%n", meno, record);
        }
    
        zapisovac.close();
    }
}
