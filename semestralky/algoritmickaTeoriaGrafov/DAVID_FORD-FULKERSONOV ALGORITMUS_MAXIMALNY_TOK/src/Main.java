import java.io.FileNotFoundException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FordFulkersov fordFulkersov = FordFulkersov.nacitajSubor("C:\\David Jurík\\Uniza\\leto\\ATG\\DAVID_FORD-FULKERSONOV ALGORITMUS_MAXIMALNY_TOK\\src\\graf_tok.txt");
        fordFulkersov.printInfo();
        fordFulkersov.printVrcholy();
        fordFulkersov.printOrHrany();
        fordFulkersov.fordFulkersov();
    }
}