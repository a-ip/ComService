package work.boku.comservice.Utils;

import android.content.Context;

import java.util.ArrayList;

import work.boku.comservice.classes.ResidentBean;
import work.boku.comservice.classes.ResidentDBHelper;

public class DBUtil {

    private ResidentDBHelper rDBh;

    private final Context context;

    public static DBUtil myDBUtil;

    public DBUtil(Context context) {
        this.context = context.getApplicationContext();
    }

    public static DBUtil getInstance(Context context) {
        if (myDBUtil == null) {
            myDBUtil = new DBUtil(context);
        }
        return myDBUtil;
    }

    // 返回居民表中的值的个数
    public int countResidentDB(Context context) {
        rDBh = new ResidentDBHelper(context);
        return rDBh.selectAllResident().size();
    }

    // 返回居民数组ArrayList<ResidentBean>的值的个数
    public static int countResident(ArrayList<ResidentBean> rbList) {
        return rbList.size();
    }
}
