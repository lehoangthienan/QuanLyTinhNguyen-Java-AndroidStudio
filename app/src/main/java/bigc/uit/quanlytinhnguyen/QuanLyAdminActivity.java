package bigc.uit.quanlytinhnguyen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class QuanLyAdminActivity extends AppCompatActivity {

    ImageButton btnThemTN , btnSuaTN , btnXoaTN , btnQuayLaiDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_admin);

        // ẩn ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();

        addEvents();
    }

    // xử lý các sự kiện của button , chủ yếu là mở activity mới để xử lý
    private void addEvents() {
        btnThemTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyAdminActivity.this , InsertTinhNguyenActivity.class));
            }
        });
         btnQuayLaiDN.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                finish();
             }
         });
        btnSuaTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyAdminActivity.this ,DanhSachTinhNguyenUpdateActivity.class));
            }
        });
        btnXoaTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyAdminActivity.this ,DeleteTinhNguyenActivity.class));
            }
        });
    }

    // ánh xạ
    private void addControls() {
        btnThemTN = (ImageButton) findViewById(R.id.btnThemHoatDongTinhNguyen);
        btnSuaTN = (ImageButton) findViewById(R.id.btnSuaHoatDongTinhNguyen);
        btnXoaTN = (ImageButton) findViewById(R.id.btnXoaHoatDongTinhNguyen);
        btnQuayLaiDN = (ImageButton) findViewById(R.id.btnQuayLaiDangNhap);
    }
}
