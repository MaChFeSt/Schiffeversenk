package JSON.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body {
    @SerializedName("map_size")
    @Expose
    private int map_size;
    @SerializedName("hit")
    @Expose
    private Hit hit;
    @SerializedName("shot")
    @Expose
    private Shot shot;

    public Body(int map_size, Hit hit, Shot shot) {
        this.map_size = map_size;
        this.hit = hit;
        this.shot = shot;
    }

    public int getMap_size() {
        return map_size;
    }

    public void setMap_size(int map_size) {
        this.map_size = map_size;
    }

    public Hit getHit() {
        return hit;
    }

    public void setHit(Hit hit) {
        this.hit = hit;
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    @Override
    public String toString() {
        return "\"map_size\" : " + map_size + " , \"hit\" : " + hit + ", \"shot\" : " + shot;
    }
}
