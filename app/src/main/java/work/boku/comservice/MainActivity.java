package work.boku.comservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import work.boku.comservice.classes.ResidentBean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResidentBean bean = new ResidentBean();
    }
}
