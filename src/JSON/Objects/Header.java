package JSON.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("systemmessage")
    @Expose
    private String systemmessage;

    public Header(String status, String systemmessage) {
        this.status = status;
        this.systemmessage = systemmessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSystemmessage() {
        return systemmessage;
    }

    public void setSystemmessage(String systemmessage) {
        this.systemmessage = systemmessage;
    }

    @Override
    public String toString() {
        return "\"status_code\" : " + status + ", \"systemmessage\" : " + systemmessage;
    }
}
