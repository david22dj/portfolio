import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky 1\\Bit Switch Cipher\\src\\text.txt"));

        int T = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Integer> vysledky = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            String riadok = scanner.nextLine();
            String[] parts = riadok.split(" ");

            int L = scanner.nextInt();
            scanner.nextLine();

            for (int j = 0; j < L; j++) {
                int cislo = Integer.parseInt(scanner.nextLine());
                int vysledneCislo = 0;
                for (int k = 0; k < parts.length; k++) {
                    int bit = (cislo >> k) & 1;
                    int pozicia = Integer.parseInt(parts[k]);
                    vysledneCislo |= (bit << pozicia);
                }
                vysledky.add(vysledneCislo);

            }

        }

        for (int i = 0; i < vysledky.size(); i++) {
            if (i == vysledky.size() - 1) {
                System.out.print(vysledky.get(i));
            } else {
                System.out.println(vysledky.get(i));
            }
        }
    }
}