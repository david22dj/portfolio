public class OrHrana {
    private Vrchol zac, kon;
    private int c;
    private int d;
    private int y;

    public OrHrana(Vrchol u, Vrchol v, int c, int d) {
        this.zac = u;
        this.kon = v;
        this.c = c;
        this.d = d;
    }

    public Vrchol getZac() {
        return zac;
    }

    public Vrchol getKon() {
        return kon;
    }

    public int getC() {
        return c;
    }
    public void setC(int c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "(" + zac.getCislo() + "," + kon.getCislo() + ")";
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}