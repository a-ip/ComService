package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import work.boku.comservice.R;
import work.boku.comservice.Utils.JavaUtil;
import work.boku.comservice.classes.ResidentBean;

// InfoActivity：用户信息界面
public class InfoActivity extends BaseActivity {

    public static final String TAG = "InfoActivity";

    /**
     * infoStat：访问用户表情况
     * 0：普通用户访问自己、用户管理员访问自己
     * 1：系统管理员访问自己
     * 2：用户管理员访问他人
     * 3：系统管理员访问他人
     */
    private TextView tv_my_info;

    private LinearLayout ll_pw;

    private TextView tv_info_cid;
    private TextView tv_info_pw;
    private TextView tv_info_in;
    private TextView tv_info_rn;
    private TextView tv_info_pn;
    private TextView tv_info_pl;

    private EditText et_new_cid;
    private EditText et_new_pw;
    private EditText et_new_in;
    private EditText et_new_rn;
    private EditText et_new_pn;
    private Spinner spinnerNewPL;

    private ViewSwitcher vs_cid;
    private ViewSwitcher vs_pw;
    private ViewSwitcher vs_in;
    private ViewSwitcher vs_rn;
    private ViewSwitcher vs_pn;
    private ViewSwitcher vs_pl;

    private Button bt_update_info;
    private Button bt_save_info;
    private Button bt_delete_info;

    private static int infoStat;
    private ResidentBean rb;
    private int insert_pl;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        rb = rDBh.selectResident(spu.getCCID());
        Log.d(TAG, "onCreate: spu-cid:" + rb.getCommunity_id());
        Log.d(TAG, "onCreate: spu-CCID:" + spu.getCCID());
        Log.d(TAG, "onCreate: spu-MyCid:" + spu.getMyCID());

        // 设置selectStat
        if (spu.getCCID() == spu.getMyCID()) {
            if (spu.getMyPL() == 2) {
                infoStat = 1;
            } else {
                infoStat = 0;
            }
        } else {
            if (spu.getMyPL() == 1) {
                infoStat = 2;
            } else if (spu.getMyPL() == 2) {
                infoStat = 3;
            } else {
                Toast.makeText(this, R.string.system_error, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCreate: 权限错误");
                return;
            }
        }
        Log.d(TAG, "onCreate: infoStat: " + infoStat);

        ll_pw = findViewById(R.id.ll_pw);

        vs_cid = findViewById(R.id.vs_cid);
        vs_pw = findViewById(R.id.vs_pw);
        vs_in = findViewById(R.id.vs_in);
        vs_rn = findViewById(R.id.vs_rn);
        vs_pn = findViewById(R.id.vs_pn);
        vs_pl = findViewById(R.id.vs_pl);

        tv_my_info = findViewById(R.id.tv_my_info);

        tv_info_cid = findViewById(R.id.tv_info_cid);
        tv_info_pw = findViewById(R.id.tv_info_pw);
        tv_info_in = findViewById(R.id.tv_info_in);
        tv_info_rn = findViewById(R.id.tv_info_rn);
        tv_info_pn = findViewById(R.id.tv_info_pn);
        tv_info_pl = findViewById(R.id.tv_info_pl);

        et_new_cid = findViewById(R.id.et_new_cid);
        et_new_pw = findViewById(R.id.et_new_pw);
        et_new_in = findViewById(R.id.et_new_in);
        et_new_rn = findViewById(R.id.et_new_rn);
        et_new_pn = findViewById(R.id.et_new_pn);
        spinnerNewPL = findViewById(R.id.spinner_new_pl);
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_dropdown_item);
        spinnerNewPL.setAdapter(aa);

        bt_update_info = findViewById(R.id.bt_update_info);
        bt_save_info = findViewById(R.id.bt_save_info);
        bt_delete_info = findViewById(R.id.bt_delete_info);

        bt_save_info.setVisibility(View.GONE);
        bt_delete_info.setVisibility(View.GONE);

