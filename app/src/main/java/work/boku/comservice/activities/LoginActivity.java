package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import work.boku.comservice.R;
import work.boku.comservice.Utils.JavaUtil;
import work.boku.comservice.classes.ResidentBean;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button bt_login = findViewById(R.id.bt_login);
        et_username = findViewById(R.id.et_insert_username);
        et_password = findViewById(R.id.et_insert_password);

        Button btAddResident = findViewById(R.id.bt_insert_end);

        bt_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = et_username.getText().toString();
                String pw = et_password.getText().toString();

                int isValid = JavaUtil.isValidLogin(un, pw);
                Log.d(TAG, "un: " + un + "pw: " + pw + "v: " + isValid);
                switch (isValid) {
                    case 1:
                        Toast.makeText(LoginActivity.this, R.string.username_error, Toast.LENGTH_SHORT).show();
                        return;
                    case 2:
                        Toast.makeText(LoginActivity.this, R.string.password_error, Toast.LENGTH_SHORT).show();
                        return;
                    case 3:
                        Toast.makeText(LoginActivity.this, R.string.username_error, Toast.LENGTH_SHORT).show();
                        return;
                    default:
                        ArrayList<ResidentBean> rbList = rDBh.selectAllResident();
                        for (ResidentBean rb : rbList
                        ) {
                            if (Integer.parseInt(un) == rb.getCommunity_id() && pw.equals(rb.getPasswd())) {
                                Toast.makeText(LoginActivity.this, R.string.login_succeed, Toast.LENGTH_SHORT).show();
                                int rlv = rb.getPermission_level();
                                switch (rlv) {
                                    case 0:
                                        spu.setLevel(rlv);
                                        Intent userIntent = new Intent(LoginActivity.this, UserActivity.class);
                                        startActivity(userIntent);
                                        return;
                                    default:
                                        spu.setLevel(rlv);
                                        Intent managerIntent = new Intent(LoginActivity.this, ManagerActivity.class);
                                        startActivity(managerIntent);
                                        return;
                                }
                            }
                        }
                        Toast.makeText(LoginActivity.this, R.string.login_error, Toast.LENGTH_SHORT).show();
                        return;
                }
            }
        });
    }
}

