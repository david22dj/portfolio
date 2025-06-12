#pragma once
#include <iostream>
#include <string>

using namespace std;

class Lyziarka
{
private:

	
	int startoveCislo;
	string menoLyziarky;
	double cas;
	string stav;

public:
	Lyziarka(int pStartoveCislo, string pMenoLyziarky);
	void pridajCas(double pCas);
	void pridajStav(string pStav);

	int getStartoveCislo();
	string getMenoLyziarky();
	double getCas();
	string getStav();

};

