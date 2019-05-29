package work.boku.comservice.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {
    private static final String TAG = "SPUtil";
    private static final String KEY_FIRST_USE = "KEY_FIRST_USE"; // 是否为第一次使用应用

    private static final String KEY_FIRST_LOGIN = "KEY_FIRST_LOGIN"; // 是否为第一次登录

    // 当前用户个人信息
    private static final String KEY_CID = "KEY_CID"; // 社区编号（用户名）

    private static final String KEY_PW = "KEY_PW"; // 密码

    private static final String KEY_IN = "KEY_IN"; // 身份证号
    private static final String KEY_RN = "KEY_RN"; // 居民姓名
    private static final String KEY_PN = "KEY_PN"; // 手机号码

    private static final String KEY_PL = "KEY_LEVEL"; // 权限等级

    private static final String KEY_CCID = "KEY_CCID"; // 当前用户选中的居民的社区编号
    private static final String KEY_CNID = "KEY_CNID"; // 当前用户选中的社区通知的通知编号

    private static SharedPreferences mySP;
    private static SharedPreferences.Editor mySPE;
    private static SPUtil mySPU;
    private Context context;

    public SPUtil(Context context) {
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
    public static SPUtil getInstance(Context context) {
        if (mySPU == null) {
            mySPU = new SPUtil(context);
        }
        return mySPU;
    }

    // 判断是否为第一次登录，默认设置为是
    public boolean isFirstUse() {
        return getBoolean(KEY_FIRST_USE, true);
    }

    // 修改第一次登录状态
    public void setFirstUse(boolean value) {
        putBoolean(KEY_FIRST_USE, value);
    }

    // 判断是否为第一次登录，默认设置为是
    public boolean isFirstLogin() {
        return getBoolean(KEY_FIRST_LOGIN, true);
    }

    // 修改第一次登录状态
    public void setFirstLogin(boolean value) {
        putBoolean(KEY_FIRST_LOGIN, value);
    }

    // 检查当前用户的社区编号，默认设置为0
    public int getMyCID() {
        return getInt(KEY_CID, 0);
    }

    // 修改当前用户的社区编号
    public void setMyCID(int value) {
        putInt(KEY_CID, value);
    }

    // 检查当前用户的密码，默认设置为123456
    public String getMyPW() {
        return getString(KEY_PW, "123456");
    }

    // 修改当前用户的密码
    public void setMyPW(String value) {
        putString(KEY_PW, value);
    }

    // 检查当前用户的身份证号，默认设置为空
    public String getMyIN() {
        return getString(KEY_IN, "");
    }

    // 修改当前用户的身份证号
    public void setMyIN(String value) {
        putString(KEY_IN, value);
    }

    // 检查当前用户的姓名，默认设置为空
    public String getMyRN() {
        return getString(KEY_RN, "");
    }

    // 修改当前用户的姓名
    public void setMyRN(String value) {
        putString(KEY_RN, value);
    }

    // 检查当前用户的手机号码，默认设置为空
    public String getMyPN() {
        return getString(KEY_PN, "");
    }

    // 修改当前用户的手机号码
    public void setMyPN(String value) {
        putString(KEY_PN, value);
    }

    // 检查当前用户权限，默认设置为0
    public int getMyPL() {
        return getInt(KEY_PL, 0);
    }

    // 修改当前用户权限
    public void setMyPL(int value) {
        putInt(KEY_PL, value);
    }

    // 检查当前用户所选择的用户的社区编号，默认设置为0
    public int getCCID() {
        return getInt(KEY_CCID, 0);
    }

    // 修改当前用户所选择的用户的社区编号
    public void setCCID(int value) {
        putInt(KEY_CCID, value);
    }

    // 检查当前用户所选择的用户的社区编号，默认设置为0
    public int getCNID() {
        return getInt(KEY_CNID, 0);
    }

    // 修改当前用户所选择的用户的社区编号
    public void setCNID(int value) {
        putInt(KEY_CNID, value);
    }

    // 私有方法
//    private void put(String key, String value) {
//        mySPE.putString(key, value);
//        mySPE.commit();
//    }
//
//    private String get(String key) {
//        return mySP.getString(key, "");
//    }

    private void putString(String key, String value) {
        mySPE.putString(key, value);
        mySPE.commit();
    }

    private String getString(String key, String defaultValue) {
        return mySP.getString(key, defaultValue);
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
        mySPE.commit();
    }

    private int getInt(String key, int defaultValue) {
        return mySP.getInt(key, defaultValue);
    }
}
