package bk.danang.quanlybanhang.model;

import java.util.List;

/**
 * Created by voqua on 6/25/2016.
 */
public class DoanhThuResponse {
    private int total;
    private List<ChiTietDoanhThu> invoices;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ChiTietDoanhThu> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<ChiTietDoanhThu> invoices) {
        this.invoices = invoices;
    }
}
