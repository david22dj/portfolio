

#include <iostream>
#include "NacitanieZoSuboru.h"
#include "MemLeak.h"

using namespace std;

int main(int argc, char* argv[])
{
	{
		NacitanieZoSuboru nacitanieZoSuboru(argv[1]);
		nacitanieZoSuboru.nacitajAuloz();
		nacitanieZoSuboru.najdiUnikatneSlovaASpocitaj();
		nacitanieZoSuboru.vypisDveNajmenejVyskytujuce();
	}
	MemLeak memLeak();
	return 1;
}


