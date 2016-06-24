package bk.danang.quanlybanhang.util;

public class Util {
    public static String ConvertGender(int gender) {
        switch (gender) {
            case 1:
                return "Nam";
            case 2:
                return "Nữ";
            default:
                return "Khác";
        }
    }
}
