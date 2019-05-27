package work.boku.comservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import work.boku.comservice.activities.AddActivity;
import work.boku.comservice.classes.ResidentDBHelper;

public class MainActivity extends AppCompatActivity {

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

        Button bt_select = findViewById(R.id.bt_select);

        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResidentDBHelper dbHelper = new ResidentDBHelper(MainActivity.this);
                int i = dbHelper.selectAllResident().size();
                Toast.makeText(MainActivity.this, "数据库中共有" + i + "个数据", Toast.LENGTH_LONG).show();
            }
        });
    }
}
