public class Predmet {
    int hmotnost;
    int cena;
    int index;

    public Predmet(int index, int hmotnost, int cena) {
        this.hmotnost = hmotnost;
        this.cena = cena;
        this.index = index;
    }

    public int getHmotnost() {
        return hmotnost;
    }

    public int getCena() {
        return cena;
    }

    public int getIndex() {
        return index;
    }
}
