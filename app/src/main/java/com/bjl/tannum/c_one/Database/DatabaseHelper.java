package com.bjl.tannum.c_one.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bjl.tannum.c_one.Model.DataInfo.ProductInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tannum on 12/7/2016 AD.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "C_ONE.db";
    public static final String DBLOCATION = "/data/data/com.bjl.tannum.c_one/databases/";

    public static final String tb_user_login = "user_login";
    public static final String col_id   = "id";
    public static final String col_username = "user_name";
    public static final String col_active = "active";


    //tb_login_info
    //public static final String tb_login_info = "tb_login_info";





    private Context mContext;
    private SQLiteDatabase mDatabase;


    public DatabaseHelper(Context context){
        super(context,DBNAME,null,1);
        this.mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath,null, SQLiteDatabase.OPEN_READWRITE);

    }
    public void closeDatabase(){
        if(mDatabase != null){
            mDatabase.close();
        }
    }


    public Cursor SqlSelect(String sql){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(sql,null);
        return  res;
    }


    public boolean InsertData_tbLoginInfo(int id , String username , int active ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_id,id);
        contentValues.put(col_username,username);
        contentValues.put(col_active,active);
        long result = db.insert(tb_user_login,null,contentValues);
        if(result == (-1)){
            return false;
        }
        return true;
    }

    public boolean UpdateData_tbLoginInfo(int id , String user_name , int active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_id,id);
        contentValues.put(col_username,user_name);
        contentValues.put(col_active,active);
        db.update(tb_user_login,contentValues,"id = ?",new String[]{String.valueOf(id)});
        return true;
    }



    public List<ProductInfo> getListProduct(){
        ProductInfo productInfo = null;
        List<ProductInfo> productInfos = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM Product",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            productInfo = new ProductInfo(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));
            productInfos.add(productInfo);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return  productInfos;
    }


}
