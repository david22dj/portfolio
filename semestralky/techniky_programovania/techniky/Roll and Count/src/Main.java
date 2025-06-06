import java.io.File;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("C:\\David Jurík\\Uniza\\2roc\\leto\\techniky 1\\Roll and Count\\src\\text.txt"));


            int pocet = 0;
            while (scanner.hasNextLine()) {

                for (int i = 0; i < 3; i++) {

                    String riadok = scanner.nextLine();

                    for (int y = 0; y < riadok.length(); y++) {
                        if (riadok.charAt(y) == 'O') {
                            pocet++;
                        }
                    }

                }
                System.out.print(pocet);
                pocet = 0;
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                    System.out.println();
                }
            }
        scanner.close();
    }
}