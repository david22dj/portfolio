import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\5555555555555555555555\\415 - Sunrise\\p415 - Sunrise\\src\\vstup.txt"));

        ArrayList<Double> testCases = new ArrayList<>();

        while(scanner.hasNext()) {
            double testCase = Double.parseDouble(scanner.nextLine());
            testCases.add(testCase);
        }

        final double vzdialenostSlnka      = 92_900_000.0; //zadanie
        final double polomerSlnka         =   432_000.0; //zadanie
        final double uholovyPolomerSlnka  = Math.atan(polomerSlnka / vzdialenostSlnka);//kolko radianov zabera polomer slnecneho disku
        final double dvePi                = 2 * Math.PI; // uplny kruh v radianoch
        final double sekundyOtocenie      = 24 * 3600.0;
        final double uhlovaRychlost       = dvePi / sekundyOtocenie; // UR = 2pi/T

        for (double cas : testCases) {
            //stred disku nad horizontom (v radianoch) // vyskaStredu = −θ+ω×t
            double vyskaStredu = -uholovyPolomerSlnka + uhlovaRychlost * cas;//kolko radiaov nad horizontom sa nachadza stred slnecknehp disku v case t

            double viditelnyZlomok;
            if (vyskaStredu <= -uholovyPolomerSlnka) {
                //cele pod horizontom
                viditelnyZlomok = 0.0;
            }
            else if (vyskaStredu >= uholovyPolomerSlnka) {
                //cele nad horizontom
                viditelnyZlomok = 1.0;
            }
            else {
                //ciastocne nad horizontom: plocha segmentu kruhu
                double y = vyskaStredu;
                double R = uholovyPolomerSlnka;
                double pomer = (Math.acos(-y / R) + (y / R) * Math.sqrt(1 - (y / R) * (y / R))) / Math.PI;// z netu
                viditelnyZlomok = pomer;
            }

            System.out.printf("%.6f\n", viditelnyZlomok);
        }
    }
}
