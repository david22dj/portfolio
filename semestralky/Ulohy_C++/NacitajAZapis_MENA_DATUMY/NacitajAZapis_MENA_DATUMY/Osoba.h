#pragma once

#include <iostream>
#include <string>

using namespace std;

class Osoba
{
private:
	string menoApriezvisko;
	int rok;
	int mesiac;
	int den;
public:
	Osoba(string PmenoApriezvisko, int Prok, int Pmesiac, int Pden);
	string getMeno();
	int getRok();
	int getMesiac();
	int getDen();
};

