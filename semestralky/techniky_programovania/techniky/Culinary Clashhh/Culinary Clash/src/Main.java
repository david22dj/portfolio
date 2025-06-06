import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky 1\\Culinary Clashhh\\Culinary Clash\\src\\text.txt"));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            LinkedHashMap<String, List<String>> zoznamSutaziacich = new LinkedHashMap<>();
            LinkedHashMap<String, List<String>> zoznamSutaziacichZoSPECStanicami = new LinkedHashMap<>();

            String line = scanner.nextLine();
            if (line.equals("0 0")) {
                break;
            }
            String[] parts = line.split(" ");

            int s = Integer.parseInt(parts[0]);
            int r = Integer.parseInt(parts[1]);

            for (int i = 0; i < s; i++) {
                String stanica = scanner.nextLine();
                zoznamSutaziacich.put(stanica, new ArrayList<>());
            }
            for (int i = 0; i < r; i++) {
                String riadok = scanner.nextLine();
                String[] party = riadok.split(" ");

                if (zoznamSutaziacich.containsKey(party[1])) {
                    zoznamSutaziacich.get(party[1]).add(party[0]);
                } else {
                    if (!zoznamSutaziacichZoSPECStanicami.containsKey(party[1])) {
                        zoznamSutaziacichZoSPECStanicami.put(party[1], new ArrayList<>());
                    }
                    zoznamSutaziacichZoSPECStanicami.get(party[1]).add(party[0]);
                }
            }

            for (List<String> value : zoznamSutaziacich.values()) {
                for (String meno : value) {
                    System.out.println(meno);
                }
            }
            for (List<String> value : zoznamSutaziacichZoSPECStanicami.values()) {
                for (String meno : value) {
                    System.out.println(meno);
                }
            }
        }
    }
}
