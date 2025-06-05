
import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;
import java.io.IOException;
/**
* Túto triedu som celú prevzal zo svojej minuloročnej semestrálnej práce.
* Trieda Hrac predstavuje loptičku v hre.
* Trieda využíva knižnicu Shapesge.
*/
public class Hrac {
    private KJTik kjtik;
    private Obrazok obrazok;
    private Manazer manazer;
    private Obrazok srdieckoPrve;
    private Obrazok srdieckoDruhe;
    private Obrazok srdieckoTretie;
    private Obrazok gameOverObrazok;
    private BlokTextu blokTextu;
    private StylFontu stylFontu = StylFontu.BOLD;
    private int velkostHraca = 50;
    private double gravitacia = 0.2;
    private int y = 800 - this.velkostHraca;
    private int x = (500 / 2) - this.velkostHraca / 2;
        
    private TypHraca typHraca = TypHraca.VPREDU;
        
    private int[] xHodnoty;
    private int[] yHodnoty;
        
    private int odrazeneX;
    private int odrazeneY;
    private int posunObrazu;
    private int skusime;
        
    private double a = -0.001;
    private double b = 0.0;
    private double c = 200.0;
    private double xKvad = -447.214;
    private double sd;
    private int yInt;
        
    private boolean prvniPodminkaSplnena = false;
    private int srdiecka = 3;
    private int kolkoKratSaOdrazil;
    private double metre;
    private String metreString;
    private boolean vykreslenie = true;
    private int vykresenieInt;
    
    private int spustenie;
    
    private Pamat pamat;
    
    private String menoPrihlaseneho;
       
    /**
    * Konštruktor pre vytvorenie loptičky a jej životov (srdiečok) v hre
    * Inicializuje polia súradníc platform, obrázky a ďalšie atribúty hráča.
    * 
    * @param xHodnoty Pole súradníc X platform.
    * @param yHodnoty Pole súradníc Y platform.
    * @param menoPrihlaseneho Meno aktuálneho hráča.
    * 
    * Inicializuje xHodnoty a yHodnoty.
    */
    public Hrac(int[] xHodnoty, int[] yHodnoty, String menoPrihlaseneho) {
        this.obrazok = new Obrazok(this.typHraca.getLink(), this.x, this.y);
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.srdieckoPrve = new Obrazok("pics/srdiecko.png", 380 , 10);
        this.srdieckoDruhe = new Obrazok("pics/srdiecko.png", 417 , 10);
        this.srdieckoTretie = new Obrazok("pics/srdiecko.png", 454 , 10);
        this.xHodnoty = xHodnoty;
        this.yHodnoty = yHodnoty;
        this.obrazok.zobraz();
        this.srdieckoPrve.zobraz();
        this.srdieckoDruhe.zobraz();
        this.srdieckoTretie.zobraz();
        
        this.pamat = new Pamat();
        this.menoPrihlaseneho = menoPrihlaseneho;
    }
        
    /**
    * Metóda pre simuláciu skoku loptičky podľa kvadratickej funkcie.
    * Spušťa metódu srdieckaHracaAKoniecHry, sledovanieHraca a getYHraca.
    * 
    * @throws IOException Ak dôjde k problémom pri práci so súbormi
    */
    public void skacKvadraticka() throws IOException {
        //this.obrazok.zmenPolohu(this.x, this.y - yInt);
        
        this.sd = this.a * Math.pow(this.xKvad, 2) + this.b * this.xKvad + this.c;
        this.yInt = (int)sd;
        
        this.xKvad += 5.9628533333;
        
        if (this.y - yInt > 750) {
            this.xKvad = -447.214;
        } 
            
        this.srdieckaHracaAKoniecHry();
            
        if (this.x > 500 - velkostHraca) {
            this.x = 0;
        }
        if (this.x < 0) {
            this.x = 500 - velkostHraca;
        }
            
            
        for (int i = 0; i < this.xHodnoty.length; i++) {
            if (this.x > this.xHodnoty[i] - velkostHraca && this.x < this.xHodnoty[i] + 100 &&
                this.yHodnoty[i] < (this.y - yInt) && (this.y - yInt) < this.yHodnoty[i] + 10 &&
                this.smerDopadu() == 1) {
                    
                this.kolkoKratSaOdrazil += 1;
                    
                this.odrazeneX = this.xHodnoty[i];
                this.odrazeneY = this.yHodnoty[i];
                    
                this.sledovanieHraca();
                    
                    
                this.xKvad = -447.214;
                this.y = this.yHodnoty[i] + 1;
                this.prvniPodminkaSplnena = true;
            }
        }
        
        this.obrazok.zmenPolohu(this.x, this.y - yInt);
            
        this.getYHraca();
    }
    
