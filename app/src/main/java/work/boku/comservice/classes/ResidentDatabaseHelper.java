package work.boku.comservice.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

public class ResidentDatabaseHelper extends SQLiteOpenHelper {
    private static String TAG = "ResidentDatabaseHelper";

    public static final String DB_NAME = "Resident";

    public static final String CREATE_RESIDENT_DB = "create table community (" +
            "uuid text primary key, " +
            "community_id text, " +
            "identity_number text, " +
            "name text, " +
            "phone_number text" +
            ")";

    public ResidentDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RESIDENT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 数据库增
    public void addResident(ResidentBean rb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("community_id", rb.getCommunity_id());
        values.put("identity_number", rb.getIdentity_number());
        values.put("name", rb.getName());
        values.put("phone_number", rb.getPhone_number());
        db.insert(DB_NAME, null, values);
    }

    // 数据库删（按社区ID）
    public void removeResident(String community_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_NAME, "community_id = ?", new String[]{community_id});
    }

    // 数据库改
    public void editResident(String community_id, ResidentBean rb) {
        removeResident(community_id);
        rb.setCommunity_id(community_id);
        addResident(rb);
    }

    // 数据库查（按社区ID遍历）
    public LinkedList<ResidentBean> readResident(String cid) {
        LinkedList<ResidentBean> rbll = new LinkedList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCE * from Community where community_id = ?", new String[]{cid});

        if (cursor.moveToFirst()) {
            do {
                String community_id = cursor.getString(cursor.getColumnIndex("community_id"));
                String identity_number = cursor.getString(cursor.getColumnIndex("identity_number"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String phone_number = cursor.getString(cursor.getColumnIndex("phone_number"));

                ResidentBean resident = new ResidentBean();

                resident.setCommunity_id(community_id);
                resident.setIdentity_number(identity_number);
                resident.setName(name);
                resident.setPhone_number(phone_number);

                rbll.add(resident);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return rbll;
    }

}
