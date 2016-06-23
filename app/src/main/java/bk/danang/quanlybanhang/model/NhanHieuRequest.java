package bk.danang.quanlybanhang.model;

/**
 * Created by voqua on 6/24/2016.
 */
public class NhanHieuRequest {
    private int id;
    private String authentication;
    private NhanHieu data;

    public NhanHieu getData() {
        return data;
    }

    public void setData(NhanHieu data) {
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
