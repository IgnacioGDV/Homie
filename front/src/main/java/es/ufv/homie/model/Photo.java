package es.ufv.homie.model;

public class Photo {
    private String url;
    private String data; // <-- Base64

    public Photo() {}

    public Photo(String url, String data) {
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
