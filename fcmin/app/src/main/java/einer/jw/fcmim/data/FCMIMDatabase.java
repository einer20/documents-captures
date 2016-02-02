package einer.jw.fcmim.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import einer.jw.fcmim.data.entities.FileUploadRegisterModel;
import einer.jw.fcmim.data.entities.ServerLocation;

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



        // indicates the server location where the packages are going to be send
        // and tells which location is going to be saved
        /*
        * And defined the authentication method
        * 1 - Web service
        * 2 - Local Server(Wifi)
        * */
        String server_location = "CREATE TABLE IF NOT EXISTS server_location(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "url TEXT," +
                "is_active INTEGER," +
                "auth_type INTEGER)";

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

        /*
        * Register the upload information of the files
        * */
        String files_uploads_register = "CREATE TABLE IF NOT EXISTS files_uploads_register(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "file_path TEXT," +
                "was_send INTEGER)";


        db.execSQL(files_uploads_register);
        db.execSQL(server_location);
        db.execSQL(cache);
        db.execSQL(logs);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static class FilesUploadRegister{


        private SQLiteOpenHelper db;

        private FilesUploadRegister(SQLiteOpenHelper db){

            this.db = db;
        }


        private FileUploadRegisterModel toFileUploadRegisterModel(Cursor cursor)
        {
            FileUploadRegisterModel model = new FileUploadRegisterModel();

            model.setId(cursor.getInt(cursor.getColumnIndex("id")));
            model.setFilePath(cursor.getString(cursor.getColumnIndex("file_path")));
            model.setWasSend(cursor.getInt(cursor.getColumnIndex("was_send")) == 1);

            return model;
        }

        public void registerNew(FileUploadRegisterModel uploadRegisterModel)
        {
            SQLiteDatabase _db = this.db.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("file_path", uploadRegisterModel.getFilePath());
            contentValues.put("was_send", uploadRegisterModel.isWasSend() ? 1 : 0);

            _db.insert("files_uploads_register", null, contentValues);


        }

        public List<FileUploadRegisterModel> getRegisters()
        {

            List<FileUploadRegisterModel> models = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = this.db.getReadableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM files_uploads_register", null);
            while (cursor.moveToNext())
            {
                FileUploadRegisterModel model = toFileUploadRegisterModel(cursor);
                models.add(model);
            }

            return models;
        }

        public List<FileUploadRegisterModel> getRegisters(boolean getSendFiles)
        {

            List<FileUploadRegisterModel> models = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = this.db.getReadableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM files_uploads_register WHERE was_send=" + (getSendFiles ? 1 : 0), null);
            while (cursor.moveToNext())
            {
                FileUploadRegisterModel model = toFileUploadRegisterModel(cursor);
                models.add(model);
            }

            return models;
        }
    }

    public static class Cache{
        private Cache()
        {

        }

        public void add(String key, String data)
        {

        }
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
            serverLocation.setAuthentication_type(cursor.getInt(cursor.getColumnIndex("auth_type")));

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

        }

    }


}
