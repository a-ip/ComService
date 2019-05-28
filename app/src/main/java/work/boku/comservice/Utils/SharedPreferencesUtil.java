package work.boku.comservice.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by smile on 02/03/2019.
 */

public class SharedPreferencesUtil {
    private static final String TAG = "TAG";
    private static final String KEY_FIRST = "KEY_FIRST";
    private static final String KEY_LEVEL = "KEY_LEVEL";

    private static SharedPreferences mySP;
    private static SharedPreferences.Editor mySPE;
    private static SharedPreferencesUtil mySPU;
    private Context context;

    public SharedPreferencesUtil(Context context) {
        this.context = context.getApplicationContext();
        mySP = this.context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        mySPE = mySP.edit();
    }

    /**
     * 单例简单实现（因为Android中没有Java Web那样的高并发量，所以单例可以这样简单实现）
     *
     * @param context
     * @return
     */
    public static SharedPreferencesUtil getInstance(Context context) {
        if (mySPU == null) {
            mySPU = new SharedPreferencesUtil(context);
        }
        return mySPU;
    }

    // 判断是否为第一次登录，默认设置为是
    public boolean isFirst() {
        return getBoolean(KEY_FIRST, true);
    }

    // 修改第一次登录状态
    public void setFirst(boolean value) {
        putBoolean(KEY_FIRST, value);
    }

    // 判断用户权限，默认设置为0
    public int getLevel() {
        return getInt(KEY_LEVEL, 0);
    }

    // 修改用户权限
    public void setLevel(int value) {
        putInt(KEY_LEVEL, value);
    }

    // 私有方法
    private void put(String key, String value) {
        mySPE.putString(key, value);
        mySPE.commit();
    }

    private String get(String key) {
        return mySP.getString(key, "");
    }

    private void putBoolean(String key, boolean value) {
        mySPE.putBoolean(key, value);
        mySPE.commit();
    }

    private boolean getBoolean(String key, boolean defaultValue) {
        return mySP.getBoolean(key, defaultValue);
    }


    private void putInt(String key, int value) {
        mySPE.putInt(key, value);
        mySPE.apply();
    }

    private int getInt(String key, int defaultValue) {
        return mySP.getInt(key, defaultValue);
    }
}
