package Ai;


public class aishot {

	static int random1;
	static int random2;

    private static int hit=0;
    static int  destroyed=0;
    static int search=0;
    static int schifflänge;
    static int schiffstreffer=0;
    static int ytreffer;
    static int xtreffer;
    static int above=0;
    static int below=0;
    static int right;
    static int left=0;
    static int outoffield=0;
    static int counter=0;
    static int zähler=1;
    static int horizontal=0;
    static int vertikal=0;
    static int abbruch=0;
    public aishot(){
    }

    /**
     * Die Methode aischiesst, feuert solange auf zufaellige Koordinaten bis ein Schiff getroffen wird. Nach dem ersten
     * Treffer feuert sie auf die umliegenden Felder des ersten Treffers. Durch den zweiten Treffer wird festgestellt
     * ob das Schiff horizontal oder vertikal positioniert ist. Je nach positionierung wird so lange in eine richtung
     * weitergefeuert bis das Schiff versenkt ist oder Wasser getroffen wird. Falls Wasser getroffen wird in die
     * gegenueberliegende Richtung weiter geschossen bis das Schiff versenkt ist.
     * @param boardsize uebergibt die groesse des Spielfelds
     * @param myGrid uebergibt das belegte Spielfeld des Spielers
     * @return Gibt true zurueck falls ein Schiff des Spielers getroffen wurde, gibt false zurueck falls nichts
     * getroffen wurde
     */

