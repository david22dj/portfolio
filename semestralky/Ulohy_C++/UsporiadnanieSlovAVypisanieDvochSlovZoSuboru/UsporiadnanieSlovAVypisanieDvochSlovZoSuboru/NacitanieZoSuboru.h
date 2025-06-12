#pragma once

#include <fstream>
#include <list>
#include <iostream>
#include <string>
#include <map>

using namespace std;


class NacitanieZoSuboru
{
private:

	string nazovSuboru;
	list<string> zoznameSlov;
	list<string> unikatneSlova;
	list<int>	 vyskytSlov;
	map<string, int> slovaAVyskyty;

	//bint* pole = new int[100];  // Alokácia pamäte pre pole

	

public:

	NacitanieZoSuboru(string pnazovSuboru);

	void nacitajAuloz();
	void najdiUnikatneSlovaASpocitaj();
	void vypisDveNajmenejVyskytujuce();
	~NacitanieZoSuboru();

};

