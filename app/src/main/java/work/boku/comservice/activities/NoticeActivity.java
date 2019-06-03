package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import work.boku.comservice.R;
import work.boku.comservice.Utils.JavaUtil;
import work.boku.comservice.classes.NoticeBean;

public class NoticeActivity extends BaseActivity {

    public static final String TAG = "NoticeActivity";

    private TextView tv_nid;
    private TextView tv_nt;
    private TextView tv_nl;
    private TextView tv_nc;
    private EditText et_nl;
    private EditText et_nc;
    private ViewSwitcher vs_nl;
    private ViewSwitcher vs_nc;
    private Button bt_update_notice;
    private LinearLayout ll_update_notice;
    private Button bt_save_notice;
    private Button bt_delete_notice;

    private NoticeBean nb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        nb = dbh.selectNotice(spu.getCNID());

        tv_nid = findViewById(R.id.tv_nid);
        tv_nt = findViewById(R.id.tv_nt);
        tv_nl = findViewById(R.id.tv_nl);
        tv_nc = findViewById(R.id.tv_nc);
        et_nl = findViewById(R.id.et_nl);
        et_nc = findViewById(R.id.et_nc);
        vs_nl = findViewById(R.id.vs_nl);
        vs_nc = findViewById(R.id.vs_nc);

        bt_update_notice = findViewById(R.id.bt_update_notice);

        ll_update_notice = findViewById(R.id.ll_update_notice);
        bt_save_notice = findViewById(R.id.bt_save_notice);
        bt_delete_notice = findViewById(R.id.bt_delete_notice);

        ll_update_notice.setVisibility(View.INVISIBLE);
        // 非管理员不可修改公告
        if (spu.getMyPL() == 0) {
            bt_update_notice.setVisibility(View.GONE);
        }

        final String nid = String.valueOf(nb.getNotice_id());
        final String nt = nb.getNotice_time();
        final String nl = nb.getNotice_title();
        final String nc = nb.getNotice_content();

        tv_nid.setText(nid);
        tv_nt.setText(nt);
        tv_nl.setText(nl);
        tv_nc.setText(nc);

        et_nl.setText(nl);
        et_nc.setText(nc);

        bt_update_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vs_nl.showNext();
                vs_nc.showNext();

                bt_update_notice.setVisibility(View.GONE);
                ll_update_notice.setVisibility(View.VISIBLE);
            }
        });

        bt_save_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newNid = Integer.parseInt(nid);
                String newNT = JavaUtil.getNow();
                String newNL = et_nl.getText().toString().trim();
                String newNC = et_nc.getText().toString().trim();

                NoticeBean newNB = new NoticeBean();
                newNB.setNotice_id(newNid);
                newNB.setNotice_time(newNT);
                newNB.setNotice_title(newNL);
                newNB.setNotice_content(newNC);

                dbh.updateNotice(newNB);

                Toast.makeText(NoticeActivity.this, R.string.update_succeed,
                        Toast.LENGTH_SHORT).show();
                SelectNoticeActivity.instance.finish();
                Intent selectIntent = new Intent(NoticeActivity.this,
                        SelectNoticeActivity.class);
                startActivity(selectIntent);
                NoticeActivity.this.finish();
            }
        });

        bt_delete_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.deleteNotice(nb.getNotice_id());
                Toast.makeText(NoticeActivity.this, R.string.delete_succeed,
                        Toast.LENGTH_SHORT).show();
                SelectNoticeActivity.instance.finish();
                Intent selectIntent = new Intent(NoticeActivity.this,
                        SelectNoticeActivity.class);
                startActivity(selectIntent);
                NoticeActivity.this.finish();
            }
        });
    }
}
