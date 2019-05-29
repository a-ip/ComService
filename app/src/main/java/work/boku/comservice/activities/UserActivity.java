package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import work.boku.comservice.R;

public class UserActivity extends BaseActivity {
    private final static String TAG = "UserActivity";

    Button bt_view_own_info;
    Button bt_view_notice;
    TextView tv_manager_detected;
    Button bt_manager_function;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        bt_view_own_info = findViewById(R.id.bt_view_personal_info);
        bt_view_notice = findViewById(R.id.bt_view_notice);
        tv_manager_detected = findViewById(R.id.tv_manager_detected);
        bt_manager_function = findViewById(R.id.bt_manager_function);

        // 非管理员登录情况下，隐藏管理员功能按钮，防止出错
        Log.d(TAG, "onCreate: spu-pl" + spu.getMyPL());
        if (spu.getMyPL() == 0) {
            tv_manager_detected.setVisibility(View.GONE);
            bt_manager_function.setVisibility(View.GONE);
        }

        bt_view_own_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent(UserActivity.this, InfoActivity.class);
                startActivity(infoIntent);
            }
        });

        bt_view_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticeIntent = new Intent(UserActivity.this, SelectNoticeActivity.class);
                startActivity(noticeIntent);
            }
        });

        bt_manager_function.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent managerIntent = new Intent(UserActivity.this, ManagerActivity.class);
                startActivity(managerIntent);
            }
        });
    }
}
