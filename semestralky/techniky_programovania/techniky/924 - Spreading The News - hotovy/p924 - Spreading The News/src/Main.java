import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\924 - Spreading The News\\p924 - Spreading The News\\src\\vstup.txt"));

        ArrayList<Integer> testCases = new ArrayList<>();

        ArrayList<int[]> priatelstva = new ArrayList<>();

        int pocetZam = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < pocetZam; i++) {
            String riadok = scanner.nextLine();
            String[] parts = riadok.split(" ");

            int pocetPriatelstiev = Integer.parseInt(parts[0]);

            int[] pole = new int[pocetPriatelstiev];

            for (int j = 0; j < pocetPriatelstiev; j++) {
                pole[j] = Integer.parseInt(parts[j+1]);
            }

            priatelstva.add(pole);
        }

        int pocetTestCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < pocetTestCases; i++) {
            testCases.add(Integer.parseInt(scanner.nextLine()));
        }

        for (int i = 0; i < testCases.size(); i++) {
            boolean[] poculSpravu = new boolean[pocetZam];
            for (int j = 0; j < poculSpravu.length; j++) {
                poculSpravu[j] = false;
            }

            poculSpravu[testCases.get(i)] = true;

            Queue<Integer> fronta = new LinkedList<>();

            fronta.add(testCases.get(i));//pridame zdroj

            int den = 1;
            int maxBoom = 0;
            int prvyBoomDen = 0;

            while(!fronta.isEmpty()) {
                int velkostFronty = fronta.size();
                ArrayList<Integer> noviLudia = new ArrayList<>();

                for (int j = 0; j < velkostFronty; j++) {
                    int aktualny = fronta.poll();

                    for (int kamarat : priatelstva.get(aktualny)) {
                        if (!poculSpravu[kamarat]) {
                            poculSpravu[kamarat] = true;
                            noviLudia.add(kamarat);
                        }
                    }
                }

                if (noviLudia.size() > maxBoom) {
                    maxBoom = noviLudia.size();
                    prvyBoomDen = den;
                }

                fronta.addAll(noviLudia);
                den++;

            }

            if (maxBoom == 0) {
                System.out.println(0);
            } else {
                System.out.println(maxBoom + " " + prvyBoomDen);
            }

        }


    }
}