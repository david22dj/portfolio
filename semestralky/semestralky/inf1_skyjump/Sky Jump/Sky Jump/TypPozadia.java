
/**
 * Túto triedu som celú prevzal zo svojej minuloročnej semestrálnej práce.
 * Enum TypPozadia obsahuje typy pozadia v hre s priradenými obrázkovými súbormi.
 */
public enum TypPozadia {
    POZADIE("pics/POZADIE.png");
    

    private String link;
    
    /**
     * Konštruktor enumu TypPozadia s inicializáciou názvu obrázkového súboru.
     *
     * @param link Názov obrázkového súboru pre daný typ pozadia.
     */
    TypPozadia(String link) {
        this.link = link;
    }

    /**
     * Metóda na získanie názvu obrázkového súboru pre daný typ pozadia.
     *
     * @return Názov obrázkového súboru.
     */
    public String getLink() {
        return this.link;
    }
}