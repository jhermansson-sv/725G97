/**********************************************************************
 *  M�nsterigenk�nning readme.txt
 **********************************************************************/

 Ungef�rligt antal timmar spenderade p� labben (valfritt):

/**********************************************************************
 *  Empirisk    Fyll i tabellen nedan med riktiga k�rtider i sekunder
 *  analys      n�r det k�nns vettigt att v�nta p� hela ber�kningen.
 *              Ge uppskattningar av k�rtiden i �vriga fall.
 *
 **********************************************************************/
    
      N      brute(s)    sortering(s)
 ----------------------------------
    150        0,03s         0,004s
    200        0,06s         0,006s
    300        0,08s         0,02s
    400        0,15s         0,03s
    800        0,68s         0,05s
   1600        5,46s         0,14s
   3200        48,95s        0,43s
   6400        375,9s        1,62s
  12800          -           6,33s


/**********************************************************************
 *  Teoretisk   Ge ordo-uttryck f�r v�rstafallstiden f�r programmen som
 *  analys      en funktion av N. Ge en kort motivering.
 *
 **********************************************************************/

Brute: O(n^4)

Sortering: O(n^3)
