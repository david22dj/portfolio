
/**
 * Túto triedu som celú prevzal zo svojej minuloročnej semestrálnej práce.
 * Enum TypPlatformy obsahuje typy platform v hre s priradenými obrázkovými súbormi.
 */
public enum TypPlatformy {
    HLINA("pics/hlina.png"),
    TRAVA("pics/trava.png"),
    OBLAK("pics/oblak.png"),
    METEORIT("pics/meteorit.png");
    
    private String link;
    
    /**
     * Konštruktor enumu TypPlatformy s inicializáciou názvu obrázkového súboru pre daný typ platformy.
     *
     * @param link Názov obrázkového súboru pre daný typ platformy.
     */
    TypPlatformy(String link) {
        this.link = link;
    }
    
    /**
     * Metóda na získanie názvu obrázkového súboru pre daný typ platformy.
     *
     * @return Názov obrázkového súboru.
     */
    public String getLink() {
        return this.link;
    }
}
