package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import work.boku.comservice.R;
import work.boku.comservice.classes.DBHelper;

public class SplashActivity extends BaseActivity {

    private final static String TAG = "SplashActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                new DBHelper(SplashActivity.this);
//                Log.d(TAG, "run: 00001 value =" + i);
                Intent mainIntent;
//                Log.d(TAG, "run: 00002 spu value =" + Boolean.toString(spu.isFirstUse()));
                if (spu.isFirstUse()) {
                    mainIntent = new Intent(SplashActivity.this, AddActivity.class);
                    Toast.makeText(SplashActivity.this, R.string.first_use, Toast.LENGTH_LONG).show();
                } else {
                    mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                }
//                Log.d(TAG, "run: 00003");
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
//                Log.d(TAG, "run: 00004");
            }
        }, 2500L);
    }

}
