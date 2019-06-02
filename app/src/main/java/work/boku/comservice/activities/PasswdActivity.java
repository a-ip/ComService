package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import work.boku.comservice.R;
import work.boku.comservice.classes.ResidentBean;

public class PasswdActivity extends BaseActivity {
    private final static String TAG = "PasswdActivity";

    private EditText et_first_pw;
    private EditText et_second_pw;
    private Button bt_change_pw;

    private ResidentBean rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwd);

        et_first_pw = findViewById(R.id.et_first_pw);
        et_second_pw = findViewById(R.id.et_second_pw);
        bt_change_pw = findViewById(R.id.bt_change_pw);

        bt_change_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb = dbh.selectResident(spu.getMyCID());
                Log.d(TAG, "pwd: *" + rb.getIdentity_number() + "*");
                String pw1 = et_first_pw.getText().toString().trim();
                String pw2 = et_second_pw.getText().toString().trim();
                if (TextUtils.isEmpty(pw1) || TextUtils.isEmpty(pw2)) {
                    Toast.makeText(PasswdActivity.this, R.string.pw_empty,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pw1.equals(pw2)) {
                    Toast.makeText(PasswdActivity.this, R.string.pw_match_err,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pw1.equals(spu.getMyPW())) {
                    Toast.makeText(PasswdActivity.this, R.string.repetitive_pw,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(PasswdActivity.this, R.string.change_pw_succeed,
                        Toast.LENGTH_SHORT).show();

                rb.setPasswd(pw1);
                Log.d(TAG, "pwd1: *" + rb.getIdentity_number() + "*");
                spu.setFirstLogin(false);
                Log.d(TAG, "pwd2: *" + rb.getIdentity_number() + "*");
                dbh.updateResident(rb);
                Log.d(TAG, "pwd3: *" + rb.getIdentity_number() + "*");
                Intent UserIntent = new Intent(PasswdActivity.this, LoginActivity.class);
                startActivity(UserIntent);
                PasswdActivity.this.finish();
            }
        });

    }
}
