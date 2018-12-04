package Network;

import JSON.JSONHandler;
import JSON.Objects.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

    PrintWriter writer;
    BufferedReader reader;

    public Client(String ip, int port) {
        System.out.println("Creating Client...");
        try {
            Socket socket = new Socket(ip, port);
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
        }

        try{
            writer.close();
            reader.close();
            System.out.println("verbindung geschlossen");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

        public void run () {



    }

    public static void main(String[] args){
        Client c = new Client("141.18.110.127", 4444);
    }

}
