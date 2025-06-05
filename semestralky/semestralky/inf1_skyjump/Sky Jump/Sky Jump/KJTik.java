import fri.shapesge.Manazer;
import java.util.Random;
import java.io.IOException;
/**
 * Túto triedu som celú prevzal zo svojej minuloročnej semestrálnej práce.
 * Trieda KJTik reprezentuje riadiaci objekt pre herný cyklus.
 * Trieda využíva knižnicu Shapesge.
 */

public class KJTik {
    private Manazer manazer;
    private Hrac hrac;
    
    private int poradie = 0;
    private Random random;
    private Pozadie pozadie;
    private UkazovatelMetrov ukazovatelMetrov;
    private Platformy platformy;
    private int posunPozadia;
    
    private int nastupaneMetre;
    private double metre;
    private double zPixelovNaMetre = 0.001;
    private int pomalyPosun = 0;
    private int pohybX = 1;
    private int kazdyTreti = 0;
    private int kazdyDruhy = 0;
    private int kazdyStvrty = 0;
    
    private String menoPrihlaseneho;
    /**
     * Konštruktor pre inicializáciu herného objektu.
     * Vytvára inštanciu triedy Pozadie, UkazovatelMetrov, Platformy.
     * Spúšta medódu stustiTik.
     * 
     * @param menoPrihlaseneho meno prihláseného hráča
     * @throws IOException ak dôjde k chybe pri práci so súbormi počas inicializácie
     */
    public KJTik(String menoPrihlaseneho) throws IOException {
        
        this.pozadie = new Pozadie();
        this.ukazovatelMetrov = new UkazovatelMetrov();
        
        this.random = new Random();
        
        
        this.platformy = new Platformy();
        
        this.menoPrihlaseneho = menoPrihlaseneho;
        
        this.stustiTik();
        
    }
    /**
     * Inicializuje herný cyklus a vytvara polia xHodnoty a yHodnoty pre triedu Hrac.
     * Spušťa metódu tik.
     * 
     * @throws IOException ak dôjde k chybe pri práci so súbormi počas inicializácie
     */
    public void stustiTik() throws IOException {
        
        int[] xHodnoty = new int[this.platformy.getPocetPlatforiem()];
        int[] yHodnoty = new int[this.platformy.getPocetPlatforiem()];
        
        for (int i = 0; i < this.platformy.getPocetPlatforiem(); i++) {
            Platforma platforma = this.platformy.getPlatforma(i);
            if (platforma != null) {
                xHodnoty[i] = platforma.getX();
                yHodnoty[i] = platforma.getY();
            }
        }
        this.hrac = new Hrac(xHodnoty, yHodnoty, this.menoPrihlaseneho);
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        
    }
    
    /**
     * Metóda pre jeden krok herného cyklu.
     * Kontroluje či to čo je v hernom cykle ma byť spustené.
     * 
     * @throws IOException ak dôjde k chybe pri práci so súbormi
     */
    public void tik() throws IOException {
        
        if (this.hrac.getVykreslenie() == 0) {
            
            this.hrac.skacKvadraticka();
        
            this.posunPlatformy();
        }
        
        
    }
    
