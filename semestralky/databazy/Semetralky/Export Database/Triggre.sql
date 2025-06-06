create or replace TRIGGER sleduj_zakaznika
AFTER INSERT OR UPDATE OR DELETE ON zakaznik
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        DBMS_OUTPUT.PUT_LINE('Pridaný nový zákazník: ' || :NEW.rod_cislo);
    ELSIF UPDATING THEN
        DBMS_OUTPUT.PUT_LINE('Zmenený zákazník: ' || :OLD.rod_cislo);
    ELSIF DELETING THEN
        DBMS_OUTPUT.PUT_LINE('Zákazník vymazaný: ' || :OLD.rod_cislo);
    END IF;
END;
/

create or replace TRIGGER aktualizuj_id_kina
AFTER UPDATE OF id_kina ON kino
FOR EACH ROW
BEGIN
    UPDATE premietanie
    SET kino_id_kina = :NEW.id_kina
    WHERE kino_id_kina = :OLD.id_kina;

    UPDATE predane_listky
    SET premietanie_kino_id_kina = :NEW.id_kina
    WHERE premietanie_kino_id_kina = :OLD.id_kina;

    UPDATE zamestnanec
    SET kino_id_kina = :NEW.id_kina
    WHERE kino_id_kina = :OLD.id_kina;
END;
/