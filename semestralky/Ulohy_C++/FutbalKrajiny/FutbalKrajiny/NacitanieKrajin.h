#pragma once
#include <iostream>
#include <random>
#include <vector>
#include <fstream>
#include <string>
#include <map>


#include "Krajina.h"

using namespace std;

class NacitanieKrajin
{
private :
	string nazovSuboru;
	vector<Krajina> krajiny;
	multimap<int, string, greater<int>> usproriadaneKrajiny;

public:
	NacitanieKrajin(string pNazovSuboru);
	void nacitajKrajinu();
	void simulujZapasu();
	void vypisKrajiny();
	void krajinaPodlaPoradia(int poradie);
	
};