    public static boolean aischiesst(int boardsize, Object myGrid[][]){


//    	try {
//			TimeUnit.SECONDS.sleep(1);
//			System.out.println("ich schlafe.");
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


        int size = boardsize;
        int rsize = size ;
        //-----------------------------------------------------------------------------------------------
        if(hit==0 && search==0) {
            //schusskoordinaten
            do {
                random1 = (int) (Math.random() * rsize + 0);
                random2 = (int) (Math.random() * rsize + 0);
            }while(myGrid[random1][random2].equals(20) || myGrid[random1][random2].equals(21));



            if(myGrid[random1][random2].equals(2) ||myGrid[random1][random2].equals(3)
                    || myGrid[random1][random2].equals(4) || myGrid[random1][random2].equals(5)
                    || myGrid[random1][random2].equals(6) || myGrid[random1][random2].equals(7)){

                hit=1;
                search=1;
                schiffstreffer=1;
                ytreffer=random1;
                xtreffer=random2;
                counter=1;

                if(myGrid[random1][random2].equals(2)){
                    schifflänge=2;
                }
                if(myGrid[random1][random2].equals(3)){
                    schifflänge=3;
                }
                if(myGrid[random1][random2].equals(4)){
                    schifflänge=4;
                }
                if(myGrid[random1][random2].equals(5)){
                    schifflänge=5;
                }
                if(myGrid[random1][random2].equals(6)){
                    schifflänge=6;
                }
                if(myGrid[random1][random2].equals(7)){
                    schifflänge=7;
                }
                myGrid[random1][random2]=21;
                return true;
            }
            if(myGrid[random1][random2].equals(0) || myGrid[random1][random2].equals(9) || myGrid[random1][random2].equals(20)){
                myGrid[random1][random2]=20;
                return false;
            }
        }
        //------------------------------------------------------------------------------------------------
        //zweiter Treffer
        //------------------------------------------------------------------------------------------------
        if(hit==1 && search==1 && schiffstreffer==1 && above==0){
            if(schiffstreffer==1){
                //-----über ersten Treffer schießen-------------------------------------------------------
                if ( ytreffer- 1 >= 0){
                    outoffield=0;
                    if(myGrid[ytreffer-1][xtreffer].equals(0) || myGrid[ytreffer-1][xtreffer].equals(9)
                            || myGrid[ytreffer-1][xtreffer].equals(20)){
                        hit=0;
                        above=1;
                        myGrid[ytreffer-1][xtreffer]=20;
                        return false;
                    }
                    if(myGrid[ytreffer-1][xtreffer].equals(schifflänge)){
                        hit=1;
                        schiffstreffer=2;
                        above=1;
                        vertikal=1;
                        counter++;
                        myGrid[ytreffer-1][xtreffer]=21;

                        if(schifflänge==counter){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zähler=1;
                            abbruch=0;
                        }
                        return true;
                    }
                }
                else{
                    above=1;
                    outoffield=1;
                    hit=0;
                    return false;

                }

            }
        }
        //------------------------------------------------------------------------------------------------
        if(hit==0 && search==1 && schiffstreffer==1 && above==1 && below==0 && left==0 && right==0){
            //----unter ersten Treffer schießen-----------------------------------------------------------
            if(ytreffer+1 <= size-1){
                outoffield=0;
                if(myGrid[ytreffer+1][xtreffer].equals(0) || myGrid[ytreffer+1][xtreffer].equals(9)
                        || myGrid[ytreffer+1][xtreffer].equals(20)){
                    hit=0;
                    below=1;
                    myGrid[ytreffer+1][xtreffer]=20;
                    return false;
                }
                if(myGrid[ytreffer+1][xtreffer].equals(schifflänge)){
                    hit=1;
                    schiffstreffer=2;
                    below=1;
                    vertikal=1;
                    counter++;
                    myGrid[ytreffer+1][xtreffer]=21;

                    if(schifflänge==counter){
                        destroyed=1;
                        search=0;
                        hit=0;
                        above=0;
                        below=0;
                        right=0;
                        left=0;
                        vertikal=0;
                        horizontal=0;
                        zähler=1;
                        abbruch=0;
                    }
                    return true;
                }

            }
            else{
                below=1;
                outoffield=1;
                hit=0;
                return  false;

            }
        }
        //--------------------------------------------------------------------------------------------------
        if(hit==0 && search==1 && schiffstreffer==1 && above==1 && below==1 && left==0 && right ==0){
            //----links von ersten treffer schießen---------------------------------------------------------
            if(xtreffer-1 >= 0){
                outoffield=0;
                if(myGrid[ytreffer][xtreffer-1].equals(0) || myGrid[ytreffer][xtreffer-1].equals(9)
                        || myGrid[ytreffer][xtreffer-1].equals(20)){
                    hit=0;
                    left=1;
                    myGrid[ytreffer][xtreffer-1]=20;
                    return false;

                }
                if(myGrid[ytreffer][xtreffer-1].equals(schifflänge)){
                    hit=1;
                    schiffstreffer=2;
                    left=1;
                    horizontal=1;
                    counter++;
                    myGrid[ytreffer][xtreffer-1]=21;

                    if(schifflänge==counter){
                        destroyed=1;
                        search=0;
                        hit=0;
                        above=0;
                        below=0;
                        right=0;
                        left=0;
                        vertikal=0;
                        horizontal=0;
                        zähler=1;
                        abbruch=0;
                    }
                    return true;
                }

            }
            else{
                left=1;
                outoffield=1;
                hit=0;
                return false;

            }

        }
        //----------------------------------------------------------------------------------------------------
        if(hit==0 && search==1 && schiffstreffer==1 && above==1 && below==1 && left==1 && right ==0){
            //----rechts von ersten treffer schießen---------------------------------------------------------
            if(xtreffer+1 <= size-1){
                outoffield=0;
                if(myGrid[ytreffer][xtreffer+1].equals(0) || myGrid[ytreffer][xtreffer+1].equals(9)
                        || myGrid[ytreffer][xtreffer+1].equals(20)){
                    hit=0;
                    right=1;
                    myGrid[ytreffer][xtreffer+1]=20;
                    return false;
                }
                if(myGrid[ytreffer][xtreffer+1].equals(schifflänge)){
                    hit=1;
                    schiffstreffer=2;
                    right=1;
                    horizontal=1;
                    counter++;
                    myGrid[ytreffer][xtreffer+1]=21;

                    if(schifflänge==counter){
                        destroyed=1;
                        search=0;
                        hit=0;
                        above=0;
                        below=0;
                        right=0;
                        left=0;
                        vertikal=0;
                        horizontal=0;
                        zähler=1;
                        abbruch=0;
                    }
                    return true;
                }

            }
            else{
                right=1;
                outoffield=1;
                hit=0;
                return  false;

            }

        }
        //------------------------------------------------------------------------------------------------------
        //weitere Treffer
        //------------------------------------------------------------------------------------------------------
        if(vertikal==1){
            //nach oben schießen
            if(above==1 && below==0){
                while(ytreffer-counter >= 0 && abbruch==0){
                    if(myGrid[ytreffer-counter][xtreffer].equals(schifflänge)){
                        schiffstreffer++;
                        myGrid[ytreffer-counter][xtreffer]=21;
                        counter++;
                        if(schifflänge==schiffstreffer){   //???????????????????????????????????????
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zähler=1;
                            abbruch=0;
                        }

                        return true;
                    }
                    if(myGrid[ytreffer-counter][xtreffer].equals(0) || myGrid[ytreffer-counter][xtreffer].equals(9)
                            || myGrid[ytreffer-counter][xtreffer].equals(20)){
                        abbruch=1;
                        myGrid[ytreffer-counter][xtreffer]=20;
                        return false;
                    }
                }
                if(ytreffer-counter < 0){
                    abbruch=1;
                }
                while(schifflänge!=schiffstreffer && abbruch==1){
                    if(myGrid[ytreffer+zähler][xtreffer].equals(schifflänge)){
                        schiffstreffer++;
                        myGrid[ytreffer+zähler][xtreffer]=21;
                        zähler++;
                        if(schifflänge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zähler=1;
                            abbruch=0;
                        }

                        return true;
                    }

                }

            }
            //nach unten schiessen
            if(above==1 && below==1 ){
                while(ytreffer+counter <= size-1 && abbruch==0){
                    if(myGrid[ytreffer+counter][xtreffer].equals(schifflänge)){
                        schiffstreffer++;
                        myGrid[ytreffer+counter][xtreffer]=21;
                        counter++;
                        if(schifflänge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zähler=1;
                            abbruch=0;
                        }

                        return true;
                    }
                    if(myGrid[ytreffer+counter][xtreffer].equals(0) || myGrid[ytreffer-counter][xtreffer].equals(9)
                            || myGrid[ytreffer+counter][xtreffer].equals(20)){
                        abbruch=1;
                        myGrid[ytreffer+counter][xtreffer]=20;
                        return  false;
                    }

                }
                if(ytreffer+counter > size-1){
                    abbruch=1;
                }
                while(schifflänge!=schiffstreffer && abbruch==1){
                    if(myGrid[ytreffer-zähler][xtreffer].equals(schifflänge)){
                        schiffstreffer++;
                        myGrid[ytreffer-zähler][xtreffer]=21;
                        zähler++;
                        if(schifflänge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zähler=1;
                            abbruch=0;
                        }

                        return true;
                    }

                }

            }

        }
        if(horizontal==1){
            //nach links schiessen
            if(above==1 && below==1 && left==1 && right==0){
                while(xtreffer-counter >=0 && abbruch==0){
                    if(myGrid[ytreffer][xtreffer-counter].equals(schifflänge)){
                        schiffstreffer++;
                        myGrid[ytreffer][xtreffer-counter]=21;
                        counter++;
                        if(schifflänge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zähler=1;
                            abbruch=0;
                        }

                        return  true;
                    }
                    if(myGrid[ytreffer][xtreffer-counter].equals(0) || myGrid[ytreffer][xtreffer-counter].equals(9)
                            || myGrid[ytreffer][xtreffer-counter].equals(20)){
                        abbruch=1;
                        myGrid[ytreffer][xtreffer-counter]=20;
                        return false;
                    }
                }
                if(xtreffer-counter < 0){
                    abbruch=1;
                }
                while(schifflänge!=schiffstreffer && abbruch==1){
                    if(myGrid[ytreffer][xtreffer+zähler].equals(schifflänge)){
                        schiffstreffer++;
                        myGrid[ytreffer][xtreffer+zähler]=21;
                        zähler++;
                        if(schifflänge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zähler=1;
                            abbruch=0;
                        }

                        return true;
                    }

                }
            }
            //nach rechts schiessen
            if(above==1 && below==1 && left==1 && right==1){
                while(xtreffer+counter <=size-1 && abbruch==0){
                    if(myGrid[ytreffer][xtreffer+counter].equals(schifflänge)){
                        schiffstreffer++;
                        myGrid[ytreffer][xtreffer+counter]=21;
                        counter++;
                        if(schifflänge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zähler=1;
                            abbruch=0;
                        }

                        return true;
                    }
                    if(myGrid[ytreffer][xtreffer+counter].equals(0) || myGrid[ytreffer][xtreffer+counter].equals(9)
                            || myGrid[ytreffer][xtreffer+counter].equals(20)){
                        abbruch=1;
                        myGrid[ytreffer][xtreffer+counter]=20;
                        return  false;
                    }
                }
                if(xtreffer+counter > size-1){
                    abbruch=1;
                }
                while(schifflänge!=schiffstreffer && abbruch==1){
                    if(myGrid[ytreffer][xtreffer-zähler].equals(schifflänge)){
                        schiffstreffer++;
                        myGrid[ytreffer][xtreffer-zähler]=21;
                        zähler++;
                        if(schifflänge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zähler=1;
                            abbruch=0;
                        }

                        return true;
                    }

                }
            }
        }
        return false;
    }
}


