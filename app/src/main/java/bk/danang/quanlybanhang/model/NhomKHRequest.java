package bk.danang.quanlybanhang.model;

public class NhomKHRequest {
    private int id;
    private String authentication;
    private NhomKH data;

    public NhomKH getData() {
        return data;
    }

    public void setData(NhomKH data) {
        this.data = data;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
