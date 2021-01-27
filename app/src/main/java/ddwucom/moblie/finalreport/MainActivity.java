// 과제명: 음악 정보 관리 앱
// 분반: 01분반
// 학번: 20180999 성명: 이지우
// 제출일: 2020년 7월 2일

package ddwucom.moblie.finalreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

    private ListView listView;
    private ArrayList<Music> musicList;
    private MyAdapter adapter;
    private MusicDBManager musicDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.customListView);
        musicList = new ArrayList();
        adapter = new MyAdapter(this, R.layout.custom_adapter_view, musicList);
        listView.setAdapter(adapter);
        musicDBManager = new MusicDBManager(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Music music = musicList.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("music", music);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("음악 삭제")
                        .setMessage(musicList.get(pos).getSongTitle() + "을(를) 삭제하시겠습니까?")
                        .setNegativeButton("취소", null)
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (musicDBManager.removeMusic(musicList.get(pos).get_id())) {
//                                    Toast.makeText(MainActivity.this, musicList.get(pos).getSongTitle() + "을(를) 삭제하였습니다.", Toast.LENGTH_SHORT).show();
                                    musicList.clear();
                                    musicList.addAll(musicDBManager.getAllMusic());
                                    adapter.notifyDataSetChanged();
                                } else {
//                                    Toast.makeText(MainActivity.this, "삭제를 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        musicList.clear();
        musicList.addAll(musicDBManager.getAllMusic());
        adapter.notifyDataSetChanged();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQ_CODE) {
//            switch (resultCode) {
//                case RESULT_OK:
//                    String music = data.getStringExtra("music");
//                    Toast.makeText(this, music + "을(를) 추가하였습니다.", Toast.LENGTH_SHORT).show();
//                    break;
//                case RESULT_CANCELED:
//                    Toast.makeText(this, "추가를 취소합니다.", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//        if (requestCode == UPDATE_CODE) {
//            switch (resultCode) {
//                case RESULT_OK:
//                    String music = data.getStringExtra("music");
//                    Toast.makeText(this, music + "을(를) 수정하였습니다.", Toast.LENGTH_SHORT).show();
//                    break;
//                case RESULT_CANCELED:
//                    Toast.makeText(this, "수정을 취소합니다.", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.addMusic:
                intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQ_CODE);
                break;
            case R.id.searchTitle:
                intent = new Intent(this, SearchTitleActivity.class);
                intent.putExtra("musicList", musicList);
                startActivity(intent);
                break;
            case R.id.developer:
                intent = new Intent(this, DeveloperActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle("앱 종료")
                            .setMessage("앱을 종료하시겠습니까?")
                            .setNegativeButton("취소", null)
                            .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .show();
                break;
        }
        return true;
    }
}