    /**
     * Metóda na spracovanie srdiečok hráča a kontroly konca hry.
     * Metóda nakreslí "Game Over" a vypíše koľko hráč prešiel metrov.
     * Taktiež hra vypíše rekord hry a tabulku všetkých hráčov a ich nastúpané metre.
     * 
     * @throws IOException Ak dôjde k problémom pri práci so súbormi
     */  
    public void srdieckaHracaAKoniecHry() throws IOException {
        if (this.prvniPodminkaSplnena && this.y - yInt > 750) {
            this.srdiecka -= 1;
            
        }
        if (this.srdiecka == 2) {
            this.srdieckoPrve.zmenObrazok("pics/srdieckoP.png");
        }
        if (this.srdiecka == 1) {
            this.srdieckoDruhe.zmenObrazok("pics/srdieckoP.png");
        }
        if (this.srdiecka == 0) {
            this.srdieckoTretie.zmenObrazok("pics/srdieckoP.png");
            
            
            if (this.vykreslenie) {
                if (this.spustenie == 0 && this.vykresenieInt == 0) {
                    this.gameOverObrazok = new Obrazok("pics/gameOverRetry.png", 0 , 0);
                    this.gameOverObrazok.zobraz();
                    
                    
                    this.vykreslenie = false;
                    this.metreString = Double.toString(this.metre);
                    
                    PamatPreHracov pamatPreHracov = new PamatPreHracov(this.menoPrihlaseneho);
                    
                    this.blokTextu = new BlokTextu("Prešiel si:" + " " + metreString + " " + "metrov a record hry je: " 
                    + this.pamat.nacitajMetreZPamate() + "\n" + pamatPreHracov.vytvorTabulkuHracov(this.metre), 40 , 550);
                    
                    this.blokTextu.zmenFont("Times New Roman", this.stylFontu, 20);
                    this.blokTextu.zmenFarbu("red");
                    this.blokTextu.zobraz();
                    this.vykresenieInt = 1;
                }
                
            }
            
        }
    }
    
    /**
     * Metóda na získanie hodnoty pre vykreslenie obrazu.
     * 
     * @return Hodnota pre vykreslenie.
     */
    public int getVykreslenie() {
        return this.vykresenieInt;
    }
    
    /**
     * Metóda pre spracovanie informácií po skončení hry.
     * 
     * @param metreKJTik Dĺžka, ktorú hráč prešiel a teda konečný počet prejdených metrov. 
     */
    public void gameOver(double metreKJTik) {
        this.metre = metreKJTik;
    }
    
    /**
     * Metóda pre daľšieho pokusu v hre.
     * Metóda vytvorí daľšiu inštanciu a ukončí staré dianie v hre.
     * 
     * @throws IOException Ak dôjde k problémom pri práci so súbormi
     */
    public void aktivuj() throws IOException {
        if (this.spustenie == 0) {
            this.spustenie += 1;
            this.kjtik = new KJTik(this.menoPrihlaseneho);
        }
        
    }
    
    /**
     * Metóda pre nastavenie súradníc platform pre hráča.
     * 
     * @param xValues Pole súradníc X platform.
     * @param yValues Pole súradníc Y platform.
     */
    public void setPlatformy(int[] xHodnoty, int[] yHodnoty) {
        this.xHodnoty = xHodnoty;
        this.yHodnoty = yHodnoty;
    }
    
    /**
     * Metóda pre sledovanie hráča po odraze od platformy a teda o koľko sa majú platformy posunúť.
     */
    public void sledovanieHraca() {
        this.posunObrazu = 800 - 80 - odrazeneY;
    }
    
    /**
     * Metóda na resetovanie posunu obrazu hráča.
     */
    public void setPosunObrazu() {
        this.posunObrazu = 0;
    }
    
    /**
     * Metóda na získanie posunu obrazu hráča.
     * 
     * @return Posun obrazu hráča.
     */
    public int getPosunObrazu() {
        return this.posunObrazu;
    }
    
    /**
     * Metóda na získanie Y-ových súradníc hráča.
     * 
     * @return Y-ové súradnice hráča.
     */
    public int getYHraca() {
        this.skusime = y;
        return this.skusime;
    }
    
    /**
     * Vráti smer dopadu hráča.
     * 
     * @return Smer dopadu (1 alebo 0).
     */
    public int smerDopadu() {
        if (this.xKvad > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    
    
    
    /**
     * Posunie loptičky doľava.
     * A mení obrázok loptičky.
     */
    public void vlavo() {
        this.x -= 7;
        if (this.y > 300) {
            this.typHraca = TypHraca.VLAVO;
            this.obrazok.zmenObrazok(this.typHraca.getLink());
        } else {
            this.typHraca = TypHraca.VLAVOO;
            this.obrazok.zmenObrazok(this.typHraca.getLink());
        }
    }
    
    /**
     * Posunie loptičky doprava.
     * A mení obrázok loptičky.
     */
    public void vpravo() {
        this.x += 7;
        if (this.y > 300) {
            this.typHraca = TypHraca.VPRAVO;
            this.obrazok.zmenObrazok(this.typHraca.getLink());
        } else {
            this.typHraca = TypHraca.VPRAVOO;
            this.obrazok.zmenObrazok(this.typHraca.getLink());
        }
    }
    
    /**
     * Keď hráč pustí tlačídko "lavá šipka" nastaví sa sa loptičke pohlaď z predu.
     * Taktiež mení obrazok loptičky pri "POWER JUMP".
     */
    public void vlavoR() {
        if (this.y > 300) {
            this.typHraca = TypHraca.VPREDU;
            this.obrazok.zmenObrazok(this.typHraca.getLink());
        } else {
            this.typHraca = TypHraca.VPREDUO;
            this.obrazok.zmenObrazok(this.typHraca.getLink());
        }
    }
    
    /**
     * Keď hráč pustí tlačídko "pravá šipka" nastaví sa sa loptičke pohlaď z predu.
     * Taktiež mení obrazok loptičky pri "POWER JUMP".
     */
    public void vpravoR() {
        if (this.y > 300) {
            this.typHraca = TypHraca.VPREDU;
            this.obrazok.zmenObrazok(this.typHraca.getLink());
        } else {
            this.typHraca = TypHraca.VPREDU;
            this.obrazok.zmenObrazok(this.typHraca.getLink());
        }
    }
    
}
