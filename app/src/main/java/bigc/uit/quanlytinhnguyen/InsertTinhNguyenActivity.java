package bigc.uit.quanlytinhnguyen;

import android.app.DatePickerDialog;
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

public class InsertTinhNguyenActivity extends AppCompatActivity {

    TextView txtNgayBDInsert , txtNgayKTInsert ;
    EditText txtMaTNInsert , txtTenTNInsert , txtNoiDungInsert , txtSDTInsert , txtDiaDiemInsert , txtSLMaxInsert , txtSLMinInsert , txtMaTruongInsert , txtLinkAnhInsert ;
    ImageButton btnNgayBDInsert , btnNgayKTInsert , btnThemTNInsert ;

    String urlInsert = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/inserttinhnguyen.php";

    Calendar calendar = Calendar.getInstance();
    Calendar calendar1 = Calendar.getInstance();
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");

    String NgayBD , NgayKT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_tinh_nguyen);

        // ẩn ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();

        addEvents();
    }
//xử lý sự kiện button
    private void addEvents() {
        btnNgayBDInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyHienThiNgayBD();
            }
        });
        btnNgayKTInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyHienThiNgayKT();
            }
        });
        btnThemTNInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyThemHoatDongTinhNguyen();
            }
        });
    }

    // xử lý kiểm tra rỗng và xử lý insert
    private void xuLyThemHoatDongTinhNguyen() {
        String ma = txtMaTNInsert.getText().toString().trim();
        String ten = txtTenTNInsert.getText().toString().trim();
        String noiDung = txtNoiDungInsert.getText().toString().trim();
        String sdt = txtSDTInsert.getText().toString().trim();
        String diaDiem = txtDiaDiemInsert.getText().toString().trim();
        String SLMax = txtSLMaxInsert.getText().toString().trim();
        String SLMin = txtSLMinInsert.getText().toString().trim();
        String maTruong = txtMaTruongInsert.getText().toString().trim();
        String linkAnh = txtLinkAnhInsert.getText().toString().trim();
        String ngayBatDau = txtNgayBDInsert.getText().toString().trim();
        String ngayKetThuc = txtNgayKTInsert.getText().toString().trim();

        if(ma.isEmpty()||
                ten.isEmpty()||
                noiDung.isEmpty()||
                sdt.isEmpty()||
                diaDiem.isEmpty()||
                SLMax.isEmpty()||
                SLMin.isEmpty()||
                maTruong.isEmpty()||
                linkAnh.isEmpty()||
                ngayBatDau.isEmpty()||
                ngayKetThuc.isEmpty()
                )
        {
            Toast.makeText(InsertTinhNguyenActivity.this, "Bạn Vui Lòng Nhập Đầy Đủ Thông Tin !",Toast.LENGTH_SHORT).show();
        }
        else
        {
            InsertTinhNguyen (urlInsert);
        }
    }

    // thêm tình nguyện vào SQL
    private void InsertTinhNguyen(String urlInsert) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlInsert,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("succes"))
                        {
                            Toast.makeText(InsertTinhNguyenActivity.this, "Thêm Hoạt Động Tình Nguyện Thành Công !",Toast.LENGTH_SHORT).show();
                            //set dự liệu lại bằng null
                            txtMaTNInsert.setText("");
                            txtTenTNInsert.setText("");
                            txtNoiDungInsert.setText("");
                            txtSDTInsert.setText("");
                            txtDiaDiemInsert.setText("");
                            txtSLMaxInsert.setText("");
                            txtSLMinInsert.setText("");
                            txtMaTruongInsert.setText("");
                            txtLinkAnhInsert.setText("");
                            txtNgayBDInsert.setText("");
                            txtNgayKTInsert.setText("");

                        }
                        else
                        {
                            Toast.makeText(InsertTinhNguyenActivity.this, "Thêm Hoạt Động Tình Nguyện Thất Bại !",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InsertTinhNguyenActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("MATN", txtMaTNInsert.getText().toString().trim());
                params.put("TenTN" , txtTenTNInsert.getText().toString().trim());
                params.put("NoiDung" , txtNoiDungInsert.getText().toString().trim());
                params.put("SDT",txtSDTInsert.getText().toString().trim());
                params.put("NgayGioBatDau" , NgayBD);
                params.put("NgayGioKetThuc" ,NgayKT);
                params.put("DiaDiem",txtDiaDiemInsert.getText().toString().trim());
                params.put("SLMax" , txtSLMaxInsert.getText().toString().trim());
                params.put("SLMin" , txtSLMinInsert.getText().toString().trim());
                params.put("MAT" , txtMaTruongInsert.getText().toString().trim());
                params.put("HinhAnh" , txtLinkAnhInsert.getText().toString().trim());
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

    // xử lý ngày giờ
    private void xuLyHienThiNgayKT() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR , year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH , dayOfMonth);

                txtNgayKTInsert.setText(sdf1.format(calendar.getTime()));

                NgayKT = sdf2.format(calendar.getTime()).toString();

            }
        };
        DatePickerDialog datePickerDialong = new DatePickerDialog(InsertTinhNguyenActivity.this ,
                callBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialong.show();
    }

    // xử lý ngày giờ
    private void xuLyHienThiNgayBD() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR , year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH , dayOfMonth);

                txtNgayBDInsert.setText(sdf1.format(calendar.getTime()));

                NgayBD = sdf2.format(calendar.getTime()).toString();

            }
        };
        DatePickerDialog datePickerDialong = new DatePickerDialog(InsertTinhNguyenActivity.this ,
                callBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialong.show();
    }

   // ánh xạ
    private void addControls() {
        txtNgayBDInsert = (TextView) findViewById(R.id.txtNgayBDInsert);
        txtNgayKTInsert = (TextView) findViewById(R.id.txtNgayKTInsert);
        txtMaTNInsert = (EditText) findViewById(R.id.txtMaTinhNguyenInsert);
        txtTenTNInsert = (EditText) findViewById(R.id.txtTenTinhNguyenInsert);
        txtNoiDungInsert = (EditText) findViewById(R.id.txtNoiDungTinhNguyenInsert);
        txtSDTInsert = (EditText) findViewById(R.id.txtSDTInsert);
        txtDiaDiemInsert = (EditText) findViewById(R.id.txtDiaDiemInsert);
        txtSLMaxInsert = (EditText) findViewById(R.id.txtSLMaxInsert);
        txtSLMinInsert = (EditText) findViewById(R.id.txtSLMinInsert);
        txtMaTruongInsert = (EditText) findViewById(R.id.txtMaTruongInsert);
        txtLinkAnhInsert = (EditText) findViewById(R.id.txtLinkAnhInsert);
        btnNgayBDInsert = (ImageButton) findViewById(R.id.btnNgayBDInsert);
        btnNgayKTInsert = (ImageButton) findViewById(R.id.btnNgayKTInsert);
        btnThemTNInsert = (ImageButton) findViewById(R.id.btnThemInsert);
    }
}
