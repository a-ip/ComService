package work.boku.comservice.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import work.boku.comservice.R;
import work.boku.comservice.Utils.GlobalUtil;
import work.boku.comservice.classes.ResidentBean;
import work.boku.comservice.classes.ResidentDBHelper;

public class AddActivity extends AppCompatActivity {

    private EditText et_identity_number;
    private EditText et_name;
    private EditText et_phone_number;

    ResidentBean rb = new ResidentBean();
    ResidentDBHelper rDBHelper = new ResidentDBHelper(this);
    GlobalUtil gu = new GlobalUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_identity_number = findViewById(R.id.et_insert_identity_number);
        et_name = findViewById(R.id.et_insert_name);
        et_phone_number = findViewById(R.id.et_insert_phone_number);

        Button btAddResident = findViewById(R.id.bt_insert_end);
        btAddResident.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String in = et_identity_number.getText().toString();
                String rn = et_name.getText().toString();
                String pn = et_phone_number.getText().toString();
                rb.setIdentity_number(in);
                Log.d(in, "Identity_number");
                rb.setResident_name(rn);
                Log.d(rn, "Resident_name");
                rb.setPhone_number(pn);
                Log.d(pn, "Phone_number");
                int e = gu.isEmptyInsert(rb);
                Log.d("eeeee = " + e, "/eeeee");
                switch (e) {
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
                        rDBHelper.addResident(rb);
                        Toast.makeText(AddActivity.this, R.string.insert_succeed, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
