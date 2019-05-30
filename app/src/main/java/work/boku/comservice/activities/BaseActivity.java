package work.boku.comservice.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import work.boku.comservice.Utils.SPUtil;
import work.boku.comservice.classes.DBHelper;

public class BaseActivity extends AppCompatActivity {

    protected SPUtil spu;
    protected DBHelper dbh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // spu：SPUtil类继承SharedPreference类所生成的对象，
        // 利用SharedPreference保存应用状态和部分数值
        spu = SPUtil.getInstance(getApplicationContext());
        // dbh：DBHelper数据库操作类的对象，用于调用数据库
        dbh = new DBHelper(this);
    }
}

