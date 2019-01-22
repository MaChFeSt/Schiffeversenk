package Ai;

public class AI {

    int x;
    int y;
    int boardsize;
    int counter=0;


    public AI() {
    }

    /**
     * Die Methode setaiships erstellt ahnhand der uebergeben Spielfeldgröße ein zweidimensionales array und ruft die
     * zugehoerige Schiffliste auf. Durch eine Zufallsvariable wird bestimmt ob das zu platzierende Schiff horizontal
     * oder vertikal platzierd wird. Das Schiff wird auf zufaelligen Koordinaten gespeichert, so lange diese nicht von
     * einem Schiff oder dessen Rahmen belegt ist. Sollte es durch eine unguenstige Belgung des Feldes nicht moeglich
     * sein alle Schiffe zu platzieren, wird die Belegung des Feldes verworfen und von vorne begonnen.
     * @param boardsize uebergibt die groesse des Spielfelds
     * @return Gibt ein zweidimensionales array zurueck, in dem die Positionen der platzierten Schiffe gespeichert sind.
     */
    public Object[][] setaiships(int boardsize) {

        int size = boardsize;
        int rsize = size ;

        //------------------------Feld mit Null initialisieren--------------------------------
        Object[][] aigrid = new Object[size][size];
        for (int i = 0; i < aigrid.length; i++) {
            for (int j = 0; j < aigrid[i].length; j++)
                aigrid[i][j] = 0;
        }
        //-----------------------zugehörige Schiffe abrufen----------------------------------
        schiffliste p = new schiffliste();
        schiffliste[] schifftyp = (p.getship(size));

        int zweier = schifftyp[0].anzahl;
        int dreier = schifftyp[1].anzahl;
        int vierer = schifftyp[2].anzahl;
        int fuenfer = schifftyp[3].anzahl;
        int sechser = schifftyp[4].anzahl;
        int siebner = schifftyp[5].anzahl;
        //------------------------erzeugen eines Randowertes innerhalb der Spielfeldgroesse---
        //int random = (int )(Math.random() * rsize + 0);
        //int rotation = (int )(Math.random() * 2 + 0);
        //------------------------------------------------------------------------------------

        //------------------------platzieren siebner------------------------------------------
        for (int i = siebner; i > 0; i--) {
            int rotation = (int) (Math.random() * 2 + 0);
            int pos = 0;

            //-----Schiff horizontal-----
            if (rotation == 0) {
                while (pos == 0) {

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);

                    try {

                        if (aigrid[random1][random2].equals(0) && aigrid[random1][random2 + 1].equals(0)
                                && aigrid[random1][random2 + 2].equals(0) && aigrid[random1][random2 + 3].equals(0)
                                && aigrid[random1][random2 + 4].equals(0) && aigrid[random1][random2 + 5].equals(0)
                                && aigrid[random1][random2 + 6].equals(0)) {

                            aigrid[random1][random2] = 7;
                            aigrid[random1][random2 + 1] = 7;
                            aigrid[random1][random2 + 2] = 7;
                            aigrid[random1][random2 + 3] = 7;
                            aigrid[random1][random2 + 4] = 7;
                            aigrid[random1][random2 + 5] = 7;
                            aigrid[random1][random2 + 6] = 7;

                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 7 <= size - 1) {
                                aigrid[random1][random2 + 7] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                                aigrid[random1 - 1][random2+1] = 9;
                                aigrid[random1 - 1][random2+2] = 9;
                                aigrid[random1 - 1][random2+3] = 9;
                                aigrid[random1 - 1][random2+4] = 9;
                                aigrid[random1 - 1][random2+5] = 9;
                                aigrid[random1 - 1][random2+6] = 9;
                            }
                            //unterer Rand
                            if (random1 + 1 <= size - 1) {
                                aigrid[random1 + 1][random2] = 9;
                                aigrid[random1 + 1][random2+1] = 9;
                                aigrid[random1 + 1][random2+2] = 9;
                                aigrid[random1 + 1][random2+3] = 9;
                                aigrid[random1 + 1][random2+4] = 9;
                                aigrid[random1 + 1][random2+5] = 9;
                                aigrid[random1 + 1][random2+6] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 7 <= size - 1) {
                                aigrid[random1 - 1][random2 + 7] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 1][random2 - 1] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 + 7 <= size - 1) {
                                aigrid[random1 + 1][random2 + 7] = 9;
                            }

                            pos = 1;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }

                }
            }
            //-----Schiff vertikal-----
            else {

                while (pos == 0) {

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);
                    try {
                        if (aigrid[random1][random2].equals(0) && aigrid[random1 + 1][random2].equals(0)
                                && aigrid[random1 + 2][random2].equals(0) && aigrid[random1 + 3][random2].equals(0)
                                && aigrid[random1 + 4][random2].equals(0) && aigrid[random1 + 5][random2].equals(0)
                                && aigrid[random1 + 6][random2].equals(0)) {

                            aigrid[random1][random2] = 7;
                            aigrid[random1 + 1][random2] = 7;
                            aigrid[random1 + 2][random2] = 7;
                            aigrid[random1 + 3][random2] = 7;
                            aigrid[random1 + 4][random2] = 7;
                            aigrid[random1 + 5][random2] = 7;
                            aigrid[random1 + 6][random2] = 7;

                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;
                                aigrid[random1 + 1][random2 - 1] = 9;
                                aigrid[random1 + 2][random2 - 1] = 9;
                                aigrid[random1 + 3][random2 - 1] = 9;
                                aigrid[random1 + 4][random2 - 1] = 9;
                                aigrid[random1 + 5][random2 - 1] = 9;
                                aigrid[random1 + 6][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 1 <= size - 1) {
                                aigrid[random1][random2 + 1] = 9;
                                aigrid[random1 + 1][random2 + 1] = 9;
                                aigrid[random1 + 2][random2 + 1] = 9;
                                aigrid[random1 + 3][random2 + 1] = 9;
                                aigrid[random1 + 4][random2 + 1] = 9;
                                aigrid[random1 + 5][random2 + 1] = 9;
                                aigrid[random1 + 6][random2 + 1] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                            }
                            //unterer Rand
                            if (random1 + 7 <= size - 1) {
                                aigrid[random1 + 7][random2] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 1 <= size - 1) {
                                aigrid[random1 - 1][random2 + 1] = 9;
                            }
                            if (random1 + 7 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 6][random2 - 1] = 9;
                            }
                            if (random1 + 7 <= size - 1 && random2 + 1 <= size - 1) {
                                aigrid[random1 + 7][random2 + 1] = 9;
                            }

                            pos = 1;
                        }


                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }
                }
            }
        }

        //---------------------------platzieren sechser--------------------------------------

        for (int i = sechser; i > 0; i--) {
            int rotation = (int) (Math.random() * 2 + 0);
            int pos = 0;

            //-----Schiff horizontal-----
            if (rotation == 0) {
                while (pos == 0) {

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);

                    try {

                        if (aigrid[random1][random2].equals(0) && aigrid[random1][random2 + 1].equals(0)
                                && aigrid[random1][random2 + 2].equals(0) && aigrid[random1][random2 + 3].equals(0)
                                && aigrid[random1][random2 + 4].equals(0) && aigrid[random1][random2 + 5].equals(0)) {

                            aigrid[random1][random2] = 6;
                            aigrid[random1][random2 + 1] = 6;
                            aigrid[random1][random2 + 2] = 6;
                            aigrid[random1][random2 + 3] = 6;
                            aigrid[random1][random2 + 4] = 6;
                            aigrid[random1][random2 + 5] = 6;


                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 6 <= size - 1) {
                                aigrid[random1][random2 + 6] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                                aigrid[random1 - 1][random2+1] = 9;
                                aigrid[random1 - 1][random2+2] = 9;
                                aigrid[random1 - 1][random2+3] = 9;
                                aigrid[random1 - 1][random2+4] = 9;
                                aigrid[random1 - 1][random2+5] = 9;
                            }
                            //unterer Rand
                            if (random1 + 1 <= size - 1) {
                                aigrid[random1 + 1][random2] = 9;
                                aigrid[random1 + 1][random2+1] = 9;
                                aigrid[random1 + 1][random2+2] = 9;
                                aigrid[random1 + 1][random2+3] = 9;
                                aigrid[random1 + 1][random2+4] = 9;
                                aigrid[random1 + 1][random2+5] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 6 <= size - 1) {
                                aigrid[random1 - 1][random2 + 6] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 1][random2 - 1] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 + 6 <= size - 1) {
                                aigrid[random1 + 1][random2 + 6] = 9;
                            }

                            pos = 1;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }

                }
            }
            //-----Schiff vertikal-----
            else {

                while (pos == 0) {

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);
                    try {
                        if (aigrid[random1][random2].equals(0) && aigrid[random1 + 1][random2].equals(0)
                                && aigrid[random1 + 2][random2].equals(0) && aigrid[random1 + 3][random2].equals(0)
                                && aigrid[random1 + 4][random2].equals(0) && aigrid[random1 + 5][random2].equals(0)) {

                            aigrid[random1][random2] = 6;
                            aigrid[random1 + 1][random2] = 6;
                            aigrid[random1 + 2][random2] = 6;
                            aigrid[random1 + 3][random2] = 6;
                            aigrid[random1 + 4][random2] = 6;
                            aigrid[random1 + 5][random2] = 6;


                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;
                                aigrid[random1 + 1][random2 - 1] = 9;
                                aigrid[random1 + 2][random2 - 1] = 9;
                                aigrid[random1 + 3][random2 - 1] = 9;
                                aigrid[random1 + 4][random2 - 1] = 9;
                                aigrid[random1 + 5][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 1 <= size - 1) {
                                aigrid[random1][random2 + 1] = 9;
                                aigrid[random1 + 1][random2 + 1] = 9;
                                aigrid[random1 + 2][random2 + 1] = 9;
                                aigrid[random1 + 3][random2 + 1] = 9;
                                aigrid[random1 + 4][random2 + 1] = 9;
                                aigrid[random1 + 5][random2 + 1] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                            }
                            //unterer Rand
                            if (random1 + 6 <= size - 1) {
                                aigrid[random1 + 6][random2] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 1 <= size - 1) {
                                aigrid[random1 - 1][random2 + 1] = 9;
                            }
                            if (random1 + 6 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 6][random2 - 1] = 9;
                            }
                            if (random1 + 6 <= size - 1 && random2 + 1 <= size - 1) {
                                aigrid[random1 + 6][random2 + 1] = 9;
                            }

                            pos = 1;
                        }


                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }
                }
            }
        }

        //------------------------platzieren fuenfer--------------------------------------------

        for (int i = fuenfer; i > 0; i--) {
            int rotation = (int) (Math.random() * 2 + 0);
            int pos = 0;

            //-----Schiff horizontal-----
            if (rotation == 0) {
                while (pos == 0) {

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);

                    try {

                        if (aigrid[random1][random2].equals(0) && aigrid[random1][random2 + 1].equals(0)
                                && aigrid[random1][random2 + 2].equals(0) && aigrid[random1][random2 + 3].equals(0)
                                && aigrid[random1][random2 + 4].equals(0)) {

                            aigrid[random1][random2] = 5;
                            aigrid[random1][random2 + 1] = 5;
                            aigrid[random1][random2 + 2] = 5;
                            aigrid[random1][random2 + 3] = 5;
                            aigrid[random1][random2 + 4] = 5;


                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 5 <= size - 1) {
                                aigrid[random1][random2 + 5] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                                aigrid[random1 - 1][random2+1] = 9;
                                aigrid[random1 - 1][random2+2] = 9;
                                aigrid[random1 - 1][random2+3] = 9;
                                aigrid[random1 - 1][random2+4] = 9;
                            }
                            //unterer Rand
                            if (random1 + 1 <= size - 1) {
                                aigrid[random1 + 1][random2] = 9;
                                aigrid[random1 + 1][random2+1] = 9;
                                aigrid[random1 + 1][random2+2] = 9;
                                aigrid[random1 + 1][random2+3] = 9;
                                aigrid[random1 + 1][random2+4] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 5 <= size - 1) {
                                aigrid[random1 - 1][random2 + 5] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 1][random2 - 1] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 + 5 <= size - 1) {
                                aigrid[random1 + 1][random2 + 5] = 9;
                            }

                            pos = 1;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }

                }
            }
            //-----Schiff vertikal-----
            else {

                while (pos == 0) {

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);
                    try {
                        if (aigrid[random1][random2].equals(0) && aigrid[random1 + 1][random2].equals(0)
                                && aigrid[random1 + 2][random2].equals(0) && aigrid[random1 + 3][random2].equals(0)
                                && aigrid[random1 + 4][random2].equals(0)) {

                            aigrid[random1][random2] = 5;
                            aigrid[random1 + 1][random2] = 5;
                            aigrid[random1 + 2][random2] = 5;
                            aigrid[random1 + 3][random2] = 5;
                            aigrid[random1 + 4][random2] = 5;


                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;
                                aigrid[random1 + 1][random2 - 1] = 9;
                                aigrid[random1 + 2][random2 - 1] = 9;
                                aigrid[random1 + 3][random2 - 1] = 9;
                                aigrid[random1 + 4][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 1 <= size - 1) {
                                aigrid[random1][random2 + 1] = 9;
                                aigrid[random1 + 1][random2 + 1] = 9;
                                aigrid[random1 + 2][random2 + 1] = 9;
                                aigrid[random1 + 3][random2 + 1] = 9;
                                aigrid[random1 + 4][random2 + 1] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                            }
                            //unterer Rand
                            if (random1 + 5 <= size - 1) {
                                aigrid[random1 + 5][random2] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 1 <= size - 1) {
                                aigrid[random1 - 1][random2 + 1] = 9;
                            }
                            if (random1 + 5 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 5][random2 - 1] = 9;
                            }
                            if (random1 + 5 <= size - 1 && random2 + 1 <= size - 1) {
                                aigrid[random1 + 5][random2 + 1] = 9;
                            }

                            pos = 1;
                        }


                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }
                }
            }
        }

        //------------------------------platzieren vierer------------------------------------------

        for (int i = vierer; i > 0; i--) {
            int rotation = (int) (Math.random() * 2 + 0);
            int pos = 0;

            //-----Schiff horizontal-----
            if (rotation == 0) {
                while (pos == 0) {
                    counter++;
                    if(counter>5000){
                        for (int random1 = 0; random1 < aigrid.length; random1++) {
                            for (int random2 = 0; random2 < aigrid[random1].length; random2++)
                                aigrid[random1][random2] = -1;
                            return aigrid;
                        }
                    }

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);

                    try {

                        if (aigrid[random1][random2].equals(0) && aigrid[random1][random2 + 1].equals(0)
                                && aigrid[random1][random2 + 2].equals(0) && aigrid[random1][random2 + 3].equals(0)) {

                            aigrid[random1][random2] = 4;
                            aigrid[random1][random2 + 1] = 4;
                            aigrid[random1][random2 + 2] = 4;
                            aigrid[random1][random2 + 3] = 4;


                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 4 <= size - 1) {
                                aigrid[random1][random2 + 4] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                                aigrid[random1 - 1][random2+1] = 9;
                                aigrid[random1 - 1][random2+2] = 9;
                                aigrid[random1 - 1][random2+3] = 9;
                            }
                            //unterer Rand
                            if (random1 + 1 <= size - 1) {
                                aigrid[random1 + 1][random2] = 9;
                                aigrid[random1 + 1][random2+1] = 9;
                                aigrid[random1 + 1][random2+2] = 9;
                                aigrid[random1 + 1][random2+3] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 4 <= size - 1) {
                                aigrid[random1 - 1][random2 + 4] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 1][random2 - 1] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 + 4 <= size - 1) {
                                aigrid[random1 + 1][random2 + 4] = 9;
                            }

                            pos = 1;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }

                }
            }
            //-----Schiff vertikal-----
            else {

                while (pos == 0) {
                    counter++;
                    if(counter>5000){
                        for (int random1 = 0; random1 < aigrid.length; random1++) {
                            for (int random2 = 0; random2 < aigrid[random1].length; random2++)
                                aigrid[random1][random2] = -1;
                            return aigrid;
                        }
                    }

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);
                    try {
                        if (aigrid[random1][random2].equals(0) && aigrid[random1 + 1][random2].equals(0)
                                && aigrid[random1 + 2][random2].equals(0) && aigrid[random1 + 3][random2].equals(0)) {

                            aigrid[random1][random2] = 4;
                            aigrid[random1 + 1][random2] = 4;
                            aigrid[random1 + 2][random2] = 4;
                            aigrid[random1 + 3][random2] = 4;


                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;
                                aigrid[random1 + 1][random2 - 1] = 9;
                                aigrid[random1 + 2][random2 - 1] = 9;
                                aigrid[random1 + 3][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 1 <= size - 1) {
                                aigrid[random1][random2 + 1] = 9;
                                aigrid[random1 + 1][random2 + 1] = 9;
                                aigrid[random1 + 2][random2 + 1] = 9;
                                aigrid[random1 + 3][random2 + 1] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                            }
                            //unterer Rand
                            if (random1 + 4 <= size - 1) {
                                aigrid[random1 + 4][random2] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 1 <= size - 1) {
                                aigrid[random1 - 1][random2 + 1] = 9;
                            }
                            if (random1 + 4 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 4][random2 - 1] = 9;
                            }
                            if (random1 + 4 <= size - 1 && random2 + 1 <= size - 1) {
                                aigrid[random1 + 4][random2 + 1] = 9;
                            }
                            pos = 1;
                        }


                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }
                }
            }
        }

        //--------------------------------platzieren dreier-------------------------------------------

        for (int i = dreier; i > 0; i--) {
            int rotation = (int) (Math.random() * 2 + 0);
            int pos = 0;

            //-----Schiff horizontal-----
            if (rotation == 0) {
                while (pos == 0) {
                    counter++;
                    if(counter>5000){
                        for (int random1 = 0; random1 < aigrid.length; random1++) {
                            for (int random2 = 0; random2 < aigrid[random1].length; random2++)
                                aigrid[random1][random2] = -1;
                            return aigrid;
                        }
                    }

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);

                    try {

                        if (aigrid[random1][random2].equals(0) && aigrid[random1][random2 + 1].equals(0)
                                && aigrid[random1][random2 + 2].equals(0)) {

                            aigrid[random1][random2] = 3;
                            aigrid[random1][random2 + 1] = 3;
                            aigrid[random1][random2 + 2] = 3;


                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 3 <= size - 1) {
                                aigrid[random1][random2 + 3] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                                aigrid[random1 - 1][random2+1] = 9;
                                aigrid[random1 - 1][random2+2] = 9;
                            }
                            //unterer Rand
                            if (random1 + 1 <= size - 1) {
                                aigrid[random1 + 1][random2] = 9;
                                aigrid[random1 + 1][random2+1] = 9;
                                aigrid[random1 + 1][random2+2] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 3 <= size - 1) {
                                aigrid[random1 - 1][random2 + 3] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 1][random2 - 1] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 + 3 <= size - 1) {
                                aigrid[random1 + 1][random2 + 3] = 9;
                            }

                            pos = 1;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }

                }
            }
            //-----Schiff vertikal-----
            else {

                while (pos == 0) {
                    counter++;
                    if(counter>5000){
                        for (int random1 = 0; random1 < aigrid.length; random1++) {
                            for (int random2 = 0; random2 < aigrid[random1].length; random2++)
                                aigrid[random1][random2] = -1;
                            return aigrid;
                        }
                    }

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);
                    try {
                        if (aigrid[random1][random2].equals(0) && aigrid[random1 + 1][random2].equals(0)
                                && aigrid[random1 + 2][random2].equals(0)) {

                            aigrid[random1][random2] = 3;
                            aigrid[random1 + 1][random2] = 3;
                            aigrid[random1 + 2][random2] = 3;


                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;
                                aigrid[random1 + 1][random2 - 1] = 9;
                                aigrid[random1 + 2][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 1 <= size - 1) {
                                aigrid[random1][random2 + 1] = 9;
                                aigrid[random1 + 1][random2 + 1] = 9;
                                aigrid[random1 + 2][random2 + 1] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                            }
                            //unterer Rand
                            if (random1 + 3 <= size - 1) {
                                aigrid[random1 + 3][random2] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 1 <= size - 1) {
                                aigrid[random1 - 1][random2 + 1] = 9;
                            }
                            if (random1 + 3 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 3][random2 - 1] = 9;
                            }
                            if (random1 + 3 <= size - 1 && random2 + 1 <= size - 1) {
                                aigrid[random1 + 3][random2 + 1] = 9;
                            }

                            pos = 1;
                        }


                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }
                }
            }
        }

        //-----------------------------------platzieren zweier---------------------------------

        for (int i = zweier; i > 0; i--) {
            int rotation = (int) (Math.random() * 2 + 0);
            int pos = 0;

            //-----Schiff horizontal-----
            if (rotation == 0) {
                while (pos == 0) {
                    counter++;
                    if(counter>5000){
                        for (int random1 = 0; random1 < aigrid.length; random1++) {
                            for (int random2 = 0; random2 < aigrid[random1].length; random2++)
                                aigrid[random1][random2] = -1;
                            return aigrid;
                        }
                    }

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);

                    try {

                        if (aigrid[random1][random2].equals(0) && aigrid[random1][random2 + 1].equals(0)) {

                            aigrid[random1][random2] = 2;
                            aigrid[random1][random2 + 1] = 2;


                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 2 <= size - 1) {
                                aigrid[random1][random2 + 2] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                                aigrid[random1 - 1][random2+1] = 9;
                            }
                            //unterer Rand
                            if (random1 + 1 <= size - 1) {
                                aigrid[random1 + 1][random2] = 9;
                                aigrid[random1 + 1][random2+1] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 2 <= size - 1) {
                                aigrid[random1 - 1][random2 + 2] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 1][random2 - 1] = 9;
                            }
                            if (random1 + 1 <= size - 1 && random2 + 2 <= size - 1) {
                                aigrid[random1 + 1][random2 + 2] = 9;
                            }

                            pos = 1;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }

                }
            }
            //-----Schiff vertikal-----
            else {

                while (pos == 0) {
                    counter++;
                    if(counter>5000){
                        for (int random1 = 0; random1 < aigrid.length; random1++) {
                            for (int random2 = 0; random2 < aigrid[random1].length; random2++)
                                aigrid[random1][random2] = -1;
                                return aigrid;
                        }
                    }

                    int random1 = (int) (Math.random() * rsize + 0);
                    int random2 = (int) (Math.random() * rsize + 0);
                    try {
                        if (aigrid[random1][random2].equals(0) && aigrid[random1 + 1][random2].equals(0)) {

                            aigrid[random1][random2] = 2;
                            aigrid[random1 + 1][random2] = 2;

                            //!!!!!!!!!!!!!!!!!Rand setzen!!!!!!!!!!!!!!!!!!!!!
                            //linker Rand
                            if (random2 - 1 >= 0) {
                                aigrid[random1][random2 - 1] = 9;
                                aigrid[random1 + 1][random2 - 1] = 9;

                            }
                            //rechter Rand
                            if (random2 + 1 <= size - 1) {
                                aigrid[random1][random2 + 1] = 9;
                                aigrid[random1 + 1][random2 + 1] = 9;

                            }
                            //oberer Rand
                            if (random1 - 1 >= 0) {
                                aigrid[random1 - 1][random2] = 9;
                            }
                            //unterer Rand
                            if (random1 + 2 <= size - 1) {
                                aigrid[random1 + 2][random2] = 9;
                            }
                            //ecken Rand
                            if (random1 - 1 >= 0 && random2 - 1 >= 0) {
                                aigrid[random1 - 1][random2 - 1] = 9;
                            }
                            if (random1 - 1 >= 0 && random2 + 1 <= size - 1) {
                                aigrid[random1 - 1][random2 + 1] = 9;
                            }
                            if (random1 + 2 <= size - 1 && random2 - 1 >= 0) {
                                aigrid[random1 + 2][random2 - 1] = 9;
                            }
                            if (random1 + 2 <= size - 1 && random2 + 1 <= size - 1) {
                                aigrid[random1 + 2][random2 + 1] = 9;
                            }

                            pos = 1;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("setze neu");
                        continue;
                    }
                }
            }
        }
        //System.out.println(counter);

        return aigrid;


    }

    public static void main(String[] args) {



        AI a = new AI();
        Object[][] aigrid;
        int end=0;
        aigrid = a.setaiships(10);
        while (end==0){

            if(!aigrid[0][0].equals(-1)){
                end=1;
            }
            else{
                 a = new AI();
                aigrid = a.setaiships(10);
            }


            }


        for (int i = 0; i < aigrid.length; i++) {
            for (int j = 0; j < aigrid[i].length; j++)
                System.out.print(aigrid[i][j].toString() + ", ");
            System.out.println();
            System.out.println();

        }





    }
    public static Object[][] aigrid (int boardsize){

        AI a = new AI();
        Object[][] aigrid;
        int end=0;
        aigrid = a.setaiships(boardsize);
        while (end==0){

            if(!aigrid[0][0].equals(-1)){
                end=1;
            }
            else{
                a = new AI();
                aigrid = a.setaiships(boardsize);
            }


        }
        for (int i = 0; i < aigrid.length; i++) {
            for (int j = 0; j < aigrid[i].length; j++)
                System.out.print(aigrid[i][j].toString() + ", ");
            System.out.println();
            System.out.println();

        }
        return aigrid;

    }
}

