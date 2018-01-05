package bigc.uit.quanlytinhnguyen;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
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

import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter.TruongAdapter;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.Truong;

public class DanhSachTruongActivity extends AppCompatActivity {

    // khởi tạo
    ListView lvDanhSachTruongDaiHoc;
    ArrayList<Truong> dsTruong;
    TruongAdapter adapterTruong;

    String url="http://quanlyhoatdongtinhnguyen.000webhostapp.com/gettruong.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_truong);

//        ActionBar actionBar =getSupportActionBar();
//        actionBar.hide();

        addControls();

        addEvents();
    }

    private void addEvents() {
    }

    // ánh xạ
    private void addControls() {

        lvDanhSachTruongDaiHoc = (ListView) findViewById(R.id.lvDanhSachTruongDaiHoc);

        dsTruong = new ArrayList<>();

        adapterTruong = new TruongAdapter(DanhSachTruongActivity.this ,R.layout.danhsachtruong, dsTruong);

        lvDanhSachTruongDaiHoc.setAdapter(adapterTruong);

        getData(url);
    }

    // get dữ liệu để đổ lên danh sách các trưởng để xem hoạt động tình nguyện cụ thể
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

    // xử lý tình kiếm trường theo mã trường
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_timkiem,menu);

        MenuItem  mnuSearch = menu.findItem(R.id.mnuSearch);

        SearchView searchView = (SearchView) mnuSearch.getActionView();


        if (mnuSearch != null) {
            searchView = (SearchView) mnuSearch.getActionView();
            if (searchView != null) {
                SearchViewCompat.setInputType(searchView, InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
            }
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterTruong.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
