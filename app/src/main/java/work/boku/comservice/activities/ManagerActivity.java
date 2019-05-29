package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import work.boku.comservice.R;

public class ManagerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Button bt_add = findViewById(R.id.bt_add);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        Button bt_select = findViewById(R.id.bt_to_select);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}
