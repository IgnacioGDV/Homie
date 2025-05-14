package es.ufv.homie.model;

public class PhotoFront {
    private String url;
    private String data; // Base64

    public PhotoFront() {}

    public PhotoFront(String url, String data) {
        this.url = url;
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
