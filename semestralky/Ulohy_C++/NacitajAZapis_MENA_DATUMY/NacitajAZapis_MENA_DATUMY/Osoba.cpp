#include "Osoba.h"



Osoba::Osoba(string PmenoApriezvisko, int Prok, int Pmesiac, int Pden)
{
	menoApriezvisko = PmenoApriezvisko;
	rok = Prok;
	mesiac = Pmesiac;
	den = Pden;
}

string Osoba::getMeno()
{
	return menoApriezvisko;
}

int Osoba::getRok()
{
	return rok;
}

int Osoba::getMesiac()
{
	return mesiac;
}

int Osoba::getDen()
{
	return den;
}
