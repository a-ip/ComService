package work.boku.comservice.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import work.boku.comservice.R;
import work.boku.comservice.Utils.MyAdapter;
import work.boku.comservice.classes.ResidentBean;

public class SelectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        RecyclerView rv_sa = findViewById(R.id.rv_select);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_sa.setHasFixedSize(true);
        ArrayList<String> strList = new ArrayList<String>();
        ArrayList<ResidentBean> rbList = rDBh.selectAllResident();
        for (int i = 0; i < rbList.size(); i++) {
            strList.add(rbList.get(i).getResident_name());
            Log.d(rbList.get(i).getResident_name(), "debug-111");
        }
        MyAdapter listAdapter = new MyAdapter(this, strList);
        rv_sa.setAdapter(listAdapter);

        Button bt_manage = findViewById(R.id.bt_manage);
        bt_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = rDBh.selectAllResident().size();
                Toast.makeText(SelectActivity.this, "数据库中共有" + i + "个数据", Toast.LENGTH_LONG).show();
            }
        });
    }
}
