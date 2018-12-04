package JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import JSON.Objects.*;

import java.io.FileReader;
import java.io.FileWriter;

public class JSONHandler {

    /**
     * Puts the "Wrapper" - Object into a JSON-formatted String.
     * @param wrp
     * @return
     */
    public static String serializeWrapper(Wrapper wrp) {
        //the \n is just an ugly workaround that is needed in this system
        //because server and client are using a BufferedReader that uses readLine()
        return new Gson().toJson(wrp) + "\n";
    }

    /**
     * Gets the "Wrapper" - Object from a JSON-formatted String.
     * @param json
     * @return
     */
    public static Wrapper getWrapper(String json) {
        return new Gson().fromJson(json, Wrapper.class);
    }

    /**
     * Writes the "Wrapper" - Object into the file "json-test.json" in your project folder.
     * @param wrp
     */
    public static void writeToFile(Wrapper wrp) {
        try {
            /*
             * If you want to write into a network output stream, you will need to change the "writer" accordingly
             *
             * don't forget to flush the network output stream after sending a message.
             */
            FileWriter writer = new FileWriter("json-test.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(wrp, writer);
            //writers need to be closed after using them or else other programs or parts of the same system
            //might not be able to access them.
            //also applies to network streams
            writer.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Reads from the given file. Does not check if the given file is .json.
     * Returns a "Wrapper" - Object on which you can perform actions.
     * @param filePath
     * @return
     */
    public static Wrapper readFromFile(String filePath) {
        try {
            return new Gson().fromJson(new FileReader(filePath), Wrapper.class);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
