package bk.danang.quanlybanhang.model;

public class LoaiSPRequest {
    private int id;

    private String authentication;

    private LoaiSP data;

    public LoaiSP getData() {
        return data;
    }

    public void setData(LoaiSP data) {
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
