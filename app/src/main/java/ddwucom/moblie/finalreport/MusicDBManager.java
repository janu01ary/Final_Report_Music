package ddwucom.moblie.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MusicDBManager {

    MusicDBHelper musicDBHelper = null;
    Cursor cursor = null;

    public MusicDBManager(Context context) {  musicDBHelper = new MusicDBHelper(context); }

    //DB의 모든 music을 반환
    public ArrayList<Music> getAllMusic() {
        ArrayList musicList = new ArrayList();
        SQLiteDatabase db = musicDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MusicDBHelper.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            long id  = cursor.getInt(cursor.getColumnIndex(MusicDBHelper.COL_ID));
            int img = cursor.getInt(cursor.getColumnIndex(MusicDBHelper.COL_IMG));
            String songTitle = cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_SONGTITLE));
            String album = cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_ALBUM));
            String artist = cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_ARTIST));
            int year = cursor.getInt(cursor.getColumnIndex(MusicDBHelper.COL_YEAR));
            int month = cursor.getInt(cursor.getColumnIndex(MusicDBHelper.COL_MONTH));
            int day = cursor.getInt(cursor.getColumnIndex(MusicDBHelper.COL_DAY));
            String genre = cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_GENRE));
            musicList.add ( new Music(id, img, songTitle, album, artist, year, month, day, genre) );
        }

        cursor.close();
        musicDBHelper.close();
        return musicList;
    }

    //DB에 새로운 music 추가
    public boolean addNewMusic(Music newMusic) {
        SQLiteDatabase db = musicDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(MusicDBHelper.COL_IMG, newMusic.getImg());
        value.put(MusicDBHelper.COL_SONGTITLE, newMusic.getSongTitle());
        value.put(MusicDBHelper.COL_ALBUM, newMusic.getAlbum());
        value.put(MusicDBHelper.COL_ARTIST, newMusic.getArtist());
        value.put(MusicDBHelper.COL_YEAR, newMusic.getYear());
        value.put(MusicDBHelper.COL_MONTH, newMusic.getMonth());
        value.put(MusicDBHelper.COL_DAY, newMusic.getDay());
        value.put(MusicDBHelper.COL_GENRE, newMusic.getGenre());

        long count = db.insert(MusicDBHelper.TABLE_NAME, null, value);
        musicDBHelper.close();
        if (count > 0) return true;
        return false;
    }

    //_id를 기준으로 music의 내용 변경
    public boolean modifyMusic(Music music) {
        SQLiteDatabase db = musicDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(MusicDBHelper.COL_IMG, music.getImg());
        value.put(MusicDBHelper.COL_SONGTITLE, music.getSongTitle());
        value.put(MusicDBHelper.COL_ALBUM, music.getAlbum());
        value.put(MusicDBHelper.COL_ARTIST, music.getArtist());
        value.put(MusicDBHelper.COL_YEAR, music.getYear());
        value.put(MusicDBHelper.COL_MONTH, music.getMonth());
        value.put(MusicDBHelper.COL_DAY, music.getDay());
        value.put(MusicDBHelper.COL_GENRE, music.getGenre());

        String whereClause = MusicDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(music.get_id()) };
        int result = db.update(MusicDBHelper.TABLE_NAME, value, whereClause, whereArgs);
        musicDBHelper.close();
        if (result > 0) return true;
        return false;
    }

    //_id를 기준으로 DB에서 music 삭제
    public boolean removeMusic(long id) {
        SQLiteDatabase sqLiteDatabase = musicDBHelper.getWritableDatabase();
        String whereClause = MusicDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        int result = sqLiteDatabase.delete(MusicDBHelper.TABLE_NAME, whereClause, whereArgs);
        musicDBHelper.close();
        if (result > 0) return true;
        return false;
    }

    //검색

    //close 수행
    public void close() {
        if (musicDBHelper != null) musicDBHelper.close();
        if (cursor != null) cursor.close();
    }
}
