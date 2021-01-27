package ddwucom.moblie.finalreport;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchTitleActivity extends AppCompatActivity {

    EditText etSearchTitle;
    ListView listView;
    ArrayList<Music> resultList, musicList;
    MyAdapter adapter;
    MusicDBManager musicDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_title);

        etSearchTitle = findViewById(R.id.etSearchTitle);
//        etSearchTitle.requestFocus();
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        listView = findViewById(R.id.searchCustomListView);
        resultList = new ArrayList();
        musicList = (ArrayList<Music>) getIntent().getSerializableExtra("musicList");
        adapter = new MyAdapter(this, R.layout.custom_adapter_view, resultList);
        listView.setAdapter(adapter);
        musicDBManager = new MusicDBManager(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearchTitle:
                resultList.clear();
                String search = etSearchTitle.getText().toString();
                for (int i = 0; i < musicList.size(); i++) {
                    if (search.equals(musicList.get(i).getSongTitle()))
                        resultList.add(musicList.get(i));
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
