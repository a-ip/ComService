package work.boku.comservice.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import work.boku.comservice.R;
import work.boku.comservice.Utils.JavaUtil;
import work.boku.comservice.classes.NoticeBean;

public class AddNoticeActivity extends BaseActivity {

    private EditText et_insert_nl;
    private EditText et_insert_nc;
    private Button bt_insert_notice_end;

    private NoticeBean nb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        et_insert_nl = findViewById(R.id.et_insert_nl);
        et_insert_nc = findViewById(R.id.et_insert_nc);
        bt_insert_notice_end = findViewById(R.id.bt_insert_notice_end);

        bt_insert_notice_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nl = et_insert_nl.getText().toString().trim();
                String nc = et_insert_nc.getText().toString().trim();
                String nt = JavaUtil.getNow();

                nb = new NoticeBean();
                nb.setNotice_time(nt);
                nb.setNotice_title(nl);
                nb.setNotice_content(nc);

                et_insert_nl.setText(null);
                et_insert_nc.setText(null);

                dbh.newNotice(nb);

                Toast.makeText(AddNoticeActivity.this, R.string.insert_succeed,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
