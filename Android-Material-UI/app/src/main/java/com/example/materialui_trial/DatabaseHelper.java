package com.example.materialui_trial;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="ProjectDB";
    private static final String DATABASE_VERSION="1";
    public static final String TABLE_NAME="Comments";
    public static final String ID="id";
    public static final String Subject="subject";



    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create TABLE "+TABLE_NAME+"("+ID+"integer primary key autoincrement,"+ Subject +"text not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);

    }

}
