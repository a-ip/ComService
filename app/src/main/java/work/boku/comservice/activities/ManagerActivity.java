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

        Button bt_add = findViewById(R.id.bt_add_resident);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        Button bt_select = findViewById(R.id.bt_select);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });

        Button bt_add_notice = findViewById(R.id.bt_add_notice);
        bt_add_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, AddNoticeActivity.class);
                startActivity(intent);
            }
        });
    }
}
