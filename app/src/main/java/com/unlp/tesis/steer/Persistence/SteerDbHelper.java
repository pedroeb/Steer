package com.unlp.tesis.steer.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pedro on 14/10/16.
 */

public class SteerDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Steer.db";

    private static SteerDbHelper sInstance;

    public SteerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Nos aseguramos de que solo haya una instancia para evitar errores.
     * Mas detalles:
     * http://www.androiddesignpatterns.com/2012/05/correctly-managing-your-sqlite-database.html
     */
    public static synchronized SteerDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SteerDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SteerContract.PaidParkingArea.SQL_CREATE_PAIDPARKINGAREA_TABLE);
        db.execSQL(SteerContract.Alert.SQL_CREATE_ALERT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SteerContract.PaidParkingArea.SQL_CREATE_PAIDPARKINGAREA_TABLE);
        db.execSQL(SteerContract.Alert.SQL_CREATE_ALERT_TABLE);
        onCreate(db);
    }
}
