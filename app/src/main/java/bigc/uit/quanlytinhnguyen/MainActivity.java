package bigc.uit.quanlytinhnguyen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter.DangNhapAdapter;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.DangNhap;

public class MainActivity extends AppCompatActivity {

    // khởi tạo
    EditText txtTenDangNhap , txtMatKhau;
    Button btnDangNhap , btnDangKy;

    TextView txtTk , txtMk;

    String TenDangNhap , MatKhau ;

    String urlGetDaTa = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/getdangnhap.php";

    ArrayList<DangNhap> dsDangNhap;
    DangNhapAdapter adapterDangNhap;

    boolean checkNhapSai = false;
    String x = "admin";
    String y = "admin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ẩn actionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        //get dữ liệu đăng nhâpk
        getData(urlGetDaTa);

        addControls();

        addEvents();



    }

    private void addEvents() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyDangKy();
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyDangNhap();
            }
        });



    }

    // xử lý mở button đăng ký sẽ mở activity mới
    private void xuLyDangKy() {
        Intent intentControl = new Intent(MainActivity.this, DangKyActivity.class);
        startActivity(intentControl);
    }

    // xử lý đăng nhập  , kiểm tra thông tin , đúng sẽ đăng nhập , đúng admin sẽ qua bên phần quản lý của admin
    private void xuLyDangNhap() {

        TenDangNhap = txtTenDangNhap.getText().toString().trim();
        MatKhau = txtMatKhau.getText().toString().trim();

        boolean c = TenDangNhap.equals(x);
        boolean d = MatKhau.equals(y);

        if(TenDangNhap.isEmpty()||MatKhau.isEmpty())
        {
            Toast.makeText(MainActivity.this, "Bạn Vui Lòng Nhập Đầy Đủ Thông Tin !",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(c==true && d==true)
            {
                Intent intentControl = new Intent(MainActivity.this, QuanLyAdminActivity.class);
                startActivity(intentControl);
            }
            else
            {
                for (DangNhap dn :dsDangNhap)
                {
                    if(dn.getTaiKhoan().toString().trim().equals(TenDangNhap)==true && dn.getMatKhau().toString().trim().equals(MatKhau)==true)
                    {
                        checkNhapSai= true;
                        Intent intentControl = new Intent(MainActivity.this, ControlTinhNguyenActivity.class);
                        intentControl.putExtra("dataDangNhap" , dn.getMASV().toString() );
                        intentControl.putExtra("TaiKhoan" ,dn.getTaiKhoan().toString());
                        intentControl.putExtra("MatKhau", dn.getMatKhau().toString());
                        startActivity(intentControl);
                    }

                }
                if(checkNhapSai== false)
                {
                    Toast.makeText(MainActivity.this, "Bạn Nhập Sai Tài Khoản Hoặc Mật Khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //ánh xạ và get dữ liệu
    private void addControls() {
        txtTenDangNhap = (EditText) findViewById(R.id.txtTenDangNhapMain);
        txtMatKhau = (EditText) findViewById(R.id.txtMatKhauMain);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKy = (Button) findViewById(R.id.btnDangKyCT);

        txtTk = (TextView) findViewById(R.id.txtTenTK);
        txtMk = (TextView) findViewById(R.id.txtMK);

        dsDangNhap = new ArrayList<>();

        getData(urlGetDaTa);

    }
    // get dữ liệu đăng nhập để so sách
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
                                dsDangNhap.add(new DangNhap(
                                        object.getString("TaiKhoan"),
                                        object.getString("MatKhau"),
                                        object.getString("MASV")

                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // de cap nhap lai du lieu
                      //  adapterDangNhap.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }

}
