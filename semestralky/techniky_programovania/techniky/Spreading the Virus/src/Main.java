import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky 1\\Spreading the Virus\\src\\text.txt"));

        int pocetUzlov = scanner.nextInt();
        int pocetSpojeni = scanner.nextInt();
        scanner.nextLine();

        List<Integer>[] graf = new ArrayList[pocetUzlov + 1];

        for (int i = 0; i <= pocetUzlov; i++) {
            graf[i] = new ArrayList<>();
        }

        for (int i = 0; i < pocetSpojeni; i++) {
            String riadok = scanner.nextLine();
            String[] casti = riadok.split(" ");
            int a = Integer.parseInt(casti[0]);
            int b = Integer.parseInt(casti[1]);
            graf[a].add(b);
            graf[b].add(a);
        }

        boolean[] navstiveny = new boolean[pocetUzlov + 1];
        int maxVelkost = 0;

        for (int i = 1; i <= pocetUzlov; i++) {
            if (!navstiveny[i]) {
                int velkost = prehladavanie(i, graf, navstiveny);
                if (velkost > maxVelkost) {
                    maxVelkost = velkost;
                }
            }
        }

        System.out.print(maxVelkost);


    }
    public static int prehladavanie(int uzol, List<Integer>[] graf, boolean[] navstiveny) {
        List<Integer> naSpracovanie = new ArrayList<>();
        naSpracovanie.add(uzol);
        navstiveny[uzol] = true;
        int velkost = 1;

        while(!naSpracovanie.isEmpty()) {
            int aktualny = naSpracovanie.get(0);
            naSpracovanie.remove(0);

            for (Integer sused: graf[aktualny]) {
                if (!navstiveny[sused]) {
                    navstiveny[sused] = true;
                    naSpracovanie.add(sused);
                    velkost++;
                }
            }
        }

        return velkost;
    }
}
