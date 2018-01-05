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

import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter.DanhSachDeleteAdapter;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.DanhSachUpdate;

public class DeleteTinhNguyenActivity extends AppCompatActivity {


    // khởi tạo
    ListView lvDanhSachDelete;

    ArrayList<DanhSachUpdate> dsUpdate;
    DanhSachDeleteAdapter adapterDelete;

    String url="http://quanlyhoatdongtinhnguyen.000webhostapp.com/getdanhsachtinhnguyenupdate.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_tinh_nguyen);

        // ẩn ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();

        addEvents();
    }

    private void addEvents() {

    }

    // ánh xạ và get dữ liệu danh sách các hoạt động tình nguyện để xóa
    private void addControls() {
        lvDanhSachDelete = (ListView) findViewById(R.id.lvDanhSachDelete);

        dsUpdate = new ArrayList<>();
        adapterDelete = new DanhSachDeleteAdapter(DeleteTinhNguyenActivity.this ,R.layout.danhsachdelete,dsUpdate);
        lvDanhSachDelete.setAdapter(adapterDelete);

        getDanhSachUpdate(url);
    }
    // xử lý get danh sách tình nguyện
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
                        adapterDelete.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DeleteTinhNguyenActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
