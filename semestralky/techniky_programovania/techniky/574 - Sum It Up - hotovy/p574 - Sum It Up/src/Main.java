import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner= new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\574 - Sum It Up\\p574 - Sum It Up\\src\\vstup.txt"));

        ArrayList<int[]> testCases = new ArrayList<>();

        while(true) {
            String riadok = scanner.nextLine();
            String[] parts = riadok.split(" ");

            if (Integer.parseInt(parts[1]) == 0) {
                break;
            }

            int[] pole = new int[parts.length];

            for (int i = 0; i < parts.length; i++) {
                pole[i] = Integer.parseInt(parts[i]);
            }

            testCases.add(pole);
        }

        for (int i = 0; i < testCases.size(); i++) {

            int[] pole = testCases.get(i);

            HashMap<Integer, Integer> poctyCisiel = new HashMap<>();

            //zistit pocty cisiel od 2 lebo prve T a N
            for (int j = 2; j < pole.length ; j++) {
                int cislo = pole[j];
                poctyCisiel.put(cislo, poctyCisiel.getOrDefault(cislo, 0) + 1);
            }

            int T = pole[0];
            int N = pole[1];

            ArrayList<Integer> zoznamCisiel = new ArrayList<>(poctyCisiel.keySet());
            zoznamCisiel.sort(Collections.reverseOrder());

            Set<List<Integer>> vysledkySet = new HashSet<>();

            hladajKombinacie(
                    T,
                    0,
                    new ArrayList<>(),
                    zoznamCisiel,
                    0,
                    poctyCisiel,
                    vysledkySet
            );


            List<List<Integer>> vysledkyList = new ArrayList<>(vysledkySet);

            vysledkyList.sort((a, b) -> {
                int minDlzka = Math.min(a.size(), b.size());

                for (int j = 0; j < minDlzka; j++) {
                    int cmp = Integer.compare(b.get(j), a.get(j));

                    if (cmp != 0) {
                        return cmp;
                    }
                }
                return Integer.compare(a.size(), b.size());
            });


            System.out.println("Sums of " + T + ":");

            if (vysledkyList.isEmpty()) {
                System.out.println("NONE");
            } else {
                for (List<Integer> kombinacia : vysledkyList) {
                    for (int j = 0; j < kombinacia.size(); j++) {
                        System.out.print(kombinacia.get(j));
                        if (j != kombinacia.size() - 1) {
                            System.out.print("+");
                        }
                    }
                    System.out.println();
                }
            }

        }




    }

    public static void hladajKombinacie(
            int cielovySucet,
            int aktualnySucet,
            ArrayList<Integer> aktualnaKombinacia,
            ArrayList<Integer> zoznamCisiel,
            int startIndex,
            HashMap<Integer, Integer> zostavajuciPocet,
            Set<List<Integer>> vysledky
    ) {
        if (aktualnySucet == cielovySucet) {
            vysledky.add(new ArrayList<>(aktualnaKombinacia));
            return;
        }

        if (aktualnySucet > cielovySucet) {
            return;
        }

        for (int i = startIndex; i < zoznamCisiel.size(); i++) {

            int cislo = zoznamCisiel.get(i);
            int pocet = zostavajuciPocet.getOrDefault(cislo, 0);

            if (pocet > 0 && aktualnySucet + cislo <= cielovySucet) {

                aktualnaKombinacia.add(cislo);
                zostavajuciPocet.put(cislo, pocet - 1);

                hladajKombinacie(
                        cielovySucet,
                        aktualnySucet + cislo,
                        aktualnaKombinacia,
                        zoznamCisiel,
                        i,
                        zostavajuciPocet,
                        vysledky
                );

                // spatny krok
                aktualnaKombinacia.remove(aktualnaKombinacia.size() - 1);
                zostavajuciPocet.put(cislo, pocet);
            }
        }
    }
}