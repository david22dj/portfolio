import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky 1\\JugglingPrediction\\JugglingPrediction\\src\\text.txt"));
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> testCases = new ArrayList<>();
        while (scanner.hasNextLine()) {
            testCases.add(scanner.nextLine());
        }

        ArrayList<String> vysledky = new ArrayList<>();

        int pocetTestCase = 0;

        while (pocetTestCase < testCases.size()) {


            String[] splitujem = testCases.get(pocetTestCase).split(" ");

            String prva = splitujem[0];
            String druha = splitujem[1];
            String tretia = splitujem[2];
            BigInteger pocet = new BigInteger(splitujem[3]);

            int pocetSkutocny = pocet.mod(BigInteger.valueOf(6)).intValue();

            String[] pole = new String[3];

            pole[0] = prva;
            pole[1] = druha;
            pole[2] = tretia;

//            for (int i = 0; i < pocetSkutocny; i++) {
//                if ((i % 2) == 0) {
//                    String premenna = pole[1];
//                    pole[1] = pole[0];
//                    pole[0] = premenna;
//                } else {
//                    String premenna = pole[2];
//                    pole[2] = pole[1];
//                    pole[1] = premenna;
//                }
//
//            }
            for (int i = 0; i < pocetSkutocny; i++) {
                if (i % 2 == 0) {
                    String temp = pole[0];
                    pole[0] = pole[1];
                    pole[1] = temp;
                } else {
                    String temp = pole[1];
                    pole[1] = pole[2];
                    pole[2] = temp;
                }
            }



            vysledky.add(pole[0] + " " + pole[1] + " " + pole[2]);
            pocetTestCase++;

        }


        for (int i = 0; i < pocetTestCase; i++) {
            System.out.print(vysledky.get(i));
            if (i < pocetTestCase - 1) {
                System.out.println();
            }
        }
    }
}

