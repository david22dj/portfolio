import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Inak {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\374 - Big Mod\\p374 - Big Mod\\src\\vstup.txt"));
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

        for (int[] tc : testCases) {
            int B = tc[0];
            int P = tc[1];
            int M = tc[2];

            int vysledok = 1;
            int zaklad = B % M;


            while (P > 0) {
                if ((P & 1) == 1) {
                    vysledok = (int)((1L * vysledok * zaklad) % M);
                }
                zaklad = (int)((1L * zaklad * zaklad) % M);
                P >>= 1;
            }

            System.out.println(vysledok);
        }
    }
}