package com.bjl.tannum.c_one.Database;

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
