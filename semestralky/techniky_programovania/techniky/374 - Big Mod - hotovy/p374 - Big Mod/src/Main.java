import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\374 - Big Mod - hotovy\\p374 - Big Mod\\src\\vstup.txt"));
        Scanner scanner = new Scanner(System.in);

        ArrayList<int[]> testCases = new ArrayList<>();

        while(scanner.hasNextLine()) {

            int[] pole = new int[3];

            int B = scanner.nextInt();
            int P = scanner.nextInt();
            int M = scanner.nextInt();

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            pole[0] = B;
            pole[1] = P;
            pole[2] = M;

            testCases.add(pole);
        }
        scanner.close();

        for (int[] testCase : testCases) {
            int bInt = testCase[0];
            int pInt = testCase[1];
            int mInt = testCase[2];

            BigInteger B = BigInteger.valueOf(bInt);
            BigInteger P = BigInteger.valueOf(pInt);
            BigInteger M = BigInteger.valueOf(mInt);

            BigInteger vysledok = B.modPow(P, M);
            System.out.println(vysledok);
        }
    }
}