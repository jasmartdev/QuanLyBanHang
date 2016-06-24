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

public interface KhachHangService {
    @GET("api/Customer")
    Call<List<KhachHang>> getAll(@Query("authentication") String authentication);

    @GET("api/Customer")
    Call<KhachHang> chiTiet(int id, @Query("authentication") String authentication);

    @POST("api/Customer")
    Call<KhachHang> them(@Body KhachHangRequest khachHang);

    @DELETE("api/Customer")
    Call<Object> xoa(@Query("id") int id, @Query("authentication") String authentication);

    @PUT("api/Customer")
    Call<Object> sua(@Body KhachHangRequest khachHang);
}
