package Network;

import JSON.JSONHandler;
import JSON.Objects.*;

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

            writer.write("VERBUNDEN");
            writer.flush();


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

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public boolean isConnected(){
        if (connected == true){
            return true;
        }else{
            return false;
        }
    }

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

    public String getIP(){
        try{
            String host = InetAddress.getLocalHost().getHostAddress();
            return host;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean schickeSchuss(int x, int y){
        if(x > 0 && y > 0) {
            Schuss schuss = new Schuss(x, y);
            String message = schuss.shussJSONerstellen();
            writer.write(message);
            writer.flush();
            return true;
        }else{
            return false;
        }
    }

    public void messageWithSize(int size){
        Header h = new Header("????", "????");
        //null are not written into json with Gson, might be different depending from the framework used
        //the Gson framework automatically detects unsent serializable attributes as null and so we need not to worry
        Body b = new Body(size, null, null);
        Wrapper wrp = new Wrapper(new Game(h, b));

        String message = JSONHandler.serializeWrapper(wrp);
        writer.write(message);
        writer.flush();
    }
}
