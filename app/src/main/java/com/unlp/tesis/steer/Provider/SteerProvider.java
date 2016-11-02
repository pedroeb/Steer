package com.unlp.tesis.steer.Provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.unlp.tesis.steer.Persistence.SteerContract;
import com.unlp.tesis.steer.Persistence.SteerDbHelper;

public class SteerProvider extends ContentProvider {


    public static final String TAG = "Provider";

    private SteerDbHelper mDbHelper;
    private ContentResolver resolver;

    public SteerProvider() {
    }

    // [URI_MATCHER]
    public static final UriMatcher uriMatcher;
    public static final int ROUTE_ALERTS = 100;
    public static final int ROUTE_ALERTS_ID = 101;

    public static final int ROUTE_PAIDPARKINGAREAS = 200;
    public static final int ROUTE_PAIDPARKINGAREAS_ID = 201;

    private static final String AUTHORITY = SteerContract.CONTENT_AUTHORITY;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, "alerts", ROUTE_ALERTS);
        uriMatcher.addURI(AUTHORITY, "alerts/*", ROUTE_ALERTS_ID);

        uriMatcher.addURI(AUTHORITY, "paidparkingareas", ROUTE_PAIDPARKINGAREAS);
        uriMatcher.addURI(AUTHORITY, "paidparkingareas/*", ROUTE_PAIDPARKINGAREAS_ID);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "Delete: " + uri);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String id;
        int result;

        switch (uriMatcher.match(uri)) {

            case ROUTE_ALERTS_ID:
                id = uri.getLastPathSegment();
                result = db.delete(SteerContract.Alert.TABLE_NAME
                        , SteerContract.Alert._ID + "=" + "\'" + id + "\'"
                        , selectionArgs);
                break;

            case ROUTE_PAIDPARKINGAREAS_ID:
                id = uri.getLastPathSegment();
                result = db.delete(SteerContract.PaidParkingArea.TABLE_NAME
                        , SteerContract.PaidParkingArea._ID + "=" + "\'" + id + "\'"
                        , selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Uri unknown =>" + uri);
        }
        return result;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case ROUTE_ALERTS:
                return SteerContract.Alert.CONTENT_TYPE;
            case ROUTE_ALERTS_ID:
                return SteerContract.Alert.CONTENT_ITEM_TYPE;
            case ROUTE_PAIDPARKINGAREAS:
                return SteerContract.PaidParkingArea.CONTENT_TYPE;
            case ROUTE_PAIDPARKINGAREAS_ID:
                return SteerContract.PaidParkingArea.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Uri unknown =>" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        Log.d(TAG, "Insert en " + uri + "( " + values.toString() + " )\n");

        SQLiteDatabase bd = mDbHelper.getWritableDatabase();
        String id = null;
        Uri result;
        switch (uriMatcher.match(uri)) {
            case ROUTE_ALERTS:
                bd.insertOrThrow(SteerContract.Alert.TABLE_NAME, null, values);
                resolver.notifyChange(uri, null);
                result = Uri.parse(SteerContract.Alert.CONTENT_URI + "/" + id);
                break;
            case ROUTE_PAIDPARKINGAREAS:
                bd.insertOrThrow(SteerContract.PaidParkingArea.TABLE_NAME, null, values);
                resolver.notifyChange(uri, null);
                result = Uri.parse(SteerContract.PaidParkingArea.CONTENT_URI + "/" + id);
                break;
            default:
                throw new UnsupportedOperationException("Uri unknown =>" + uri);
        }

        return result;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = SteerDbHelper.getInstance(getContext());
        resolver = getContext().getContentResolver();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase bd = mDbHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        int match = uriMatcher.match(uri);

        String id;
        Cursor cursor;

        switch (match) {
            case ROUTE_ALERTS:
                builder.setTables(SteerContract.Alert.TABLE_NAME);
                cursor = builder.query(bd, projection, selection,selectionArgs,null,null, sortOrder);
                break;
            case ROUTE_ALERTS_ID:
                id = uri.getLastPathSegment();
                builder.setTables(SteerContract.Alert.TABLE_NAME);
                cursor = builder.query(bd
                        , projection
                        , SteerContract.Alert._ID + "=" + "\'" + id + "\'"
                        , selectionArgs, null, null, sortOrder);
                break;

            case ROUTE_PAIDPARKINGAREAS:
                builder.setTables(SteerContract.PaidParkingArea.TABLE_NAME);
                cursor = builder.query(bd, projection, selection,selectionArgs,null,null, sortOrder);
                break;
            case ROUTE_PAIDPARKINGAREAS_ID:
                id = uri.getLastPathSegment();
                builder.setTables(SteerContract.PaidParkingArea.TABLE_NAME);
                cursor = builder.query(bd
                        , projection
                        , SteerContract.PaidParkingArea._ID + "=" + "\'" + id + "\'"
                        , selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Uri unknown =>" + uri);
        }

        cursor.setNotificationUri(resolver, uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String id;
        int result;

        switch (uriMatcher.match(uri)) {
            case ROUTE_ALERTS_ID:
                id = uri.getLastPathSegment();
                result = db.delete(SteerContract.PaidParkingArea.TABLE_NAME,
                        SteerContract.PaidParkingArea._ID + "=" + "\"" + id + "\""
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);

                id = uri.getLastPathSegment();
                result = db.update(SteerContract.Alert.TABLE_NAME
                        , values
                        , SteerContract.Alert._ID + "=" + "\"" + id + "\""
                        , selectionArgs);
                resolver.notifyChange(uri, null);
                break;

            case ROUTE_PAIDPARKINGAREAS_ID:
                id = uri.getLastPathSegment();
                result = db.update(SteerContract.Alert.TABLE_NAME
                        , values
                        , SteerContract.PaidParkingArea._ID + "=" + "\"" + id + "\""
                        , selectionArgs);
                resolver.notifyChange(uri, null);
                break;

            default:
                throw new UnsupportedOperationException("Uri unknown =>" + uri);
        }


        return result;
    }

}
