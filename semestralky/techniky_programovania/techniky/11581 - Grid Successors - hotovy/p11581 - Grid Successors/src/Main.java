import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\11581 - Grid Successors - hotovy\\p11581 - Grid Successors\\src\\vstup.txt"));

        int T = Integer.parseInt(scanner.nextLine());

        List<int[][]> testy = new ArrayList<>();

        for (int t = 0; t < T; t++) {

            while (scanner.hasNextLine()) {
                String riadok = scanner.nextLine();
                if (!riadok.trim().isEmpty()) {
                    int[][] matica = new int[3][3];
                    for (int i = 0; i < 3; i++) {
                        char[] line = riadok.trim().toCharArray();
                        for (int j = 0; j < 3; j++) {
                            matica[i][j] = line[j] - '0';
                        }
                        if (i < 2) {
                            riadok = scanner.nextLine();
                        }
                    }
                    testy.add(matica);
                    break;
                }
            }
        }

        for (int[][] g : testy) {
            Set<String> ulozeneKeys = new HashSet<>();
            int[][] aktualne = g;
            ulozeneKeys.add(maticaNaString(aktualne));

            int index = -1;

            while (true) {
                int[][] dalsiaMatica = transformuj(aktualne);
                String nextKey = maticaNaString(dalsiaMatica);
                index += 1;
                if (ulozeneKeys.contains(nextKey)) {
                    if (index == 0) {
                        System.out.println(-1);
                    } else {
                        System.out.println(index - 1);
                    }
                    break;
                } else {
                    ulozeneKeys.add(nextKey);
                    aktualne = dalsiaMatica;
                }
            }
        }
    }

    public static int[][] transformuj(int[][] matica) {
        int[][] nova = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int sucet = 0;
                if (i > 0) sucet += matica[i - 1][j];
                if (i < 2) sucet += matica[i + 1][j];
                if (j > 0) sucet += matica[i][j - 1];
                if (j < 2) sucet += matica[i][j + 1];
                nova[i][j] = sucet % 2;
            }
        }
        return nova;
    }

    public static String maticaNaString(int[][] matica) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] riadok : matica) {
            for (int x : riadok) {
                stringBuilder.append(x);
            }
        }
        return stringBuilder.toString();
    }
}
