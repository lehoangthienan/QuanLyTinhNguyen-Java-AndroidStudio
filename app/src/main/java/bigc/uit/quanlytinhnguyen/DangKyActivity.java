package bigc.uit.quanlytinhnguyen;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {

    EditText txtMaSinhVien , txtHoTen , txtTenTruong , txtTenDangNhap  , txtMatKhau;
    ImageButton btnDangKy ;
    ImageButton btnHuyDangKy;
    ImageButton btnDate;
    TextView txtDate;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");

    String url1 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/insertdangky.php";
    String url2 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/insertsinhvien.php";

    boolean check = false;
    String NgaySinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Hoten =txtHoTen.getText().toString().trim();
                String TenTruong = txtTenTruong.getText().toString().trim();
                String TenDangNhap = txtTenDangNhap.getText().toString().trim();
                String MatKhau = txtMatKhau.getText().toString().trim();
                String MaSinhVien =txtMaSinhVien.getText().toString().trim();
                if(Hoten.isEmpty()||TenTruong.isEmpty()||TenDangNhap.isEmpty()||MatKhau.isEmpty()||MaSinhVien.isEmpty())
                {
                    Toast.makeText(DangKyActivity.this, "Bạn Vui Lòng Nhập Đầy Đủ Thông Tin !",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ThemTaiKhoang(url1);
                    ThemSinhVien(url2);
                }

            }
        });
        btnHuyDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyHienThiDataPicker();
            }
        });
    }

    private void xuLyHienThiDataPicker() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR , year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH , dayOfMonth);

                txtDate.setText(sdf1.format(calendar.getTime()));

                NgaySinh = sdf2.format(calendar.getTime()).toString();

            }
        };
        DatePickerDialog datePickerDialong = new DatePickerDialog(DangKyActivity.this ,
                callBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialong.show();
    }

    private void ThemSinhVien(String url2) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("succes"))
                        {
                            check = true;
                        }
                        else
                        {
                            check = false;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangKyActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("MASV",txtMaSinhVien.getText().toString().trim());
                params.put("TenSV" , txtHoTen.getText().toString().trim());
                params.put("MAT",txtTenTruong.getText().toString().trim());
                params.put("NgaySinh" , NgaySinh);
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

    private void ThemTaiKhoang(String url1) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("succes"))
                        {
                            if(check == true)
                            {
                                Toast.makeText(DangKyActivity.this, "Đăng Kí Thành Công !",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DangKyActivity.this , MainActivity.class));
                            }
                          else
                            {
                                Toast.makeText(DangKyActivity.this, "Lỗi Đăng Ký Về Thông Tin Cá Nhân !",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(DangKyActivity.this, "Lỗi Đăng Ký Về Tài Khoản  !",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangKyActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("TaiKhoan",txtTenDangNhap.getText().toString().trim());
                params.put("MatKhau" , txtMatKhau.getText().toString().trim());
                params.put("MASV" , txtMaSinhVien.getText().toString().trim());
                return params;
            }

        };
        requestQueue.add(stringRequest);

    }

    private void addControls() {
        txtHoTen = (EditText) findViewById(R.id.txtHoten);
        txtMaSinhVien = (EditText) findViewById(R.id.txtMaSinhVien);
        txtTenTruong = (EditText) findViewById(R.id.txtTenTruong);
        txtTenDangNhap = (EditText) findViewById(R.id.txtTenDangNhap);
        txtMatKhau = (EditText) findViewById(R.id.txtMatKhau);
        btnDangKy = (ImageButton) findViewById(R.id.btnDangKyDK);
        btnHuyDangKy = (ImageButton) findViewById(R.id.btnHuyDangKyDK);
        btnDate = (ImageButton) findViewById(R.id.btnDate);
        txtDate = (TextView) findViewById(R.id.txtDate);
    }
}
