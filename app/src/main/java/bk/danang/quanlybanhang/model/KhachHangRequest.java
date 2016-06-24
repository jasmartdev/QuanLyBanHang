package bk.danang.quanlybanhang.model;

public class KhachHangRequest {
    private int id;
    private String authentication;
    private KhachHang data;

    public KhachHang getData() {
        return data;
    }

    public void setData(KhachHang data) {
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
