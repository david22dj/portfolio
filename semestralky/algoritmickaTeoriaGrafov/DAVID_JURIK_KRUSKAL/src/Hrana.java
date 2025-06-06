public class Hrana {
    private Vrchol zac, kon;
    private int cena;
    private boolean jeVKostre;

    public Hrana(Vrchol u, Vrchol v, int cena) {
        this.zac = u;
        this.kon = v;
        this.cena = cena;
        this.jeVKostre = false;
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
        return "{" + zac.getCislo() + "," + kon.getCislo() + "}";
    }
    // Getter pre atribút jeVKostre
    public boolean getJeVKostre() {
        return jeVKostre;
    }

    // Setter pre atribút jeVKostre
    public void setJeVKostre(boolean jeVKostre) {
        this.jeVKostre = jeVKostre;
    }
}