        // 根据访问情况隐藏部分信息
        switch (infoStat) {
            case 0: // 普通用户访问自己、用户管理员（合称普管）访问自己
                // 普管不能修改自己的社区编号
                et_new_cid.setFocusable(false);
                et_new_cid.setFocusableInTouchMode(false);
                // 普管不能修改自己的身份证号
                et_new_in.setFocusable(false);
                et_new_in.setFocusableInTouchMode(false);
                // 普管不能修改自己的姓名
                et_new_rn.setFocusable(false);
                et_new_rn.setFocusableInTouchMode(false);
                spinnerNewPL.setEnabled(false); // 普管不可以修改自己的权限
                break;
            case 1: // 系统管理员访问自己
                spinnerNewPL.setEnabled(false); // 系统管理员不可以修改自己的权限
                break;
            case 2: // 用户管理员访问他人
                bt_update_info.setVisibility(View.GONE); // 用户管理员不能修改他人信息
                tv_my_info.setVisibility(View.GONE);
                ll_pw.setVisibility(View.GONE); // 不能查看他人的密码
                break;
            case 3: // 系统管理员访问他人
                tv_my_info.setVisibility(View.GONE);
                ll_pw.setVisibility(View.GONE); // 不能查看他人的密码
                break;
        }

        final String cid = String.valueOf(rb.getCommunity_id());
        final String pw = rb.getPasswd();
        final String in = rb.getIdentity_number();
        final String rn = rb.getResident_name();
        final String pn = rb.getPhone_number();
        int pl = rb.getPermission_level();
        String plDetail;
        switch (pl) {
            case 0:
                plDetail = "普通用户";
                break;
            case 1:
                plDetail = "用户管理员";
                break;
            case 2:
                plDetail = "系统管理员";
                break;
            default:
                Toast.makeText(this, R.string.system_error, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCreate: 权限错误");
                return;
        }

        tv_info_cid.setText(cid);
        tv_info_pw.setText(pw);
        tv_info_in.setText(in);
        tv_info_rn.setText(rn);
        tv_info_pn.setText(pn);
        tv_info_pl.setText(plDetail);

        et_new_cid.setText(cid);
        et_new_pw.setText(pw);
        et_new_in.setText(in);
        et_new_rn.setText(rn);
        et_new_pn.setText(pn);
        spinnerNewPL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spinnerNewPL.setSelection(rb.getPermission_level(), true);
        Log.d(TAG, "onClick: oldpl = " + insert_pl);

        bt_update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vs_cid.showNext();
                vs_pw.showNext();
                vs_in.showNext();
                vs_rn.showNext();
                vs_pn.showNext();
                vs_pl.showNext();

                bt_update_info.setVisibility(View.GONE);
                bt_save_info.setVisibility(View.VISIBLE);
                if (infoStat != 1) {
                    bt_delete_info.setVisibility(View.VISIBLE);
                }
            }
        });

        bt_save_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCid = et_new_cid.getText().toString();
                String newPw = et_new_pw.getText().toString();
                String newIn = et_new_in.getText().toString();
                String newRn = et_new_rn.getText().toString();
                String newPn = et_new_pn.getText().toString();

                ResidentBean newRB = new ResidentBean();
                if (TextUtils.isEmpty(newCid) || !JavaUtil.isNumeric(newCid)) {
                    Toast.makeText(InfoActivity.this, R.string.username_error, Toast.LENGTH_SHORT).show();
                    return;
                }

                newRB.setCommunity_id(Integer.parseInt(newCid));
                newRB.setPasswd(newPw);
                newRB.setIdentity_number(newIn);
                newRB.setResident_name(newRn);
                newRB.setPhone_number(newPn);
                newRB.setPermission_level(insert_pl);
                Log.d(TAG, "onClick: newpl = " + insert_pl);

                int i = JavaUtil.isRepetitiveInfo(newRB, rDBh.selectAllResident());
                Log.d(TAG, "onClick: newcid = " + newRB.getCommunity_id());
                Log.d(TAG, "onClick: oldcid = " + rb.getCommunity_id());
                switch (i) {
                    case 1:
                        if (!rb.getIdentity_number().equals(newRB.getIdentity_number())) {
                            Toast.makeText(InfoActivity.this, R.string.repetitive_in_update,
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                    case 2:
                        if (rb.getCommunity_id() != newRB.getCommunity_id()) {
                            Toast.makeText(InfoActivity.this, R.string.repetitive_cid_update,
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                }

                rDBh.updateResident(newRB);
                Toast.makeText(InfoActivity.this, R.string.update_succeed,
                        Toast.LENGTH_SHORT).show();
            }
        });

        bt_delete_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rDBh.deleteResident(rb.getCommunity_id());
                SelectActivity.instance.finish();
                Intent selectIntent = new Intent (InfoActivity.this, SelectActivity.class);
                startActivity(selectIntent);
                InfoActivity.this.finish();
            }
        });
    }
}
