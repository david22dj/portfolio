import java.util.ArrayList;

public class Vrchol {
    private int cislo;
    private ArrayList<Vrchol> vrcholy;
    private int t;
    private int z;
    private int k;
    private int tVDigrafe;
    private Vrchol x;
    private int znackaD;

    public Vrchol(int cislo) {
        this.cislo = cislo;
        this.vrcholy = new ArrayList<Vrchol>();
    }

    public int getCislo() {
        return cislo;
    }

    public void addVrchol(Vrchol vrchol) {
        vrcholy.add(vrchol);
    }

    public ArrayList<Vrchol> getVrcholy() {
        return vrcholy;
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

    public int getZnackaD() {
        return znackaD;
    }

    public void setZnackaD(int znackaD) {
        this.znackaD = znackaD;
    }

    public int getTvdigrafe() {
        return tVDigrafe;
    }

    public void setTvdigrafe(int tvdigrafe) {
        this.tVDigrafe = tvdigrafe;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}
