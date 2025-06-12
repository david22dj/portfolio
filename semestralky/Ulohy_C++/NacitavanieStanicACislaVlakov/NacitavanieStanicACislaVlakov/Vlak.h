#pragma once
#include <string>
using namespace std;

class Vlak
{
private:
	string cisloVlaku;
	int pocetStanic;

public:
	Vlak(string pNazovVlaku);
	Vlak();
	void pridajStanicu();
	string getCisloVlaku();
	int getPocetStatic();

};

