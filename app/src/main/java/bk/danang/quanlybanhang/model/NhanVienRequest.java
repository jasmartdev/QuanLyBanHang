package bk.danang.quanlybanhang.model;

/**
 * Created by voqua on 6/24/2016.
 */
public class NhanVienRequest {
    private int id;
    private String authentication;
    private NhanVien data;

    public NhanVien getData() {
        return data;
    }

    public void setData(NhanVien data) {
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
