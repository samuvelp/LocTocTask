

        package com.designthepresent.samuvelp.loctoctask;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class PostTemp {

    @SerializedName("tempKey")
    @Expose
    private String tempKey;
    @SerializedName("permKey")
    @Expose
    private String permKey;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public String getTempKey() {
        return tempKey;
    }

    public void setTempKey(String tempKey) {
        this.tempKey = tempKey;
    }

    public String getPermKey() {
        return permKey;
    }

    public void setPermKey(String permKey) {
        this.permKey = permKey;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PostTemp{" +
                "tempKey='" + tempKey + '\'' +
                ", permKey='" + permKey + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}