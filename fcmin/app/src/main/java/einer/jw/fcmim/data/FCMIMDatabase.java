package einer.jw.fcmim.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Einer on 31/1/2016.
 */
public class FCMIMDatabase extends SQLiteOpenHelper {


    public final static String NAME = "fcmim_database";
    public final static int VERSION = 1;

    public FCMIMDatabase(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       // String users = "CREATE TABLE IF NOT EXISTS auth("

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
