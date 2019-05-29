package work.boku.comservice.classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ResidentDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "resident.db";
//    private static final String TB_NAME = "Resident";

    public ResidentDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    // 建表
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS Resident" +
                "(community_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "passwd TEXT NOT NULL DEFAULT '123456', " +
                "identity_number TEXT NOT NULL, " +
                "resident_name TEXT NOT NULL, " +
                "phone_number TEXT NOT NULL, " +
                "permission_level INTEGER DEFAULT 0)";
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // 添加第一名居民（系统管理员）
    public void addWithPL(ResidentBean rb, int pl) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String FIRST_SQL = "INSERT INTO Resident" +
                "(identity_number, resident_name, phone_number, permission_level)VALUES('" +
                rb.getIdentity_number() + "', '" +
                rb.getResident_name() + "', '" +
                rb.getPhone_number() + "', " +
                pl + ")";
        db.execSQL(FIRST_SQL);
    }

    // 向居民表中添加数据
    public void addFullResident(ResidentBean rb) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String INSERT_SQL = "INSERT INTO Resident(community_id, passwd, identity_number, " +
                "resident_name, phone_number, permission_level) VALUES(" +
                rb.getCommunity_id() + " , '" + rb.getPasswd() + "', '" +
                rb.getIdentity_number() + " ', '" + rb.getResident_name() + "', '" +
                rb.getPhone_number() + "', " + rb.getPermission_level() + ")";
        db.execSQL(INSERT_SQL);
    }

    // 删除
    public void deleteResident(int cid) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String DELETE_SQL = "DELETE FROM Resident WHERE community_id = " + cid;
        db.execSQL(DELETE_SQL);
    }

    // 更改居民表中数据
    public void updateResident(ResidentBean rb) {
        deleteResident(rb.getCommunity_id());
        addFullResident(rb);
    }

    // 查询居民表中所有数据，返回为ArrayList<ResidentBean>形式的数组
    public ArrayList<ResidentBean> selectAllResident() {
        ArrayList<ResidentBean> rbList = new ArrayList<ResidentBean>();
        SQLiteDatabase db = this.getWritableDatabase();
        final String SELECT_SQL = "SELECT * FROM Resident";
        Cursor cur = db.rawQuery(SELECT_SQL, null);
        if (cur.moveToFirst()) {
            do {
                int cid = cur.getInt(cur.getColumnIndex("community_id"));
                String pw = cur.getString(cur.getColumnIndex("passwd"));
                String in = cur.getString(cur.getColumnIndex("identity_number"));
                String rn = cur.getString(cur.getColumnIndex("resident_name"));
                String pn = cur.getString(cur.getColumnIndex("phone_number"));
                int pl = cur.getInt(cur.getColumnIndex("permission_level"));

                ResidentBean rb = new ResidentBean();
                rb.setCommunity_id(cid);
                rb.setPasswd(pw);
                rb.setIdentity_number(in);
                rb.setResident_name(rn);
                rb.setPhone_number(pn);
                rb.setPermission_level(pl);

                rbList.add(rb);
            } while (cur.moveToNext());
            cur.close();
        }
        return rbList;
    }

    // 按社区编号在居民表中查找居民，返回一个ResidentBean类的对象
    public ResidentBean selectResident(int cid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ResidentBean rb = new ResidentBean();
        final String SELECT_SQL = "SELECT * FROM Resident WHERE community_id = " + cid;
        Cursor cur = db.rawQuery(SELECT_SQL, null);
        if (cur.moveToFirst()) {
            do {
                String pw = cur.getString(cur.getColumnIndex("passwd"));
                String in = cur.getString(cur.getColumnIndex("identity_number"));
                String rn = cur.getString(cur.getColumnIndex("resident_name"));
                String pn = cur.getString(cur.getColumnIndex("phone_number"));
                int pl = cur.getInt(cur.getColumnIndex("permission_level"));

                rb.setCommunity_id(cid);
                rb.setPasswd(pw);
                rb.setIdentity_number(in);
                rb.setResident_name(rn);
                rb.setPhone_number(pn);
                rb.setPermission_level(pl);
            } while (cur.moveToNext());
            cur.close();
        }
        return rb;
    }

    // 返回居民表中总数
    public int countDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        final String COUNT_SQL = "SELECT COUNT(*) FROM Resident";
        Cursor cur = db.rawQuery(COUNT_SQL, null);
        cur.moveToFirst();
        int dbCount = cur.getInt(0);
        cur.close();
        return dbCount;
    }
}
