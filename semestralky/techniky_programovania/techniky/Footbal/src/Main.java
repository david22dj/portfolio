import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        int testovaciePripady = scanner.nextInt();
        scanner.nextLine();


        while (testovaciePripady > 0) {
            HashMap<String, String> timy = new HashMap<>();
            String[][] vysledkyZapasov = new String[4][4];
            String[] skratkyVPoradi = new String[4];

            // Načítanie tímov a ich skratiek
            for (int i = 0; i < 4; i++) {
                String line = scanner.nextLine();
                String[] casti = line.split(" ", 2);
                if (casti.length < 2) {
                    System.err.println("CHYBA: Nesprávny formát riadku -> " + line);
                    continue;
                }

                timy.put(casti[1], casti[0]);
                skratkyVPoradi[i] = casti[0];
            }
            //vypisTabulku(skratkyVPoradi, vysledkyZapasov);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    vysledkyZapasov[i][j] = (i == j) ? " X " : "   ";
                }
            }

            scanner.nextLine();
            // Spracovanie výsledkov zápasov
            while (scanner.hasNextLine()) {
                String zapas = scanner.nextLine().trim();
                String[] parts = zapas.split(" - ");

                String timJedna = parts[0]; // "FC Valencia"
                String timDva = parts[1].substring(0, parts[1].lastIndexOf(" ")); // "Real Madrid"
                String skore = parts[1].substring(parts[1].lastIndexOf(" ") + 1); // "4:3"

                //System.out.println(timJedna + " vs " + timDva + " Skóre: " + skore);

                // Zobrazíme názvy tímov pre kontrolu
                //System.out.println("Tímy:");
               // System.out.println("Prvý tím: " + timJedna);
                //System.out.println("Druhý tím: " + timDva);
                ///.out.println("skore :" + skore);


                vysledkyZapasov[najdiIndex(skratkyVPoradi, getSkratkaTimu(timy, timJedna))][najdiIndex(skratkyVPoradi, getSkratkaTimu(timy, timDva))] = skore;

            }




            // Výpis tabuľky
            vypisTabulku(skratkyVPoradi, vysledkyZapasov);
            if (testovaciePripady > 1) {
                System.out.println();
            }
            testovaciePripady--;
        }

    }


    private static String getSkratkaTimu(HashMap<String, String> timy, String nazovTimu) {
        return timy.getOrDefault(nazovTimu, null);
    }


    private static int najdiIndex(String[] list, String kluc) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(kluc)) return i;
        }
        return -1;
    }


    private static void vypisTabulku(String[] skratky, String[][] vysledky) {
        System.out.println("+---+---+---+---+---+");
        System.out.print("|   ");
        for (String skratka : skratky) {
            System.out.print("|" + skratka);
        }
        System.out.println("|");
        System.out.println("+---+---+---+---+---+");

        for (int i = 0; i < 4; i++) {
            System.out.print("|" + skratky[i]);
            for (int j = 0; j < 4; j++) {
                System.out.print("|" + vysledky[i][j]);
            }
            System.out.println("|");
            System.out.println("+---+---+---+---+---+");
        }


    }
}
