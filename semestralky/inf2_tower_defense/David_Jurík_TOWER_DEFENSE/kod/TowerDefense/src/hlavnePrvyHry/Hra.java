package hlavnePrvyHry;


import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import hlavnePrvyHry.tlacidla.TlacidloKatapult;
import hlavnePrvyHry.tlacidla.TlacidloKusa;
import hlavnePrvyHry.tlacidla.TlacidloPlamenomet;
import nepriatelia.Dedincan;
import nepriatelia.Nepriatel;
import nepriatelia.Obor;
import nepriatelia.Rytier;
import veze.Katapult;
import veze.Kusa;
import veze.Plamenomet;
import veze.Veza;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Random;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * Trieda zodpovedná za hlavnú hernú slučku a správu všetkých herných objektov.
 * Inicializuje prostredie, spracováva vstupy, spawnovanie nepriateľov, aktualizácie
 * veží, zobrazenie UI prvkov a manažment priechodu medzi levelmi.
 */
public class Hra {
    private Obrazok pozadie;
    private Random random = new Random();
    private Cesta cesta;
    private Manazer manazer;
    private TlacidloKatapult tlacidloKatapult;
    private TlacidloPlamenomet tlacidloPlamenomet;
    private TlacidloKusa tlacidloKusa;

    private ArrayList<Nepriatel> nepriatelia;
    private ArrayList<Veza> veze;
    private String vyberanyTypVeze = null;

    private Hrad hrad;

    private OknoHry oknoHry;
    private JButton tlacidloStart;
    private JButton tlacidloUkonci;
    private JLabel popis;

    private int aktualnyLevel;

    private int aktualnyPocetDedicnanov;
    private int aktualnyPocetRytierov;
    private int aktualnyPocetObrov;
    private Levely levely;
    private Level aktualnyLevelObjekt;
    private int pocitadloSpawnu;

    private BarPenazi barPenazi;


