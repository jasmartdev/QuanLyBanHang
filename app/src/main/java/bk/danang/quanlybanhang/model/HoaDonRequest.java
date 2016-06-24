package bk.danang.quanlybanhang.model;

/**
 * Created by voqua on 6/24/2016.
 */
public class HoaDonRequest {
    private int id;
    private HoaDon data;
    private String authentication;

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public HoaDon getData() {
        return data;
    }

    public void setData(HoaDon data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
