package bigc.uit.quanlytinhnguyen;

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

import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter.DanhSachUpdateAdapter;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.DanhSachUpdate;

public class DanhSachTinhNguyenUpdateActivity extends AppCompatActivity {

    // khởi tạo
    ListView lvDanhSachTinhNguyenUpdate;

    ArrayList<DanhSachUpdate> dsUpdate;
    DanhSachUpdateAdapter adapterUpdate;

    String url="http://quanlyhoatdongtinhnguyen.000webhostapp.com/getdanhsachtinhnguyenupdate.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tinh_nguyen_update);

        // ẩn ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();

        addEvents();
    }

    private void addEvents() {
    }

    // ánh xạ  , get dữ liệu
    private void addControls() {
        lvDanhSachTinhNguyenUpdate = (ListView) findViewById(R.id.lvDanhSachTinhNguyenUpdate);

        dsUpdate = new ArrayList<>();
        adapterUpdate = new DanhSachUpdateAdapter(DanhSachTinhNguyenUpdateActivity.this ,R.layout.danhsachtinhnguyenupdate,dsUpdate);
        lvDanhSachTinhNguyenUpdate.setAdapter(adapterUpdate);

        getDanhSachUpdate(url);
    }

    // get tất cả danh sách tình nguyện để chỉnh sửa
    private void getDanhSachUpdate(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                dsUpdate.add(new DanhSachUpdate(
                                        object.getString("MATN"),
                                        object.getString("TenTN")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // de cap nhap lai du lieu
                        adapterUpdate.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DanhSachTinhNguyenUpdateActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
