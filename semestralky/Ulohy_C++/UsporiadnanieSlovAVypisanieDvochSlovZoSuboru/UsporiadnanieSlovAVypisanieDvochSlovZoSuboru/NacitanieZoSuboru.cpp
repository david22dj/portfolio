#include "NacitanieZoSuboru.h"

NacitanieZoSuboru::NacitanieZoSuboru(string pnazovSuboru)
{
	nazovSuboru = pnazovSuboru;
}

void NacitanieZoSuboru::nacitajAuloz()
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
			
			zoznameSlov.push_back(riadok);
			//cout << riadok << endl;

		}
	}
	subor.close();
}

void NacitanieZoSuboru::najdiUnikatneSlovaASpocitaj()
{


	for  (string  slovo : zoznameSlov)
	{
		if (find(unikatneSlova.begin(), unikatneSlova.end(), slovo) == unikatneSlova.end()) {
			unikatneSlova.push_back(slovo);
		}

	}

	unikatneSlova.sort();

	for (string  hladaneSlovo : unikatneSlova) 
	{
		cout << hladaneSlovo << " : ";
		int premenna = 0;
		for (string  slovo : zoznameSlov) {
			if (hladaneSlovo._Equal(slovo)) {
				premenna++;
			}
		}
		vyskytSlov.push_back(premenna);
		cout << premenna << endl;
		premenna = 0;

	}
}

void NacitanieZoSuboru::vypisDveNajmenejVyskytujuce()
{
	for (string slovo : zoznameSlov)
	{
		slovaAVyskyty[slovo]++;
	}
	
	using Pair = pair<string, int>;
	Pair max1 = { "", INT_MIN };
	Pair max2 = { "", INT_MIN };

	for (const auto& pair : slovaAVyskyty)
	{
		if (pair.second > max1.second)
		{
			max2 = max1;
			max1 = pair;

		}
		else if (pair.second > max2.second)
		{
			max2 = pair;
		}
	}

	cout << "Dve najcastejsie slova su:" << endl;
	cout << max1.first << " : " << max1.second << endl;
	cout << max2.first << " : " << max2.second << endl;


}

NacitanieZoSuboru::~NacitanieZoSuboru()
{
	
}
