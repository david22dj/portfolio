# Diskrétna optimalizácia – Duálna a výmenná heuristika

Tento semestrálny projekt rieši klasickú **úlohu o batohu**. Cieľom je maximalizovať celkovú cenu zaradených predmetov, pričom ich hmotnosť a počet nesmie prekročiť stanovené hranice.

## Použité algoritmy

- **Duálna vsúvacia heuristika**  
  Začína s neprípustným riešením obsahujúcim všetkých 500 predmetov a postupne odstraňuje tie s najnižšou cenou, kým riešenie nespĺňa obe obmedzenia.

- **Výmenná heuristika (1:1)**  
  Po nájdení prípustného riešenia sa heuristicky skúšajú výmeny medzi zaradenými a nezaradenými predmetmi, ktoré by mohli zlepšiť hodnotu účelovej funkcie bez prekročenia kapacity.
