package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.LoaiSanPhamRequest;
import bk.danang.quanlybanhang.model.NhanHieu;
import bk.danang.quanlybanhang.model.NhanHieuRequest;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;

/**
 * Created by voqua on 6/24/2016.
 */
public interface LoaiSanPhamService {
    @GET("api/CategoryProduct")
    Call<List<LoaiSP>> getAll();

    @GET("api/CategoryProduct")
    Call<LoaiSP> chiTiet(int id);

    @POST("api/CategoryProduct")
    Call<LoaiSP> them(@Body LoaiSanPhamRequest loaiSanPhamRequest);

    @DELETE("api/CategoryProduct")
    Call<Object> xoa(@Body LoaiSanPhamRequest loaiSanPhamRequest);

    @PUT("api/CategoryProduct")
    Call<Object> sua(@Body LoaiSanPhamRequest loaiSanPhamRequest);
}
