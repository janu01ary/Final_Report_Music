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

public class UpdateActivity extends AppCompatActivity {

    Music music;

    ImageView ivAlbum;
    EditText etSongTitle;
    EditText etAlbum;
    EditText etArtist;
    TextView tvReleaseDate;
    DatePicker datePicker;
    EditText etGenre;

    MusicDBManager musicDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        music = (Music) getIntent().getSerializableExtra("music");

        ivAlbum = findViewById(R.id.ivAlbum);
        etSongTitle = findViewById(R.id.etSongTitle);
        etAlbum = findViewById(R.id.etAlbum);
        etArtist = findViewById(R.id.etArtist);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        etGenre = findViewById(R.id.etGenre);

        ivAlbum.setImageResource(music.getImg());
        etSongTitle.setHint(music.getSongTitle());
        etAlbum.setHint(music.getAlbum());
        etArtist.setHint(music.getArtist());
        tvReleaseDate.setHint(music.dateString());
        datePicker.init(music.getYear(), music.getMonth() - 1, music.getDay(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                music.setDate(year, monthOfYear + 1, dayOfMonth);
                tvReleaseDate.setText(music.dateString());
            }
        });
        etGenre.setHint(music.getGenre());

        musicDBManager = new MusicDBManager(this);
    }

    public void onClick(View v)
    {
        switch(v.getId()) {
            case R.id.btnUpdateOK:
                String songTitle = etSongTitle.getText().toString();
                String album = etAlbum.getText().toString();
                String artist = etArtist.getText().toString();
                String releaseDate = tvReleaseDate.getText().toString();
                String genre = etGenre.getText().toString();

                if (songTitle.equals("") || album.equals("") || artist.equals("") || releaseDate.equals("") || genre.equals("")) {
                    Toast.makeText(this, "필수 항목을 입력하세요", Toast.LENGTH_SHORT).show();
                    break;
                }

                music.setSongTitle(songTitle);
                music.setAlbum(album);
                music.setArtist(artist);
                music.setGenre(genre);

                if (musicDBManager.modifyMusic(music)) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("music", songTitle);
                    setResult(RESULT_OK, resultIntent);
                } else {
                    setResult(RESULT_CANCELED);
                }
                finish();
                break;
            case R.id.btnUpdateCancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
