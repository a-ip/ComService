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
import work.boku.comservice.classes.NoticeBean;

public class SelectNoticeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_notice);

        ArrayList<NoticeBean> nbList = dbh.selectAllNotice();

        LinearLayout ll_notice = this.findViewById(R.id.ll_notice);
        for (NoticeBean nb : nbList
        ) {
            TextView tv_nb1 = new TextView(SelectNoticeActivity.this);
            TextView tv_nb2 = new TextView(SelectNoticeActivity.this);

            String firstLine = "公告：" + nb.getNotice_title();
            tv_nb1.setText(firstLine);
            tv_nb1.setTextSize(20);
            tv_nb1.setBackgroundResource(R.drawable.tvborder_layout);

            SpannableStringBuilder ssb = new SpannableStringBuilder();
            String lightText = nb.getNotice_time() + "，点击查看详情";
            ssb.append(lightText);

            final String nt = nb.getNotice_time();
            final int nid = nb.getNotice_id();
            ClickableSpan cs = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    spu.setCNID(nid);
                    Intent infoIntent = new Intent(SelectNoticeActivity.this,
                            NoticeActivity.class);
                    startActivity(infoIntent);
                    Toast.makeText(SelectNoticeActivity.this, "查看" + nt + "的社区公告",
                            Toast.LENGTH_LONG).show();
                }
            };

            ssb.setSpan(cs, lightText.length() - 4, lightText.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            tv_nb2.setText(ssb);
            tv_nb2.setMovementMethod(LinkMovementMethod.getInstance());

            tv_nb2.setTextSize(20);
            tv_nb2.setBackgroundResource(R.drawable.tvborder_layout);
//            Log.d(TAG, "00002" + rb.getCommunity_id() + rb.getResident_name());
            ll_notice.addView(tv_nb1);
            ll_notice.addView(tv_nb2);
        }
    }
}
