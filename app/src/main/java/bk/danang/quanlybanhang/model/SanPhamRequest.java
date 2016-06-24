package bk.danang.quanlybanhang.model;

public class SanPhamRequest {
    private int id;
    private String authentication;
    private SanPham data;

    public SanPham getData() {
        return data;
    }

    public void setData(SanPham data) {
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
