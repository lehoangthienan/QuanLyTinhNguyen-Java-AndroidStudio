package kerryle.thienan.quanlytinhnguyen;

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

import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.adapter.TinhNguyenAdapter;
import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model.TinhNguyen;

public class XyLyXemTinhNguyenTruongActivity extends AppCompatActivity {

    ListView lvDanhSachTinhNguyen;
    ArrayList<TinhNguyen> dsTinhNguyen;
    TinhNguyenAdapter adapterTinhNguyen;

    String MAT , url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xy_ly_xem_tinh_nguyen_truong);

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();

        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvDanhSachTinhNguyen = (ListView) findViewById(R.id.lvDanhSachTinhNguyen);
        dsTinhNguyen = new ArrayList<>();
        adapterTinhNguyen = new TinhNguyenAdapter(XyLyXemTinhNguyenTruongActivity.this ,R.layout.danhsachtinhnguyen,dsTinhNguyen);
        lvDanhSachTinhNguyen.setAdapter(adapterTinhNguyen);

        Intent intent = getIntent();
        MAT = intent.getStringExtra("MAT");

        url = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/gettinhnguyentheomatruong.php?MAT1="+MAT;

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
                                dsTinhNguyen.add(new TinhNguyen(
                                        object.getString("MATN"),
                                        object.getString("TenTN"),
                                        object.getString("NoiDung"),
                                        object.getString("NgayGioBatDau"),
                                        object.getString("NgayGioKetThuc"),
                                        object.getString("DiaDiem"),
                                        object.getInt("SLMax"),
                                        object.getInt("SLMin"),
                                        object.getInt("SLThamGia"),
                                        object.getString("MAT")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // de cap nhap lai du lieu
                        adapterTinhNguyen.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(XyLyXemTinhNguyenTruongActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }

}
