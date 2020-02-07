package dapjoo.nl.voortgangsappjoost;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VakkenDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "vakken.db";
    public static final int DATABASE_VERSION = 1;

    // De dbhelper om database op juiste wijze aan te roepen in classes
    public VakkenDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Bouwen van een database bij eerste startup
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_VAKKENLIST_TABLE = "CREATE TABLE " +
                VakkenContract.VakkenEntry.TABLE_NAME + " (" +
                VakkenContract.VakkenEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VakkenContract.VakkenEntry.COLUMN_NAAM + " TEXT NOT NULL, " +
                VakkenContract.VakkenEntry.COLUMN_CIJFER + " REAL NOT NULL, " +
                VakkenContract.VakkenEntry.COLUMN_SCHOOLJAAR + " INTEGER NOT NULL, " +
                VakkenContract.VakkenEntry.COLUMN_EC + " INTEGER NOT NULL, " +
                VakkenContract.VakkenEntry.COLUMN_NOTITIE + " TEXT NOT NULL, " +
                VakkenContract.VakkenEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_VAKKENLIST_TABLE);
    }

    //Bij het upgraden van een database (gebeurd niet in de app)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + VakkenContract.VakkenEntry.TABLE_NAME);
        onCreate(db);
    }
}
