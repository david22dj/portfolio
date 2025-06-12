// Lyziarky.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include "Nacitanie.h"
#include <cstdlib>
#include <ctime>
#include "MemLeak.h"

int main(int argc, char* argv[])
{
    {
        if (argc != 2) {
            cerr << "Použitie: " << argv[0] << " <nazov_suboru>" << endl;
            return 1;
        }


        //testovanieleakov
        //for (int i = 0; i < 10; i++) {
        //    int* leak = new int[i]; // alokuje pamä na heap
        //    leak[0] = i;           // robí nejakú operáciu, aby to vyzeralo legitímne
        //    // ale pamä sa nikdy neuvo¾ní (žiadne delete)
        //}


        srand(time(0));

        Nacitanie nacitanie(argv[1]);
        nacitanie.NacitajLyziarky();
        nacitanie.SimulujPretek();
        nacitanie.VypisLiarky();
    }
    MemLeak memLeak();

    return 1;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