    /**
     * Konštruktor, ktorý nastaví pozadie, UI prvky, tlačidlá, manažéra
     * a spustí spracovanie herných objektov.
     */
    public Hra() {
        this.pozadie = new Obrazok("pozadie_FINAAAAAAAAL.png");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();

        this.levely = new Levely();

        this.nepriatelia = new ArrayList<>();
        this.veze = new ArrayList<>();
        this.cesta = new Cesta();


        this.tlacidloKatapult = new TlacidloKatapult();
        this.tlacidloPlamenomet = new TlacidloPlamenomet();
        this.tlacidloKusa = new TlacidloKusa();

        this.hrad = new Hrad(10000);

        this.barPenazi = new BarPenazi(500);

        this.oknoHry = new OknoHry();
        this.tlacidloStart = this.oknoHry.dajTlacidloStart();
        this.tlacidloUkonci = this.oknoHry.dajTlacidloUkonci();
        this.popis = this.oknoHry.dajPopis();


        this.aktualnyLevel = 0;

        this.tlacidloStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hra.this.spustiLevel(Hra.this.aktualnyLevel + 1);
                Hra.this.oknoHry.dajOkno().setVisible(false);
            }
        });

        this.tlacidloUkonci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

    }
    /**
     * Spustí level s daným číslom. Ak neexistuje ďalší level, ukončí hru víťazstvom
     * a resetuje stav hry.
     *
     * @param cislo číslo levelu, ktorý sa má spustiť (1-based)
     */
    public void spustiLevel(int cislo) {

        Level novyLevel = this.levely.dajLevel(cislo);
        if (novyLevel == null) {
            this.barPenazi.skryPanel();
            this.oknoHry.dajPopis().setText("Vyhral si!");
            this.oknoHry.dajOkno().setVisible(true);
            this.oknoHry.dajTlacidloStart().setVisible(true);
            this.aktualnyLevel = 0;

            for (Nepriatel n : this.nepriatelia) {
                n.zanikni();
            }
            this.nepriatelia.clear();

            for (Veza v : this.veze) {
                v.vezaPremazProjektily();
                v.zanikni();
            }
            this.veze.clear();


            this.hrad = new Hrad(10000);
            this.barPenazi = new BarPenazi(500);
            this.barPenazi.zobrazPanel();
            return;
        }

        this.aktualnyLevel = cislo;
        this.popis.setText("Level " + this.aktualnyLevel);
        this.oknoHry.dajPopis().setVisible(true);

        this.barPenazi.nastavCenyPodlaLevelu(cislo);
        this.barPenazi.zobrazPanel();

        this.aktualnyLevelObjekt = this.levely.dajLevel(cislo);
        this.aktualnyPocetDedicnanov = this.aktualnyLevelObjekt.getPocetDedicnanov();
        this.aktualnyPocetRytierov = this.aktualnyLevelObjekt.getPocetRytierov();
        this.aktualnyPocetObrov = this.aktualnyLevelObjekt.getPocetObrov();

        this.pocitadloSpawnu = 0;
    }
    /**
     * Obnoví hru do počiatočného stavu po prehratí: zruší všetkých nepriateľov,
     * vymaže veže, resetuje hrad a panel peňazí, zobrazí tlačidlo Štart.
     */
    public void prehralSi() {
        this.barPenazi.skryPanel();
        for (Nepriatel n : this.nepriatelia) {
            n.zanikni();
        }
        this.nepriatelia.clear();

        for (Veza v : this.veze) {
            v.vezaPremazProjektily();
            v.zanikni();
        }
        this.veze.clear();

        this.hrad = new Hrad(10000);
        this.barPenazi = new BarPenazi(500);
        this.barPenazi.zobrazPanel();

        this.oknoHry.dajPopis().setText("Prehral si!");
        this.oknoHry.dajTlacidloStart().setVisible(true);
        this.oknoHry.dajOkno().setVisible(true);
        this.aktualnyLevel = 0;

    }
    /**
     * Jedno cyklické volanie (tik) hernej slučky.
     * - aktualizuje farby cien
     * - spawnuje nepriateľov podľa času a počtu zostávajúcich
     * - aktualizuje nepriateľov a veže
     * - kontroluje koniec hry (prehral/vyhral)
     */
    public void tik() {
        this.barPenazi.aktualizujFarbyCien();
        this.pocitadloSpawnu++;
        if (this.pocitadloSpawnu >= 50) {
            this.pocitadloSpawnu = 0;
            if (this.aktualnyPocetDedicnanov + this.aktualnyPocetRytierov + this.aktualnyPocetObrov > 0) {
                double nahoda = this.random.nextDouble() * 100;
                if (nahoda > 10 && nahoda < 33 && this.aktualnyPocetDedicnanov > 0) {
                    this.nepriatelia.add(new Dedincan());
                    this.aktualnyPocetDedicnanov--;
                } else if (nahoda < 45 && this.aktualnyPocetRytierov > 0) {
                    this.nepriatelia.add(new Rytier());
                    this.aktualnyPocetRytierov--;
                } else if (nahoda < 48 && this.aktualnyPocetObrov > 0) {
                    this.nepriatelia.add(new Obor());
                    this.aktualnyPocetObrov--;
                }
            }
        }

        for (int i = this.nepriatelia.size() - 1; i >= 0; i--) {
            Nepriatel n = this.nepriatelia.get(i);
            n.aktualizuj();//polymorfizmus - kazdy nepiatel inak spracovana metodu aktualizuj()
            if (!n.jeNazive()) {

                if (n instanceof Obor) {
                    this.barPenazi.pridaj(20);
                } else if (n instanceof Rytier) {
                    this.barPenazi.pridaj(10);
                } else if (n instanceof Dedincan) {
                    this.barPenazi.pridaj(5);
                }
                n.zanikni();
                this.nepriatelia.remove(i);
            }
        }
        for (Nepriatel nepriatel : this.nepriatelia) {
            if (nepriatel.jeAktivnyNaUtok()) {
                nepriatel.tikUtok();
                if (nepriatel.mozeUtok()) {
                    this.hrad.uberZivot(nepriatel.dajSiluUtoku());
                    nepriatel.nastavPocitadloUtoku(0);
                }
            }
        }

        for (Veza v : this.veze) {
            v.aktualizuj(Collections.unmodifiableList(this.nepriatelia));
            //polymorfizmus - kazdy veze inak spracovana metodu vysterl()... ktora je v metode aktualizuj()
        }

        if (this.hrad.dajZivot() <= 0) {
            this.prehralSi();
        } else if (this.nepriatelia.isEmpty() && this.aktualnyPocetDedicnanov == 0 && this.aktualnyPocetRytierov == 0 && this.aktualnyPocetObrov == 0) {
            this.oknoHry.dajOkno().setVisible(true);
            this.tlacidloStart.setVisible(true);
        }
    }

    /**
     * Spracuje kliknutie hráča na pozícii (x, y):
     * - ak klikol na tlačidlo, prepne výber typu veže
     * - ak už typ veže vybral, skontroluje cenu a kolíziu s cestou,
     *   vytvorí vežu a odpočíta peniaze
     *
     * @param x vodorovná súradnica kliknutia
     * @param y zvislá súradnica kliknutia
     */
    public void vyberSuradnice(int x, int y) {
        int cenaKat = this.barPenazi.dajCenuKatapultu();
        int cenaPla = this.barPenazi.dajCenuPlamenometu();
        int cenaKus = this.barPenazi.dajCenuKusy();
        //System.out.println(x + " " + y);
        if (this.tlacidloKatapult.obsahujeBod(x, y) && this.barPenazi.dajStav() >= cenaKat) {
            this.nastavVyberVeze("katapult");
        } else if (this.tlacidloPlamenomet.obsahujeBod(x, y) && this.barPenazi.dajStav() >= cenaPla) {
            this.nastavVyberVeze("plamenomet");
        } else if (this.tlacidloKusa.obsahujeBod(x, y) && this.barPenazi.dajStav() >= cenaKus) {
            this.nastavVyberVeze("kusa");
        } else {
            if (this.vyberanyTypVeze != null) {
                int cena = 0;
                if ("katapult".equals(this.vyberanyTypVeze)) {
                    cena = cenaKat;
                } else if ("plamenomet".equals(this.vyberanyTypVeze)) {
                    cena = cenaPla;
                } else if ("kusa".equals(this.vyberanyTypVeze)) {
                    cena = cenaKus;
                }

                if (this.barPenazi.dajStav() < cena) {
                    //System.out.println("Nedostatok mincí na kúpu veže!");
                    return;
                }

                if (!this.cesta.jeBlizkoCesty(x, y, 60, 83, 10)) {
                    Veza novaVeza = this.vytvorVezuNaPozicii(this.vyberanyTypVeze, x, y);
                    this.veze.add(novaVeza);
                    novaVeza.zobraz();
                    this.barPenazi.uber(cena);
                    this.vyberanyTypVeze = null;
                    this.zrusVyberTlacidiel();
                } else {
                    try {
                        throw new PrilisBlizkoKCesteException();
                    } catch (PrilisBlizkoKCesteException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
    /**
     * Resetuje vizuálny stav všetkých tlačidiel na "nestlačené".
     */
    public void zrusVyberTlacidiel() {
        this.tlacidloKatapult.nestlacene();
        this.tlacidloPlamenomet.nestlacene();
        this.tlacidloKusa.nestlacene();
    }
    /**
     * Nastaví interný stav vyberanej veže a vizuálne zvýrazní príslušné tlačidlo.
     *
     * @param typ identifikátor typu veže ("katapult", "plamenomet", "kusa")
     */
    public void nastavVyberVeze(String typ) {
        this.vyberanyTypVeze = typ;
        this.tlacidloKatapult.nestlacene();
        this.tlacidloPlamenomet.nestlacene();
        this.tlacidloKusa.nestlacene();

        if ("katapult".equals(typ)) {
            this.tlacidloKatapult.stlacene();
        } else if ("plamenomet".equals(typ)) {
            this.tlacidloPlamenomet.stlacene();
        } else if ("kusa".equals(typ)) {
            this.tlacidloKusa.stlacene();
        }
    }
    /**
     * Vytvorí nový objekt veže na základe zvoleného typu a pozície.
     *
     * @param typ identifikátor typu veže
     * @param x   vodorovná súradnica umiestnenia veže
     * @param y   zvislá súradnica umiestnenia veže
     * @return nová inštancia Veza alebo null pri neznámom type
     */
    public Veza vytvorVezuNaPozicii(String typ, int x, int y) {
        if ("katapult".equals(typ)) {
            return new Katapult(x, y);
        } else if ("plamenomet".equals(typ)) {
            return new Plamenomet(x, y);
        } else if ("kusa".equals(typ)) {
            return new Kusa(x, y);
        }
        return null;
    }
}
