package bk.danang.quanlybanhang.model;

import com.google.gson.annotations.SerializedName;

public class NhomKH {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @SerializedName("discount")
    private int discountPercent;

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        if (discountPercent > 100) discountPercent = 100;
        else if (discountPercent < 0) discountPercent = 0;
        this.discountPercent = discountPercent;
    }

    public String toString() {
        return name;
    }
}
