--report_filmy_podla_predajov
SELECT
    f.nazov AS film,
    'počet predaných lístkov',
    COUNT(*) AS pocet_predanych_listkov
    
        FROM predane_listky pl
        JOIN film f ON f.id_filmu = pl.premietanie_film_id_filmu
        GROUP BY f.nazov
        ORDER BY pocet_predanych_listkov DESC;

--report_pocty_platieb
SELECT
    'počet danej platby',
    platobna_metoda,
    COUNT(*) as pocet
FROM predane_listky
GROUP BY platobna_metoda;

--report_priemerna_obsadenost_kin
SELECT
     k.nazov_kina,
     'priemerná obsadenosť',
     ROUND(SUM(p.obsadenost_miest)/COUNT(*), 2) AS priemer_obsadenosti
        FROM premietanie p
        JOIN kino k ON k.id_kina = p.kino_id_kina
        GROUP BY k.id_kina, k.nazov_kina;