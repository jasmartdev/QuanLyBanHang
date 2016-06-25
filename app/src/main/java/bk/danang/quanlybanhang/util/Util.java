package bk.danang.quanlybanhang.util;

import android.widget.EditText;
import android.widget.TextView;

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

    public static int GetNumber(EditText editText) {
        int value = 0;
        try {
            value = Integer.parseInt(editText.getText().toString());
        } catch (Exception ec) {
            value = 0;
        }
        return value;
    }

    public static String GetTextNotNull(EditText editText) {
        String value = editText.getText().toString();
        if (value == null) {
            value = "";
        }
        return value;
    }
}
