#include "Stanica.h"

Stanica::Stanica(string pNazovStanice)
{
	nazovStanice = pNazovStanice;
}

string Stanica::getNazovStanice()
{
	return nazovStanice;
}

void Stanica::pridajKolaj(int kolaj)
{
	koleje.push_back(kolaj);
}

const list<int>& Stanica::getKolaje()
{
	return koleje;
}





Stanica::~Stanica()
{
}
