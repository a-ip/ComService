package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import work.boku.comservice.R;
import work.boku.comservice.classes.ResidentBean;

public class PasswdActivity extends BaseActivity {


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
                String pw1 = et_first_pw.getText().toString();
                String pw2 = et_second_pw.getText().toString();
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

                ResidentBean newRB = rb;
                newRB.setPasswd(pw1);
                spu.setFirstLogin(false);
                dbh.updateResident(newRB);
                Intent UserIntent = new Intent(PasswdActivity.this, LoginActivity.class);
                startActivity(UserIntent);
                PasswdActivity.this.finish();
            }
        });

    }
}
