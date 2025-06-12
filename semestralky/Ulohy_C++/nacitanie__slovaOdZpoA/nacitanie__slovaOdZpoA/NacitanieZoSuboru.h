#pragma once
#include <string>
#include <iostream>
#include <fstream>
#include <list>
#include <algorithm>
#include <cctype> 
using namespace std;

class NacitanieZoSuboru
{

private:
	string nazovSuboru;
	list<string> zoznamSlov;
	
public:
	NacitanieZoSuboru(string pNazovSuboru);
	void nacitaj();
	void vypis();
	void vypisPodlaPismena(char Ppismeno);
};

