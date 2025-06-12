#pragma once
#include <map>
#include <fstream>
#include <string>
#include <iostream>
#include <sstream>
#include "Vlak.h"
#include <map>
#include <vector>
using namespace std;

class Nacitaj
{
private:
	string nazovSuboru;
	map<string, int> stanice;
	vector<Vlak> vlaky;
public:
	Nacitaj(string pNazovSuboru);
	void nacitajS();
	void nacitajV();

	void vypisStanic();
	void vypisPoctuStanicVlaku(string vlak);

};

