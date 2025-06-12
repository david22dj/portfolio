#include "Nacitaj.h"

Nacitaj::Nacitaj(string pNazovSuboru)
{
	nazovSuboru = pNazovSuboru;
}

void Nacitaj::nacitajS()
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
			
			if (!isdigit(riadok[0]))
			{
				stanice[riadok]++;
			}
			
			//cout << riadok << endl;

		}
	}
	subor.close();
}

void Nacitaj::nacitajV()
{
	fstream subor(nazovSuboru);
	if (!subor.is_open())
	{
		cout << "Nepodarilo sa otvorit subor!";
	}
	else
	{
		string riadok;
		Vlak* premenna = nullptr;
		while (getline(subor, riadok))
		{

			if (isdigit(riadok[0]))
			{
				Vlak vlak(riadok);
				vlaky.push_back(vlak);
			}
			else
			{
				premenna = &vlaky.back();
				premenna->pridajStanicu();
			}
			//cout << riadok << endl;

		}
	}
	subor.close();
}

void Nacitaj::vypisStanic()
{
	for (const auto& pair : stanice)
	{
		cout << "Cez stanicu: " << pair.first << " prechadza " << pair.second << " vlaky." << endl;
	}
}

void Nacitaj::vypisPoctuStanicVlaku(string vlak)
{
	for (Vlak vlak : vlaky)
	{
		cout << "Vlak cislo: " << vlak.getCisloVlaku() << " prechadza cez: " << vlak.getPocetStatic() << " stanic." << endl;
	}
}
