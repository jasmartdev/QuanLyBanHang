package bk.danang.quanlybanhang.model;

public class SanPham {
    private int id;
    private String name;
    private String type;
    private int number;
    private int price;
    private int priceVip;
    private int priceRetail; //giá bán lẻ
    private int priceWholesale ; //giá bán sỉ

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriceVip() {
        return priceVip;
    }

    public void setPriceVip(int priceVip) {
        this.priceVip = priceVip;
    }

    public int getPriceRetail() {
        return priceRetail;
    }

    public void setPriceRetail(int priceRetail) {
        this.priceRetail = priceRetail;
    }

    public int getPriceWholesale() {
        return priceWholesale;
    }

    public void setPriceWholesale(int priceWholesale) {
        this.priceWholesale = priceWholesale;
    }
}
