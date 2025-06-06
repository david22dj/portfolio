import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky 1\\SumOfPowers\\SumOfPowers\\src\\text.txt"));

        ArrayList<int[]> testCases = new ArrayList<>();
        ArrayList<Integer> vysledky = new ArrayList<>();

        while (scanner.hasNext()) {
            int x = scanner.nextInt();
            if (x == -1) {
                break;
            }
            int n = scanner.nextInt();

            int[] premenna = new int[2];
            premenna[0] = x;
            premenna[1] = n;

            testCases.add(premenna);
        }

        for (int[] testCase : testCases) {
            int vysledok = najdi(testCase[0], testCase[1], 1);
            vysledky.add(vysledok);
        }

        for (int i = 0; i < vysledky.size(); i++) {
            System.out.print(vysledky.get(i));
            if (i < vysledky.size() - 1) {
                System.out.println();
            }
        }

    }

    public static int najdi(int x, int n, int cislo) {
        int mocnina = (int) Math.pow(cislo, n);
        if (mocnina > x) {
            return 0;
        }
        if (mocnina == x) {
            return 1;
        }

        return najdi(x - mocnina, n, cislo + 1) + najdi(x, n, cislo + 1);
    }
}
