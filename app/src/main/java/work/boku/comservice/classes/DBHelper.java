package work.boku.comservice.classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "community.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    // 建表
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_SQL_R = "CREATE TABLE IF NOT EXISTS Resident" +
                "(community_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "passwd TEXT NOT NULL DEFAULT '123456', " +
                "identity_number TEXT NOT NULL, " +
                "resident_name TEXT NOT NULL, " +
                "phone_number TEXT NOT NULL, " +
                "permission_level INTEGER DEFAULT 0)";
        db.execSQL(CREATE_SQL_R);

        final String CREATE_SQL_N = "CREATE TABLE IF NOT EXISTS notice" +
                "(notice_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "notice_time TEXT, " +
                "notice_title TEXT, " +
                "notice_content TEXT)";
        db.execSQL(CREATE_SQL_N);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // 添加第一名居民（系统管理员）
    public void addResidentWithPL(ResidentBean rb, int pl) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String FIRST_SQL_R1 = "INSERT INTO resident" +
                "(identity_number, resident_name, phone_number, permission_level)VALUES('" +
                rb.getIdentity_number() + "', '" +
                rb.getResident_name() + "', '" +
                rb.getPhone_number() + "', " +
                pl + ")";
        db.execSQL(FIRST_SQL_R1);
    }

    // 向居民表中添加数据
    public void addFullResident(ResidentBean rb) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String INSERT_SQL_R2 = "INSERT INTO resident(community_id, passwd, identity_number, " +
                "resident_name, phone_number, permission_level) VALUES(" +
                rb.getCommunity_id() + " , '" + rb.getPasswd() + "', '" +
                rb.getIdentity_number() + " ', '" + rb.getResident_name() + "', '" +
                rb.getPhone_number() + "', " + rb.getPermission_level() + ")";
        db.execSQL(INSERT_SQL_R2);
    }

    // 删除
    public void deleteResident(int cid) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String DELETE_SQL_R = "DELETE FROM resident WHERE community_id = " + cid;
        db.execSQL(DELETE_SQL_R);
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
        final String SELECT_ALL_SQL_R = "SELECT * FROM resident";
        Cursor cur = db.rawQuery(SELECT_ALL_SQL_R, null);
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
        final String SELECT_SQL_R = "SELECT * FROM resident WHERE community_id = " + cid;
        Cursor cur = db.rawQuery(SELECT_SQL_R, null);
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

    public void addNotice(NoticeBean nb) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String INSERT_SQL_N1 = "INSERT INTO notice (notice_time, notice_title, " +
                "notice_content) VALUES(" +
                "DATETIME('NOW', 'LOCALTIME'), '" +
                nb.getNotice_title() + "', '" +
                nb.getNotice_content() + "')";
        db.execSQL(INSERT_SQL_N1);
    }

    public void addNoticeWithNT(NoticeBean nb) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String INSERT_SQL_N2 = "INSERT INTO notice (notice_time, " +
                "notice_title, notice_content) VALUES('" +
                nb.getNotice_time() + "', '" +
                nb.getNotice_title() + "', '" +
                nb.getNotice_content() + "')";
        db.execSQL(INSERT_SQL_N2);
    }

    public void addFullNotice(NoticeBean nb) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String INSERT_SQL_N3 = "INSERT INTO notice (notice_id, notice_time, " +
                "notice_title, notice_content) VALUES(" +
                nb.getNotice_id() + ", '" +
                nb.getNotice_time() + "', '" +
                nb.getNotice_title() + "', '" +
                nb.getNotice_content() + "')";
        db.execSQL(INSERT_SQL_N3);
    }

    // 删除公告
    public void deleteNotice(int nid) {
        SQLiteDatabase db = this.getWritableDatabase();
        final String DELETE_SQL_N = "DELETE FROM notice WHERE notice_id =" + nid;
        db.execSQL(DELETE_SQL_N);
    }

    // 更改公告
    public void updateNotice(NoticeBean nb) {
        deleteNotice(nb.getNotice_id());
        addFullNotice(nb);
    }

    // 查询公告表中所有数据，返回为ArrayList<ResidentBean>形式的数组
    public ArrayList<NoticeBean> selectAllNotice() {
        ArrayList<NoticeBean> nbList = new ArrayList<NoticeBean>();
        SQLiteDatabase db = this.getWritableDatabase();
        final String SELECT_ALL_SQL_N = "SELECT * FROM notice";
        Cursor cur = db.rawQuery(SELECT_ALL_SQL_N, null);
        if (cur.moveToFirst()) {
            do {
                int nid = cur.getInt(cur.getColumnIndex("notice_id"));
                String nt = cur.getString(cur.getColumnIndex("notice_time"));
                String nl = cur.getString(cur.getColumnIndex("notice_title"));
                String nc = cur.getString(cur.getColumnIndex("notice_content"));

                NoticeBean nb = new NoticeBean();
                nb.setNotice_id(nid);
                nb.setNotice_time(nt);
                nb.setNotice_title(nl);
                nb.setNotice_content(nc);

                nbList.add(nb);
            } while (cur.moveToNext());
            cur.close();
        }
        return nbList;
    }

    // 按公告编号在公告表中查找公告，返回一个NoticeBean类的对象
    public NoticeBean selectNotice(int nid) {
        SQLiteDatabase db = this.getWritableDatabase();
        NoticeBean nb = new NoticeBean();
        final String SELECT_SQL_R = "SELECT * FROM notice WHERE notice_id = " + nid;
        Cursor cur = db.rawQuery(SELECT_SQL_R, null);
        if (cur.moveToFirst()) {
            do {
                String nt = cur.getString(cur.getColumnIndex("notice_time"));
                String nl = cur.getString(cur.getColumnIndex("notice_title"));
                String nc = cur.getString(cur.getColumnIndex("notice_content"));

                nb.setNotice_id(nid);
                nb.setNotice_time(nt);
                nb.setNotice_title(nl);
                nb.setNotice_content(nc);
            } while (cur.moveToNext());
            cur.close();
        }
        return nb;
    }
}
