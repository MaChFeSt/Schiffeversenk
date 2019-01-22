package Ai;

public class schiffliste {

    int boardsize ;
    int anzahl;
    int schiffname;

    schiffliste(){
    }

    schiffliste(int schiffname, int anzahl) {
        this.schiffname = schiffname;
        this.anzahl = anzahl;


    }

    /**
     * Ruft anhand der uebergebenen groesse des Spielfelds die zugehoerige Schiffliste ab und speichert diese in einem
     * Array, das zurueckgegeben wird.
     * @param boardsize uebergibt die groesse des Spielfelds
     * @return Gibt das array schiflliste zurueck, dass den schiffnamen und die Anzahl erhält
     */

    public schiffliste[] getship(int boardsize) {
        int size = boardsize;

        if (size == 5) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,1);
            schifftyp[1] = new schiffliste(3,1);
            schifftyp[2] = new schiffliste(4,1);
            schifftyp[3] = new schiffliste(5,0);
            schifftyp[4] = new schiffliste(6,0);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 6) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,2);
            schifftyp[1] = new schiffliste(3,1);
            schifftyp[2] = new schiffliste(4,1);
            schifftyp[3] = new schiffliste(5,0);
            schifftyp[4] = new schiffliste(6,0);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 7) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,2);
            schifftyp[1] = new schiffliste(3,1);
            schifftyp[2] = new schiffliste(4,1);
            schifftyp[3] = new schiffliste(5,1);
            schifftyp[4] = new schiffliste(6,0);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 8) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,3);
            schifftyp[1] = new schiffliste(3,1);
            schifftyp[2] = new schiffliste(4,1);
            schifftyp[3] = new schiffliste(5,1);
            schifftyp[4] = new schiffliste(6,0);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 9) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,3);
            schifftyp[1] = new schiffliste(3,2);
            schifftyp[2] = new schiffliste(4,2);
            schifftyp[3] = new schiffliste(5,1);
            schifftyp[4] = new schiffliste(6,0);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 10) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,4);
            schifftyp[1] = new schiffliste(3,3);
            schifftyp[2] = new schiffliste(4,2);
            schifftyp[3] = new schiffliste(5,1);
            schifftyp[4] = new schiffliste(6,0);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 11) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,5);
            schifftyp[1] = new schiffliste(3,3);
            schifftyp[2] = new schiffliste(4,3);
            schifftyp[3] = new schiffliste(5,1);
            schifftyp[4] = new schiffliste(6,0);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 12) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,6);
            schifftyp[1] = new schiffliste(3,3);
            schifftyp[2] = new schiffliste(4,3);
            schifftyp[3] = new schiffliste(5,2);
            schifftyp[4] = new schiffliste(6,0);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 13) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,5);
            schifftyp[1] = new schiffliste(3,5);
            schifftyp[2] = new schiffliste(4,4);
            schifftyp[3] = new schiffliste(5,2);
            schifftyp[4] = new schiffliste(6,0);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 14) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,6);
            schifftyp[1] = new schiffliste(3,5);
            schifftyp[2] = new schiffliste(4,4);
            schifftyp[3] = new schiffliste(5,3);
            schifftyp[4] = new schiffliste(6,0);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 15) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,6);
            schifftyp[1] = new schiffliste(3,6);
            schifftyp[2] = new schiffliste(4,4);
            schifftyp[3] = new schiffliste(5,3);
            schifftyp[4] = new schiffliste(6,1);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 16) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,8);
            schifftyp[1] = new schiffliste(3,6);
            schifftyp[2] = new schiffliste(4,4);
            schifftyp[3] = new schiffliste(5,3);
            schifftyp[4] = new schiffliste(6,2);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 17) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,8);
            schifftyp[1] = new schiffliste(3,6);
            schifftyp[2] = new schiffliste(4,5);
            schifftyp[3] = new schiffliste(5,4);
            schifftyp[4] = new schiffliste(6,2);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 18) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,9);
            schifftyp[1] = new schiffliste(3,7);
            schifftyp[2] = new schiffliste(4,5);
            schifftyp[3] = new schiffliste(5,4);
            schifftyp[4] = new schiffliste(6,3);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 19) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,11);
            schifftyp[1] = new schiffliste(3,7);
            schifftyp[2] = new schiffliste(4,5);
            schifftyp[3] = new schiffliste(5,4);
            schifftyp[4] = new schiffliste(6,4);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 20) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,12);
            schifftyp[1] = new schiffliste(3,8);
            schifftyp[2] = new schiffliste(4,6);
            schifftyp[3] = new schiffliste(5,5);
            schifftyp[4] = new schiffliste(6,4);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 21) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,13);
            schifftyp[1] = new schiffliste(3,8);
            schifftyp[2] = new schiffliste(4,7);
            schifftyp[3] = new schiffliste(5,6);
            schifftyp[4] = new schiffliste(6,4);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 22) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,15);
            schifftyp[1] = new schiffliste(3,9);
            schifftyp[2] = new schiffliste(4,7);
            schifftyp[3] = new schiffliste(5,6);
            schifftyp[4] = new schiffliste(6,5);
            schifftyp[5] = new schiffliste(7,0);

            return schifftyp;
        }
        if (size == 23) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,12);
            schifftyp[1] = new schiffliste(3,9);
            schifftyp[2] = new schiffliste(4,7);
            schifftyp[3] = new schiffliste(5,6);
            schifftyp[4] = new schiffliste(6,6);
            schifftyp[5] = new schiffliste(7,2);

            return schifftyp;
        }
        if (size == 24) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,10);
            schifftyp[1] = new schiffliste(3,10);
            schifftyp[2] = new schiffliste(4,7);
            schifftyp[3] = new schiffliste(5,6);
            schifftyp[4] = new schiffliste(6,5);
            schifftyp[5] = new schiffliste(7,5);

            return schifftyp;
        }
        if (size == 25) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,8);
            schifftyp[1] = new schiffliste(3,10);
            schifftyp[2] = new schiffliste(4,9);
            schifftyp[3] = new schiffliste(5,7);
            schifftyp[4] = new schiffliste(6,6);
            schifftyp[5] = new schiffliste(7,5);

            return schifftyp;
        }
        if (size == 26) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,6);
            schifftyp[1] = new schiffliste(3,10);
            schifftyp[2] = new schiffliste(4,9);
            schifftyp[3] = new schiffliste(5,8);
            schifftyp[4] = new schiffliste(6,6);
            schifftyp[5] = new schiffliste(7,7);

            return schifftyp;
        }
        if (size == 27) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,4);
            schifftyp[1] = new schiffliste(3,12);
            schifftyp[2] = new schiffliste(4,9);
            schifftyp[3] = new schiffliste(5,7);
            schifftyp[4] = new schiffliste(6,9);
            schifftyp[5] = new schiffliste(7,7);

            return schifftyp;
        }
        if (size == 28) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,4);
            schifftyp[1] = new schiffliste(3,12);
            schifftyp[2] = new schiffliste(4,10);
            schifftyp[3] = new schiffliste(5,8);
            schifftyp[4] = new schiffliste(6,9);
            schifftyp[5] = new schiffliste(7,8);

            return schifftyp;
        }
        if (size == 29) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,4);
            schifftyp[1] = new schiffliste(3,14);
            schifftyp[2] = new schiffliste(4,11);
            schifftyp[3] = new schiffliste(5,8);
            schifftyp[4] = new schiffliste(6,9);
            schifftyp[5] = new schiffliste(7,9);

            return schifftyp;
        }
        if (size == 30) {
            schiffliste[] schifftyp = new schiffliste[6];

            schifftyp[0] = new schiffliste(2,4);
            schifftyp[1] = new schiffliste(3,15);
            schifftyp[2] = new schiffliste(4,12);
            schifftyp[3] = new schiffliste(5,9);
            schifftyp[4] = new schiffliste(6,10);
            schifftyp[5] = new schiffliste(7,9);

            return schifftyp;
        }
        else{
            return null;
        }


    }
    public static void main(String[] args) {

        schiffliste p = new schiffliste();
        schiffliste[] schifftyp = (p.getship(22));
        //System.out.println(schifftyp[5].schiffname);

        for (int i=0;i<schifftyp.length;i++) {
            System.out.println("Schifftyp "+schifftyp[i].schiffname+"   Anzahl "+schifftyp[i].anzahl);


        }
    }





}







