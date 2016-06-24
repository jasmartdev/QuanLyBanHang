package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.NhanVien;
import bk.danang.quanlybanhang.model.NhanVienRequest;
import bk.danang.quanlybanhang.model.SanPham;
import bk.danang.quanlybanhang.model.SanPhamRequest;
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
public interface NhanVienService {
    @GET("api/User")
    Call<List<NhanVien>> getAll(@Query("authentication") String authentication);

    @GET("api/User")
    Call<NhanVien> chiTiet(@Query("id") int id, @Query("authentication") String authentication);

    @POST("api/User")
    Call<NhanVien> them(@Body NhanVienRequest nhanVienRequest);

    @DELETE("api/User")
    Call<Object> xoa(@Query("id") int id, @Query("authentication") String authentication);

    @PUT("api/User")
    Call<Object> sua(@Body NhanVienRequest nhanVienRequest);
}
