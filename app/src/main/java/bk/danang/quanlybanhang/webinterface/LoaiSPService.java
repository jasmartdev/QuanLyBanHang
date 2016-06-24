package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.LoaiSPRequest;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;

public interface LoaiSPService {
    @GET("api/CategoryProduct")
    Call<List<LoaiSP>> getAll(@Query("authentication") String authentication);

    @GET("api/CategoryProduct")
    Call<LoaiSP> chiTiet(int id, @Query("authentication") String authentication);

    @POST("api/CategoryProduct")
    Call<LoaiSP> them(@Body LoaiSPRequest loaiSPRequest);

    @DELETE("api/CategoryProduct")
    Call<Object> xoa(@Query("id") int id, @Query("authentication") String authentication);

    @PUT("api/CategoryProduct")
    Call<Object> sua(@Body LoaiSPRequest loaiSPRequest);
}
