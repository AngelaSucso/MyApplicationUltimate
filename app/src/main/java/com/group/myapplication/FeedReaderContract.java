package com.group.myapplication;
import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "tabla_reservas";
        public static final String COLUMN_DNI = "dni";
        public static final String COLUMN_PELICULA = "pelicula";
        public static final String COLUMN_HORARIO = "horario";
    }
}
