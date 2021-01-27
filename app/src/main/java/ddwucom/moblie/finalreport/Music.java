package ddwucom.moblie.finalreport;

import android.media.Image;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Music implements Serializable {

    private long _id;
    private int img;
    private String songTitle;
    private String album;
    private String artist;
    private int year;
    private int month;
    private int day;
    private String genre;

    public Music(int img, String songTitle, String album, String artist, int year, int month, int day, String genre) {
        this.img = img;
        this.songTitle = songTitle;
        this.album = album;
        this.artist = artist;
        this.year = year;
        this.month = month;
        this.day = day;
        this.genre = genre;
    }

    public Music(long _id, int img, String songTitle, String album, String artist, int year, int month, int day, String genre) {
        this._id = _id;
        this.img = img;
        this.songTitle = songTitle;
        this.album = album;
        this.artist = artist;
        this.year = year;
        this.month = month;
        this.day = day;
        this.genre = genre;
    }

    public long get_id() { return _id; }

    public void set_id(long _id) { this._id = _id; }

    public int getImg() { return img; }

    public void setImg(int img) { this.img = img; }

    public String getSongTitle() { return songTitle; }

    public void setSongTitle(String songTitle) { this.songTitle = songTitle; }

    public String getAlbum() { return album; }

    public void setAlbum(String album) { this.album = album; }

    public String getArtist() { return artist; }

    public void setArtist(String artist) { this.artist = artist; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public int getMonth() { return month; }

    public void setMonth(int month) { this.month = month; }

    public int getDay() { return day; }

    public void setDay(int day) { this.day = day; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String dateString() {
        return year + "년 " + month  + "월 " + day + "일";
    }
}
