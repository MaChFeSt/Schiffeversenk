package Network;

import JSON.JSONHandler;
import JSON.Objects.*;
import gui.Spielfeld;
import gui.Window;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Host extends Thread{
    int port;
    public boolean gameLaeuft;
    PrintWriter writer;
    BufferedReader reader;
    ServerSocket serverSocket;
    boolean connected = false;
    int size;

    /**
     * Konstruktor des Hosts, zum erstellen eines Servers unter einem uebergebenen Port.
     * @param port Eingabe auf welchem Port der Server laufen soll.
     */
    public Host(int port){
        System.out.println("Creating Server...");
        gameLaeuft = true;
        this.port = port;
        try{
            InetAddress addr = InetAddress.getLocalHost();
            ServerSocket serverSocket = new ServerSocket(port,50, addr);
            this.serverSocket = serverSocket;
            System.out.println("Server gestartet mit " + addr + ":" + port);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Server Wartet auf eine einkommende Verbindung, erstellt die In- und Output-Streams und
     * faengt aufkommende Exceptions ab.
     */
    public void run(){
        try{
            Socket client = serverSocket.accept();
            System.out.println("Client hat sich verbunden unter der adresse: " + client.getLocalAddress());
            connected = true;

            //Streams
            OutputStream out = client.getOutputStream();
            PrintWriter writer = new PrintWriter(out);
            this.writer = writer;

            InputStream in = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            this.reader = reader;
            //--------------------------------

        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Die Spielfeldgroeße wird gesetzt.
     * @param size ist die Spielfeldgroeße die uebergeben wird.
     */
    public void setSize(int size){ this.size = size; }

    /**
     * Ueberpruefung ob eine Verbindung steht.
     * @return gibt einen boolean-Wert zurueck ob eine Verbindung aufgebaut ist oder nicht.
     */
    public boolean isConnected(){
        return connected;
    }

    /**
     * Empfaengt solange dateien und gibt sie auf der Konsole aus, bis eine Exception geworfen wird.
     */
    public void msgErhalten(){
        try {

            boolean done = false;
            while (!done) {
                try {
                    String line = reader.readLine();
                    Wrapper wrp = JSONHandler.getWrapper(line);
                    System.out.println("Received from server: " + wrp);
                } catch (Exception e) {
                    e.printStackTrace();
                    done = true;
                }

            }
            writer.close();
            reader.close();
            System.out.println("verbindung geschlossen");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Gibt die IP-Adresse des Hosts zurueck.
     * @return IP-Adresse vom Host
     */
    public String getIP(){
        try{
            String host = InetAddress.getLocalHost().getHostAddress();
            return host;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ein Schuss inklusive der Koordinaten,der in ein JSON-Object
     * gespeichert und anschließend an den Partner geschickt wird.
     * @param x X-Koordinate des Schusses
     * @param y Y-Koordinate des Schusses
     * @return Rückgabe: boolean-Wert ob der Schuss geschickt wurde oder nicht.
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

    public void messageSchussAntwort(boolean hit, boolean destroyed, int x, int y){
        Header h = new Header("Game", "");
        Body b = new Body(size, new Hit(hit, destroyed, x, y), null);
        Wrapper wrp = new Wrapper(new Game(h, b));

        String message = JSONHandler.serializeWrapper(wrp);
        writer.write(message);
        writer.flush();
    }

    /**
     * Es wird eine Nachricht mit der Spielfeldgroeße an den gegenueber gesendet.
     */
    public void messageWithSize(){
        Header h = new Header("Prepare", "");
        
        Body b = new Body(size, null, null);
        Wrapper wrp = new Wrapper(new Game(h, b));

        String message = JSONHandler.serializeWrapper(wrp);
        writer.write(message);
        writer.flush();
        System.out.println("size geschickt." + size);
    }

    public void messageConfirm(){
        Header h = new Header("Game", "Confirm");
        Body b = new Body(size, null, null);
        Wrapper wrp = new Wrapper(new Game(h, b));

        String message = JSONHandler.serializeWrapper(wrp);
        writer.write(message);
        writer.flush();
    }

    /**
     * Schließen von allen In- und Outputstreams, ebenso schließen vom socket.
     */
    public void close(){
        writer.close();
        try{
            reader.close();
            serverSocket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Host geschlossen");
    }

    public void listen(){
        try{
        	boolean sent = true;
        	while(sent) {
	            String line = reader.readLine();
	            //System.out.println(line);
	            Wrapper wrp = JSONHandler.getWrapper(line);
	
	            //CLIENT RDY
	            if(wrp.game.getHeader().getStatus().equals("Game_Start")){
	                System.out.println("Client rdy, Spiel kann losgehen");
	                Window.hostStart();
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
	                System.out.println("Schiff zerstört: " + destroyed);
	                sent = false;
	            }
	            //Höre auf Confirm message
	            if(wrp.game.getHeader().getSystemmessage().equals("Confirm")){
	                System.out.println("Hat die antwort bekommen wir sind dran.");
	                sent = false;
	            }
        	}
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
