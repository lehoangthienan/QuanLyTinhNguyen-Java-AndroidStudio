package bigc.uit.quanlytinhnguyen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter.ThongTinSinhVienAdapter;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.ThongTinSinhVien;

public class DanhSachSinhVienThamGiaActivity extends AppCompatActivity {

    ListView lvDanhSachSinhVienTinhNguyen;

    String url ;

    ArrayList<ThongTinSinhVien> dsThongTinSinhVien;
    ThongTinSinhVienAdapter adapterThongTinSinhVien;

    String MATN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sinh_vien_tham_gia);

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();
        
        addControls();
        
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvDanhSachSinhVienTinhNguyen = (ListView) findViewById(R.id.lvDanhSachSinhVienTinhNguyen);
        dsThongTinSinhVien= new ArrayList<>();
        adapterThongTinSinhVien = new ThongTinSinhVienAdapter(DanhSachSinhVienThamGiaActivity.this , R.layout.danhsachsinhvien , dsThongTinSinhVien);
        lvDanhSachSinhVienTinhNguyen.setAdapter(adapterThongTinSinhVien);

        Intent intent = getIntent();
        MATN = intent.getStringExtra("MATN");

        url = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/getthongtinsinhvien.php?MATN1="+MATN;

        getData(url);
    }

    private void getData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                dsThongTinSinhVien.add(new ThongTinSinhVien(
                                        object.getString("MASV"),
                                        object.getString("TenSV"),
                                        object.getString("MAT")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // de cap nhap lai du lieu
                        adapterThongTinSinhVien.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DanhSachSinhVienThamGiaActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
