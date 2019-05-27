package work.boku.comservice.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import work.boku.comservice.Utils.SharedPreferencesUtil;

public class BaseActivity extends AppCompatActivity {

    protected SharedPreferencesUtil sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = SharedPreferencesUtil.getInstance(getApplicationContext());
    }
}

