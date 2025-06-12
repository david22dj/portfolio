#include "Vlak.h"

Vlak::Vlak(string pNazovVlaku)
{
	cisloVlaku = pNazovVlaku;
	pocetStanic = 0;
}

Vlak::Vlak()
{
}

void Vlak::pridajStanicu()
{
	pocetStanic++;
}

string Vlak::getCisloVlaku()
{
	return cisloVlaku;
}

int Vlak::getPocetStatic()
{
	return pocetStanic;
}
