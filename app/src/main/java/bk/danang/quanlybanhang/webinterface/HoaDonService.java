package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.KhachHang;
import bk.danang.quanlybanhang.model.KhachHangRequest;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;

/**
 * Created by voqua on 6/24/2016.
 */
public interface HoaDonService {
    @GET("api/Invoice")
    Call<List<KhachHang>> getAll(@Query("authentication") String authentication);

    @GET("api/Invoice")
    Call<KhachHang> chiTiet(int id, @Query("authentication") String authentication);

    @POST("api/Invoice")
    Call<KhachHang> them(@Body KhachHangRequest khachHang);

    @DELETE("api/Invoice")
    Call<Object> xoa(@Query("id") int id, @Query("authentication") String authentication);

    @PUT("api/Invoice")
    Call<Object> sua(@Body KhachHangRequest khachHang);
}
