package hlub.com.quanlythuchi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
    //b1:định nghĩa các thông tin DB
    public final String DB_NAME="QuanLyThuChi";
    public final String TB_LOAITHU="LoaiThu";
    public final String TB_LOAICHI="LoaiChi";
    public final String TB_LOAIKHOANTHU="LoaiKhoanThu";
    public final String TB_LOAIKHOANCHI="LoaiKhoanChi";
    public final int DB_VERSION=1;

    //kb tp giúp sửa, xóa DB
    private SQLiteDatabase database;

    //b2:xd lớp SQLLiteOpenHelper để quản lý việc tạo bảng
    public class OpenHelper extends SQLiteOpenHelper{
        public OpenHelper (Context context){ super(context, DB_NAME, null,DB_VERSION);}
        public Cursor getData(String sql){
            SQLiteDatabase database=getReadableDatabase();
            return database.rawQuery(sql,null);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            //tạo bảng
            String lenhTaoBangLoaiThu="CREATE TABLE IF NOT EXISTS LoaiThu(_id INTEGER PRIMARY KEY AUTOINCREMENT, LoaiThu TEXT) ";
            String lenhTaoBangLoaiChi="CREATE TABLE IF NOT EXISTS LoaiChi(_id INTEGER PRIMARY KEY AUTOINCREMENT, LoaiChi TEXT) ";
            String lenhTaoBangLoaiKhoanThu="CREATE TABLE IF NOT EXISTS LoaiKhoanThu(_id INTEGER PRIMARY KEY AUTOINCREMENT, LoaiThu TEXT, KhoanThu TEXT)";
            String lenhTaoBangLoaiKhoanChi="CREATE TABLE IF NOT EXISTS LoaiKhoanChi(_id INTEGER PRIMARY KEY AUTOINCREMENT, LoaiChi TEXT, KhoanChi TEXT)";
            db.execSQL(lenhTaoBangLoaiThu);
            db.execSQL(lenhTaoBangLoaiChi);
            db.execSQL(lenhTaoBangLoaiKhoanThu);
            db.execSQL(lenhTaoBangLoaiKhoanChi);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //tạo bảng mới
            db.execSQL("DROP TABLE IF EXISTS LoaiThu");
            db.execSQL("DROP TABLE IF EXISTS LoaiChi");
            db.execSQL("DROP TABLE IF EXISTS LoaiKhoanThu");
            db.execSQL("DROP TABLE IF EXISTS LoaiKhoanChi");
            onCreate(db);
        }
    }

    //b3:xây dựng các pt làm việc với DB
    //insert
    public void insertLoaiThu(String loaiThu){
        ContentValues values=new ContentValues();
        values.put("LoaiThu",loaiThu);
        database.insert(TB_LOAITHU,null,values);
    }
    public void insertLoaiChi(String loaiChi){
        ContentValues values=new ContentValues();
        values.put("LoaiChi",loaiChi);
        database.insert(TB_LOAICHI,null,values);
    }
    public void insertLoaiKhoanThu(String loaiThu, String khoanThu){
        ContentValues values=new ContentValues();
        values.put("LoaiThu",loaiThu);
        values.put("KhoanThu",khoanThu);
        database.insert(TB_LOAIKHOANTHU,null,values);
    }
    public void insertLoaiKhoanChi(String loaiChi, String khoanChi){
        ContentValues values=new ContentValues();
        values.put("LoaiChi",loaiChi);
        values.put("KhoanChi",khoanChi);
        database.insert(TB_LOAIKHOANCHI,null,values);
    }

    //UPDATE
    public void updateLoaiThu(String loaiThu, int id){
        ContentValues values=new ContentValues();
        values.put("LoaiThu", loaiThu);
        database.update(TB_LOAITHU,values,"_id="+id,null);
    }
    public void updateLoaiChi(String loaiChi, int id){
        ContentValues values=new ContentValues();
        values.put("LoaiChi", loaiChi);
        database.update(TB_LOAICHI,values,"_id="+id,null);
    }
    public void updateLoaiKhoanThu(String loaiThu,String khoanThu, int id ){
        ContentValues values=new ContentValues();
        values.put("LoaiThu",loaiThu);
        values.put("KhoanThu",khoanThu);
        database.update(TB_LOAIKHOANTHU,values,"_id="+id,null);
    }
    public void updateLoaiKhoanChi(String loaiChi,String khoanChi, int id ){
        ContentValues values=new ContentValues();
        values.put("LoaiChi",loaiChi);
        values.put("KhoanChi",khoanChi);
        database.update(TB_LOAIKHOANCHI,values,"_id="+id,null);
    }

    //delete
    public void deleteLoaiThu(int id){
        database.delete(TB_LOAITHU,"_id="+id,null);
    }
    public void deleteLoaiChi(int id){
        database.delete(TB_LOAICHI,"_id="+id,null);
    }
    public void deleteLoaiKhoanThu(int id){
        database.delete(TB_LOAIKHOANTHU,"_id="+id,null);
    }
    public void deleteLoaiKhoanChi(int id){
        database.delete(TB_LOAIKHOANCHI,"_id="+id,null);
    }

    //select
    public Cursor getLoaiThu(){
        return database.query(TB_LOAITHU,
                null,
                null,
                null,
                null,
                null,
                null);
    }
    public Cursor getLoaiChi(){
        return database.query(TB_LOAICHI,
                null,
                null,
                null,
                null,
                null,
                null);
    }
    public Cursor getLoaiKhoanThu(){
        return database.query(TB_LOAIKHOANTHU,
                null,
                null,
                null,
                null,
                null,
                null);
    }
    public Cursor getLoaiKhoanChi(){
        return database.query(TB_LOAIKHOANCHI,
                null,
                null,
                null,
                null,
                null,
                null);
    }


    //B4:XD pt khởi tạo DB
    public DatabaseManager(Context context){
        OpenHelper helper=new OpenHelper(context);
        database=helper.getWritableDatabase();
    }

}
