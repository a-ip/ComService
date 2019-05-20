package work.boku.comservice.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import work.boku.comservice.R;
import work.boku.comservice.classes.ResidentBean;
import work.boku.comservice.classes.ResidentDatabaseHelper;

public class AddActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_community_id;
    private EditText et_identity_number;
    private EditText et_name;
    private EditText et_phone_number;

    ResidentBean bean = new ResidentBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_community_id = findViewById(R.id.et_insert_community_id);
        et_identity_number = findViewById(R.id.et_insert_identity_number);
        et_name = findViewById(R.id.et_insert_name);
        et_phone_number = findViewById(R.id.et_insert_phone_number);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_insert_end:
                new ResidentDatabaseHelper(this,);
                break;
        }
    }
}
