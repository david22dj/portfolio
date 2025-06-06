import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class KruskalovAlg {
    ArrayList<Vrchol> vrcholy;
    ArrayList<Hrana> hrany;
    ArrayList<Hrana> hranyPreNajdrahsiu;
    ArrayList<Hrana> hranyPreNajlacnejsiu;



    ArrayList<Vrchol> Kostra;

    //public static final int NEKONECNO = Integer.MAX_VALUE / 2;
    public static final int NEKONECNO = 999;
    private int novaCena;
    private int cena;

    public KruskalovAlg() {
        this.vrcholy = new ArrayList<Vrchol>();
        this.hrany = new ArrayList<Hrana>();
        this.Kostra = new ArrayList<Vrchol>();
        this.hranyPreNajdrahsiu = new ArrayList<Hrana>();
        this.hranyPreNajlacnejsiu = new ArrayList<Hrana>();
    }

    public KruskalovAlg(int pocetVrcholov) {
        this.vrcholy = new ArrayList<Vrchol>();
        this.hrany = new ArrayList<Hrana>();
        this.hranyPreNajdrahsiu = new ArrayList<Hrana>();
        this.hranyPreNajlacnejsiu = new ArrayList<Hrana>();
        this.Kostra = new ArrayList<Vrchol>();

        for (int i = 1; i <= pocetVrcholov; i++) {
            Vrchol x = new Vrchol(i);
            this.vrcholy.add(x);
        }
    }

    public ArrayList<Vrchol> getVrcholy() {
        return vrcholy;
    }

    public ArrayList<Hrana> getHrany() {
        return hrany;
    }

    public void printInfo() {
        System.out.println("Pocet vrcholov: " + vrcholy.size());
        System.out.println("Pocet hran: " + hrany.size());
    }

    public Vrchol najdiVrchol(int i) {
        return vrcholy.get(i - 1);
    }

    public static KruskalovAlg nacitajSubor(String nazovSuboru)
            throws FileNotFoundException {
        int pocetVrcholov = 1;
        int pocetHran = 0;
        Scanner s = new Scanner(new FileInputStream(nazovSuboru));
        while (s.hasNext()) {
            int u = s.nextInt();
            int v = s.nextInt();
            int c = s.nextInt();

            pocetHran++;
            if (u > pocetVrcholov) pocetVrcholov = u;
            if (v > pocetVrcholov) pocetVrcholov = v;
        }
        s.close();

        KruskalovAlg kruskalovAlg = new KruskalovAlg(pocetVrcholov);

        s = new Scanner(new FileInputStream(nazovSuboru));
        while (s.hasNext()) {
            int u = s.nextInt();
            int v = s.nextInt();
            int c = s.nextInt();

            Hrana h = new Hrana(kruskalovAlg.najdiVrchol(u), kruskalovAlg.najdiVrchol(v), c);
            kruskalovAlg.hrany.add(h);
            kruskalovAlg.hranyPreNajlacnejsiu.add(h);
            kruskalovAlg.hranyPreNajdrahsiu.add(h);
        }
        s.close();

        return kruskalovAlg;
    }

    public void printVrcholy() {
        System.out.print("V = {");
        boolean prvy = true;
        for (Vrchol v : vrcholy) {
            if (prvy) prvy = false;
            else System.out.print(",");
            System.out.print("" + v);
        }
        System.out.println("}");
    }

    public void printHrany() {
        System.out.print("H = {");
        boolean prva = true;
        for (Hrana h : hrany) {
            if (prva) prva = false;
            else System.out.print(",");
            System.out.print("" + h);
        }
        System.out.println("}");
    }
    private Vrchol najdiKoren(Vrchol vrchol) {
        while (vrchol.getX() != null && vrchol != vrchol.getX()) {
            vrchol = vrchol.getX();
        }
        return vrchol;
    }
    private void zjednot(Vrchol koren1, Vrchol koren2, int povodnyAtribut) {
        // Nastavíme koreňový vrchol menšieho stromu ako potomka väčšieho stromu
        if (koren1.getT() > koren2.getT()) {
            koren2.setX(koren1);
            koren1.setAtribut(povodnyAtribut); // Nastavíme k-atribyt pre celý strom
        } else {
            koren1.setX(koren2);
            koren2.setAtribut(povodnyAtribut); // Nastavíme k-atribyt pre celý strom
        }
    }
    public void KruskalovNajlacnejsiaKostra() {
        zoradOdMin(); // Zoradíme hrany od najlacnejšej po najdrahšiu

        int cenaKostryNajlacnejsia = 0; // Inicializujeme premennú pre celkovú cenu kostry
        int pocetHranVKostre = 0; // Inicializujeme premennú pre počet hrán v kostre

        // Pre každú hranu v poradí od najlacnejšej po najdrahšiu
        for (Hrana hrana : hranyPreNajlacnejsiu) {
            Vrchol zaciatocny = hrana.getZac();
            Vrchol koncovy = hrana.getKon();

            // Nájdeme korene stromov, ku ktorým patria vrcholy hrany
            Vrchol koren1 = najdiKoren(zaciatocny);
            Vrchol koren2 = najdiKoren(koncovy);

            // Ak vrcholy patria k rôznym stromom, spojíme ich
            if (koren1 != koren2) {
                // Uložíme pôvodný atribút koreňového vrchola pre prípadné obnovenie
                int povodnyAtribut = koren2.getAtribut();
                // Zjednotíme stromy a nastavíme spoločný atribút
                zjednot(koren1, koren2, povodnyAtribut);
                // Označíme hranu ako súčasť kostry
                hrana.setJeVKostre(true); // Použijeme nový atribút

                // Pripočítame cenu hrany k celkovej cene kostry
                cenaKostryNajlacnejsia += hrana.getCena();

                // Inkrementujeme počet hrán v kostre
                pocetHranVKostre++;
            }
        }

        if (pocetHranVKostre != vrcholy.size() - 1) {
            System.out.println("Kostra neexistuje.");
        } else {
            System.out.println("Celková cena najlacnejšej kostry: " + cenaKostryNajlacnejsia);
            // Výpis kostry a celkovej ceny kostry
            System.out.println("Najlacnejsia kostra:");
            for (Hrana hrana : hranyPreNajlacnejsiu) {
                if (hrana.getJeVKostre()) {
                    System.out.println("Hrana: " + hrana.toString() + ", Cena: " + hrana.getCena());
                }
            }
        }
    }

    public void KruskalovNajdrahsiaKostra() {
        zoradOdMax(); // Zoradíme hrany od najdrahšej po najlacnejšiu

        int cenaKostryNajdrahsia = 0; // Inicializujeme premennú pre celkovú cenu kostry
        int pocetHranVKostre = 0; // Inicializujeme premennú pre počet hrán v kostre

        // Pre každú hranu v poradí od najdrahšej po najlacnejšiu
        for (Hrana hrana : hranyPreNajdrahsiu) {
            Vrchol zaciatocny = hrana.getZac();
            Vrchol koncovy = hrana.getKon();

            // Nájdeme korene stromov, ku ktorým patria vrcholy hrany
            Vrchol koren1 = najdiKoren(zaciatocny);
            Vrchol koren2 = najdiKoren(koncovy);

            // Ak vrcholy patria k rôznym stromom, spojíme ich
            if (koren1 != koren2) {
                // Uložíme pôvodný atribút koreňového vrchola pre prípadné obnovenie
                int povodnyAtribut = koren2.getAtribut();
                // Zjednotíme stromy a nastavíme spoločný atribút
                zjednot(koren1, koren2, povodnyAtribut);
                // Označíme hranu ako súčasť kostry
                hrana.setJeVKostre(true); // Použijeme nový atribút

                // Pripočítame cenu hrany k celkovej cene kostry
                cenaKostryNajdrahsia += hrana.getCena();

                // Inkrementujeme počet hrán v kostre
                pocetHranVKostre++;
            }
        }

        if (pocetHranVKostre != vrcholy.size() - 1) {
            System.out.println("Kostra neexistuje.");
        } else {
            System.out.println("Celková cena najdrahšej kostry: " + cenaKostryNajdrahsia);
            // Výpis kostry a celkovej ceny kostry
            System.out.println("Najdrahsia kostra:");
            for (Hrana hrana : hranyPreNajdrahsiu) {
                if (hrana.getJeVKostre()) {
                    System.out.println("Hrana: " + hrana.toString() + ", Cena: " + hrana.getCena());
                }
            }
        }
    }


    public void Kruskalov(){
        KruskalovNajdrahsiaKostra();
        KruskalovNajlacnejsiaKostra();
    }

    public void zoradOdMax() {
        Collections.sort(hranyPreNajdrahsiu, new Comparator<Hrana>() {
            @Override
            public int compare(Hrana h1, Hrana h2) {
                return h2.getCena() - h1.getCena();
            }
        });
    }
    public void zoradOdMin() {
        Collections.sort(hranyPreNajlacnejsiu, new Comparator<Hrana>() {
            @Override
            public int compare(Hrana h1, Hrana h2) {
                return h1.getCena() - h2.getCena();
            }
        });
    }
    public void vypisHranySCenami() {
        for (Hrana h : hrany) {
            System.out.println("Hrana: " + h.toString() + ", Cena: " + h.getCena());
        }
    }



}