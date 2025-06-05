import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Trieda NacitanieUdajov slúži na načítanie mena hráča a inicializáciu
 * údajov potrebných pre hru.
 */
public class NacitanieUdajov {
    private String menoPrihlasenehoHraca;
    
    /**
     * Konštruktor triedy NacitanieUdajov.
     * Inicializuje meno hráča a vytvára objekty tried PamatPreHracov a KJTik.
     * 
     * @throws IOException ak dôjde k chybe pri práci so súborom počas inicializácie
     */
    public NacitanieUdajov() throws IOException {
        this.nacitajHraca();
        PamatPreHracov pamatPreHracov = new PamatPreHracov(this.menoPrihlasenehoHraca);
        KJTik kjtik = new KJTik(this.menoPrihlasenehoHraca);
    }
    
    /**
     * Načíta meno hráča z grafického dialógového okna.
     * Ak užívateľ nezadá meno, premennej sa priradí hodnota `null`.
     */
    public void nacitajHraca() {
        this.menoPrihlasenehoHraca = JOptionPane.showInputDialog(null, "Zadaj svoje meno:");
    }
}
