package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import work.boku.comservice.R;
import work.boku.comservice.Utils.JavaUtil;
import work.boku.comservice.classes.ResidentBean;

public class AddActivity extends BaseActivity {

    private static String TAG = "AddActivity";

    /**
     * addStat：添加用户表情况
     * 0：首次登录操作
     * 1：用户管理员操作
     * 2：系统管理员操作
     */
    private int addStat;

    private EditText et_insert_in;
    private EditText et_insert_rn;
    private EditText et_insert_pn;
    private TextView tv_insert_pl;
    private Spinner spinnerPL;

    private ResidentBean rb;

    private String insert_in;
    private String insert_rn;
    private String insert_pn;
    private int insert_pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        if (spu.isFirstUse()) {
            addStat = 0;
        } else if (spu.getMyPL() == 2) {
            addStat = 2;
        } else if (spu.getMyPL() == 1) {
            addStat = 1;
        } else {
            Toast.makeText(this, R.string.system_error, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onCreate: 权限错误");
            return;
        }

        TextView tvFirst = findViewById(R.id.tv_first_hint);
        et_insert_in = findViewById(R.id.et_insert_in);
        et_insert_rn = findViewById(R.id.et_insert_rn);
        et_insert_pn = findViewById(R.id.et_insert_pn);
        tv_insert_pl = findViewById(R.id.tv_insert_pl);

        spinnerPL = findViewById(R.id.spinner_pl);
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_dropdown_item);
        spinnerPL.setAdapter(aa);

        Button btAddResident = findViewById(R.id.bt_insert_end);
        Button btAddFirst = findViewById(R.id.bt_first_insert);

        // 根据添加用户表状况隐藏部分空间
        switch (addStat) {
            case 0:
                btAddResident.setVisibility(View.GONE);
                spinnerPL.setVisibility(View.GONE);
                tv_insert_pl.setVisibility(View.GONE);
                break;
            case 1:
                tvFirst.setVisibility(View.GONE);
                btAddFirst.setVisibility(View.GONE);
                spinnerPL.setEnabled(false);
                tv_insert_pl.setVisibility(View.GONE);
                break;
            case 2:
                tvFirst.setVisibility(View.GONE);
                btAddFirst.setVisibility(View.GONE);
                break;
        }

        spinnerPL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = parent.getItemAtPosition(position).toString();
                switch (str) {
                    case "普通用户":
                        insert_pl = 0;
                        break;
                    case "用户管理员":
                        insert_pl = 1;
                        break;
                    case "系统管理员":
                        insert_pl = 2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerPL.setSelection(0, true);

        btAddResident.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_in = et_insert_in.getText().toString().trim();
                insert_rn = et_insert_rn.getText().toString().trim();
                insert_pn = et_insert_pn.getText().toString().trim();

                rb = new ResidentBean();
                rb.setIdentity_number(insert_in);
                rb.setResident_name(insert_rn);
                rb.setPhone_number(insert_pn);
                rb.setPermission_level(insert_pl);

                int isValid = JavaUtil.isValidInsert(rb);
                switch (isValid) {
                    case 1:
                        Toast.makeText(AddActivity.this, R.string.err_in_hint,
                                Toast.LENGTH_SHORT).show();
                        return;
                    case 2:
                        Toast.makeText(AddActivity.this, R.string.err_rn_hint,
                                Toast.LENGTH_SHORT).show();
                        return;
                    case 3:
                        Toast.makeText(AddActivity.this, R.string.err_pn_hint,
                                Toast.LENGTH_SHORT).show();
                        return;
                    default:
                        ArrayList<ResidentBean> rbList = dbh.selectAllResident();
                        if (JavaUtil.isRepetitiveInfo(rb, rbList)) {
                            Toast.makeText(AddActivity.this, R.string.repetitive_in_add,
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // 添加完成后将文本框自动清空
                        et_insert_in.setText(null);
                        et_insert_rn.setText(null);
                        et_insert_pn.setText(null);

                        dbh.newResident(rb);
                        Toast.makeText(AddActivity.this, R.string.insert_succeed,
                                Toast.LENGTH_SHORT).show();
                }
            }
        });

        btAddFirst.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_in = et_insert_in.getText().toString().trim();
                insert_rn = et_insert_rn.getText().toString().trim();
                insert_pn = et_insert_pn.getText().toString().trim();

                rb = new ResidentBean();
                rb.setIdentity_number(insert_in);
                rb.setResident_name(insert_rn);
                rb.setPhone_number(insert_pn);
                rb.setPermission_level(2);
                int isValid = JavaUtil.isValidInsert(rb);

                switch (isValid) {
                    case 1:
                        Toast.makeText(AddActivity.this, R.string.err_in_hint,
                                Toast.LENGTH_SHORT).show();
                        return;
                    case 2:
                        Toast.makeText(AddActivity.this, R.string.err_rn_hint,
                                Toast.LENGTH_SHORT).show();
                        return;
                    case 3:
                        Toast.makeText(AddActivity.this, R.string.err_pn_hint,
                                Toast.LENGTH_SHORT).show();
                        return;
                    default:
                        // 输入完成后重启前，不允许修改之前的信息
                        et_insert_in.setFocusable(false);
                        et_insert_in.setFocusableInTouchMode(false);
                        et_insert_rn.setFocusable(false);
                        et_insert_rn.setFocusableInTouchMode(false);
                        et_insert_pn.setFocusable(false);
                        et_insert_pn.setFocusableInTouchMode(false);

                        dbh.newResident(rb);
                        Toast.makeText(AddActivity.this, R.string.first_succeed,
                                Toast.LENGTH_LONG).show();
                        spu.setFirstUse(false);

                        // 延迟1.5秒
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                // 首次输入完成后重启应用
                                Intent intent = new Intent(AddActivity.this,
                                        SplashActivity.class);
                                startActivity(intent);
                                AddActivity.this.finish();
                            }
                        }, 1000L);
                }
            }
        });
    }
}
