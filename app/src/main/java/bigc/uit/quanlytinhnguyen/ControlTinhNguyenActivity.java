package bigc.uit.quanlytinhnguyen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.ChinhSuaThongTinCaNhanActivity;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.ThayDoiMatKhau;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter.HoatDongDangKyAdapter;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter.TinhNguyenAdapter;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.HoatDongDangKy;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.NguoiDung;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.SoHoatDongThamGia;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.TenTruong;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.TinhNguyen;

public class ControlTinhNguyenActivity extends AppCompatActivity {

    // khởi tạo list view
    // khởi tạo adapter
    // khởi tạo danh sách
    ListView lvDanhSachTinhNguyen, lvDanhSachTinhNguyenDaDangKy , lvChiTietNguoiDung;

    ArrayList<TinhNguyen> dsTinhNguyen;
    TinhNguyenAdapter adapterTinhNguyen;


    ArrayList<HoatDongDangKy> dsHoatDongDangKy;
    HoatDongDangKyAdapter adapterHoatDongDangKy;

    // khởi tạo chuỗi thao tác với MYSQL
    String urlGetDaTa1;
    String urlGetDaTa = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/gettinhnguyen.php";
    String urlGetDaTa2;
    String urlGetDaTa3;
    String url1 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/inserttinhnguyensinhvien.php";
    String urlGetMa;
    // khởi tạo Tabhost
    TabHost tabHost ;

    // khai báo biến static để dùng chung với nhiều activity
    public static String maSinhVien;
    String TK , MK;

    // khởi tạo item
    TextView txtHoTenCTND , txtNamSinhCTND , txtMaSoSinhVienCTND ,txtTenTruongDaiHocCTND ,txtSoHoatDongThamGiaCTND , txtMaTruongCTND;
    ImageButton btnThayDoiThongTinCaNhan , btnThayDoiMatKhau ;

   // Button btnChiTiet;

   // List<MaTinhNguyenSinhVien> dsMaTinhNguyen ;

    // khởi tạo SearchView
    SearchView sv;

    String  urlGetTenTruong , maTruong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_tinh_nguyen);

        // ánh xạ , khởi tạo
        addControls();

        //xử lý sự kiện
        addEvents();

    }

    // xử lý xự kiện
    private void addEvents()
    {
        // xử lý nhấn button thay đổi mật khẩu , mở activity  mới và đẩy dữ liệu qua để xử lý
        btnThayDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ThayDoiMK = new Intent(ControlTinhNguyenActivity.this , ThayDoiMatKhau.class);
                ThayDoiMK.putExtra("TaiKhoan" , TK);
                ThayDoiMK.putExtra("MatKhau" , MK);
                startActivity(ThayDoiMK);
            }
        });
        // xử lý button thay đổi thông tin cái nhân mở activity mới vầ đẩy dữ liệu qua
        btnThayDoiThongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ThayDoiMK = new Intent(ControlTinhNguyenActivity.this , ChinhSuaThongTinCaNhanActivity.class);
                ThayDoiMK.putExtra("TaiKhoan" , TK);
                ThayDoiMK.putExtra("MASV" , txtMaSoSinhVienCTND.getText().toString());
                ThayDoiMK.putExtra("HoTen" , txtHoTenCTND.getText().toString());
                ThayDoiMK.putExtra("MaTruong" , txtMaTruongCTND.getText().toString());
                ThayDoiMK.putExtra("NgaySinh" , txtNamSinhCTND.getText().toString());

                startActivity(ThayDoiMK);
            }
        });
        // xử lý thay đổi giữa các tabhost
        // t1 là tab1
        //t2 là tab2
        //t3 là tab3
        // t1 , t2 ,t3 là ID của từng tabspect
        // t1 là tab danh sách chung
        // t2 là tab danh sách đã đăng ký
        // t3 là tad thông tin cá nhân của người dùng
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1"))
                {
                    xuLyDangKyNhanh();
                }
                if (tabId.equalsIgnoreCase("t2"))
                {
                    xuLyDSDangKyNhanh();
                }
                if (tabId.equalsIgnoreCase("t3"))
                {
                    xuLyShowThongTinNguoiDung();
                }
            }
        });
    }

    // xử lý show thông tin , get thông tin và get tên trường
    private void xuLyShowThongTinNguoiDung() {
        getData1(urlGetDaTa1);
        getData3(urlGetDaTa3);
    }

    // clean  dữ liêu
    // cập nhâp lại danh sách
    //và notifyDataSetChanged để nhận biết thay đổi và thay đổi trên giao diện dữ  liệu
    private void xuLyDSDangKyNhanh() {
        dsHoatDongDangKy.clear();
        getData2(urlGetDaTa2);
        adapterHoatDongDangKy.notifyDataSetChanged();
    }
    // clean  dữ liêu
    // cập nhâp lại danh sách
    //và notifyDataSetChanged để nhận biết thay đổi và thay đổi trên giao diện dữ  liệu
    private void xuLyDangKyNhanh() {
        dsTinhNguyen.clear();
        getData(urlGetDaTa);
        adapterTinhNguyen = new TinhNguyenAdapter(ControlTinhNguyenActivity.this ,R.layout.danhsachtinhnguyen,dsTinhNguyen);
        lvDanhSachTinhNguyen.setAdapter(adapterTinhNguyen);
        adapterTinhNguyen.notifyDataSetChanged();
    }


