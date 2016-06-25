package bk.danang.quanlybanhang.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by voqua on 6/25/2016.
 */
public class ChiTietDoanhThu {
    private String production;
    private int quantity;
    private String employee;
    private int price;
    @SerializedName("price_type")
    private int priceType;
    private String customer;
    private int discount;
    private int total;


    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
