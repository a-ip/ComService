package work.boku.comservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import work.boku.comservice.activities.AddActivity;
import work.boku.comservice.activities.BaseActivity;
import work.boku.comservice.classes.ResidentBean;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_add = findViewById(R.id.bt_add);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);

                startActivity(intent);
            }
        });

        ResidentBean rb = new ResidentBean();

    }
}
