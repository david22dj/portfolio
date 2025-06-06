import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FordFulkersov {
    ArrayList<Vrchol> vrcholy;
    ArrayList<OrHrana> orhrany;

    ArrayList<Vrchol> E;
    ArrayList<Vrchol> Cesta;

    //public static final int NEKONECNO = Integer.MAX_VALUE / 2;
    public static final int NEKONECNO = 999;
    private int novaCena;
    private int cena;

    public FordFulkersov() {
        this.vrcholy = new ArrayList<Vrchol>();
        this.orhrany = new ArrayList<OrHrana>();
        this.E = new ArrayList<Vrchol>();
        this.Cesta = new ArrayList<Vrchol>();
    }

    public FordFulkersov(int pocetVrcholov) {
        this.vrcholy = new ArrayList<Vrchol>();
        this.orhrany = new ArrayList<OrHrana>();
        this.E = new ArrayList<Vrchol>();
        this.Cesta = new ArrayList<Vrchol>();

        for (int i = 1; i <= pocetVrcholov; i++) {
            Vrchol x = new Vrchol(i);
            this.vrcholy.add(x);
        }
    }

    public ArrayList<Vrchol> getVrcholy() {
        return vrcholy;
    }

    public ArrayList<OrHrana> getOrHrany() {
        return orhrany;
    }

    public void printInfo() {
        System.out.println("Pocet vrcholov: " + vrcholy.size());
        System.out.println("Pocet orientovanych hran: " + orhrany.size());
    }

    public Vrchol najdiVrchol(int i) {
        return vrcholy.get(i - 1);
    }

    public static FordFulkersov nacitajSubor(String nazovSuboru)
            throws FileNotFoundException {
        int pocetVrcholov = 1;
        int pocetOrHran = 0;
        Scanner s = new Scanner(new FileInputStream(nazovSuboru));
        while (s.hasNext()) {
            int u = s.nextInt();
            int v = s.nextInt();
            int c = s.nextInt();
            int d = s.nextInt();

            pocetOrHran++;
            if (u > pocetVrcholov) pocetVrcholov = u;
            if (v > pocetVrcholov) pocetVrcholov = v;
        }
        s.close();

        FordFulkersov fordFulkersov = new FordFulkersov(pocetVrcholov);

        s = new Scanner(new FileInputStream(nazovSuboru));
        while (s.hasNext()) {
            int u = s.nextInt();
            int v = s.nextInt();
            int c = s.nextInt();
            int d = s.nextInt();

            OrHrana h = new OrHrana(fordFulkersov.najdiVrchol(u), fordFulkersov.najdiVrchol(v), c, d);
            fordFulkersov.orhrany.add(h);
        }
        s.close();

        return fordFulkersov;
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

    public void printOrHrany() {
        System.out.print("H = {");
        boolean prva = true;
        for (OrHrana h : orhrany) {
            if (prva) prva = false;
            else System.out.print(",");
            System.out.print("" + h);
        }
        System.out.println("}");
    }

    public void fordFulkersov() {
        System.out.println("FordFulkersov algoritmus");

        Vrchol zdroj;
        Vrchol ustie;


        for (Vrchol v : vrcholy) {
            v.setT(NEKONECNO);
            v.setX(null);
        }

        boolean koniec = false;
        zdroj = najdiVrchol(1);
        ustie = najdiVrchol(vrcholy.size());

        zdroj.setT(0);
        zdroj.setX(null);
        E.add(zdroj);

        System.out.println("Hladam zvacsujucu polocestu:");
            int velkostToku = 0;


        while (!E.isEmpty()) {
            int cenaToku = 0;
            if(E.isEmpty()){
                koniec = true;
                break;
            }
            while (ustie.getT() == NEKONECNO) {

                Vrchol riadiaci = E.getFirst();
                E.removeFirst();
                System.out.println("Riadiaci vrchol: " + riadiaci);
                for (OrHrana orHrana: orhrany){


                        //Pre tie v smere
                        if (riadiaci == orHrana.getZac()){
                            if(orHrana.getY() < orHrana.getC()){
                                if(orHrana.getKon().getT() == NEKONECNO) {



                                        orHrana.getKon().setT(orHrana.getZac().getCislo());
                                        System.out.println(orHrana + " nie je nasytena, oznacim vrchol " + orHrana.getKon().getCislo() + ": +" + orHrana.getZac().getCislo());
                                        E.add(orHrana.getKon());






                                }
                            } else {
                                System.out.println(orHrana + " je nasytena.");
                            }
                        }
                        //protismere
                        if (riadiaci == orHrana.getKon()){
                            if(orHrana.getY() > 0){
                                if(orHrana.getZac().getT() == NEKONECNO) {


                                        System.out.println("Riadiaci vrchol: " + riadiaci);
                                        orHrana.getZac().setT(-riadiaci.getCislo());
                                        System.out.println(orHrana + " nie je nasytena, oznacim vrchol " + orHrana.getZac().getCislo() + ": +" + orHrana.getKon().getCislo());
                                        E.add(orHrana.getZac());



                                }
                            } else {
                                System.out.println(orHrana + " je nasytena.");
                            }
                        }

                        if(E.isEmpty()){
                            koniec = true;
                        }
                }
            }
            for (Vrchol vrchol : vrcholy) {
                System.out.println(vrchol.getCislo()+". " + vrchol.getT());
            }

            System.out.println("/////////CAS ZUCTOVANIA///////////////");
            //chcem kukat od konca hrany cesty
            ArrayList<OrHrana> polcesta = new ArrayList<>(); // Inicializujeme zoznam hran polcesty

// TOTO JE ZLE
//        for (Vrchol vrchol : vrcholy) {
//            int znacka = vrchol.getT();
//
//            // Pre každý vrchol, ktorý má predchádzajúcu značku, pridáme hranu do polcesty
//            if (znacka != 0 && vrchol != vrcholy.get(0)) {
//                int predchZnacka = vrchol.getT() - 1; // Získame predchádzajúcu značku
//                for (OrHrana orHrana : orhrany) {
//                    if (orHrana.getKon() == vrchol && orHrana.getZac().getT() == predchZnacka) {
//                        // Nastavíme tok hrane na minimum z aktuálneho toku a značky vrchola
//                        int tokHrany = Math.min(orHrana.getY(), vrchol.getT());
//
//                        // Pridáme hranu do polcesty
//                        polcesta.add(orHrana);
//                        break; // Ukončíme hľadanie hran pre tento vrchol
//                    }
//                }
//            }
//        }

            //idem od ustia az po zdroj a zistujem miminalnu REZERVU
            int znacka_kam_idem;
            int znacka_z_kadial_idem;

            znacka_z_kadial_idem = ustie.getCislo();
            znacka_kam_idem = ustie.getT();
            while (znacka_z_kadial_idem != zdroj.getCislo()){
                for (OrHrana orHrana : orhrany) {
                    if(znacka_kam_idem > 0 && orHrana.getKon().getCislo() == znacka_z_kadial_idem && orHrana.getZac().getCislo() == znacka_kam_idem){
                        polcesta.add(orHrana);
                        System.out.println("pridana hrana" + orHrana.toString());
                        znacka_z_kadial_idem = orHrana.getZac().getCislo();
                        znacka_kam_idem = orHrana.getZac().getT();
                    } else if (znacka_kam_idem < 0 && orHrana.getZac().getCislo() == znacka_z_kadial_idem && orHrana.getKon().getCislo() == -(znacka_kam_idem)) {
                        orHrana.setY(-orHrana.getY());
                        polcesta.add(orHrana);
                        System.out.println("pridana hrana v opacnom smere" + orHrana.toString());
                        znacka_z_kadial_idem = orHrana.getKon().getCislo();
                        znacka_kam_idem = orHrana.getKon().getT();
                    }
                }
            }
            System.out.println("////////////////////////////////////////////////////////////");
            for (OrHrana orHrana : polcesta) {
                System.out.println(orHrana.toString());
            }

            // Vypočítanie rezervy polocesty
            int rezervaPolocesty = Integer.MAX_VALUE; // Nastavíme na maximálnu hodnotu, aby sme našli minimálnu rezervu
            for (OrHrana hrana : polcesta) { // Predpokladajme, že polocesta je zoznam hrán tvoriacich polocestu
                int rezervaHrany = hrana.getC() - hrana.getY(); // Vypočítame rezervu pre každú hranu polocesty
                rezervaPolocesty = Math.min(rezervaPolocesty, rezervaHrany); // Nájdeme najmenšiu rezervu
            }

// Upravenie toku na poloceste o rezervu
            for (OrHrana hrana : polcesta) {
                int novyTok = hrana.getY() + rezervaPolocesty; // Nový tok je súčasný tok plus rezerva
                hrana.setY(novyTok); // Nastavíme nový tok hrane
            }

// Výpočet toku v celej sieti


            for (OrHrana hrana : polcesta) {
                int tokHrany = hrana.getY();

                //cenaToku += hrana.getD() * tokHrany;
            }
            System.out.println("toto"+polcesta.getFirst().getY());
            //velkostToku += polcesta.getFirst().getY();
            velkostToku += rezervaPolocesty;
// Výpis výsledkov
            System.out.println("Rezerva polocesty je: " + rezervaPolocesty);
            System.out.println("Tok v sieti:");
            for (OrHrana hrana : orhrany) {
                int tokHrany = hrana.getY();
                cenaToku += hrana.getD() * Math.abs(tokHrany);
                System.out.println("Hrana " + hrana + ": Tok = " + Math.abs(tokHrany) + "(" + hrana.getC() + "), Cena = " + hrana.getD() + " * " + Math.abs(tokHrany) + " = " + (hrana.getD() * Math.abs(tokHrany)));
            }
            System.out.println("Veľkosť toku: " + velkostToku);
            System.out.println("Cena toku: " + cenaToku);

            System.out.println("Hrany polcesty:");
            for (OrHrana hrana : polcesta) {
                System.out.println(hrana); // Vypíšeme každú hranu polcesty
            }

            System.out.println("///////IDEME OD ZNOVA??????????????????????????????????????????????????????????????????????????????????");
            //IDEME ODZNOVU CIZE ZASE VSETKY NA NEKONECNO
            for (Vrchol v : vrcholy) {
                v.setT(NEKONECNO);
                v.setX(null);
            }

            E.clear();
            E.add(zdroj);
            polcesta.clear();
        }

    }




}