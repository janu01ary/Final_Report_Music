package ddwucom.moblie.finalreport;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DeveloperActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btnDeveloper:
                finish();
                break;
        }
    }
}
