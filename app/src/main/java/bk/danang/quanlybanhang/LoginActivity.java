package bk.danang.quanlybanhang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bk.danang.quanlybanhang.controller.PermissionController;
import bk.danang.quanlybanhang.model.LoginForm;
import bk.danang.quanlybanhang.model.LoginFormResponse;
import bk.danang.quanlybanhang.util.LoadAllData;
import bk.danang.quanlybanhang.webinterface.AuthenticationService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity implements Callback<LoginFormResponse> {
    EditText ed_user_name;
    EditText ed_user_pass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_user_name = (EditText) findViewById(R.id.ed_user_name);
        ed_user_pass = (EditText) findViewById(R.id.ed_user_pass);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_msg));
    }

    public void Login(View view) {
        progressDialog.show();
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

        if (response == null || response.body() == null) {
            Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
        } else {
            PermissionController.getInstance().setIsAdmin(response.body().getRole() == 1);
            PermissionController.getInstance().setAuthentication(response.body().getAuthentication());
            new LoadAllData(new Runnable() {
                public void run() {
                    progressDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }, new Runnable() {
                public void run() {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, getString(R.string.loading_msg_fail), Toast.LENGTH_SHORT).show();
                }
            }).start();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
    }
}
