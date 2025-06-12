#include "NacitanieZoSuboru.h"

NacitanieZoSuboru::NacitanieZoSuboru(string pNazovSuboru)
{
	nazovSuboru = pNazovSuboru;
}

void NacitanieZoSuboru::nacitaj()
{
	fstream subor(nazovSuboru);
	if (!subor.is_open())
	{
		cout << "Nepodarilo sa otvorit subor!";
	}
	else
	{
		string riadok;
		while (getline(subor, riadok))
		{

			zoznamSlov.push_back(riadok);
			cout << riadok << endl;

		}
	}
	subor.close();
}


void NacitanieZoSuboru::vypis()
{
	zoznamSlov.sort(greater<string>());
	cout << "--------------" << endl;
	for (string slovo : zoznamSlov)
	{
		cout << slovo << endl;
	}
}

void NacitanieZoSuboru::vypisPodlaPismena(char Ppismeno)
{
	
	char pismeno = std::tolower(Ppismeno);

	for (string slovo : zoznamSlov)
	{
		string maleSlovo = slovo;
		transform(maleSlovo.begin(), maleSlovo.end(), maleSlovo.begin(), [](unsigned char c) { return std::tolower(c); });
		
		for (const char& pismenoVSlove : maleSlovo) {
			if (pismeno == pismenoVSlove) {
				cout << slovo << endl;
				break; // Ukonèí vnútorný cyklus pre aktuálne slovo
			}
		}

	}
	
	
}
