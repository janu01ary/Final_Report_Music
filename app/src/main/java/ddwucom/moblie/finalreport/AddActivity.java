package ddwucom.moblie.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    ImageView ivAlbum;
    EditText etSongTitle;
    EditText etAlbum;
    EditText etArtist;
    TextView tvReleaseDate;
    DatePicker datePicker;
    EditText etGenre;

    MusicDBManager musicDBManager;
    int img;
    static int year, month, day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ivAlbum = findViewById(R.id.ivAlbum);
        etSongTitle = findViewById(R.id.etSongTitle);
        etAlbum = findViewById(R.id.etAlbum);
        etArtist = findViewById(R.id.etArtist);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        etGenre = findViewById(R.id.etGenre);

        int rd = (int) (Math.random() * 7);
        switch (rd) {
            case 0:
                img = R.mipmap.c;
                break;
            case 1:
                img = R.mipmap.colors_in_black;
                break;
            case 2:
                img = R.mipmap.dreaming_out_loud;
                break;
            case 3:
                img = R.mipmap.escaping_gravity;
                break;
            case 4:
                img = R.mipmap.holding_onto_gravity;
                break;
            case 5:
                img = R.mipmap.non_linear;
                break;
            default:
                img = R.mipmap.parachutes;
                break;
        }
        ivAlbum.setImageResource(img);

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                AddActivity.year = year;
                AddActivity.month = monthOfYear;
                AddActivity.day = dayOfMonth;
                tvReleaseDate.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
            }
        });

        musicDBManager = new MusicDBManager(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddOK:
                String songTitle = etSongTitle.getText().toString();
                String album = etAlbum.getText().toString();
                String artist = etArtist.getText().toString();
                String releaseDate = tvReleaseDate.getText().toString();
                String genre = etGenre.getText().toString();

                if (songTitle.equals("") || album.equals("") || artist.equals("") || releaseDate.equals("") || genre.equals("")) {
                    Toast.makeText(this, "필수 항목을 입력하세요", Toast.LENGTH_SHORT).show();
                    break;
                }

                boolean result = musicDBManager.addNewMusic(
                        new Music(img, songTitle, album, artist, year, month + 1, day, genre));

                if (result) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("music", songTitle);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    setResult(RESULT_CANCELED);
                }
                break;
            case R.id.btnAddCancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
