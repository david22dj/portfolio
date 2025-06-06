import java.io.FileNotFoundException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        KruskalovAlg kruskalovAlg = KruskalovAlg.nacitajSubor("C:\\David Jurík\\Uniza\\leto\\ATG\\DAVID_JURIK_KRUSKAL\\src\\graf_kruskal_1500.txt");
        KruskalovAlg kruskalovAlg2 = KruskalovAlg.nacitajSubor("C:\\David Jurík\\Uniza\\leto\\ATG\\DAVID_JURIK_KRUSKAL\\src\\graf_kruskal_1500.txt");
        kruskalovAlg.printInfo();
        kruskalovAlg.printVrcholy();
        kruskalovAlg.printHrany();
        //kruskalovAlg.vypisHranySCenami();
        kruskalovAlg.KruskalovNajlacnejsiaKostra();
        kruskalovAlg2.KruskalovNajdrahsiaKostra();


    }
}