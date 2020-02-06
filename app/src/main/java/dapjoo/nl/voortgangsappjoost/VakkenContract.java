package dapjoo.nl.voortgangsappjoost;

import android.provider.BaseColumns;

public class VakkenContract {

    private VakkenContract() {
        //We willen niet perongeluk objecten gaan maken van deze class
    }

    public static final class VakkenEntry implements BaseColumns {
        public static final String TABLE_NAME = "vakken";
        public static final String COLUMN_NAAM = "naam";
        public static final String COLUMN_CIJFER = "cijfer";
        public static final String COLUMN_SCHOOLJAAR = "schooljaar";
        public static final String COLUMN_EC = "ec";
        public static final String COLUMN_NOTITIE = "notitie";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}