package iix.se.trippybeerbook.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BeerColumns.TABLE_NAME + " (" +
                    BeerColumns._ID + " INTEGER PRIMARY KEY," +
                    BeerColumns.BEER_NAME  + " TEXT," +
                    BeerColumns.BREWERY    + " TEXT," +
                    BeerColumns.BEER_TYPE  + " TEXT," +
                    BeerColumns.COUNTRY    + " TEXT," +
                    BeerColumns.PERCENTAGE + " TEXT," +
                    BeerColumns.STARS      + " TEXT," +
                    BeerColumns.COMMENT    + " TEXT)";
    public static final String SQL_DROP_ENTRIES =
            "DROP TABLE IF EXISTS " + BeerColumns.TABLE_NAME;

    public static abstract class BeerColumns implements BaseColumns {
        public static final String TABLE_NAME = "Beers";
        public static final String BEER_NAME = "name";
        public static final String BREWERY = "brewery";
        public static final String BEER_TYPE = "type";
        public static final String COUNTRY = "country";
        public static final String PERCENTAGE = "percentage";
        public static final String STARS = "stars";
        public static final String COMMENT = "comment";
    }

    public static final String[] projection = {
            BeerColumns._ID,
            BeerColumns.BEER_NAME,
            BeerColumns.BREWERY,
            BeerColumns.BEER_TYPE,
            BeerColumns.COUNTRY,
            BeerColumns.PERCENTAGE,
            BeerColumns.STARS,
            BeerColumns.COMMENT
    };
}