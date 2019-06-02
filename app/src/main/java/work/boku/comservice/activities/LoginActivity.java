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

        bt_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = et_username.getText().toString().trim();
                String pw = et_password.getText().toString().trim();

                int isValid = JavaUtil.isValidLogin(un, pw);
//                Log.d(TAG, "un: " + un + "pw: " + pw + "v: " + isValid);
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
                        ArrayList<ResidentBean> rbList = dbh.selectAllResident();
                        for (ResidentBean rb : rbList
                        ) {
                            Log.d(TAG, "onClick: " + rb.getCommunity_id() + "::" + rb.getIdentity_number() + "::" + rb.getResident_name());
                            if (Integer.parseInt(un) == rb.getCommunity_id() && pw.equals(rb.getPasswd())) {

                                spu.setMyCID(rb.getCommunity_id());
                                spu.setMyPW(rb.getPasswd());
                                spu.setMyIN(rb.getIdentity_number());
                                spu.setMyRN(rb.getResident_name());
                                spu.setMyPN(rb.getPhone_number());
                                spu.setMyPL(rb.getPermission_level());

                                spu.setCCID(rb.getCommunity_id());
                                if (pw.equals("123456")) {
                                    Toast.makeText(LoginActivity.this,
                                            R.string.first_login, Toast.LENGTH_SHORT).show();
                                    Intent userIntent = new Intent(LoginActivity.this,
                                            PasswdActivity.class);
                                    startActivity(userIntent);
                                    LoginActivity.this.finish();
                                    return;
                                } else {
                                    spu.setFirstLogin(false);
                                }

                                Log.d(TAG, "spu-CID: " + spu.getMyCID());
                                Log.d(TAG, "spu-PW: " + spu.getMyPW());
                                Log.d(TAG, "spu-IN: " + spu.getMyIN());
                                Log.d(TAG, "spu-RN: " + spu.getMyRN());
                                Log.d(TAG, "spu-PN: " + spu.getMyPN());
                                Log.d(TAG, "spu-PL: " + spu.getMyPL());

                                et_username.setText(null);
                                et_password.setText(null);

                                Toast.makeText(LoginActivity.this, R.string.login_succeed, Toast.LENGTH_SHORT).show();
                                Intent userIntent = new Intent(LoginActivity.this, UserActivity.class);
                                startActivity(userIntent);
                                LoginActivity.this.finish();
                                return;
                            }
                        }
                        Toast.makeText(LoginActivity.this, R.string.login_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

