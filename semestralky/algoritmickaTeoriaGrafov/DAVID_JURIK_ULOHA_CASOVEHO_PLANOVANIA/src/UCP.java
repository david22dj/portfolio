import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UCP {
    ArrayList<Vrchol> vrcholy;
    ArrayList<OrHrana> orhrany;
    private int maximalna;

    ArrayList<Vrchol> monotonneUsporiadaneVrcholy;
    ArrayList<Vrchol> Cesta;

    //public static final int NEKONECNO = Integer.MAX_VALUE / 2;
    public static final int NEKONECNO = 999;
    private int novaCena;
    private int cena;

    public UCP() {
        this.vrcholy = new ArrayList<Vrchol>();
        this.orhrany = new ArrayList<OrHrana>();
        this.monotonneUsporiadaneVrcholy = new ArrayList<Vrchol>();
        this.Cesta = new ArrayList<Vrchol>();
    }

    public UCP(int pocetVrcholov) {
        this.vrcholy = new ArrayList<Vrchol>();
        this.orhrany = new ArrayList<OrHrana>();
        this.monotonneUsporiadaneVrcholy = new ArrayList<Vrchol>();
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

    public static UCP nacitajSubor(String nazovSuboru)
            throws FileNotFoundException {
        int pocetVrcholov = 1;
        int pocetOrHran = 0;
        Scanner s = new Scanner(new FileInputStream(nazovSuboru));
        while (s.hasNext()) {
            int u = s.nextInt();
            int v = s.nextInt();


            pocetOrHran++;
            if (u > pocetVrcholov) pocetVrcholov = u;
            if (v > pocetVrcholov) pocetVrcholov = v;
        }
        s.close();

        UCP UCP = new UCP(pocetVrcholov);

        s = new Scanner(new FileInputStream(nazovSuboru));
        while (s.hasNext()) {
            int u = s.nextInt();
            int v = s.nextInt();


            OrHrana h = new OrHrana(UCP.najdiVrchol(u), UCP.najdiVrchol(v));
            UCP.orhrany.add(h);
        }
        s.close();

        return UCP;
    }
    public void nacitajSuborVrcholyT(String nazovSuboru)
            throws FileNotFoundException {

        Scanner s = new Scanner(new FileInputStream(nazovSuboru));
        while (s.hasNext()) {
            int cislovrchola = s.nextInt();
            int Tvrchola = s.nextInt();

            for (Vrchol vrchol : vrcholy) {
                if(cislovrchola == vrchol.getCislo()){
                    vrchol.setT(Tvrchola);
                }
            }

        }
        s.close();
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

    public void mononotneUsporiadanieVrholov() {
        //zaciatocne priradenie znackyD
        for(Vrchol vrchol : vrcholy){
            for(OrHrana orHrana : orhrany){
                if(vrchol == orHrana.getKon()){
                    int znacka = vrchol.getZnackaD();
                    znacka += 1;
                    vrchol.setZnackaD(znacka);
                }
            }
        }
        /* printujem zaciatocne znackaD vrcholov
        for(Vrchol vrchol : vrcholy){
            System.out.print(vrchol.getZnackaD());
            System.out.print(",");
        }
        */
        //danie vrchlov do poradia a do arraylistu a monotonneUsporiadaneVrcholy

        //pridame vrcholy ktore maju d(v) == 0 do arraylistu
        for(Vrchol vrchol : vrcholy){
            if(vrchol.getZnackaD() == 0){
                monotonneUsporiadaneVrcholy.add(vrchol);
            }
        }
        //pokracujeme tym ze uz vsetkym vrcholov mavame spravne znacky
        // a davame ich v poradi do Arraylistu
        int index = 0;
        while (index < monotonneUsporiadaneVrcholy.size()) {
            Vrchol pozorovanyVrchol = monotonneUsporiadaneVrcholy.get(index);

            for (OrHrana orHrana : orhrany) {
                if (pozorovanyVrchol.equals(orHrana.getZac())) {
                    Vrchol konVrchol = orHrana.getKon();
                    int znackaD = konVrchol.getZnackaD();
                    znackaD -= 1;
                    konVrchol.setZnackaD(znackaD);

                    if (znackaD == 0 && !monotonneUsporiadaneVrcholy.contains(konVrchol)) {
                        monotonneUsporiadaneVrcholy.add(konVrchol);
                    }
                }
            }

            index++;
        }

        System.out.println();

        System.out.println("Monotonne usporiadane vrcholy:");
        for(Vrchol vrchol : monotonneUsporiadaneVrcholy){
            System.out.print(vrchol.getCislo());
        }
    }


    public void zistenieMozneZaciatkov(){
//        vrcholy.get(0).setT(30);
//        vrcholy.get(1).setT(50);
//        vrcholy.get(2).setT(20);
//        vrcholy.get(3).setT(50);
//        vrcholy.get(4).setT(40);
//        vrcholy.get(5).setT(60);
//        vrcholy.get(6).setT(10);
//        System.out.println();


        //System.out.print(monotonneUsporiadaneVrcholy.get(0).getT());


        for (Vrchol vrchol : monotonneUsporiadaneVrcholy) {
            for (OrHrana orHrana : orhrany) {
                Vrchol zacVrchol = orHrana.getZac();
                Vrchol konVrchol = orHrana.getKon();
                if(vrchol==zacVrchol){
                    if (vrchol.getZ()+vrchol.getT() > konVrchol.getZ()) {
                        konVrchol.setZ(vrchol.getT() + zacVrchol.getZ());
                    }
                }

            }
        }
        maximalna = 0;
        for (Vrchol vrchol : monotonneUsporiadaneVrcholy) {
            if (vrchol. getZ()+ vrchol.getT() > maximalna){
                maximalna = vrchol. getZ()+ vrchol.getT();
                //System.out.print(maximalna);
                //System.out.println(",");
            }
        }
//        for (Vrchol vrchol : monotonneUsporiadaneVrcholy) {
//            System.out.print(vrchol.getZ());
//            System.out.println(",");
//        }
        //maximalna je celkove trvanie
        System.out.print("Celkove trvanie: ");
        System.out.print(maximalna);
        System.out.println("");


    }
    public void zistenieMoznychKoncov(){
        for (Vrchol vrchol : monotonneUsporiadaneVrcholy) {
            vrchol.setK(this.maximalna);
        }
        Collections.reverse(monotonneUsporiadaneVrcholy);

        for (Vrchol vrchol : monotonneUsporiadaneVrcholy) {
            for (OrHrana orHrana : orhrany) {
                Vrchol zacVrchol = orHrana.getZac();
                Vrchol konVrchol = orHrana.getKon();
                if(vrchol==zacVrchol){
                    if (konVrchol.getK() - konVrchol.getT() < vrchol.getK()) {
                        vrchol.setK(konVrchol.getK() - konVrchol.getT());
                    }
                }

            }
        }
        for (Vrchol vrchol : vrcholy) {
            System.out.print(vrchol.getCislo() + ".");
            System.out.print("|");
            System.out.print("p(v)" + vrchol.getT());
            System.out.print("|");
            System.out.print("z(v)" + vrchol.getZ());
            System.out.print("|");
            System.out.print("k(v)" + vrchol.getK());
            System.out.print("|");
            System.out.print("R(v)" + (vrchol.getK()-vrchol.getZ()-vrchol.getT()));
            System.out.println();
        }

    }


}