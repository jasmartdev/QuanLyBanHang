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
import retrofit.http.Path;


public interface SanPhamService {
    @GET("api/CategoryProduct")
    Call<List<SanPham>> getAll();

    @GET("api/CategoryProduct/{id}")
    Call<SanPham> chiTiet(@Path("id") int id);

    @POST("api/CategoryProduct")
    Call<SanPham> them(@Body SanPhamRequest sanPhamRequest);

    @PUT("api/CategoryProduct")
    Call<Object> sua(@Body SanPhamRequest sanPhamRequest);

    @DELETE("api/CategoryProduct")
    Call<Object> xoa(SanPhamRequest sanPhamRequest);
}
