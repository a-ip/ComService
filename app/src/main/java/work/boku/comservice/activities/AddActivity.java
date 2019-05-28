package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import work.boku.comservice.R;
import work.boku.comservice.Utils.JavaUtil;
import work.boku.comservice.classes.ResidentBean;

public class AddActivity extends BaseActivity {

    private static String TAG = "AddActivity";
    private EditText et_identity_number;
    private EditText et_name;
    private EditText et_phone_number;

    ResidentBean rb = new ResidentBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: 00001");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Log.d(TAG, "onCreate: 00002");
        et_identity_number = findViewById(R.id.et_insert_identity_number);
        et_name = findViewById(R.id.et_insert_name);
        et_phone_number = findViewById(R.id.et_insert_phone_number);

        Button btAddResident = findViewById(R.id.bt_insert_end);
        Button btAddFirst = findViewById(R.id.bt_first_insert);
        TextView tvFirst = findViewById(R.id.tv_first_hint);
        if (spu.isFirst()) {
            btAddResident.setVisibility(View.GONE);
        } else {
            tvFirst.setVisibility(View.GONE);
            btAddFirst.setVisibility(View.GONE);
        }

        btAddResident.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String in = et_identity_number.getText().toString();
                String rn = et_name.getText().toString();
                String pn = et_phone_number.getText().toString();
                rb.setIdentity_number(in);
//                Log.d(in, "Identity_number");
                rb.setResident_name(rn);
//                Log.d(rn, "Resident_name");
                rb.setPhone_number(pn);
//                Log.d(pn, "Phone_number");
                int isValid = JavaUtil.isValidInsert(rb);
//                Log.d("eeeee = " + e, "/eeeee");
                switch (isValid) {
                    case 1:
                        Toast.makeText(AddActivity.this, R.string.err_in_hint, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(AddActivity.this, R.string.err_rn_hint, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(AddActivity.this, R.string.err_pn_hint, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        rDBh.addResident(rb);
                        Toast.makeText(AddActivity.this, R.string.insert_succeed, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        btAddFirst.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String in = et_identity_number.getText().toString();
                String rn = et_name.getText().toString();
                String pn = et_phone_number.getText().toString();
                rb.setIdentity_number(in);
//                Log.d(in, "Identity_number");
                rb.setResident_name(rn);
//                Log.d(rn, "Resident_name");
                rb.setPhone_number(pn);
//                Log.d(pn, "Phone_number");
                int isValid = JavaUtil.isValidInsert(rb);
//                Log.d("eeeee = " + e, "/eeeee");
                switch (isValid) {
                    case 1:
                        Toast.makeText(AddActivity.this, R.string.err_in_hint, Toast.LENGTH_SHORT).show();
                        return;
                    case 2:
                        Toast.makeText(AddActivity.this, R.string.err_rn_hint, Toast.LENGTH_SHORT).show();
                        return;
                    case 3:
                        Toast.makeText(AddActivity.this, R.string.err_pn_hint, Toast.LENGTH_SHORT).show();
                        return;
                    default:
                        rDBh.addFirst(rb);
                        Toast.makeText(AddActivity.this, R.string.first_succeed, Toast.LENGTH_SHORT).show();
                        spu.setFirst(false);

                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                // 首次输入完成后重启应用
                                Intent intent = new Intent(AddActivity.this, SplashActivity.class);
                                startActivity(intent);
                                android.os.Process.killProcess(android.os.Process.myPid());
                            }
                        }, 3000);
                        return;
                }
            }
        });
    }
}
