package work.boku.comservice.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import work.boku.comservice.Utils.SPUtil;
import work.boku.comservice.classes.ResidentDBHelper;

public class BaseActivity extends AppCompatActivity {

    protected SPUtil spu;
    protected ResidentDBHelper rDBh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spu = SPUtil.getInstance(getApplicationContext());
        rDBh = new ResidentDBHelper(this);
    }
}

