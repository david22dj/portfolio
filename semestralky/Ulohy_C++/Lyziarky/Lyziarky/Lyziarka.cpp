#include "Lyziarka.h"

Lyziarka::Lyziarka(int pStartoveCislo, string pMenoLyziarky)
{
	startoveCislo = pStartoveCislo;
	menoLyziarky = pMenoLyziarky;
	cas = 0.0;
	stav = "";
}

void Lyziarka::pridajCas(double pCas)
{
	cas = pCas;
}

void Lyziarka::pridajStav(string pStav)
{
	stav = pStav;
}

int Lyziarka::getStartoveCislo()
{
	return startoveCislo;
}

string Lyziarka::getMenoLyziarky()
{
	return menoLyziarky;
}

double Lyziarka::getCas()
{
	return cas;
}

string Lyziarka::getStav()
{
	return stav;
}
