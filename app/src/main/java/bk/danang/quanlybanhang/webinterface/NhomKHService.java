package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.NhomKH;
import bk.danang.quanlybanhang.model.NhomKHRequest;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;

public interface NhomKHService {
    @GET("api/CustomerGroup")
    Call<List<NhomKH>> getAll(@Query("authentication") String authentication);

    @GET("api/CustomerGroup")
    Call<NhomKH> chiTiet(int id, @Query("authentication") String authentication);

    @POST("api/CustomerGroup")
    Call<NhomKH> them(@Body NhomKHRequest nhomKH);

    @DELETE("api/CustomerGroup")
    Call<Object> xoa(@Query("id") int id, @Query("authentication") String authentication);

    @PUT("api/CustomerGroup")
    Call<Object> sua(@Body NhomKHRequest nhomKH);
}
