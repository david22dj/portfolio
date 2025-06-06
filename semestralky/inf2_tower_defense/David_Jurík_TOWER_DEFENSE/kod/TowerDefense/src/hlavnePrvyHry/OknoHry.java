package hlavnePrvyHry;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
/**
 * Trieda zodpovedná za zobrazenie ovládacieho okna hry.
 * Obsahuje tlačidlá Štart a Ukonči a popisný štítok pre spätnú väzbu hráčovi.
 */
public class OknoHry {
    private JFrame okno;
    private JPanel panel;
    private JButton tlacidloStart;
    private JButton tlacidloUkonci;
    private JLabel popis;
    /**
     * Vytvorí okno s pevnou veľkosťou a pozíciou,
     * pridá tlačidlá Štart/Ukonči a štítok pre popis.
     */
    public OknoHry() {
        this.okno = new JFrame("Tower Defense – Ovládanie");
        this.okno.setSize(300, 200);
        this.okno.setLayout(null);
        this.okno.setLocation(100, 100);
        this.okno.setResizable(false);

        this.panel = new JPanel(null);
        this.panel.setBounds(0, 0, 300, 200);

        this.tlacidloStart = new JButton("Start");
        this.tlacidloStart.setBounds(50, 30, 200, 30);
        this.panel.add(this.tlacidloStart);

        this.tlacidloUkonci = new JButton("Ukonči");
        this.tlacidloUkonci.setBounds(50, 70, 200, 30);
        this.panel.add(this.tlacidloUkonci);

        this.popis = new JLabel("", JLabel.CENTER);
        this.popis.setBounds(50, 110, 200, 30);
        this.popis.setFont(new Font("Arial", Font.BOLD, 16));
        this.popis.setForeground(Color.BLUE);
        this.panel.add(this.popis);

        this.okno.add(this.panel);
        this.okno.setVisible(true);
    }
    /**
     * Vráti referenciu na tlačidlo Štart.
     *
     * @return JButton reprezentujúci tlačidlo Štart
     */
    public JButton dajTlacidloStart() {
        return this.tlacidloStart;
    }
    /**
     * Vráti referenciu na tlačidlo Ukonči.
     *
     * @return JButton reprezentujúci tlačidlo Ukonči
     */
    public JButton dajTlacidloUkonci() {
        return this.tlacidloUkonci;
    }
    /**
     * Vráti referenciu na štítok popisu pre spätnú väzbu.
     *
     * @return JLabel používaný na zobrazovanie textových správ hráčovi
     */
    public JLabel dajPopis() {
        return this.popis;
    }
    /**
     * Vráti referenciu na samotné JFrame okno.
     *
     * @return JFrame hlavné okno ovládania
     */
    public JFrame dajOkno() {
        return this.okno;
    }


}
