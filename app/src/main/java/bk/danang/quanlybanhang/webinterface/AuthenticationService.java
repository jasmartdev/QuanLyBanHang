package bk.danang.quanlybanhang.webinterface;

import java.util.List;

import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.LoginForm;
import bk.danang.quanlybanhang.model.LoginFormResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by voqua on 6/24/2016.
 */
public interface AuthenticationService {
    @GET("api/Authentication")
    Call<LoginFormResponse> authenticate(LoginForm loginForm);
}
