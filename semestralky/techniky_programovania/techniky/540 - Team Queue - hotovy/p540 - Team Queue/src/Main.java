import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\5555555555555555555555\\540 - Team Queue\\p540 - Team Queue\\src\\vstup.txt"));

        int pocet = 0;

        while (true) {
            String riadok = scanner.nextLine();
            int pocetTimov = Integer.parseInt(riadok);

            if (pocetTimov == 0) {
                return;
            }
            pocet++;

            Map<Integer, Integer> hracNaTim = new HashMap<>();
            Map<Integer, Queue<Integer>> timNaFrontuHracov = new HashMap<>();
            Queue<Integer> frontaTimov = new LinkedList<>();

            for (int i = 0; i < pocetTimov; i++) {
                int pocetHracov = scanner.nextInt();
                for (int j = 0; j < pocetHracov; j++) {
                    int hrac = scanner.nextInt();
                    hracNaTim.put(hrac, i);
                }
                // prazdnu frontu pre tim
                timNaFrontuHracov.put(i, new LinkedList<>());
            }

            scanner.nextLine();

            System.out.println("Scenario #" + pocet);

            while(true) {
                String line = scanner.nextLine();
                String[] patrs = line.split(" ");

                if (line.equals("STOP")) {
                    break;
                }

                if (patrs.length == 2) {
                    int hrac = Integer.parseInt(patrs[1]);
                    int tim = hracNaTim.get(hrac);

                    if (!frontaTimov.contains(tim)) {
                        frontaTimov.add(tim);
                    }
                    timNaFrontuHracov.get(tim).add(hrac);
                } else {
                    int timZPredu = frontaTimov.peek();
                    Queue<Integer> frontaHracov = timNaFrontuHracov.get(timZPredu);
                    int hrac = frontaHracov.poll();
                    System.out.println(hrac);

                    if (frontaHracov.isEmpty()) {
                        frontaTimov.poll();
                    }

                }
            }

            System.out.println();


        }
    }
}
