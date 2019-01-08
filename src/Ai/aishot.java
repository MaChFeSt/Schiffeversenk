package Ai;

public class aishot {

    int hit=0;
    int destroyed=0;
    int search=0;
    int schifflänge;
    int schiffstreffer=0;
    int ytreffer;
    int xtreffer;
    int above=0;
    int below=0;
    int right;
    int left=0;
    int outoffield=0;
    int zugbeenden=0; //muss am ende wieder auf null gesetzt werden
    int counter=0;
    int zähler=1;
    int horizontal=0;
    int vertikal=0;
    int abbruch=0;
    public aishot(){

    }


    public Object aischiesst(int boardsize, Object aigrid[][]){


        int size = boardsize;
        int rsize = size ;
        //-----------------------------------------------------------------------------------------------
        if(hit==0 && search==0) {
            //schusskoordinaten
            int random1 = (int) (Math.random() * rsize + 0);
            int random2 = (int) (Math.random() * rsize + 0);

            if(aigrid[random1][random2].equals(2) ||aigrid[random1][random2].equals(3)
                || aigrid[random1][random2].equals(4) || aigrid[random1][random2].equals(5)
                || aigrid[random1][random2].equals(6) || aigrid[random1][random2].equals(7)){

                hit=1;
                search=1;
                schiffstreffer=1;
                ytreffer=random1;
                xtreffer=random2;
                zugbeenden=1;
                counter=1;

                if(aigrid[random1][random2].equals(2)){
                    schifflänge=2;
                }
                if(aigrid[random1][random2].equals(3)){
                    schifflänge=3;
                }
                if(aigrid[random1][random2].equals(4)){
                    schifflänge=4;
                }
                if(aigrid[random1][random2].equals(5)){
                    schifflänge=5;
                }
                if(aigrid[random1][random2].equals(6)){
                    schifflänge=6;
                }
                if(aigrid[random1][random2].equals(7)){
                    schifflänge=7;
                }
            }
            if(aigrid[random1][random2].equals(0) || aigrid[random1][random2].equals(9)){

            }
        }
        //------------------------------------------------------------------------------------------------
        //zweiter Treffer
        //------------------------------------------------------------------------------------------------
        if(hit==1 && search==1 && schiffstreffer==1 && above==0 && zugbeenden==0){
            if(schiffstreffer==1){
                //-----über ersten Treffer schießen-------------------------------------------------------
                if ( ytreffer- 1 >= 0){
                    outoffield=0;
                    zugbeenden=1;
                    if(aigrid[ytreffer-1][xtreffer].equals(0) || aigrid[ytreffer-1][xtreffer].equals(9)){
                        hit=0;
                        above=1;
                    }
                    if(aigrid[ytreffer-1][xtreffer].equals(schifflänge)){
                        hit=1;
                        schiffstreffer=2;
                        above=1;
                        vertikal=1;
                        counter++;

                        if(schifflänge==counter){
                            destroyed=1;
                            search=0;
                        }
                    }
                }
                else{
                    above=1;
                    outoffield=1;

                }

            }
        }
        //------------------------------------------------------------------------------------------------
        if(hit==0 && search==1 && schiffstreffer==1 && above==1 && below==0 && left==0 && right==0 && zugbeenden==0){
            //----unter ersten Treffer schießen-----------------------------------------------------------
            if(ytreffer+1 <= size-1){
                outoffield=0;
                zugbeenden=1;
                if(aigrid[ytreffer+1][xtreffer].equals(0) || aigrid[ytreffer+1][xtreffer].equals(9)){
                    hit=0;
                    below=1;
                }
                if(aigrid[ytreffer+1][xtreffer].equals(schifflänge)){
                    hit=1;
                    schiffstreffer=2;
                    below=1;
                    vertikal=1;
                    counter++;

                    if(schifflänge==counter){
                        destroyed=1;
                        search=0;
                    }
                }

            }
            else{
                below=1;
                outoffield=1;

            }
        }
        //--------------------------------------------------------------------------------------------------
        if(hit==0 && search==1 && schiffstreffer==1 && above==1 && below==1 && left==0 && right ==0 && zugbeenden==0){
            //----links von ersten treffer schießen---------------------------------------------------------
            if(xtreffer-1 >= 0){
                outoffield=0;
                zugbeenden=1;
                if(aigrid[ytreffer][xtreffer-1].equals(0) || aigrid[ytreffer][xtreffer-1].equals(9)){
                    hit=0;
                    left=1;

                }
                if(aigrid[ytreffer][xtreffer-1].equals(schifflänge)){
                    hit=1;
                    schiffstreffer=2;
                    left=1;
                    horizontal=1;
                    counter++;

                    if(schifflänge==counter){
                        destroyed=1;
                        search=0;
                    }
                }

            }
            else{
                left=1;
                outoffield=1;

            }

        }
        //----------------------------------------------------------------------------------------------------
        if(hit==0 && search==1 && schiffstreffer==1 && above==1 && below==1 && left==1 && right ==0 && zugbeenden==0){
            //----rechts von ersten treffer schießen---------------------------------------------------------
            if(xtreffer+1 <= size-1){
                outoffield=0;
                zugbeenden=1;
                if(aigrid[ytreffer][xtreffer+1].equals(0) || aigrid[ytreffer][xtreffer+1].equals(9)){
                    hit=0;
                    //right=1;
                }
                if(aigrid[ytreffer][xtreffer-1].equals(schifflänge)){
                    hit=1;
                    schiffstreffer=2;
                    //right=1;
                    horizontal=1;
                    counter++;

                    if(schifflänge==counter){
                        destroyed=1;
                        search=0;
                    }
                }

            }
            else{
                //right=1;
                outoffield=1;

            }

        }
        //------------------------------------------------------------------------------------------------------
        //weitere Treffer
        //------------------------------------------------------------------------------------------------------
        if(vertikal==1 && zugbeenden==0){
            //nach oben schießen
            if(below==0){
                while(ytreffer-counter >= 0 && zugbeenden==0 && abbruch==0){
                    if(aigrid[ytreffer-counter][xtreffer].equals(schifflänge)){
                        schiffstreffer++;
                        counter++;
                        zugbeenden=1;
                        if(schifflänge==counter){
                            destroyed=1;
                        }
                    }
                    if(aigrid[ytreffer-counter][xtreffer].equals(0) || aigrid[ytreffer-counter][xtreffer].equals(9)){
                        abbruch=1;
                    }
                }
                while(schifflänge!=schiffstreffer){
                    if(aigrid[ytreffer+zähler][xtreffer].equals(schifflänge)){
                        schiffstreffer++;
                        zähler++;
                        zugbeenden=1;
                        if(schifflänge==schiffstreffer){
                           destroyed=1;
                           abbruch=1;
                        }
                    }

                }

            }
            //nach unten schießen
            if(below==1 && above==0){
                while(ytreffer+counter <= size-1 && zugbeenden==0 && abbruch==0){
                    if(aigrid[ytreffer+counter][])
                }

            }

        }

    }
}
