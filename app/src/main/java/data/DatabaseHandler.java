package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by harri on 23/06/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context){
        super(context,Constants.TABLE_NAME,null,Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
