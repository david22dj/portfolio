import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String[] riadky = {
                "....x.xxxxx.xxxxx.x...x.xxxxx.xxxxx.xxxxx.......xxxxx.xxxxx.xxxxx",
                "....x.....x.....x.x...x.x.....x.........x...x...x...x.x...x.x...x",
                "....x.....x.....x.x...x.x.....x.........x...x...x...x.x...x.x...x",
                "....x.xxxxx.xxxxx.xxxxx.xxxxx.xxxxx.....x.xxxxx.xxxxx.xxxxx.x...x",
                "....x.x.........x.....x.....x.x...x.....x...x...x...x.....x.x...x",
                "....x.x.........x.....x.....x.x...x.....x...x...x...x.....x.x...x",
                "....x.xxxxx.xxxxx.....x.xxxxx.xxxxx.....x.......xxxxx.xxxxx.xxxxx"
        };

        String[] kluce = {"1", "2", "3", "4", "5", "6", "7", "+", "8", "9", "0"};

        HashMap<String, String[]> asciiMap = new HashMap<>();

        for (int i = 0; i < kluce.length; i++) {

            String[] jednoCislo = new String[7];

            for (int j = 0; j < 7; j++) {
                jednoCislo[j] = riadky[j].substring(i * (5 + 1), (i * (5 + 1)) + 5);
            }
            asciiMap.put(kluce[i], jednoCislo);
        }

        String[] riadkyPrikladu = new String[7];

        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky 1\\ASCII\\src\\sample-ASCII ART.1.in"));

        for (int i = 0; i < 7; i++) {
            riadkyPrikladu[i] = scanner.nextLine();
        }


        String prveCislo = "";
        String druheCislo = "";
        boolean druheNacitavanie = false;

        for (int i = 0; i < (riadkyPrikladu[0].length() +1) / 6; i++) {

            String[] jednoCislo = new String[7];

            for (int j = 0; j < 7; j++) {
                jednoCislo[j] = riadkyPrikladu[j].substring(i * (5 + 1), (i * (5 + 1)) + 5);
            }

            String cislo = "";

            for (Map.Entry<String, String[]> stringEntry : asciiMap.entrySet()) {
                boolean zhodne = true;
                for (int j = 0; j < 7; j++) {

                    if (!stringEntry.getValue()[j].equals(jednoCislo[j])) {
                        zhodne = false;
                        break;
                    }
                }
                if (zhodne) {
                    cislo = stringEntry.getKey();
                    break;
                }
            }
            if (cislo.equals("+")) {
                druheNacitavanie = true;
                continue;
            }
            if (!druheNacitavanie) {
                prveCislo += cislo;
            } else {
                druheCislo += cislo;
            }

        }

        int vysledok = Integer.parseInt(prveCislo) + Integer.parseInt(druheCislo);

        String vysledokStr = String.valueOf(vysledok);
        for (int k = 0; k < 7; k++) {
            for (int i = 0; i < vysledokStr.length(); i++) {
                String znak = String.valueOf(vysledokStr.charAt(i));
                System.out.print(asciiMap.get(znak)[k]);
                if (i < vysledokStr.length() - 1) {
                    System.out.print(".");
                }
            }
            if (k != 6) {
                System.out.println();
            }

        }

    }

}