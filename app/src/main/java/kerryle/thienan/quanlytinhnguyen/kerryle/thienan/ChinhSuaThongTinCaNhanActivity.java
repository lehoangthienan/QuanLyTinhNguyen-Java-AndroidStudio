package kerryle.thienan.quanlytinhnguyen.kerryle.thienan;

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

import kerryle.thienan.quanlytinhnguyen.MainActivity;
import kerryle.thienan.quanlytinhnguyen.R;

public class ChinhSuaThongTinCaNhanActivity extends AppCompatActivity {

    // Khởi tạo
    EditText   txtTenSinhVienTDTT,txtTenTruongTDTT ,txtNgaySinhTDTT ;
    ImageButton btnNgaySinhTDTT ,btnCapNhapTDTT ;
    TextView txtMaSinhVienTDTT;

    //Khởi tạo lịch và formart ngày tháng năm theo định dạng
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
    String ngaySinh ,ngaySinh2;

    // Khởi tạo biến để lưu giá trị bên Control đẩy qua
    String MASV , TaiKhoan  , hoTen ,tenTruong , ngaySinh1;

    //Khởi tạo biến lưu đường dẩn xử lý dữ liệu  MySQL
    String url1 , url2 , url3 ;

    //Biến check thêm thành công và thất bại
    Boolean check1 , check2 , check3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin_ca_nhan);

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();

        addEvents();
    }

    private void addEvents() {

        btnNgaySinhTDTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check3 = true;
                xuLyHienThiDataPicker();
            }
        });
        btnCapNhapTDTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  xuLyCapNhapMASVTaiKhoan();
              //  xuLyCapNhapMASVHoatDong();
                if(check3==true)
                {
                    ngaySinh2 =ngaySinh;
                }
                else
                {
                    ngaySinh2= ngaySinh1;
                }
                xuLyCapNhapSinhVien();
            }
        });
        if(btnNgaySinhTDTT.hasOnClickListeners()==false)
        {
            ngaySinh = ngaySinh1.toString();
        }
    }

//    private void xuLyCapNhapMASVHoatDong() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                url3,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if(response.trim().equals("succes"))
//                        {
//                            check1 = true;
//                        }
//                        else
//                        {
//                            check1 = false;
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ChinhSuaThongTinCaNhanActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_LONG).show();
//                        Log.d("AAA" , "ERROR!\n"+error.toString());
//                    }
//                }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String ,String> params = new HashMap<>();
//                params.put("MASV",txtMaSinhVienTDTT.getText().toString().trim());
//                return params;
//            }
//
//        };
//        requestQueue.add(stringRequest);
//    }

//    private void xuLyCapNhapMASVTaiKhoan() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                url2,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if(response.trim().equals("succes"))
//                        {
//                            check2 = true;
//                        }
//                        else
//                        {
//                            check2 = false;
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ChinhSuaThongTinCaNhanActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_LONG).show();
//                        Log.d("AAA" , "ERROR!\n"+error.toString());
//                    }
//                }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String ,String> params = new HashMap<>();
//                params.put("MASV",txtMaSinhVienTDTT.getText().toString().trim());
//                return params;
//            }
//
//        };
//        requestQueue.add(stringRequest);
//
//    }

    private void xuLyCapNhapSinhVien() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("succes"))
                        {
                                Toast.makeText(ChinhSuaThongTinCaNhanActivity.this, "Cập Nhập Thông Tin Thành Công !",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ChinhSuaThongTinCaNhanActivity.this, MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(ChinhSuaThongTinCaNhanActivity.this, "Cập Nhập Thông Tin Thất Bại !",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChinhSuaThongTinCaNhanActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("TenSV" , txtTenSinhVienTDTT.getText().toString().trim());
                params.put("TenTruong",txtTenTruongTDTT.getText().toString().trim());
                params.put("NgaySinh" , ngaySinh2.toString());
                return params;
            }

        };
        requestQueue.add(stringRequest);

    }


    private void xuLyHienThiDataPicker() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR , year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH , dayOfMonth);

                txtNgaySinhTDTT.setText(sdf1.format(calendar.getTime()));
                ngaySinh = sdf2.format(calendar.getTime()).toString();
            }
        };
        DatePickerDialog datePickerDialong = new DatePickerDialog(ChinhSuaThongTinCaNhanActivity.this ,
                callBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialong.show();
    }

    private void addControls() {
        txtMaSinhVienTDTT = (TextView) findViewById(R.id.txtMaSinhVienTDTT);
        txtTenSinhVienTDTT = (EditText) findViewById(R.id.txtTenSinhVienTDTT);
        txtTenTruongTDTT = (EditText) findViewById(R.id.txtTenTruongTDTT);
        txtNgaySinhTDTT = (EditText) findViewById(R.id.txtNgaySinhTDTT);
        btnNgaySinhTDTT = (ImageButton) findViewById(R.id.btnNgaySinhTDTT);
        btnCapNhapTDTT = (ImageButton) findViewById(R.id.btnCapNhapTDTT);

        Intent intent = getIntent();
        MASV = intent.getStringExtra("MASV");
        TaiKhoan = intent.getStringExtra("TaiKhoan");
        hoTen = intent.getStringExtra("HoTen");
        tenTruong = intent.getStringExtra("TenTruong");
        ngaySinh1 = intent.getStringExtra("NgaySinh");

        txtMaSinhVienTDTT.setText(MASV);
        txtTenSinhVienTDTT.setText(hoTen);
        txtTenTruongTDTT.setText(tenTruong);
        txtNgaySinhTDTT.setText(ngaySinh1);

        check1=false;
        check2=false;
        check3=false;

        url1="http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatesinhvien.php?MASV1="+MASV;
        url2="http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatemasinhviendangnhap.php?TaiKhoan="+TaiKhoan;
        url3="http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatemasinhvienhoatdongtinhnguyen.php?MASV1="+MASV;
    }
}
