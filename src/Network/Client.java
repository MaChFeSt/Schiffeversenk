package Network;

import JSON.JSONHandler;
import JSON.Objects.*;
import gui.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client extends Thread {

    PrintWriter writer;
    BufferedReader reader;
    Socket socket;
    public static int size;

    /**
     * Konstruktor um den Client im eigenen Thread zu starten.
     * In- und Output Streams werden erstellt.
     */
    public Client(String ip, int port) {
        System.out.println("Creating Client...");
        try {
            socket = new Socket(ip, port);
            Scanner eingabe = new Scanner(System.in);


            //Streams
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            this.writer = writer;
            this.reader = reader;
            //--------------------------------
        }catch(Exception e){
            System.out.println(e);
            try{
                writer.close();
                reader.close();
                System.out.println("verbindung geschlossen");
            }catch(Exception err){
                err.printStackTrace();
            }
        }
    }

    public void run () {

    }

    /**
     * SchlieÃŸen von allen In- und Outputstreams, ebenso schliessen vom socket.
     */
    public void close(){
        writer.close();
        try{
            reader.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Client geschlossen");
    }

    /**
     * Hier wird auf die antwort vom gegenueber gehoert. Es wird auch unterschieden, was fÃ¼r eine Nachricht vom
     * gegenueber geschickt wurde.
     */
    public void listen(){
        try{
        	boolean sent = true;

        	
        	while(sent){
            	System.out.println("Warte auf message...");
	            String line = reader.readLine();
	            //System.out.println(line);
	            Wrapper wrp = JSONHandler.getWrapper(line);
	
	            //MAPSIZE
	            if(wrp.game.getHeader().getStatus().equals("Prepare")){
	                int mapsize = wrp.game.getBody().getMap_size();
	                setSize(mapsize);
	                System.out.println("Mapsize bekommen: " + mapsize);
					Gui.frame.setVisible(false);
					Window.size=mapsize;
					new Window();
					sent = false;
	            }
	            //SCHUSS VOM GEGNER ERHALTEN
	            if(wrp.game.getHeader().getStatus().equals("Game") &&
	                    wrp.game.getBody().getShot() != null){
	                int x = wrp.game.getBody().getShot().getX();
	                int y = wrp.game.getBody().getShot().getY();
	                System.out.println("Schuss auf: " + x + ", " + y);
	                sent = false;
	            }
	            //ANTWORT AUF MEINEN SCHUSS => gucken ob Hit true oder Schiff destroyed
	            if(wrp.game.getHeader().getStatus().equals("Game") &&
	                    wrp.game.getHeader().getSystemmessage().equals("") &&
	                    wrp.game.getBody().getShot() == null){
	                boolean hit = wrp.game.getBody().getHit().isHit();
	                boolean destroyed = wrp.game.getBody().getHit().isDestroyed();
	                System.out.println("Schiff getroffen: " + hit);
	                System.out.println("Schiff zerstÃ¶rt: " + destroyed);
	                sent = false;
	            }
	            //HÃ¶re auf Confirm message
	            if(wrp.game.getHeader().getSystemmessage().equals("Confirm")){
	                System.out.println("Hat die antwort bekommen wir sind dran.");
	                sent = false;
	            }
        	}
        	System.out.println("message bekommen!!!!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Die SpielfeldgroeÃŸe wird gesetzt.
     * @param size ist die SpielfeldgroeÃŸe die uebergeben wird.
     */
    public void setSize(int size){
        this.size = size;
    }
    
    /**
     * Ein Schuss inklusive der Koordinaten, der in ein JSON-Object
     * gespeichert und anschlieÃŸend an den Partner geschickt wird.
     * @param x X-Koordinate des Schusses
     * @param y Y-Koordinate des Schusses
     * @return Rueckgabe: boolean-Wert ob der Schuss geschickt wurde oder nicht.
     */
    public boolean schickeSchuss(int x, int y){
        if(x > 0 && y > 0) {
            Shot schuss = new Shot(x, y);
            Header h = new Header("Game", "");
            Body b = new Body(size, null, schuss);
            Wrapper wrp = new Wrapper(new Game(h, b));

            String message = JSONHandler.serializeWrapper(wrp);
            writer.write(message);
            writer.flush();
            return true;
        }else{
            return false;
        }
    }

    /**
     * Dem Host wird eine Message geschickt, dass das Spiel losgehen kann.
     */
    public void messageGameReady(){
        Header h = new Header("Game_Start", "");
        Body b = new Body(size, null, null);
        Wrapper wrp = new Wrapper(new Game(h, b));

        String message = JSONHandler.serializeWrapper(wrp);
        writer.write(message);
        writer.flush();
    }

    /**
     * 
     */
    public void messageConfirm(){
        Header h = new Header("Game", "Confirm");
        Body b = new Body(size, null, null);
        Wrapper wrp = new Wrapper(new Game(h, b));

        String message = JSONHandler.serializeWrapper(wrp);
        writer.write(message);
        writer.flush();
    }

    /**
     * Die Antwort auf den Schuss des gegenuebers, dieser wird in ein JSON-Object gespeichert,
     * und anschliessend verschickt
     * @param hit ??
     * @param destroyed ??
     * @param x ??
     * @param y ??
     */
    //Wird als Antwort auf einen Schuss an den Server gesendet
    public void messageSchussAntwort(boolean hit, boolean destroyed, int x, int y){
        Header h = new Header("Game", "");
        Body b = new Body(size, new Hit(hit, destroyed, x, y), null);
        Wrapper wrp = new Wrapper(new Game(h, b));

        String message = JSONHandler.serializeWrapper(wrp);
        writer.write(message);
        writer.flush();
    }

    public static void main(String[] args){
        Client c = new Client("141.18.110.127", 4444);
        c.listen();
        c.messageGameReady();
        c.listen();
        c.messageSchussAntwort(true, false, 3, 4);
        c.listen();
        c.schickeSchuss(4,5);
        c.listen();
        c.messageConfirm();
    }

}