    /**
     * Posunie platformy po osi x a y.
     * Kontroluje a otača ich smer po osi x (v závisloti od úrovne hry).
     * Kontroluje a ak prekročia 800 da na 0 na osi y.
     * Taktiež platformy mení (v závisloti od úrovne hry).
     * Vykonáva sa v nej setovanie xHodnoty a yHodnoty pre triedu Hrac.
     * Vykonáva sa v nej aj sčítavanie hodnôt pre ukazovatelMetrov.
     * Vykonáva sa v nej aj aktualizovanie rekordu hry.
     * Vykonáva sa v nej aj posun pozadia.
     * 
     * @throws IOException ak dôjde k chybe pri práci so súbormi
     */
    public void posunPlatformy() throws IOException {
        this.nastupaneMetre += this.hrac.getPosunObrazu();
        this.metre = this.nastupaneMetre * zPixelovNaMetre;
        this.metre = Math.round(this.metre * 100.0) / 100.0;
        
        
        for (int i = 0; i < this.platformy.getPocetPlatforiem(); i++) {
            Platforma platforma = this.platformy.getPlatforma(i);
            
            if (this.hrac.getYHraca() < 750) {
                platforma.posunPlatformu(platforma.getX(), platforma.getY() + (int)this.hrac.getPosunObrazu() / 130);
                
            } else {
                this.pomalyPosun = 0;
                this.hrac.setPosunObrazu();
            }
            if (this.kazdyStvrty == 4 && this.metre < 1000 && this.metre > 500) {
                this.kazdyStvrty = 0;
                
                platforma.posunPlatformu(platforma.getX() + pohybX, platforma.getY() + (int)this.hrac.getPosunObrazu() / 130);
                if (platforma.getX() + pohybX > 400) {
                    this.pohybX = -1;
                } else if (platforma.getX() + pohybX < 0) {
                    this.pohybX = +1;
                }
                
            }
            if (this.kazdyTreti == 3 && this.metre < 2000 && this.metre > 1000 ) {
                this.kazdyTreti = 0;
                
                platforma.posunPlatformu(platforma.getX() + pohybX, platforma.getY() + (int)this.hrac.getPosunObrazu() / 130);
                if (platforma.getX() + pohybX > 400) {
                    this.pohybX = -1;
                } else if (platforma.getX() + pohybX < 0) {
                    this.pohybX = +1;
                }
                
            }
            if (this.kazdyDruhy == 2 && this.metre > 2000) {
                this.kazdyDruhy = 0;
                
                platforma.posunPlatformu(platforma.getX() + pohybX, platforma.getY() + (int)this.hrac.getPosunObrazu() / 130);
                if (platforma.getX() + pohybX > 400) {
                    this.pohybX = -1;
                } else if (platforma.getX() + pohybX < 0) {
                    this.pohybX = +1;
                }
                
            }
            this.kazdyStvrty += 1;
            if (this.kazdyStvrty >= 4) {
                this.kazdyStvrty = 4;
            }
            this.kazdyTreti += 1;
            if (this.kazdyTreti >= 3) {
                this.kazdyTreti = 3;
            }
            this.kazdyDruhy += 1;
            if (this.kazdyDruhy >= 2) {
                this.kazdyDruhy = 2;
            }
            if (platforma.getY() > 800) {
                platforma.posunPlatformu(this.random.nextInt(401), 0);
                if (this.metre < 1000 && this.metre > 500) {
                    platforma.zmenSaNaTravu();
                    if (this.posunPozadia < 4) {
                        this.posunPozadia += 1;
                        this.pozadie.posunPozadie();
                        this.pozadie.posunPozadie();
                    }
                    
                }
                if (this.metre < 2000 && this.metre > 1000) {
                    platforma.zmenSaNaOblak();
                    if (this.posunPozadia < 8) {
                        this.posunPozadia += 1;
                        this.pozadie.posunPozadie();
                        this.pozadie.posunPozadie();
                    }
                    
                }
                if (this.metre > 2000) {
                    platforma.zmenSaNaMeteorit();
                    if (this.posunPozadia < 12) {
                        this.posunPozadia += 1;
                        this.pozadie.posunPozadie();
                        this.pozadie.posunPozadie();
                    }
                    
                }
            }
        }
        this.ukazovatelMetrov.zmenMetre(this.metre);
        this.ukazovatelMetrov.zmenRecord(this.metre);
        
        this.pomalyPosun += 1;
        this.hrac.gameOver(this.metre);
        int[] xHodnoty = new int[this.platformy.getPocetPlatforiem()];
        int[] yHodnoty = new int[this.platformy.getPocetPlatforiem()];
        
        for (int i = 0; i < this.platformy.getPocetPlatforiem(); i++) {
            Platforma platforma = this.platformy.getPlatforma(i);
            if (platforma != null) {
                xHodnoty[i] = platforma.getX();
                yHodnoty[i] = platforma.getY();
            }
        }
        this.hrac.setPlatformy(xHodnoty, yHodnoty);
    }
    
}