package einer.jw.fcmim.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

        /*
        * Defined the authentication method
        * 1 - Web service
        * 2 - Local Server(Wifi)
        * */
        String users = "CREATE TABLE IF NOT EXISTS authentication_remote_method(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "auth_type INTEGER)";

        // indicates the server location where the packages are going to be send
        // and tells which location is going to be saved
        String server_location = "CREATE TABLE IF NOT EXISTS server_location(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "url TEXT," +
                "active INTEGER)";

        /*
        * Add cache information of the application as string data.
        * */
        String cache = "CREATE TABLE IF NOT EXISTS cache(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cache_key VARCHAR(50)," +
                "data TEXT)";



        // register logs
        String logs = "CREATE TABLE IF NOT EXISTS logs(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "caption TEXT," +
                "message TEXT," +
                "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        db.execSQL(users);
        db.execSQL(server_location);
        db.execSQL(cache);
        db.execSQL(logs);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Settings
    {
        private SQLiteOpenHelper db;



        private Settings(SQLiteOpenHelper db)
        {
              this.db = db;
        }

        public List<ServerLocation> getServersLocations()
        {
            SQLiteDatabase wdb = this.db.getReadableDatabase();
            Cursor cursor = wdb.rawQuery("SELECT * FROM server_locations", null);

            List<ServerLocation> serverLocations = new ArrayList<>();
            while (cursor.moveToNext())
            {
                serverLocations.add(toServerLocation(cursor));
            }

            return serverLocations;
        }

        private ServerLocation toServerLocation(Cursor cursor)
        {
            ServerLocation serverLocation = new ServerLocation();
            String url = cursor.getString(cursor.getColumnIndex("url"));
            serverLocation.setUrl(url);
            serverLocation.setActive(cursor.getInt(cursor.getColumnIndex("active")) == 1);
            serverLocation.setId(cursor.getInt(cursor.getColumnIndex("id")));

            return serverLocation;
        }

        public ServerLocation getActiveServerLocation()
        {
            SQLiteDatabase rdb = db.getReadableDatabase();
            Cursor cursor = rdb.rawQuery("SELECT * FROM server_location WHERE is_active = 1", null);

            if (cursor.moveToNext())
            {
                return toServerLocation(cursor);
            }
            else{
                return null;
            }
        }


        public void setActiveServer(ServerLocation server)
        {

            SQLiteDatabase rdb = this.db.getReadableDatabase();
            Cursor cursor = rdb.rawQuery("SELECT count(1) FROM server_location WHERE url ='" + server + "'",  null);

            if (cursor.moveToFirst())
            {
                boolean exists = cursor.getInt(0) >= 1;
                if (exists)
                {

                    SQLiteDatabase wrb = this.db.getWritableDatabase();

                    ContentValues contentValues = new ContentValues();
                    // TODO finish the update command here of the server location
                }
            }

        }

    }

}
