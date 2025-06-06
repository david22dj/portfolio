public class Vrchol {
    private int cislo;

    private int t;
    private Vrchol x;

    private int atribut;

    public Vrchol(int cislo) {
        this.cislo = cislo;
    }

    public int getCislo() {
        return cislo;
    }

    @Override
    public String toString() {
        return "" + cislo;
    }

    public int getT() {
        return t;
    }

    public Vrchol getX() {
        return x;
    }

    public void setT(int t) {
        this.t = t;
    }

    public void setX(Vrchol x) {
        this.x = x;
    }

    public int getAtribut() {
        return atribut;
    }

    public void setAtribut(int atribut) {
        this.atribut = atribut;
    }
}