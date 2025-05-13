package es.ufv.homie.model;

public class PhotoDTO {
    private String url;
    private String data; // Base64

    // Getters y setters
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

