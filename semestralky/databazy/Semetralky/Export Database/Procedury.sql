create or replace PROCEDURE uprav_film 
(
    p_id_filmu             IN film.id_filmu%TYPE,
    p_nazov                IN film.nazov%TYPE,
    p_jazykova_verzia      IN film.jazykova_verzia%TYPE,
    p_produkcna_spolocnost IN film.produkcna_spolocnost%TYPE
) 
AS
BEGIN
    UPDATE film
    SET
        nazov = p_nazov,
        jazykova_verzia = p_jazykova_verzia,
        produkcna_spolocnost = p_produkcna_spolocnost
    WHERE id_filmu = p_id_filmu;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20000, 'Nepodarilo sa upraviť film!');
END;
/

create or replace PROCEDURE vloz_film 
(
    p_id_filmu             IN film.id_filmu%TYPE,
    p_nazov                IN film.nazov%TYPE,
    p_jazykova_verzia      IN film.jazykova_verzia%TYPE,
    p_produkcna_spolocnost IN film.produkcna_spolocnost%TYPE
) 
AS
BEGIN
    INSERT INTO film 
    (
        id_filmu, nazov, jazykova_verzia, produkcna_spolocnost
    ) 
    VALUES (
        p_id_filmu, p_nazov, p_jazykova_verzia, p_produkcna_spolocnost
    );

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20000, 'Nepodarilo sa vložiť film!');
END;
/

create or replace PROCEDURE vypis_filmy_podla_predajov 
(
    p_datum_od IN DATE,
    p_datum_do IN DATE
) 
AS
    CURSOR film_cursor IS
        SELECT f.nazov, COUNT(*) AS pocet_listkov
        FROM predane_listky pl
        JOIN film f ON f.id_filmu = pl.premietanie_film_id_filmu
        WHERE pl.premietanie_premietanie_cas BETWEEN p_datum_od AND p_datum_do
        GROUP BY f.nazov
        ORDER BY pocet_listkov DESC;

    v_nazov         film.nazov%TYPE;
    v_pocet_listkov INTEGER;
    v_counter       INTEGER := 0;
BEGIN
    OPEN film_cursor;
    LOOP
        FETCH film_cursor INTO v_nazov, v_pocet_listkov;
        EXIT WHEN film_cursor%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('Film: ' || v_nazov || ' – počet lístkov: ' || v_pocet_listkov);
        v_counter := v_counter + 1;
    END LOOP;
    CLOSE film_cursor;

    IF v_counter = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Za zadané obdobie nebol predaný žiadny lístok.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20000, 'Chyba!');
END;
/

create or replace PROCEDURE vypis_kina_pre_film 
(
    p_id_filmu IN premietanie.film_id_filmu%TYPE
) 
AS
    CURSOR kina_cursor IS
        SELECT DISTINCT k.id_kina, k.nazov_kina, k.adresa
        FROM kino k
        JOIN premietanie p ON k.id_kina = p.kino_id_kina
        WHERE p.film_id_filmu = p_id_filmu;

    v_kino_id    kino.id_kina%TYPE;
    v_nazov      kino.nazov_kina%TYPE;
    v_adresa     kino.adresa%TYPE;
    v_counter    INTEGER := 0;
BEGIN
    OPEN kina_cursor;
    LOOP
        FETCH kina_cursor INTO v_kino_id, v_nazov, v_adresa;
        EXIT WHEN kina_cursor%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('Kino ID: ' || v_kino_id || ', Názov: ' || v_nazov || ', Adresa: ' || v_adresa);

        v_counter := v_counter + 1;
    END LOOP;
    CLOSE kina_cursor;

    IF v_counter = 0 
    THEN DBMS_OUTPUT.PUT_LINE('Žiadne kino nepremieta zadaný film.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20000, 'Chyba!');
END;
/


create or replace PROCEDURE vypis_najnavstevovanejsie_kina 
AS
    CURSOR navstevnost_cursor IS
        SELECT
            k.id_kina,
            k.nazov_kina,
            COUNT(pl.zakaznik_rod_cislo) AS pocet_listkov
        FROM predane_listky pl
        JOIN kino k ON k.id_kina = pl.premietanie_kino_id_kina
        GROUP BY k.id_kina, k.nazov_kina
        ORDER BY pocet_listkov DESC;

    v_id_kina       kino.id_kina%TYPE;
    v_nazov_kina    kino.nazov_kina%TYPE;
    v_pocet_listkov NUMBER;
    v_counter       INTEGER := 0;
BEGIN
    OPEN navstevnost_cursor;
    LOOP
        FETCH navstevnost_cursor INTO v_id_kina, v_nazov_kina, v_pocet_listkov;
        EXIT WHEN navstevnost_cursor%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(
            'Kino: ' || v_nazov_kina || ' (ID: ' || v_id_kina || ') – Predané lístky: ' || v_pocet_listkov
        );
        v_counter := v_counter + 1;
    END LOOP;
    CLOSE navstevnost_cursor;

    IF v_counter = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Žiadne dáta o predaji lístkov.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20000, 'Chyba!');
