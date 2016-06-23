package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.LoginForm;
import bk.danang.quanlybanhang.model.LoginFormResponse;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by voqua on 6/24/2016.
 */
public interface AuthenticationService {
    @POST("api/Authentication")
    Call<LoginFormResponse> authenticate(@Body LoginForm loginForm);
}
