#pragma once
#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include "Lyziarka.h"
#include <map>


using namespace std;

class Nacitanie
{
private:
	string nazovSuboru;
	vector<Lyziarka> lyziarky;
	multimap<double, Lyziarka> usporiadaneLyziarky;
	
	
public :
	Nacitanie(string pNazovSuboru);
	void NacitajLyziarky();
	void SimulujPretek();
	
	void VypisLiarky();

};

