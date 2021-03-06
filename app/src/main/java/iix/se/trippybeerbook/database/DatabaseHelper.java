package iix.se.trippybeerbook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Backend class which accesses the SQLite database
 */
class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TrippyBeerBook.db";
    private SQLiteDatabase mCurrentDB;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void close(Cursor cursor) {
        cursor.close();
        mCurrentDB.close();
    }

    public Cursor query(String selection, String sort) {
        mCurrentDB = getReadableDatabase();
        return mCurrentDB.query(
                DatabaseContract.BeerColumns.TABLE_NAME,
                DatabaseContract.projection,
                selection,
                null,
                null,
                null,
                sort);
    }

    public void insertOrThrowThenClose(Beer item) {
        final ContentValues values = new ContentValues();
        values.put(DatabaseContract.BeerColumns.BEER_NAME, item.mName);
        values.put(DatabaseContract.BeerColumns.BREWERY, item.mBrewery);
        values.put(DatabaseContract.BeerColumns.BEER_TYPE, item.mBeerType);
        values.put(DatabaseContract.BeerColumns.COUNTRY, item.mCountry);
        values.put(DatabaseContract.BeerColumns.PERCENTAGE, item.mPercentage);
        values.put(DatabaseContract.BeerColumns.STARS, item.mStars);
        values.put(DatabaseContract.BeerColumns.COMMENT, item.mComment);

        mCurrentDB = getWritableDatabase();
        mCurrentDB.insertOrThrow(DatabaseContract.BeerColumns.TABLE_NAME, null, values);
        mCurrentDB.close();
    }

    public void update (Beer item) {
        final String where = DatabaseContract.BeerColumns._ID + " = " + item.mID;
        final ContentValues values = new ContentValues();
        values.put(DatabaseContract.BeerColumns.BEER_NAME, item.mName);
        values.put(DatabaseContract.BeerColumns.BREWERY, item.mBrewery);
        values.put(DatabaseContract.BeerColumns.BEER_TYPE, item.mBeerType);
        values.put(DatabaseContract.BeerColumns.COUNTRY, item.mCountry);
        values.put(DatabaseContract.BeerColumns.PERCENTAGE, item.mPercentage);
        values.put(DatabaseContract.BeerColumns.STARS, item.mStars);
        values.put(DatabaseContract.BeerColumns.COMMENT, item.mComment);

        mCurrentDB = getWritableDatabase();
        mCurrentDB.update(DatabaseContract.BeerColumns.TABLE_NAME, values, where, null);
        mCurrentDB.close();
    }

    public void remove(long id) {
        String where = DatabaseContract.BeerColumns._ID + " = " + id;
        mCurrentDB = getWritableDatabase();
        mCurrentDB.delete(DatabaseContract.BeerColumns.TABLE_NAME, where, null);
        mCurrentDB.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DatabaseContract.SQL_DROP_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}