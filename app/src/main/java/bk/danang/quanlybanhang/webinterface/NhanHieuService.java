package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.LoginForm;
import bk.danang.quanlybanhang.model.LoginFormResponse;
import bk.danang.quanlybanhang.model.NhanHieu;
import bk.danang.quanlybanhang.model.NhanHieuRequest;
import bk.danang.quanlybanhang.model.NhanVien;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;

/**
 * Created by voqua on 6/24/2016.
 */
public interface NhanHieuService {
    @GET("api/Brand")
    Call<List<NhanHieu>> getAll();

    @GET("api/Brand")
    Call<NhanHieu> chiTiet(int id);

    @POST("api/Brand")
    Call<NhanHieu> them(@Body NhanHieuRequest nhanHieu);

    @DELETE("api/Brand")
    Call<Object> xoa(@Body NhanHieuRequest nhanHieu);

    @PUT("api/Brand")
    Call<Object> sua(@Body NhanHieuRequest nhanHieu);
}
