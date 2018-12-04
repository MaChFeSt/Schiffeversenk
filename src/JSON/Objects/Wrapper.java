package JSON.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wrapper {
    @SerializedName("game")
    @Expose
    public Game game;

    public Wrapper(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "{\"game\": " + game + " }";
    }
}
