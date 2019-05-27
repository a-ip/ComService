package work.boku.comservice.Utils;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import work.boku.comservice.classes.ResidentBean;

public class GlobalUtil extends AppCompatActivity {

    public GlobalUtil() {
    }



    public int isEmptyInsert(ResidentBean rb) {
        if (TextUtils.isEmpty(rb.getIdentity_number())) {
            return 1;
        }
        if (TextUtils.isEmpty(rb.getResident_name())) {
            return 2;
        }
        if (TextUtils.isEmpty(rb.getPhone_number())) {
            return 3;
        }
        return 0;
    }
}
