package Network;

import JSON.JSONHandler;
import JSON.Objects.*;

public class Schuss {
    int x;
    int y;

    public Schuss(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String shussJSONerstellen(){
        Header h = new Header("", "");
        Body b = new Body(10, null, new Shot(x, y));
        Wrapper wrp = new Wrapper(new Game(h, b));
        return JSONHandler.serializeWrapper(wrp);
    }
}