END;
/

create or replace PROCEDURE vypis_premietania_pre_kino 
(
    p_id_kina IN premietanie.kino_id_kina%TYPE,
    p_datum   IN DATE
) 
AS
    CURSOR premietania_cursor IS
        SELECT
            p.premietanie_cas,
            f.nazov,
            p.cena_listka,
            p.obsadenost_miest,
            p.max_kapacita
        FROM premietanie p
        JOIN film f ON f.id_filmu = p.film_id_filmu
        WHERE p.kino_id_kina = p_id_kina
          AND TRUNC(p.premietanie_cas) = TRUNC(p_datum)
        ORDER BY p.premietanie_cas;

    v_cas           DATE;
    v_nazov_filmu   film.nazov%TYPE;
    v_cena          NUMBER;
    v_obsadenost    NUMBER;
    v_kapacita      NUMBER;
    v_counter       INTEGER := 0;
BEGIN
    OPEN premietania_cursor;
    LOOP
        FETCH premietania_cursor INTO v_cas, v_nazov_filmu, v_cena, v_obsadenost, v_kapacita;
        EXIT WHEN premietania_cursor%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(
            TO_CHAR(v_cas, 'HH24:MI') || ' – ' || v_nazov_filmu ||
            ' | Cena: ' || v_cena || '€ | Obsadenosť: ' || v_obsadenost || '/' || v_kapacita
        );
        v_counter := v_counter + 1;
    END LOOP;
    CLOSE premietania_cursor;

    IF v_counter = 0 THEN
        DBMS_OUTPUT.PUT_LINE('V daný deň nie sú premietania pre toto kino.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20000, 'Chyba!');
END;
/

create or replace PROCEDURE vypis_priemernu_obsadenost_kin 
AS
    CURSOR obsadenost_cursor IS
        SELECT
            k.id_kina,
            k.nazov_kina,
            ROUND(AVG(p.obsadenost_miest * 100 / p.max_kapacita), 2) AS priemer_obsadenosti
        FROM premietanie p
        JOIN kino k ON k.id_kina = p.kino_id_kina
        WHERE p.premietanie_cas BETWEEN ADD_MONTHS(SYSDATE, -1) AND SYSDATE
        GROUP BY k.id_kina, k.nazov_kina;

    v_id_kina        kino.id_kina%TYPE;
    v_nazov_kina     kino.nazov_kina%TYPE;
    v_priemer        NUMBER;
    v_counter        INTEGER := 0;
BEGIN
    OPEN obsadenost_cursor;
    LOOP
        FETCH obsadenost_cursor INTO v_id_kina, v_nazov_kina, v_priemer;
        EXIT WHEN obsadenost_cursor%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(
            'Kino: ' || v_nazov_kina || ' (ID: ' || v_id_kina || ') – Priemerná obsadenosť: ' || v_priemer || '%'
        );
        v_counter := v_counter + 1;
    END LOOP;
    CLOSE obsadenost_cursor;

    IF v_counter = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Žiadne premietania za posledný mesiac.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20000, 'Chyba!');
END;
/

create or replace PROCEDURE vypis_statistiku_platby
AS
    v_total        NUMBER := 0;
    v_karta        NUMBER := 0;
    v_hotovost     NUMBER := 0;
    v_online       NUMBER := 0;
BEGIN

    SELECT COUNT(*) INTO v_total FROM predane_listky;

    IF v_total = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Neboli predané žiadne lístky.');
        RETURN;
    END IF;

    SELECT
        SUM(CASE WHEN platobna_metoda = 'K' THEN 1 ELSE 0 END),
        SUM(CASE WHEN platobna_metoda = 'H' THEN 1 ELSE 0 END),
        SUM(CASE WHEN platobna_metoda = 'O' THEN 1 ELSE 0 END)
    INTO v_karta, v_hotovost, v_online
    FROM predane_listky;

    DBMS_OUTPUT.PUT_LINE('Štatistika platobných metód (%):');
    DBMS_OUTPUT.PUT_LINE('Kartou:     ' || ROUND(v_karta / v_total * 100, 2) || '%');
    DBMS_OUTPUT.PUT_LINE('Hotovosť:   ' || ROUND(v_hotovost / v_total * 100, 2) || '%');
    DBMS_OUTPUT.PUT_LINE('Online:     ' || ROUND(v_online / v_total * 100, 2) || '%');

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20000, 'Chyba!');
END;
/

create or replace PROCEDURE zmaz_film 
(
    p_id_filmu IN film.id_filmu%TYPE
) 
AS
BEGIN
    DELETE FROM film
    WHERE id_filmu = p_id_filmu;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20000, 'Nepodarilo sa zmazať film!');
END;
/