//    private void dangKyNhanh(String url1) {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                url1,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if(response.trim().equals("succes"))
//                        {
//                            Toast.makeText(ControlTinhNguyenActivity.this, "Đăng Kí Tham Gia Thành Công !",Toast.LENGTH_LONG).show();
//                        }
//                        else
//                        {
//                            Toast.makeText(ControlTinhNguyenActivity.this, "Đăng Kí Tham Gia Thất Bại !",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ControlTinhNguyenActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_LONG).show();
//                        Log.d("AAA" , "ERROR!\n"+error.toString());
//                    }
//                }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String ,String> params = new HashMap<>();
//                params.put("MATN",maTinhNguyen.toString());
//                params.put("MASV" ,maSinhVien.toString());
//                return params;
//            }
//
//        };
//        requestQueue.add(stringRequest);
//    }


    // xử lý ánh xạ và khởi tạo
    private void addControls() {
        // ánh xạ
        txtHoTenCTND = (TextView) findViewById(R.id.txtHoTenCTND);
        txtNamSinhCTND = (TextView) findViewById(R.id.txtNamSinhCTND);
        txtMaSoSinhVienCTND = (TextView) findViewById(R.id.txtMaSoSinhVienCTND);
        txtTenTruongDaiHocCTND = (TextView) findViewById(R.id.txtTenTruongDaiHocCTND);
        txtSoHoatDongThamGiaCTND = (TextView) findViewById(R.id.txtSoHoatDongThamGiaCTND);
        btnThayDoiThongTinCaNhan = (ImageButton) findViewById(R.id.btnThayDoiThongTinCaNhan);
        btnThayDoiMatKhau = (ImageButton) findViewById(R.id.btnThayDoiMatKhau);
        txtMaTruongCTND= (TextView) findViewById(R.id.txtMaTruongCTND);

       // btnChiTiet = (Button) findViewById(R.id.btnChiTiet);

        // khởi tạo TabHost
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        // khởi tạo TabSpec
        TabHost.TabSpec tab1 =tabHost.newTabSpec("t1");
        tab1.setIndicator("" ,getResources().getDrawable(R.drawable.danhsachtinhnguyen));
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 =tabHost.newTabSpec("t2");
        tab2.setIndicator("" ,getResources().getDrawable(R.drawable.listdk));
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3 =tabHost.newTabSpec("t3");
        tab3.setIndicator("" ,getResources().getDrawable(R.drawable.jobsearch));
        tab3.setContent(R.id.tab3);
        tabHost.addTab(tab3);

        // khởi tạo list view
        lvDanhSachTinhNguyen = (ListView) findViewById(R.id.lvDanhSachTinhNguyen);
        lvDanhSachTinhNguyenDaDangKy = (ListView) findViewById(R.id.lvDanhDachTinhNguyenDaDangKy);

        // khởi tạo và setAdapter
        // nguyên lý hoạt động
        // dữ liệu sẽ được đưa vào mảng và thông qua adapter đổ lên layout và đổ layout đó lên listview
        dsTinhNguyen = new ArrayList<>();
        adapterTinhNguyen = new TinhNguyenAdapter(ControlTinhNguyenActivity.this ,R.layout.danhsachtinhnguyen,dsTinhNguyen);
        lvDanhSachTinhNguyen.setAdapter(adapterTinhNguyen);

         //lấy dữ liệu bên acticity đăng nhập chuyển qua
        Intent intent = getIntent();
        maSinhVien = intent.getStringExtra("dataDangNhap");
        TK = intent.getStringExtra("TaiKhoan");
        MK = intent.getStringExtra("MatKhau");

        //đường dẩn thao tác
        urlGetDaTa1 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/getsinhvien.php?MASV="+maSinhVien;
        urlGetDaTa2 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/getdanhsachdangky.php?MASV="+maSinhVien;
        urlGetDaTa3 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/getsoluonghoatdongthamgia.php?MASV="+maSinhVien;
        urlGetMa= "http://quanlyhoatdongtinhnguyen.000webhostapp.com/gettinhnguyensinhvien.php?MASV="+maSinhVien;

        // khởi tạo và setAdapter
        // nguyên lý hoạt động
        // dữ liệu sẽ được đưa vào mảng và thông qua adapter đổ lên layout và đổ layout đó lên listview
        dsHoatDongDangKy = new ArrayList<>();
        adapterHoatDongDangKy = new HoatDongDangKyAdapter(ControlTinhNguyenActivity.this , R.layout.danhsachdangky ,dsHoatDongDangKy);
        lvDanhSachTinhNguyenDaDangKy.setAdapter(adapterHoatDongDangKy);

        //get dữ liệu
        getData(urlGetDaTa);
        getData1(urlGetDaTa1);
        getData2(urlGetDaTa2);
        getData3(urlGetDaTa3);



    }

    // get tên trường
    private void getTenTruong(String urlGetTenTruong) {
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
            Request.Method.GET, urlGetTenTruong, null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i = 0; i < response.length(); i++)
                    {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            TenTruong tentruong =new Gson().fromJson(String.valueOf(object), TenTruong.class);
                            txtTenTruongDaiHocCTND.setText(tentruong.getTenTruong());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    // de cap nhap lai du lieu
                    //adapterNguoiDung.notifyDataSetChanged();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ControlTinhNguyenActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            }
    );
    requestQueue.add(jsonArrayRequest);
}


