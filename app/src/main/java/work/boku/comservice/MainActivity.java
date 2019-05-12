package work.boku.comservice;

import android.os.Bundle;

import work.boku.comservice.activities.BaseActivity;
import work.boku.comservice.classes.ResidentBean;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResidentBean bean = new ResidentBean();

    }
}
