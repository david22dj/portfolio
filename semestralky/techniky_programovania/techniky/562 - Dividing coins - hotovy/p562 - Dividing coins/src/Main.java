import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\562 - Dividing coins - hotovy\\p562 - Dividing coins\\src\\vstup.txt"));
        //Scanner scanner = new Scanner(System.in);

        ArrayList<int[]> testCases = new ArrayList<>();
        
        int pocetTestCases = Integer.valueOf(scanner.nextLine());

        for (int i = 0; i < pocetTestCases; i++) {
            int pocetMinci = Integer.valueOf(scanner.nextLine());
            String[] parts = scanner.nextLine().split(" ");
            
            int[] pole = new int[pocetMinci];

            for (int j = 0; j < pocetMinci; j++) {
                pole[j] = Integer.valueOf(parts[j]);
            }

            testCases.add(pole);
        }

        for (int[] mince : testCases) {
            int sucet = 0;
            for (int i : mince) {
                sucet += i;
            }
            int ciel = sucet / 2;

            boolean[] mozmeSpravit = new boolean[ciel + 1];
            mozmeSpravit[0] = true;

            for (int minca : mince) {
                for (int s = ciel; s >= minca; s--) {
                    if (mozmeSpravit[s - minca]) {
                        mozmeSpravit[s] = true;
                    }
                }
            }

            int najlepsi = 0;
            for (int s = ciel; s >= 0; s--) {
                if (mozmeSpravit[s]) {
                    najlepsi = s;
                    break;
                }
            }

            int rozdiel = sucet - 2 * najlepsi;
            System.out.println(rozdiel);
        }
        
    }
}