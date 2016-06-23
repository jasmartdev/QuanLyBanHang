package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.Request;
import retrofit.Call;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;


/**
 * Created by voqua on 6/24/2016.
 */
public interface LoaiSPService {
    @GET("api/CategoryProduct")
    Call<List<LoaiSP>> listLoaiSP();

    @GET("api/CategoryProduct/{id}")
    Call<LoaiSP> getLoaiSP(@Path("id") int id);

    @POST("api/CategoryProduct")
    Call<LoaiSP> themLoaiSP(Request request);

    @PUT("api/CategoryProduct")
    Call<Object> suaLoaiSP(Request request);

    @DELETE("api/CategoryProduct")
    Call<Object> xoaLoaiSP(Request request);
}
