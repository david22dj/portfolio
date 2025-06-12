#include "NacitanieKrajin.h"


NacitanieKrajin::NacitanieKrajin(string pNazovSuboru)
{
	nazovSuboru = pNazovSuboru;
}

void NacitanieKrajin::nacitajKrajinu()
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
			
			krajiny.push_back(Krajina(riadok));
			//cout << riadok << endl;

		}
	}
	subor.close();
}

void NacitanieKrajin::simulujZapasu()
{
	
	for (size_t i = 0; i < krajiny.size(); i++)
	{
		for (size_t y = i + 1; y < krajiny.size(); y++)
		{
			int skorePrve = rand() % (6 - 0 + 1) + 0;
			int skoreDruhe = rand() % (6 - 0 + 1) + 0;
			if (skorePrve < skoreDruhe) 
			{
				krajiny[i].pridajBody(3);
			}
			else if (skorePrve > skoreDruhe)
			{
				krajiny[y].pridajBody(3);
			}
			else
			{
				krajiny[i].pridajBody(1);
				krajiny[y].pridajBody(1);
			}
		}
	}
}

void NacitanieKrajin::vypisKrajiny()
{
	for (Krajina krajina : krajiny)
	{
		usproriadaneKrajiny.emplace(krajina.getBody(), krajina.getNazovKrajiny());
	}

	

	int poradie = 1;

	for (const auto& pair : usproriadaneKrajiny)
	{
		cout << poradie << ". " << pair.second << " - " << pair.first << " bodov" << endl;
		poradie++;
	}


	
}

void NacitanieKrajin::krajinaPodlaPoradia(int poradie)
{
	if (poradie < 1 || poradie > usproriadaneKrajiny.size()) {
		std::cout << "Neplatne poradie! Zadaj cislo od 1 do " << usproriadaneKrajiny.size() << ".\n";
		return;
	}
	int pocitadlo = 1;

	for (const auto& pair : usproriadaneKrajiny)
	{
		if (poradie == pocitadlo)
		{
			cout << pair.second << " - " << pair.first << " bodov" << endl;
			return;
		}
		pocitadlo++;
	}
}
