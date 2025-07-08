import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\474 - Heads  Tails Probability - hotovy\\p474 - Heads  Tails Probability\\src\\vstup.txt"));

        double log10_2 = Math.log10(2.0);

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            double cislo = Math.floor(log10_2 * n + 1);
            double mantisa = Math.pow(2.0, cislo / log10_2 - n);
            System.out.printf("2^-%d = %.3fe-%.0f%n", n, mantisa, cislo);
        }

        scanner.close();
    }
}
