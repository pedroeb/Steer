package com.unlp.tesis.steer.Persistence;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Contiene las constantes relacionadas con la base de datos. Nombres de tablas, nombres de columnas
 * , operaciones estandar como crear o eliminar tablas, etc.
 */
public final class SteerContract {

    public static final String CONTENT_AUTHORITY = "com.unlp.tesis.steer";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final String PATH_ALERTS = "alerts";
    private static final String PATH_PAIDPARKINGAREA = "paidParkingAreas";

    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String REAL_TYPE = " REAL";

    public static final String COMMA_SEP = ",";


    public SteerContract() { }

    public static abstract class PaidParkingArea implements BaseColumns {

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.steer.paidParkingAreas";
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.steer.paidParkingArea";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PAIDPARKINGAREA).build();
        public static final String TABLE_NAME = "PaidParkingArea";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_RADIUS = "radius";


        public static final String SQL_CREATE_PAIDPARKINGAREA_TABLE =
                "CREATE TABLE " + PaidParkingArea.TABLE_NAME + " (" +
                        PaidParkingArea._ID + " INTEGER PRIMARY KEY," +
                        PaidParkingArea.COLUMN_NAME_LATITUDE + REAL_TYPE + COMMA_SEP +
                        PaidParkingArea.COLUMN_NAME_LONGITUDE + REAL_TYPE + COMMA_SEP +
                        PaidParkingArea.COLUMN_NAME_RADIUS + REAL_TYPE +
                        " )";

        public static final String SQL_DELETE_PAIDPARKINGAREA_TABLE =
                "DROP TABLE IF EXISTS " + PaidParkingArea.TABLE_NAME;
    }

    public static abstract class Alert implements BaseColumns {

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.steer.alerts";
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.steer.alert";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ALERTS).build();
        public static final String TABLE_NAME = "Alert";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";


        public static final String SQL_CREATE_ALERT_TABLE =
                "CREATE TABLE " + Alert.TABLE_NAME + " (" +
                        Alert._ID + " INTEGER PRIMARY KEY," +
                        Alert.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                        Alert.COLUMN_NAME_LATITUDE + REAL_TYPE + COMMA_SEP +
                        Alert.COLUMN_NAME_LONGITUDE + REAL_TYPE +
                        " )";

        public static final String SQL_DELETE_PAIDPARKINGAREA_TABLE =
                "DROP TABLE IF EXISTS " + Alert.TABLE_NAME;
    }
}