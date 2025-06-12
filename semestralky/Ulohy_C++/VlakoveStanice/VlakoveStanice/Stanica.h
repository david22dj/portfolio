#pragma once
#include <iostream>
#include <string>
#include <list>

using namespace std;

class Stanica
{
private:
	string	nazovStanice;
	list<int> koleje;

public:
	
	Stanica(string pNazovStanice);
	string getNazovStanice();
	void pridajKolaj(int kolaj);
	const list<int>& getKolaje();
	~Stanica();
};

