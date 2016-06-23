package bk.danang.quanlybanhang;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import bk.danang.quanlybanhang.model.LoaiSP;
import bk.danang.quanlybanhang.model.LoginForm;
import bk.danang.quanlybanhang.model.LoginFormResponse;
import bk.danang.quanlybanhang.webinterface.AuthenticationService;
import bk.danang.quanlybanhang.webinterface.LoaiSPService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity implements Callback<LoginFormResponse> {
    EditText ed_user_name;
    EditText ed_user_pass;
    String name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_user_name = (EditText) findViewById(R.id.ed_user_name);
        ed_user_pass = (EditText) findViewById(R.id.ed_user_pass);
    }

    public void Login(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.WEB_API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // prepare call in Retrofit 2.0
        AuthenticationService stackOverflowAPI = retrofit.create(AuthenticationService.class);
        LoginForm loginForm = new LoginForm();
        loginForm.setUserName(ed_user_name.getText().toString());
        loginForm.setPassword(ed_user_pass.getText().toString());

        final Call<LoginFormResponse> call = stackOverflowAPI.authenticate(loginForm);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Response<LoginFormResponse> response, Retrofit retrofit) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
    }
}
