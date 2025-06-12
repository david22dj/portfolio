#include "Krajina.h"

Krajina::Krajina(string pKrajina)
{
	nazovKrajiny = pKrajina;
	body = 0;
}

void Krajina::pridajBody(int bodyNaPridanie)
{
	body += bodyNaPridanie;
}

string Krajina::getNazovKrajiny()
{
	return nazovKrajiny;
}


int Krajina::getBody()
{
	return body;
}

Krajina::~Krajina()
{
}
