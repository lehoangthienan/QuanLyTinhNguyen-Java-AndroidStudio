package bigc.uit.quanlytinhnguyen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class XuLyDeleteActivity extends AppCompatActivity {

    ImageButton btnDongY , btnKhongDongY;

    String MATN , url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_ly_delete);

        // ẩn actionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();

        addVents();
    }

    // xử lý xự kiện
    private void addVents() {
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTinhNguyen(url);
            }
        });
        btnKhongDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(XuLyDeleteActivity.this , DeleteTinhNguyenActivity.class));
            }
        });
    }

    //ánh xạ và kkhởi tạo
    private void addControls() {
        btnDongY = (ImageButton) findViewById(R.id.btnDongY);
        btnKhongDongY = (ImageButton) findViewById(R.id.btnKhongDongY);

        Intent intent = getIntent();
        MATN = intent.getStringExtra("MATN");

        url = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/deletetinhnguyen.php";

    }

    // xử lý xóa hoạt động tình nguyện
    private void deleteTinhNguyen(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("succes"))
                        {
                            Toast.makeText(XuLyDeleteActivity.this, "Xóa Hoạt Động Tình Nguyện Thành Công !",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(XuLyDeleteActivity.this, "Xóa Hoạt Động Tình Nguyện Thất Bại !",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(XuLyDeleteActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("MATN" ,MATN);
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}
