package bk.danang.quanlybanhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
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
        name = ed_user_name.getText().toString();
        pass = ed_user_pass.getText().toString();
    }
}
