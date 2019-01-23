package Network;

import JSON.JSONHandler;
import JSON.Objects.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client extends Thread {

    PrintWriter writer;
    BufferedReader reader;
    Socket socket;
    int size;

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
     * Schließen von allen In- und Outputstreams, ebenso schliessen vom socket.
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
     * Hier wird auf die antwort vom gegenueber gehoert. Es wird auch unterschieden, was für eine Nachricht vom
     * Gegner geschickt wurde.
     */
    public void listen(){
        try{
            String line = reader.readLine();
            //System.out.println(line);
            Wrapper wrp = JSONHandler.getWrapper(line);

            //MAPSIZE
            if(wrp.game.getHeader().getStatus().equals("Prepare")){
                int mapsize = wrp.game.getBody().getMap_size();
                setSize(mapsize);
                System.out.println("Mapsize bekommen: " + mapsize);
            }
            //SCHUSS VOM GEGNER ERHALTEN
            if(wrp.game.getHeader().getStatus().equals("Game") &&
                    wrp.game.getBody().getShot() != null){
                int x = wrp.game.getBody().getShot().getX();
                int y = wrp.game.getBody().getShot().getY();
                System.out.println("Schuss auf: " + x + ", " + y);
            }
            //ANTWORT AUF MEINEN SCHUSS => gucken ob Hit true oder Schiff destroyed
            if(wrp.game.getHeader().getStatus().equals("Game") &&
                    wrp.game.getHeader().getSystemmessage().equals("") &&
                    wrp.game.getBody().getShot() == null){
                boolean hit = wrp.game.getBody().getHit().isHit();
                boolean destroyed = wrp.game.getBody().getHit().isDestroyed();
                System.out.println("Schiff getroffen: " + hit);
                System.out.println("Schiff zerstört: " + destroyed);
            }
            //Höre auf Confirm message
            if(wrp.game.getHeader().getSystemmessage().equals("Confirm")){
                System.out.println("Hat die antwort bekommen wir sind dran.");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Die Spielfeldgroeße wird gesetzt.
     * @param size ist die Spielfeldgroeße die uebergeben wird.
     */
    public void setSize(int size){
        this.size = size;
    }

    /**
     * Ein Schuss inklusive der Koordinaten, der in ein JSON-Object
     * gespeichert und anschließend an den Partner geschickt wird.
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
     * Gegner hat nicht getroffen, Confirm wird an ihn geschickt.
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
     * und anschliessend verschickt.
     * @param hit ob der Gegner getroffen hat
     * @param destroyed ob der Gegner Schiff zerstört hat
     * @param x x-Koordinate des hits
     * @param y y-Koordinate des hits
     */
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
