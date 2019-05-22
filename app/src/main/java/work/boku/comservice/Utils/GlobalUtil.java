package work.boku.comservice.Utils;

import android.content.Context;

import work.boku.comservice.classes.ResidentDatabaseHelper;

public class GlobalUtil {
    private static GlobalUtil instance;
    private Context context;

    public ResidentDatabaseHelper databaseHelper;

    public static GlobalUtil getInstance() {
        if (instance == null) {
            instance = new GlobalUtil();
        }
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
        databaseHelper = new ResidentDatabaseHelper(context, ResidentDatabaseHelper.DB_NAME, null, 1);
    }
}
