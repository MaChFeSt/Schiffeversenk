package Ai;

import java.util.concurrent.TimeUnit;

public class aishot {

	static int random1;
	static int random2;

    private static int hit=0;
    static int  destroyed=0;
    static int search=0;
    static int schifflÃ¤nge;
    static int schiffstreffer=0;
    static int ytreffer;
    static int xtreffer;
    static int above=0;
    static int below=0;
    static int right;
    static int left=0;
    static int outoffield=0;
    static int counter=0;
    static int zÃ¤hler=1;
    static int horizontal=0;
    static int vertikal=0;
    static int abbruch=0;
    public aishot(){
    }


    public static boolean aischiesst(int boardsize, Object myGrid[][]){


    	try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("ich schlafe.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


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
                    schifflÃ¤nge=2;
                }
                if(myGrid[random1][random2].equals(3)){
                    schifflÃ¤nge=3;
                }
                if(myGrid[random1][random2].equals(4)){
                    schifflÃ¤nge=4;
                }
                if(myGrid[random1][random2].equals(5)){
                    schifflÃ¤nge=5;
                }
                if(myGrid[random1][random2].equals(6)){
                    schifflÃ¤nge=6;
                }
                if(myGrid[random1][random2].equals(7)){
                    schifflÃ¤nge=7;
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
                //-----Ã¼ber ersten Treffer schieÃŸen-------------------------------------------------------
                if ( ytreffer- 1 >= 0){
                    outoffield=0;
                    if(myGrid[ytreffer-1][xtreffer].equals(0) || myGrid[ytreffer-1][xtreffer].equals(9)
                            || myGrid[ytreffer-1][xtreffer].equals(20)){
                        hit=0;
                        above=1;
                        myGrid[ytreffer-1][xtreffer]=20;
                        return false;
                    }
                    if(myGrid[ytreffer-1][xtreffer].equals(schifflÃ¤nge)){
                        hit=1;
                        schiffstreffer=2;
                        above=1;
                        vertikal=1;
                        counter++;
                        myGrid[ytreffer-1][xtreffer]=21;

                        if(schifflÃ¤nge==counter){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zÃ¤hler=1;
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
            //----unter ersten Treffer schieÃŸen-----------------------------------------------------------
            if(ytreffer+1 <= size-1){
                outoffield=0;
                if(myGrid[ytreffer+1][xtreffer].equals(0) || myGrid[ytreffer+1][xtreffer].equals(9)
                        || myGrid[ytreffer+1][xtreffer].equals(20)){
                    hit=0;
                    below=1;
                    myGrid[ytreffer+1][xtreffer]=20;
                    return false;
                }
                if(myGrid[ytreffer+1][xtreffer].equals(schifflÃ¤nge)){
                    hit=1;
                    schiffstreffer=2;
                    below=1;
                    vertikal=1;
                    counter++;
                    myGrid[ytreffer+1][xtreffer]=21;

                    if(schifflÃ¤nge==counter){
                        destroyed=1;
                        search=0;
                        hit=0;
                        above=0;
                        below=0;
                        right=0;
                        left=0;
                        vertikal=0;
                        horizontal=0;
                        zÃ¤hler=1;
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
            //----links von ersten treffer schieÃŸen---------------------------------------------------------
            if(xtreffer-1 >= 0){
                outoffield=0;
                if(myGrid[ytreffer][xtreffer-1].equals(0) || myGrid[ytreffer][xtreffer-1].equals(9)
                        || myGrid[ytreffer][xtreffer-1].equals(20)){
                    hit=0;
                    left=1;
                    myGrid[ytreffer][xtreffer-1]=20;
                    return false;

                }
                if(myGrid[ytreffer][xtreffer-1].equals(schifflÃ¤nge)){
                    hit=1;
                    schiffstreffer=2;
                    left=1;
                    horizontal=1;
                    counter++;
                    myGrid[ytreffer][xtreffer-1]=21;

                    if(schifflÃ¤nge==counter){
                        destroyed=1;
                        search=0;
                        hit=0;
                        above=0;
                        below=0;
                        right=0;
                        left=0;
                        vertikal=0;
                        horizontal=0;
                        zÃ¤hler=1;
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
            //----rechts von ersten treffer schieÃŸen---------------------------------------------------------
            if(xtreffer+1 <= size-1){
                outoffield=0;
                if(myGrid[ytreffer][xtreffer+1].equals(0) || myGrid[ytreffer][xtreffer+1].equals(9)
                        || myGrid[ytreffer][xtreffer+1].equals(20)){
                    hit=0;
                    right=1;
                    myGrid[ytreffer][xtreffer+1]=20;
                    return false;
                }
                if(myGrid[ytreffer][xtreffer+1].equals(schifflÃ¤nge)){
                    hit=1;
                    schiffstreffer=2;
                    right=1;
                    horizontal=1;
                    counter++;
                    myGrid[ytreffer][xtreffer+1]=21;

                    if(schifflÃ¤nge==counter){
                        destroyed=1;
                        search=0;
                        hit=0;
                        above=0;
                        below=0;
                        right=0;
                        left=0;
                        vertikal=0;
                        horizontal=0;
                        zÃ¤hler=1;
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
            //nach oben schieÃŸen
            if(above==1 && below==0){
                while(ytreffer-counter >= 0 && abbruch==0){
                    if(myGrid[ytreffer-counter][xtreffer].equals(schifflÃ¤nge)){
                        schiffstreffer++;
                        myGrid[ytreffer-counter][xtreffer]=21;
                        counter++;
                        if(schifflÃ¤nge==schiffstreffer){   //???????????????????????????????????????
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zÃ¤hler=1;
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
                while(schifflÃ¤nge!=schiffstreffer && abbruch==1){
                    if(myGrid[ytreffer+zÃ¤hler][xtreffer].equals(schifflÃ¤nge)){
                        schiffstreffer++;
                        myGrid[ytreffer+zÃ¤hler][xtreffer]=21;
                        zÃ¤hler++;
                        if(schifflÃ¤nge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zÃ¤hler=1;
                            abbruch=0;
                        }

                        return true;
                    }

                }

            }
            //nach unten schiessen
            if(above==1 && below==1 ){
                while(ytreffer+counter <= size-1 && abbruch==0){
                    if(myGrid[ytreffer+counter][xtreffer].equals(schifflÃ¤nge)){
                        schiffstreffer++;
                        myGrid[ytreffer+counter][xtreffer]=21;
                        counter++;
                        if(schifflÃ¤nge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zÃ¤hler=1;
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
                while(schifflÃ¤nge!=schiffstreffer && abbruch==1){
                    if(myGrid[ytreffer-zÃ¤hler][xtreffer].equals(schifflÃ¤nge)){
                        schiffstreffer++;
                        myGrid[ytreffer-zÃ¤hler][xtreffer]=21;
                        zÃ¤hler++;
                        if(schifflÃ¤nge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zÃ¤hler=1;
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
                    if(myGrid[ytreffer][xtreffer-counter].equals(schifflÃ¤nge)){
                        schiffstreffer++;
                        myGrid[ytreffer][xtreffer-counter]=21;
                        counter++;
                        if(schifflÃ¤nge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zÃ¤hler=1;
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
                while(schifflÃ¤nge!=schiffstreffer && abbruch==1){
                    if(myGrid[ytreffer][xtreffer+zÃ¤hler].equals(schifflÃ¤nge)){
                        schiffstreffer++;
                        myGrid[ytreffer][xtreffer+zÃ¤hler]=21;
                        zÃ¤hler++;
                        if(schifflÃ¤nge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zÃ¤hler=1;
                            abbruch=0;
                        }

                        return true;
                    }

                }
            }
            //nach rechts schiessen
            if(above==1 && below==1 && left==1 && right==1){
                while(xtreffer+counter <=size-1 && abbruch==0){
                    if(myGrid[ytreffer][xtreffer+counter].equals(schifflÃ¤nge)){
                        schiffstreffer++;
                        myGrid[ytreffer][xtreffer+counter]=21;
                        counter++;
                        if(schifflÃ¤nge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zÃ¤hler=1;
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
                while(schifflÃ¤nge!=schiffstreffer && abbruch==1){
                    if(myGrid[ytreffer][xtreffer-zÃ¤hler].equals(schifflÃ¤nge)){
                        schiffstreffer++;
                        myGrid[ytreffer][xtreffer-zÃ¤hler]=21;
                        zÃ¤hler++;
                        if(schifflÃ¤nge==schiffstreffer){
                            destroyed=1;
                            search=0;
                            hit=0;
                            above=0;
                            below=0;
                            right=0;
                            left=0;
                            vertikal=0;
                            horizontal=0;
                            zÃ¤hler=1;
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


