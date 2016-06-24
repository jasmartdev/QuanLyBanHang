package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.SanPham;
import bk.danang.quanlybanhang.model.SanPhamRequest;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;


public interface SanPhamService {
    @GET("api/Product")
    Call<List<SanPham>> getAll(@Query("authentication") String authentication);

    @GET("api/Product")
    Call<SanPham> chiTiet(@Query("id") int id, @Query("authentication") String authentication);

    @POST("api/Product")
    Call<SanPham> them(@Body SanPhamRequest sanPhamRequest);

    @DELETE("api/Product")
    Call<Object> xoa(@Query("id") int id, @Query("authentication") String authentication);

    @PUT("api/Product")
    Call<Object> sua(@Body SanPhamRequest sanPhamRequest);
}
