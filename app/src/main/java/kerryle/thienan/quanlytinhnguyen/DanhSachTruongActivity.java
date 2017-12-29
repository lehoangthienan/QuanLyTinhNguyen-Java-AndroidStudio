package kerryle.thienan.quanlytinhnguyen;

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

import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.adapter.TruongAdapter;
import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model.Truong;

public class DanhSachTruongActivity extends AppCompatActivity {

    ListView lvDanhSachTruongDaiHoc;
    ArrayList<Truong> dsTruong;
    TruongAdapter adapterTruong;

    String url="http://quanlyhoatdongtinhnguyen.000webhostapp.com/gettruong.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_truong);

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();

        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {

        lvDanhSachTruongDaiHoc = (ListView) findViewById(R.id.lvDanhSachTruongDaiHoc);

        dsTruong = new ArrayList<>();

        adapterTruong = new TruongAdapter(DanhSachTruongActivity.this ,R.layout.danhsachtruong, dsTruong);

        lvDanhSachTruongDaiHoc.setAdapter(adapterTruong);

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
                                dsTruong.add(new Truong(
                                        object.getString("MAT"),
                                        object.getString("TenTruong")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // de cap nhap lai du lieu
                        adapterTruong.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DanhSachTruongActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
