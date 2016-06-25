package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.DoanhThuResponse;
import bk.danang.quanlybanhang.model.HoaDon;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by voqua on 6/25/2016.
 */
public interface DoanhThuService {
    @GET("api/Revenue")
    Call<DoanhThuResponse> getAll(@Query("authentication") String authentication);
}
