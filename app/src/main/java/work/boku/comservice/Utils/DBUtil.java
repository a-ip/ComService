package work.boku.comservice.Utils;

import android.content.Context;

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


}
