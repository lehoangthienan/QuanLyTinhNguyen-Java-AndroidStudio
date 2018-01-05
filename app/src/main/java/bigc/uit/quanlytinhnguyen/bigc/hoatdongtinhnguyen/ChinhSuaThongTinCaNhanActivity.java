package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen;

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

import bigc.uit.quanlytinhnguyen.MainActivity;
import bigc.uit.quanlytinhnguyen.R;

public class ChinhSuaThongTinCaNhanActivity extends AppCompatActivity {

    // Khởi tạo
    EditText   txtTenSinhVienTDTT,txtMaTruongTDTT ,txtNgaySinhTDTT ;
    ImageButton btnNgaySinhTDTT ,btnCapNhapTDTT ;
    TextView txtMaSinhVienTDTT;

    //Khởi tạo lịch và formart ngày tháng năm theo định dạng
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
    String ngaySinh ,ngaySinh2;

    // Khởi tạo biến để lưu giá trị bên Control đẩy qua
    String MASV , TaiKhoan  , hoTen ,maTruong , ngaySinh1;

    //Khởi tạo biến lưu đường dẩn xử lý dữ liệu  MySQL
    String url1 , url2 , url3 ;

    //Biến check thêm thành công và thất bại
    Boolean check1 , check2 , check3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin_ca_nhan);

        // ẩn thanh ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        // khởi tạo các item để ánh xạ
        addControls();

        // khởi tạo các điều kiện
        addEvents();
    }

    private void addEvents() {

        // xử lý xự kiện Nhấn vào button ngày sinh
        btnNgaySinhTDTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check3 = true;
                //xử lý get ngày tháng năm mà người dùng chọn
                xuLyHienThiDataPicker();
            }
        });
        //xử lý nhấn button cập nhập thông tin cá nhân
        btnCapNhapTDTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // xử lý  nếu không nhấn vào button ngày sinh để thay đổi ngày sinh
                // thì sẽ lấy dữ liệu cũ và cập nhập lại ,
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

// xử lý cập nhập dữ liệu
    private void xuLyCapNhapSinhVien() {
        //đọc Json và đưa dữ liệu lên
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // trên file dữ liệu php có 2 giá trị trả về khi đẩy dữ liệu lên succes nếu thành công và failure nếu thất bại , check kiểm tra và thông báo cho người dùng biết .
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
                    //check lỗi nếu có
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChinhSuaThongTinCaNhanActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            //put dữ liệu lên MYSQL
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("TenSV" , txtTenSinhVienTDTT.getText().toString().trim());
                params.put("MAT",txtMaTruongTDTT.getText().toString().trim());
                params.put("NgaySinh" , ngaySinh2.toString());
                return params;
            }

        };
        requestQueue.add(stringRequest);

    }

    // xử lý nhấn vào button chọn ngày sinh
    private void xuLyHienThiDataPicker() {
        //định dạng
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR , year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH , dayOfMonth);

                // xử lý hiện ngày tháng năm sinh lên textview theo định dạng ngày của người việt nam
                txtNgaySinhTDTT.setText(sdf1.format(calendar.getTime()));
                //lưu dữ liệu theo định dạng trên MYSQL để lưu xuống DATABASE .
                ngaySinh = sdf2.format(calendar.getTime()).toString();
            }
        };
        //định dạng
        DatePickerDialog datePickerDialong = new DatePickerDialog(ChinhSuaThongTinCaNhanActivity.this ,
                callBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialong.show();
    }

    private void addControls() {
        // ánh xạ
        txtMaSinhVienTDTT = (TextView) findViewById(R.id.txtMaSinhVienTDTT);
        txtTenSinhVienTDTT = (EditText) findViewById(R.id.txtTenSinhVienTDTT);
        txtMaTruongTDTT = (EditText) findViewById(R.id.txtMaTruongTDTT);
        txtNgaySinhTDTT = (EditText) findViewById(R.id.txtNgaySinhTDTT);
        btnNgaySinhTDTT = (ImageButton) findViewById(R.id.btnNgaySinhTDTT);
        btnCapNhapTDTT = (ImageButton) findViewById(R.id.btnCapNhapTDTT);

        // lấy dữ liệu được chuyển qua từ control Tình nguyện là Mã sinh viên ... để xử lý dữ liệu khi cập nhập
        Intent intent = getIntent();
        MASV = intent.getStringExtra("MASV");
        TaiKhoan = intent.getStringExtra("TaiKhoan");
        hoTen = intent.getStringExtra("HoTen");
        maTruong = intent.getStringExtra("MaTruong");
        ngaySinh1 = intent.getStringExtra("NgaySinh");

        // đổ dữ liệu từ SQL lên textview
        txtMaSinhVienTDTT.setText(MASV);
        txtTenSinhVienTDTT.setText(hoTen);
        txtMaTruongTDTT.setText(maTruong);
        txtNgaySinhTDTT.setText(ngaySinh1);

        //set bollen kiểm tra
        check1=false;
        check2=false;
        check3=false;

        // đường dẩn thao tác SQL
        url1="http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatesinhvien.php?MASV1="+MASV;
        url2="http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatemasinhviendangnhap.php?TaiKhoan="+TaiKhoan;
        url3="http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatemasinhvienhoatdongtinhnguyen.php?MASV1="+MASV;
    }
}
