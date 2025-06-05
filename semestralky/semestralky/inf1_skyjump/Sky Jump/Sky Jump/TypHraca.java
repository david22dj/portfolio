
/**
 * Túto triedu som celú prevzal zo svojej minuloročnej semestrálnej práce.
 * Enum TypHraca obsahuje typy vizuálneho zobrazenia hráča v hre s priradenými obrázkovými súbormi.
 */
public enum TypHraca {
    VPRAVO("pics/ballVpravo.png"),
    VPRAVOO("pics/ballOVpravo.png"),
    VLAVO("pics/ballVlavo.png"),
    VLAVOO("pics/ballOVlavo.png"),
    VPREDU("pics/Ball.png"),
    VPREDUO("pics/BallO.png");

    private String link;
    
    /**
     * Konštruktor enumu TypHraca s inicializáciou názvu obrázkového súboru pre daný typ hráča.
     *
     * @param link Názov obrázkového súboru pre daný typ hráča.
     */
    TypHraca(String link) {
        this.link = link;
    }
    
    /**
     * Metóda na získanie názvu obrázkového súboru pre daný typ hráča.
     *
     * @return Názov obrázkového súboru.
     */
    public String getLink() {
        return this.link;
    }
}