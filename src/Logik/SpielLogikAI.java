package Logik;
import gui.*;

public class SpielLogikAI {

    static boolean meinZug = true;

    static Object[][] aigrid = gui.Window.getaigrid();

    static Object[][] mygrid = gui.Window.getmyGrid();

    /**
     * Hier wird getestet wer am Zug ist, true fuer Spieler, false fuer AI.
     * @return boolean-Wert wer am Zug ist
     */
    public static boolean testeObMeinSpielzug(){
        if(meinZug){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Hier schickt der Spieler einen Schuss auf die AI ab. Diese Methode testet auch ob ein Schiff getroffen wurde
     * und ob ein Spiel bereits zuende ist oder nicht.
     * @param x X-Koordinate vom Schuss
     * @param y Y-Koordinate vom Schuss
     */
    
    public static void sendeSchuss(int x, int y) {
    	int mode = Gui.getMode();
    	if(mode == 1) {
        	Gui.host1.schickeSchuss(x, y);
        	System.out.println("Host schickt Schuss!");
        	Gui.host1.listen();
        }
        
        if(mode == 2) {
        	Gui.client1.schickeSchuss(x, y);
        	System.out.println("Client schickt Schuss!");
        	Gui.client1.listen();
        }
    	
    }
    
    
    public static void schussVonMenschAufAI(int x, int y){
        //Teste ob der Schuss irgendein Schiff getroffen hat
        if (aigrid[x][y].equals(2) || aigrid[x][y].equals(3) || aigrid[x][y].equals(4) ||
                aigrid[x][y].equals(5) || aigrid[x][y].equals(6) || aigrid[x][y].equals(7)) {
            aigrid[x][y] = 21;
            //gui.Spielfeld.updatefeld(aigrid);
            Window.updateGrid(mygrid, aigrid);
            Window.lp.validate();
            System.out.println("getroffen.");
            //CHECK IF WON
            int mode =Gui.getMode();
            if (mode == 0) {
		        if(aiSpielfeldDurchlaufen()) {
		            System.out.println("YOU WIN");
		            Window.won.setVisible(true);
		            Window.menu.setVisible(true);
		            Window.quit.setVisible(true);
		        }
		        if(spielerSpielfeldDurchlaufen()) {
		            System.out.println("YOU LOST");
		            Window.lost.setVisible(true);
		            Window.menu.setVisible(true);
		            Window.quit.setVisible(true);
		        }
            }
            Window.myTurn();
        }else{
            aigrid[x][y] = 20;
            Window.updateGrid(mygrid, aigrid);
            Window.myT.setVisible(false);
            Window.lp.validate();
            System.out.println("nicht getroffen.");
            Window.aiTurn();
        }
    }

    /**
     * Hier wird das Spielfeld vom Spieler durchlaufen, um zu schauen ob das Spiel zuende ist.
     * @return boolean-Wert true wenn das Spiel vorbei ist
     */
    public static boolean spielerSpielfeldDurchlaufen(){
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
    public static boolean aiSpielfeldDurchlaufen(){
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
