package Logik;
import Ai.*;
import gui.*;

public class SpielLogikAI {

    boolean meinZug = true;

    boolean spielLaeuft = true;

    Object[][] aigrid = gui.Window.getaigrid();

    Object[][] mygrid = gui.Window.getmyGrid();

    int boardsize = gui.Window.getBoardsize();

    /**
     * "Spielloop" wird gestartet, das Spiel laeuft, solang die Variable
     * spielLaeuft auf true gesetzt ist.
     */
    public void spielStart(){
        aishot shot = new aishot();

        while(spielLaeuft){
            System.out.println(meinZug);
            if(meinZug){
                int[] schussKoordinaten = gui.Spielfeld.getPos();
                int x = schussKoordinaten[0];
                int y = schussKoordinaten[1];
                schussVonMenschAufAI(x, y);
                System.out.println("Schieße auf die AI" + x + " " + y);
            }else{
                do{
                    shot.aischiesst(boardsize, mygrid);
                }while(!meinZug);
            }

//            if(spielerSpielfeldDurchlaufen()){
//                spielLaeuft = false;
//            }
//
//            if(aiSpielfeldDurchlaufen()){
//                spielLaeuft = false;
//            }
        }

        //gui.Gui.sieg();
    }

    /**
     * Hier wird getestet wer am Zug ist, true für Spieler, false für AI.
     * @return boolean-Wert wer am Zug ist
     */
    public boolean testeObMeinSpielzug(){
        if(meinZug){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Hier schickt der Spieler einen Schuss auf die AI ab.
     * @param x X-Koordinate vom Schuss
     * @param y Y-Koordinate vom Schuss
     * @return boolean-Wert ob der Schuss erfolgreich abgeschickt wurde
     */
    boolean schussVonMenschAufAI(int x, int y){
        if(testeObMeinSpielzug()){
            //Teste ob der Schuss irgendein Schiff getroffen hat
            if (aigrid[x][y].equals(2) || aigrid[x][y].equals(3) || aigrid[x][y].equals(4) ||
                    aigrid[x][y].equals(5) || aigrid[x][y].equals(6) || aigrid[x][y].equals(7)) {
                aigrid[x][y] = 21;
                //gui.Spielfeld.updatefeld(aigrid);
                meinZug = true;
                return true;
            }else{
                aigrid[x][y] = 20;
                meinZug = false;
                return false;
            }
        }else{
            System.out.println("ERROR BEIM SCHIESSEN VON MENSCH AUF AI");
            return false;
        }
    }

    /**
     * Hier wird das Spielfeld vom Spieler durchlaufen, um zu schauen ob das Spiel zuende ist.
     * @return boolean-Wert true wenn das Spiel vorbei ist
     */
    boolean spielerSpielfeldDurchlaufen(){
        for (int i = 0; i < mygrid.length; i++) {
            for (int j = 0; j < mygrid[i].length; j++) {
                if (mygrid[i][j].equals(2) || mygrid[i][j].equals(3) || mygrid[i][j].equals(4) ||
                        mygrid[i][j].equals(5) || mygrid[i][j].equals(6) || mygrid[i][j].equals(7)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Hier wird das Spielfeld von der AI durchlaufen, um zu schauen ob das Spiel zuende ist.
     * @return boolean-Wert true wenn das Spiel vorbei ist
     */
    boolean aiSpielfeldDurchlaufen(){
        for (int i = 0; i < aigrid.length; i++) {
            for (int j = 0; j < aigrid[i].length; j++) {
                if (aigrid[i][j].equals(2) || aigrid[i][j].equals(3) || aigrid[i][j].equals(4) ||
                        aigrid[i][j].equals(5) || aigrid[i][j].equals(6) || aigrid[i][j].equals(7)) {
                    return false;
                }
            }
        }
        return true;
    }

}
