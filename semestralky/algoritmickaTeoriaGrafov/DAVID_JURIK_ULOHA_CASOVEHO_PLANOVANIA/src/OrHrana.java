public class OrHrana {
    private Vrchol zac, kon;
    private int cena;

    public OrHrana(Vrchol u, Vrchol v) {
        this.zac = u;
        this.kon = v;
    }

    public Vrchol getZac() {
        return zac;
    }

    public Vrchol getKon() {
        return kon;
    }

    public int getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return "(" + zac.getCislo() + "," + kon.getCislo() + ")";
    }
}