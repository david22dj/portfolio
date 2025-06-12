#pragma once
#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include "Osoba.h"
#include <sstream>

using namespace std;

class Nacitaj
{
private:
	string nazovSuboru;
	vector<Osoba> osoby;
	
public:
	Nacitaj(string pNazovSuboru);
	void NacitajOsoby();

	vector<char> VytvorKodovaciuTabulku();
	int VypocitajNasadu(Osoba osoba);
	string ZakodujMeno(const string& meno, const vector<char>& tabulka, int nasada);
	void ZapiseDoSuboru();
	void VypisZakodovaneOsoby();
};

