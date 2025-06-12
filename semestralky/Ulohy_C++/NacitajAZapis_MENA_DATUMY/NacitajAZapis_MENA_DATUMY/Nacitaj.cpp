#include "Nacitaj.h"

Nacitaj::Nacitaj(string pNazovSuboru)
{
	nazovSuboru = pNazovSuboru;
}

void Nacitaj::NacitajOsoby()
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
			istringstream ss(riadok);
			
			string menoApriezvisko;
			string meno;
			string priezvisko;
			int rok;
			int mesiac;
			int den;
			char bodka;
			char bodkaDva;

			ss >> meno >> priezvisko >> den >> bodka >> mesiac >> bodkaDva >> rok;

			
			menoApriezvisko = meno + " " + priezvisko;
			osoby.push_back(Osoba(menoApriezvisko, rok, mesiac, den));
			cout << meno << " " << priezvisko << " " << den << " " << mesiac << " " << rok << endl;
		}
		
	}
	subor.close();


}

vector<char> Nacitaj::VytvorKodovaciuTabulku()
{
	vector<char> tabulka;
	for (char c = 'A'; c <= 'Z'; c++) {
		tabulka.push_back(c);
	}
	tabulka.push_back(' '); // Pridáme medzeru
	return tabulka;
}

int Nacitaj::VypocitajNasadu(Osoba osoba)
{
	return osoba.getRok() * 10000 + osoba.getMesiac() * 100 + osoba.getDen();
}

string Nacitaj::ZakodujMeno(const string& meno, const vector<char>& tabulka, int nasada)
{
	string zakodovane;
	int velkostTabulky = tabulka.size();

	for (char c : meno) {
		if (c == ' ') {
			zakodovane += tabulka[nasada % velkostTabulky]; // Ak je medzera
		}
		else {
			int index = (c - 'A' + nasada) % velkostTabulky; // toto robi ze da na zaciatok a pripoci cislo nasadu to sa ale vydeli velkostou tabulky


			//if (index < 0 || index >= tabulka.size()) {
			//	cout << "Chyba: Index mimo rozsahu! Index: " << index << ", Velkost: " << tabulka.size() << endl;
			//	continue; // Alebo vhodná náhrada
			//} //////// TO KEDY TO IDE MIMOOOO


			zakodovane += tabulka[index];
		}
		//nasada /= velkostTabulky; // Posun nasady pre ïalší znak //toto je asi zle
	}
	return zakodovane;
}

void Nacitaj::ZapiseDoSuboru()
{
	ofstream subor("xxx.txt"); // Používame ofstream
	if (!subor.is_open()) {
		cout << "Nepodarilo sa vytvorit alebo otvorit subor!" << endl;
		return;
	}

	for (Osoba osoba : osoby) {
		int nasada = VypocitajNasadu(osoba);
		vector<char> tabulka = VytvorKodovaciuTabulku();
		string zakodovane = ZakodujMeno(osoba.getMeno(), tabulka, nasada);

		subor << osoba.getMeno() << " " << zakodovane << endl;
	}

	subor.close(); // Súbor je bezpeène uzavretý po dokonèení zápisu
}

void Nacitaj::VypisZakodovaneOsoby()
{
	for (Osoba osoba : osoby) {
		int nasada = VypocitajNasadu(osoba);
		vector<char> tabulka = VytvorKodovaciuTabulku();
		string zakodovane = ZakodujMeno(osoba.getMeno(), tabulka, nasada);

		cout << osoba.getMeno() << " " << zakodovane << endl;
	}
}
