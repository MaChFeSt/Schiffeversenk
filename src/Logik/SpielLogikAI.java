package Logik;
import AI.*;
import gui.Gui;

public class SpielLogikAI {

    boolean meinZug;

    boolean spielLaeuft = true;

    Object[][] aigrid;
    //Mein Spielfeld übergeben in die Logik datei
    Object[][] mygrid = gui.Gui.getGrid();

    int boardsize = gui.Gui.getBoardsize();

    //Spiel starten, AI grid in die Logik datei übergeben
    void spielStart(){
        aigrid = AI.aigrid(boardsize);

        while(spielLaeuft){
            if(meinZug){
                int[] schussKoordinaten = gui.Gui.getSchuss();
                int x = schussKoordinaten[0];
                int y = schussKoordinaten[1];
                schussVonMenschAufAI(x, y);
            }else{
                do{
                    aishot.aischiesst();
                }while(!meinZug);
            }

            if(spielerSpielfeldDurchlaufen()){
                spielLaeuft = false;
            }

            if(aiSpielfeldDurchlaufen()){
                spielLaeuft = false;
            }
        }

        gui.Gui.sieg();
    }

    boolean testeObMeinSpielzug(){
        if(meinZug){
            return true;
        }else{
            return false;
        }
    }

    //Schuss auf AI von Mensch
    boolean schussVonMenschAufAI(int x, int y){
        if(testeObMeinSpielzug()){
            //Teste ob der Schuss irgendein Schiff getroffen hat
            if (aigrid[x][y].equals(2) || aigrid[x][y].equals(3) || aigrid[x][y].equals(4) ||
                    aigrid[x][y].equals(5) || aigrid[x][y].equals(6) || aigrid[x][y].equals(7)) {
                mygrid[x][y] = 21;
                meinZug = true;
                return true;
            }else{
                mygrid[x][y] = 20;
                meinZug = false;
                return false;
            }
        }else{
            System.out.println("ERROR BEIM SCHIESSEN VON MENSCH AUF AI");
            return false;
        }
    }


    //return true wenn das Spiel VORBEI!!!! sein soll, bei false geht es weiter
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

    //return true wenn das Spiel VORBEI!!!! sein soll, bei false geht es weiter
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
