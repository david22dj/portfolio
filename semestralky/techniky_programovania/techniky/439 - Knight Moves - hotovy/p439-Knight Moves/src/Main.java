import javax.management.Query;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky\\SKUSKA\\5555555555555555555555\\439 - Knight Moves\\p439-Knight Moves\\src\\vstup.txt"));

        ArrayList<int[]> testCases = new ArrayList();
        ArrayList<String[]> testCasesNaVypis = new ArrayList();


        while(scanner.hasNextLine()) {
            String riadok = scanner.nextLine();

            Scanner scannerRiadky = new Scanner(riadok);

            String a = scannerRiadky.next();
            String b = scannerRiadky.next();


            String[] novyNaVypis = new String[2];
            novyNaVypis[0] = a;
            novyNaVypis[1] = b;
            testCasesNaVypis.add(novyNaVypis);

            char ApismenoSuradnica = a.charAt(0);
            char AcisloSuradnica = a.charAt(1);

            char BpismenoSuradnica = b.charAt(0);
            char BcisloSuradnica = b.charAt(1);

            int AX = ApismenoSuradnica - 'a';
            int AY = AcisloSuradnica - '1';

            int BX = BpismenoSuradnica - 'a';
            int BY = BcisloSuradnica - '1';

            int[] novy = new int[4];
            novy[0] = AX;
            novy[1] = AY;
            novy[2] = BX;
            novy[3] = BY;


            testCases.add(novy);
        }

        for (int i = 0; i < testCases.size(); i++) {
            boolean[][] nastivene = new boolean[8][8];
            int startX = testCases.get(i)[0];
            int startY = testCases.get(i)[1];
            int cielX = testCases.get(i)[2];
            int cielY = testCases.get(i)[3];

            Queue<int[]> fronta = new LinkedList<>();

            fronta.add(new int[]{startX, startY, 0});
            nastivene[startX][startY] = true;

            int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
            int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};

            int celkoveKroky = 0;

            while (!fronta.isEmpty()) {
                int[] aktualna = fronta.poll();
                int x = aktualna[0];
                int y = aktualna[1];
                int kroky = aktualna[2];

                if (x == cielX && y == cielY) {
                    celkoveKroky = kroky;
                    break;
                }

                for (int j = 0; j < 8; j++) {
                    int novyX = x + dx[j];
                    int novyY = y + dy[j];

                    if (novyX >= 0 && novyX < 8 && novyY >= 0 && novyY < 8 && !nastivene[novyX][novyY]) {
                        nastivene[novyX][novyY] = true;
                        fronta.add(new int[] {novyX, novyY, kroky + 1});
                    }
                }
            }

            System.out.println("To get from " + testCasesNaVypis.get(i)[0] + " to " + testCasesNaVypis.get(i)[1] + " takes " + celkoveKroky + " knight moves.");
        }


    }
}