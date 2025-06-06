import java.io.FileNotFoundException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        UCP ucp = UCP.nacitajSubor("C:\\David Jurík\\Uniza\\leto\\ATG\\DAVID_JURIK_ULOHA_CASOVEHO_PLANOVANIA\\src\\skuska.txt");
        ucp.nacitajSuborVrcholyT("C:\\David Jurík\\Uniza\\leto\\ATG\\DAVID_JURIK_ULOHA_CASOVEHO_PLANOVANIA\\src\\vysledky_1500_20.txt");
        ucp.printInfo();
        ucp.printVrcholy();
        ucp.printOrHrany();
        ucp.mononotneUsporiadanieVrholov();
        ucp.zistenieMozneZaciatkov();
        ucp.zistenieMoznychKoncov();
    }
}