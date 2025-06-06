import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> hmotnosti = new ArrayList<>();
        ArrayList<Integer> ceny = new ArrayList<>();

        try {
            InputStream input = Main.class.getClassLoader().getResourceAsStream("H4_a.txt");
            if (input == null) {
                System.out.println("Subor sa nenasiel!");
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                hmotnosti.add(Integer.parseInt(line.trim()));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Chyba pri citani: " + e.getMessage());
        }

        try {
            InputStream input = Main.class.getClassLoader().getResourceAsStream("H4_c.txt");
            if (input == null) {
                System.out.println("Subor sa nenasiel!");
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                ceny.add(Integer.parseInt(line.trim()));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Chyba pri citani: " + e.getMessage());
        }

        ArrayList<Predmet> predmety = new ArrayList<>();

        List<Predmet> naVymennu = new ArrayList<>();

        for (int i = 0; i < hmotnosti.size(); i++) {
            predmety.add(new Predmet(i, hmotnosti.get(i), ceny.get(i)));
            naVymennu.add(new Predmet(i, hmotnosti.get(i), ceny.get(i)));
        }



        int kapicataBatohu = 11750;
        int maxPocetPredmetov = 370;

        while (true) {
            int sucetHmotnosti = 0;
            for (Predmet predmet : predmety) {
                sucetHmotnosti += predmet.hmotnost;
            }
            if (sucetHmotnosti <= kapicataBatohu && predmety.size() <= maxPocetPredmetov) {
                break;
            }

            int najlacnejsiPredmet = Integer.MAX_VALUE;
            for (Predmet predmet : predmety) {
                if (predmet.getCena() < najlacnejsiPredmet) {
                    najlacnejsiPredmet = predmet.getCena();
                }
            }
            for (Predmet predmet : predmety) {
                if (predmet.getCena() == najlacnejsiPredmet) {
                    predmety.remove(predmet);
                    break;
                }
            }
        }
        int vyslednaHmotnost = 0;
        int ucelovaFunkcia = 0;
        for (Predmet predmet : predmety) {
            vyslednaHmotnost += predmet.getHmotnost();
            ucelovaFunkcia += predmet.getCena();
        }
        System.out.println("Dualna vsuvacia heuristika:");
        System.out.println("Vysledna hmotnost: " + vyslednaHmotnost);
        System.out.println("Hodnota ucelovej funkcie:  " + ucelovaFunkcia);
        System.out.println("Vysledny pocet predmetov: " + predmety.size());

        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            for (Predmet predmet : predmety) {
                writer.println(predmet.getIndex());
            }
        } catch (Exception e) {
            System.out.println("Chyba pri zapisovani do suboru: " + e.getMessage());
        }

        //Vymenna heuristika na zlepsenie akutualneho riesenia


        List<Predmet> nezaradene = new ArrayList<>();

        for (Predmet kandidat : naVymennu) {
            boolean jeZaradeny = false;
            for (Predmet z : predmety) {
                if (kandidat.getIndex() == z.getIndex()) {
                    jeZaradeny = true;
                    break;
                }
            }
            if (!jeZaradeny) {
                nezaradene.add(kandidat);
            }
        }
        boolean vylepsene;

        do {
            vylepsene = false;

            int aktualnaHmotnost = 0;
            int aktualnaUcelovaFunkcia = 0;
            for (Predmet p : predmety) {
                aktualnaHmotnost += p.getHmotnost();
                aktualnaUcelovaFunkcia += p.getCena();
            }

            for (int i = 0; i < predmety.size(); i++) {
                Predmet pZ = predmety.get(i);

                for (int j = 0; j < nezaradene.size(); j++) {
                    Predmet pN = nezaradene.get(j);

                    int novaHmotnost = aktualnaHmotnost - pZ.getHmotnost() + pN.getHmotnost();
                    int novaUcelovka = aktualnaUcelovaFunkcia - pZ.getCena() + pN.getCena();

                    if (novaHmotnost <= 11750 && novaUcelovka > aktualnaUcelovaFunkcia) {
                        predmety.set(i, pN);
                        nezaradene.set(j, pZ);
                        vylepsene = true;
                        break;
                    }
                }

                if (vylepsene) break;
            }

        } while (vylepsene);

        int vyslednaHmotnostPoVymennej = 0;
        int ucelovaFunkciaPoVymennej = 0;
        for (Predmet predmet : predmety) {
            vyslednaHmotnostPoVymennej += predmet.getHmotnost();
            ucelovaFunkciaPoVymennej += predmet.getCena();
        }
        System.out.println();
        System.out.println("Vymenna heuristika:");
        System.out.println("Vysledna hmotnost : " + vyslednaHmotnostPoVymennej);
        System.out.println("Hodnota ucelovej funkcie:  " + ucelovaFunkciaPoVymennej);
        System.out.println("Vysledny pocet predmetov: " + predmety.size());


    }
}
