package JSON.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit {
    @SerializedName("hit")
    @Expose
    private boolean hit;
    @SerializedName("destroyed")
    @Expose
    private boolean destroyed;
    @SerializedName("x")
    @Expose
    private int x;
    @SerializedName("y")
    @Expose
    private int y;

    public Hit(boolean hit, boolean destroyed, int x, int y) {
        this.hit = hit;
        this.destroyed = destroyed;
        this.x = x;
        this.y = y;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "\"hit\" : " + hit + ", \"destroyed\" : " + destroyed + ", \"x\" : " + x + ", \"y\" : " + y;
    }
}
