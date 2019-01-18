package Network;

import JSON.JSONHandler;
import JSON.Objects.*;

public class Schuss {
    int x;
    int y;

    /**
     * Konstruktor um ein Object Schuss zu erstellen. Initialisieren von X- und Y-Koordinaten
     * @param x X-Koordinate des Schusses
     * @param y Y-Koordinate des Schusses
     */
    public Schuss(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Erstellt aus den Koordinaten ein Schuss-Object und dementsprechend auch das JSON-Object
     * @return Rückgabe ist das JSON-Object
     */
    public String shussJSONerstellen(){
        Header h = new Header("Game", "");
        Body b = new Body(10, null, new Shot(x, y));
        Wrapper wrp = new Wrapper(new Game(h, b));
        return JSONHandler.serializeWrapper(wrp);
    }
}
