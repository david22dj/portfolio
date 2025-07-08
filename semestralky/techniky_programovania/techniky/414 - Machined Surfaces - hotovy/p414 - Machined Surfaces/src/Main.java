import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\414 - Machined Surfaces - hotovy\\p414 - Machined Surfaces\\src\\vstup.txt"));

        ArrayList<ArrayList<char[]>> testCases = new ArrayList<>();

        while (true) {
            String riadok = scanner.nextLine();
            int pocet = Integer.parseInt(riadok);
            if (pocet == 0) {
                break;
            }

            ArrayList<char[]> jeden = new ArrayList<>();

            for (int i = 0; i < pocet; i++) {
                String line = scanner.nextLine();
                char[] pole = new char[25];
                for (int j = 0; j < 25; j++) {
                    pole[j] = line.charAt(j);
                }
                jeden.add(pole);
            }

            testCases.add(jeden);
        }

        for (ArrayList<char[]> testCase : testCases) {
            ArrayList<Integer> vzdialenosti = new ArrayList<>();

            for (char[] riadok : testCase) {
                int endLeft = 0;
                while (endLeft < 25 && riadok[endLeft] == 'X') {
                    endLeft++;
                }
                endLeft = Math.max(0, endLeft - 1);

                int startRight = 24;
                while (startRight >= 0 && riadok[startRight] == 'X') {
                    startRight--;
                }
                startRight = Math.min(24, startRight + 1);

                int vzdialenost = startRight - endLeft - 1;

                if (vzdialenost >= 0) {
                    vzdialenosti.add(vzdialenost);
                } else {
                    vzdialenosti.add(0);
                }
            }

            int minimalnyPosun = Integer.MAX_VALUE;
            for (int d : vzdialenosti) {
                minimalnyPosun = Math.min(minimalnyPosun, d);
            }

            int konecnySucet = 0;

            for (char[] riadok : testCase) {
                int endLeft = 0;
                while (endLeft < 25 && riadok[endLeft] == 'X') {
                    endLeft++;
                }
                endLeft = Math.max(0, endLeft - 1);

                int startRight = 24;
                while (startRight >= 0 && riadok[startRight] == 'X') {
                    startRight--;
                }
                startRight = Math.min(24, startRight + 1);

                int lavyKoniecPoPosune = endLeft + minimalnyPosun;
                int medzera = startRight - lavyKoniecPoPosune - 1;
                konecnySucet += Math.max(0, medzera);
            }

            System.out.println(konecnySucet);
        }
    }
}