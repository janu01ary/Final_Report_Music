package ddwucom.moblie.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MusicDBHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "musics.db";
    public final static String TABLE_NAME = "music_table";

    public final static String COL_ID = "_id";
    public final static String COL_IMG = "img";
    public final static String COL_SONGTITLE = "songTitle";
    public final static String COL_ALBUM = "album";
    public final static String COL_ARTIST = "artist";
    public final static String COL_YEAR = "year";
    public final static String COL_MONTH = "month";
    public final static String COL_DAY = "day";
    public final static String COL_GENRE = "genre";

    public MusicDBHelper(Context context) { super(context, DB_NAME, null, 1) ;}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_IMG + " INTEGER, " + COL_SONGTITLE + " TEXT, " + COL_ALBUM + " TEXT, " +
                COL_ARTIST + " TEXT, " + COL_YEAR + " INTEGER, " + COL_MONTH + " INTEGER, " + COL_DAY + " INTEGER, " + COL_GENRE + " TEXT)";
        db.execSQL(sql);

        db.execSQL("insert into " + TABLE_NAME + " values (null, " + R.mipmap.colors_in_black + ", 'A to Z', 'COLORS IN BLACK', '넬', '2019', '10', '10', '락');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, " + R.mipmap.parachutes + ", 'Spies', 'Parachutes', 'Coldplay', '2000', '7', '10', '락');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, " + R.mipmap.escaping_gravity + ", 'Burn', 'Escaping Gravity', '넬', '2013', '6', '10', '락');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, " + R.mipmap.non_linear + ", '카페인', '비선형(Non-Linear)', '못', '2004', '6', '18', '락');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, " + R.mipmap.dreaming_out_loud + ", 'Mercy', 'Dreaming Out Loud', 'OneRepublic', '2007', '11', '20', '락');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
