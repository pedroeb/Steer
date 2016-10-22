package com.unlp.tesis.steer.Persistence;

import android.provider.BaseColumns;

/**
 * Contiene las constantes relacionadas con la base de datos. Nombres de tablas, nombres de columnas
 * , operaciones estandar como crear o eliminar tablas, etc.
 */
public final class DatabaseContract {

    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String REAL_TYPE = " REAL";

    public static final String COMMA_SEP = ",";

    public DatabaseContract() { }

    public static abstract class PaidParkingArea implements BaseColumns {

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