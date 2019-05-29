package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import work.boku.comservice.R;
import work.boku.comservice.classes.ResidentBean;

public class SelectActivity extends BaseActivity {

    static SelectActivity instance;
    public static final String TAG = "SelectActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        instance = this;

        ArrayList<ResidentBean> rbList = rDBh.selectAllResident();

        TextView tv_resident_info = findViewById(R.id.tv_resident_info);

        LinearLayout ll_resident = this.findViewById(R.id.ll_resident);
        for (ResidentBean rb : rbList
        ) {
            TextView tv_rb = new TextView(SelectActivity.this);
            SpannableStringBuilder ssb = new SpannableStringBuilder();
            String showText = "社区编号：" + rb.getCommunity_id() + "，姓名：" +
                    rb.getResident_name() + "，点击查看详情";
            ssb.append(showText);

            final String rn = rb.getResident_name();
            final int cid = rb.getCommunity_id();
            ClickableSpan cs = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    spu.setCCID(cid);
                    Intent infoIntent = new Intent(SelectActivity.this, InfoActivity.class);
                    startActivity(infoIntent);
                    Toast.makeText(SelectActivity.this, "查看" + rn + "的个人信息",
                            Toast.LENGTH_LONG).show();
                }
            };

            ssb.setSpan(cs, showText.length() - 4, showText.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_rb.setText(ssb);
            tv_rb.setMovementMethod(LinkMovementMethod.getInstance());

            tv_rb.setTextSize(20);
            tv_rb.setBackgroundResource(R.drawable.tvborder_layout);
//            Log.d(TAG, "00002" + rb.getCommunity_id() + rb.getResident_name());
            ll_resident.addView(tv_rb);
        }
    }
}
