#include "Nacitanie.h"

Nacitanie::Nacitanie(string pNazovSuboru)
{
	nazovSuboru = pNazovSuboru;
}

void Nacitanie::NacitajLyziarky()
{
	fstream subor(nazovSuboru);
	if (!subor.is_open())
	{
		cout << "Nepodarilo sa otvorit subor!";
	}
	else
	{
		string riadok;
		int poradoveCislo = 1;
		while (getline(subor, riadok))
		{

			lyziarky.push_back(Lyziarka(poradoveCislo, riadok));
			
			//cout << poradoveCislo << ". " << riadok << endl;
			poradoveCislo++;
		}
	}
	subor.close();
}

void Nacitanie::SimulujPretek()
{
	for (Lyziarka& lyziarka : lyziarky)
	{
		int nahodnaUdalost = rand() % (100 - 0 + 1) + 0;
		if (nahodnaUdalost < 11)
		{
			lyziarka.pridajStav("DSQ");
		}
		else if (nahodnaUdalost > 89)
		{
			lyziarka.pridajStav("DNF");
		}
		else if (nahodnaUdalost > 46 && nahodnaUdalost < 54)
		{
			lyziarka.pridajStav("DNF!");
		}
		else
		{
			double nahodnyCas = rand() % (5999 - 5200 + 1) + 5200;
			nahodnyCas = nahodnyCas / 100;
			lyziarka.pridajCas(nahodnyCas);
		}
		cout << "Lyziarka: " << lyziarka.getMenoLyziarky()
			<< ", Cas: " << lyziarka.getCas()
			<< ", Stav: " << lyziarka.getStav() << endl;
	}
	
	
	
}

void Nacitanie::VypisLiarky()
{
	

	for (Lyziarka lyziarka : lyziarky)
	{
		usporiadaneLyziarky.emplace(lyziarka.getCas(), lyziarka);
	}
	
	int vysledneCislo = 1;
	double casPred = 0;
	for (const auto& pair : usporiadaneLyziarky)
	{

		
		Lyziarka lyziarka = pair.second;
		
		

		if (pair.first != 0)
		{
			if (vysledneCislo == 1)
			{
				cout << vysledneCislo << ". " << lyziarka.getMenoLyziarky() << " [" << lyziarka.getStartoveCislo() << "]       " << pair.first << endl;
				
				casPred = lyziarka.getCas();
				vysledneCislo++;

			}
			else
			{
				double cas = pair.first - casPred;
				cout << vysledneCislo << ". " << lyziarka.getMenoLyziarky() << " [" << lyziarka.getStartoveCislo() << "]       +" << pair.first << "    " << cas << endl;
				vysledneCislo++;
			}
		}
	}
	for (const auto& pair : usporiadaneLyziarky)
	{
		Lyziarka lyziarka = pair.second;


		if (pair.first == 0)
		{
			if (lyziarka.getStav() == "DNF")
			{
				cout << vysledneCislo << ". " << lyziarka.getMenoLyziarky() << " [" << lyziarka.getStartoveCislo() << "]      " << lyziarka.getStav() << endl;
				vysledneCislo++;
			}
		}
	}
	for (const auto& pair : usporiadaneLyziarky)
	{
		Lyziarka lyziarka = pair.second;


		if (pair.first == 0)
		{
			if (lyziarka.getStav() == "DNF!")
			{
				cout << vysledneCislo << ". " << lyziarka.getMenoLyziarky() << " [" << lyziarka.getStartoveCislo() << "]      " << lyziarka.getStav() << endl;
				vysledneCislo++;
			}
		}
	}
	for (const auto& pair : usporiadaneLyziarky)
	{
		Lyziarka lyziarka = pair.second;


		if (pair.first == 0)
		{
			if (lyziarka.getStav() == "DSQ")
			{
				cout << vysledneCislo << ". " << lyziarka.getMenoLyziarky() << " [" << lyziarka.getStartoveCislo() << "]      " << lyziarka.getStav() << endl;
				vysledneCislo++;
			}
		}
	}

	cout << "koniec" << endl;
}


