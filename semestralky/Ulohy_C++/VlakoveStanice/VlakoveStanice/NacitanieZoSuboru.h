#pragma once
#include <map>
#include <fstream>
#include <string>
#include <iostream>
#include <sstream>
#include "Stanica.h"

using namespace std;

class NacitanieZoSuboru
{
private :
	string nazovSuboru;
	map<int, Stanica*> zoznamStanic;


	
public:
	NacitanieZoSuboru(string pNazovSuboru);
	void citaj();
	void vypisStanic();
	void priradenieKolajiKStaniciam(string pnazovSuboru);
	void vypisKolajiStanic();
	~NacitanieZoSuboru();
};

