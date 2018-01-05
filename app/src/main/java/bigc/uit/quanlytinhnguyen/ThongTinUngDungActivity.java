package bigc.uit.quanlytinhnguyen;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class ThongTinUngDungActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ung_dung);

        // ẩn actionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();
    }
}