// get số lượng hoạt động đã tham gia
    private void getData3(String urlGetDaTa3) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, urlGetDaTa3, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++)
                        {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                 SoHoatDongThamGia SOLUONG =new Gson().fromJson(String.valueOf(object), SoHoatDongThamGia.class);
                                txtSoHoatDongThamGiaCTND.setText("Số Hoạt Động Tình Nguyện Đã Tham Gia : " + SOLUONG.getSOLUONG().toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // de cap nhap lai du lieu
                        //adapterNguoiDung.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ControlTinhNguyenActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    // get dữ liệu  cho listview danh sách hoạt động đã đăng ký
    // cách làm tương tự
    // đổ dữ liệu lên mảng , và thông qua adapter đổ lên listview
    private void getData2(String urlGetDaTa2) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, urlGetDaTa2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                dsHoatDongDangKy.add(new HoatDongDangKy(
                                        object.getString("MATN"),
                                        object.getString("TenTN"),
                                        object.getString("DiaDiem"),
                                        object.getString("NgayGioBatDau"),
                                        object.getString("NgayGioKetThuc")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // de cap nhap lai du lieu
                        adapterHoatDongDangKy.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ControlTinhNguyenActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }

    //get dữ liệu người dùng
    private void getData1(String url1) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++)
                        {
                            try {
                                JSONObject object = response.getJSONObject(i);
                               NguoiDung nguoiDung =new Gson().fromJson(String.valueOf(object), NguoiDung.class);
                                txtHoTenCTND.setText(nguoiDung.getTenSV().toString());
                                txtMaSoSinhVienCTND.setText(nguoiDung.getMASV().toString());
                                txtMaTruongCTND.setText(nguoiDung.getMAT().toString());
                                txtNamSinhCTND.setText(nguoiDung.getNgaySinh().toString());
                                maTruong = nguoiDung.getMAT().toString();
                                urlGetTenTruong="http://quanlyhoatdongtinhnguyen.000webhostapp.com/gettentruongtheomatruong.php?MAT1="+maTruong;
                                getTenTruong(urlGetTenTruong);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // de cap nhap lai du lieu
                        //adapterNguoiDung.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ControlTinhNguyenActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

// get dũng liệu thông tin tình nguyện để đổ lên listview danh sách tất cả các hoạt động tình nguyện
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
                        Toast.makeText(ControlTinhNguyenActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }

    // xử lý search view , tìm kiếm trong danh sách tất cả các hoạt động tình nguyện
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // khởi tạo MenuInflater
        // ánh xạ ID
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_timkiem,menu);

        MenuInflater   inflater1 =getMenuInflater();
        inflater1.inflate(R.menu.menu_main,menu);

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
                // xử lý fillter  dữ liệu và tìm kiếm
                adapterTinhNguyen.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    // option menu
    // ứng với từng id sẽ làm 1 việc khác nhau
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnuXemTinhNguyenTheoTruong)
        {
            startActivity(new Intent(ControlTinhNguyenActivity.this , DanhSachTruongActivity.class));
        }
//        else if (item.getItemId()==R.id.mnuThongTinUngDung)
//        {
//            startActivity(new Intent(ControlTinhNguyenActivity.this , ThongTinUngDungActivity.class));
//        }
        else if (item.getItemId()==R.id.mnuDangXuat)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
