import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int y = 0; y < T; y++) {


            int N = scanner.nextInt();
            int nula = 0;
            int jedna = 1;
            int fibonacci = 0;

            if (N == 1) {
                System.out.print("000001");
                System.out.println();
                continue;
            }

            for (int i = 0; i < N - 1; i++) {
                fibonacci = nula + jedna;
                fibonacci = fibonacci % 1000000;
                nula = jedna;
                jedna = fibonacci;
            }
            System.out.printf("%06d%n",fibonacci);

        }
    }
}