package com.example.mad_submission_2.Database;

import android.provider.BaseColumns;

public final class AddOff {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private AddOff() {}

    /* Inner class that defines the table contents */
    public static class Offers implements BaseColumns {
        public static final String TABLE_NAME = "offerInfo";
        public static final String COLUMN_1 = "offerName";
        public static final String COLUMN_2 = "startAndEndDate";
        public static final String COLUMN_3 = "discount";
        public static final String COLUMN_4 = "previousPrice";
        public static final String COLUMN_5 = "currentPrice";
        public static final String COLUMN_6 = "offerType";
    }
}
