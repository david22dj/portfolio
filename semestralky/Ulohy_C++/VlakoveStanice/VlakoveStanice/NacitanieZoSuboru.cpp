#include "NacitanieZoSuboru.h"

NacitanieZoSuboru::NacitanieZoSuboru(string pNazovSuboru)
{
	nazovSuboru = pNazovSuboru;
}

void NacitanieZoSuboru::citaj()
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
			stringstream ss(riadok);
			int nazovStaniceCislo;
			string nazovStanice;
			ss >> nazovStaniceCislo >> nazovStanice;
			zoznamStanic.emplace(nazovStaniceCislo, new Stanica(nazovStanice));
		}
	}
	subor.close();
}

void NacitanieZoSuboru::vypisStanic()
{
	for (const auto& pair : zoznamStanic)
	{
		cout << pair.second->getNazovStanice() << endl;
	}
	cout << endl;
}

void NacitanieZoSuboru::priradenieKolajiKStaniciam(string pnazovSuboru)
{
	fstream subor(pnazovSuboru);
	if (!subor.is_open())
	{
		cout << "Nepodarilo sa otvorit subor!";
	}
	else
	{
		string riadok;
		while (getline(subor, riadok))
		{
			stringstream ss(riadok);
			int nazovStaniceCislo;
			int cisloKolaje;
			ss >> nazovStaniceCislo >> cisloKolaje;
			auto it = zoznamStanic.find(nazovStaniceCislo);
			if (it != zoznamStanic.end())
			{
				it->second->pridajKolaj(cisloKolaje);
			}
		}
	}
	subor.close();
}

void NacitanieZoSuboru::vypisKolajiStanic()
{
	for (const auto& pair : zoznamStanic)
	{
		
	}
	for (const auto& pair : zoznamStanic)
	{
		cout << pair.second->getNazovStanice() << ":" << endl;
		list<int> zoradeneKolaje = pair.second->getKolaje();
		zoradeneKolaje.sort(greater<int>());
		for (int kolaj : zoradeneKolaje)
		{
			cout << kolaj << endl;
		}
		cout << endl;
	}
}

NacitanieZoSuboru::~NacitanieZoSuboru()
{
}
