package hlavnePrvyHry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Trieda spravujúca načítanie a prácu s bodmi reprezentujúcimi cestu.
 * Cesta je načítaná zo súboru resources pomocou metódy nacitajCestu.
 */
public class Cesta {
    private ArrayList<BodCesty> cesta;

    /**
     * Vytvorí novú inštanciu Cesta a načíta zoznam bodov z preddefinovaného súboru.
     */
    public Cesta() {
        this.cesta = new ArrayList<>();
        this.nacitajCestu("cestaPixelov_smooth.txt");
    }
    /**
     * Načíta body cesty zo zadaného textového súboru.
     * Každý bod je reprezentovaný dvoma číslami (x, y) na samostatnom riadku.
     *
     * @param subor názov súboru obsahujúceho súradnice cesty
     */
    public void nacitajCestu(String subor) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(subor);
            if (inputStream == null) {
                throw new FileNotFoundException("Súbor " + subor + " sa nenašiel v resources.");
            }

            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                this.cesta.add(new BodCesty(x, y));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Skontroluje, či sa obdĺžnik s daným stredom a rozmermi priblížil ku ktorejkoľvek časti cesty
     * o zadaný okraj.
     *
     * @param xStred vodorovná súradnica stredu obdĺžnika
     * @param yStred zvislá súradnica stredu obdĺžnika
     * @param sirka  šírka obdĺžnika
     * @param vyska  výška obdĺžnika
     * @param okraj  vzdialenosť od okraja obdĺžnika na kontrolu blízkosti
     * @return true, ak je niektorý bod cesty v rámci tohto rozšíreného obdĺžnika; inak false
     */
    public boolean jeBlizkoCesty(int xStred, int yStred, int sirka, int vyska, int okraj) {
        int lavy = xStred - sirka / 2 - okraj;
        int pravy = xStred + sirka / 2 + okraj;
        int horny = yStred - vyska / 2 - okraj;
        int dolny = yStred + vyska / 2 + okraj;

        for (BodCesty bod : this.cesta) {
            int bx = bod.getX();
            int by = bod.getY();

            if (bx >= lavy && bx <= pravy && by >= horny && by <= dolny) {
                return true;
            }
        }
        return false;
    }
    /**
     * Vráti počet bodov načítaných v tejto ceste.
     *
     * @return veľkosť zoznamu bodov cesty
     */
    public int getVelkost() {
        return this.cesta.size();
    }
    /**
     * Poskytne bod cesty na zadanom indexe.
     *
     * @param index index bodu v zozname (0-based)
     * @return inštancia BodCesty na danej pozícii
     */
    public BodCesty getBod(int index) {
        return this.cesta.get(index);
    }


}
