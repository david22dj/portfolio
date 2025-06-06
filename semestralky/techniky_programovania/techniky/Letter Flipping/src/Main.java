import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky 1\\Letter Flipping\\src\\text.txt"));

        int pocetTestCases = scanner.nextInt();
        scanner.nextLine();


        for (int i = 0; i < pocetTestCases; i++) {
            List<String> slovnik = new ArrayList<>();
            HashMap<String, List<String>> grafSlov = new HashMap<>();
            List<String[]> startStop = new ArrayList<>();

            while(true) {
                String riadok = scanner.nextLine();
                if (riadok.equals("*")) {
                    break;
                }
                if (!riadok.isEmpty()) {
                    slovnik.add(riadok);
                }
            }

            while(scanner.hasNextLine()) {
                String riadok = scanner.nextLine();
                if (riadok.isEmpty()) {
                    break;
                }
                String[] parts = riadok.split(" ");
                String[] pole = new String[2];
                pole[0] = parts[0];
                pole[1] = parts[1];
                startStop.add(pole);
            }

            for (int j = 0; j < slovnik.size(); j++) {
                String slovoA = slovnik.get(j);
                List<String> premenna = new ArrayList<>();

                for (int k = 0; k < slovnik.size(); k++) {
                    String slovoB = slovnik.get(k);
                    if (jeOdlisneOJedno(slovoA, slovoB)) {
                        premenna.add(slovoB);
                    }
                }

                grafSlov.put(slovoA, premenna);
            }

            for (String[] dvojica : startStop) {
                String start = dvojica[0];
                String stop = dvojica[1];

                Queue<String> fronta = new LinkedList<>();

                HashMap<String, Integer> vzdialenosti = new HashMap<>();

                fronta.add(start);
                vzdialenosti.put(start, 0);

                while (!fronta.isEmpty()) {
                    String aktualneSlovo = fronta.poll();
                    int aktualnaVzdialenost = vzdialenosti.get(aktualneSlovo);

                    if (aktualneSlovo.equals(stop)) {
                        System.out.println(start + " " + stop + " " + aktualnaVzdialenost);
                        break;
                    }

                    for (String sused : grafSlov.get(aktualneSlovo)) {
                        if (!vzdialenosti.containsKey(sused)) {
                            fronta.add(sused);
                            vzdialenosti.put(sused, aktualnaVzdialenost + 1);
                        }
                    }
                }
            }
            if (i < pocetTestCases - 1 && scanner.hasNextLine()) {
                System.out.println();
            }
        }
    }

    public static boolean jeOdlisneOJedno(String A, String B) {
        int odlisne = 0;
        if (A.length() != B.length()) {
            return false;
        }

        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                odlisne++;
            }
            if (odlisne > 1) {
                return false;
            }
        }

        if (odlisne == 1) {
            return true;
        } else {
            return false;
        }
    }
}