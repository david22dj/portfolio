#pragma once
#include <iostream>

using namespace std;

class Krajina


{
private:
	int body;
	string nazovKrajiny;
public:
	Krajina(string pKrajina);
	void pridajBody(int bodyNaPridanie);
	string getNazovKrajiny();
	int	getBody();
	~Krajina();



};

