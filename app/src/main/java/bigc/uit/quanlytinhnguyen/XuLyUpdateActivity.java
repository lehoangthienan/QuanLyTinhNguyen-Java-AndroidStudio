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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.TinhNguyen1;

public class XuLyUpdateActivity extends AppCompatActivity {

    // khởi tạo
    EditText txtTenTNUpdate ,
            txtDiaDiemUpdate ,
            txtSDTUpdate ,
            txtNoiDungUpdate ,
            txtSLMaxUpdate ,
            txtSLMinUpdate ,
            txtMaTruongUpdate ,
            txtinkAnhUpdate;

    TextView txtNgayBDUpdate , txtNgayKTUpdate;
    ImageButton btnNgayBDUpdate , btnNgayKTUpdate , btnCapNhapUpdate;

    String url ,MATN , urlUpdate;

    Calendar calendar = Calendar.getInstance();
    Calendar calendar1 = Calendar.getInstance();
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_ly_update);

        // ẩn ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();

        addEvents();
    }

    // xử lý xự kiện button
    private void addEvents() {
        btnNgayBDUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyHienThiNgayBD();
            }
        });
        btnNgayKTUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyHienThiNgayKT();
            }
        });
        btnCapNhapUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyUpdate();
            }
        });
    }

    private void xuLyUpdate() {
        // get dữ liệu
        // nếu trống báo người dùng biết để nhập đầy đủ
        String ten = txtTenTNUpdate.getText().toString().trim();
        String noiDung = txtNoiDungUpdate.getText().toString().trim();
        String sdt = txtSDTUpdate.getText().toString().trim();
        String diaDiem = txtDiaDiemUpdate.getText().toString().trim();
        String SLMax = txtSLMaxUpdate.getText().toString().trim();
        String SLMin = txtSLMinUpdate.getText().toString().trim();
        String maTruong = txtMaTruongUpdate.getText().toString().trim();
        String linkAnh = txtinkAnhUpdate.getText().toString().trim();
        String ngayBatDau = txtNgayBDUpdate.getText().toString().trim();
        String ngayKetThuc = txtNgayKTUpdate.getText().toString().trim();

        if(
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
            Toast.makeText(XuLyUpdateActivity.this, "Bạn Vui Lòng Nhập Đầy Đủ Thông Tin !",Toast.LENGTH_SHORT).show();
        }
        // nhập đầy đủ tiến hành update
        else
        {
            UpdateTinhNguyen (urlUpdate);
        }
    }

    // update thông tin tình nguyện
    private void UpdateTinhNguyen(String urlUpdate) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlUpdate,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("succes"))
                        {
                            Toast.makeText(XuLyUpdateActivity.this, "Cập Nhập Hoạt Động Tình Nguyện Thành Công !",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(XuLyUpdateActivity.this, "Cập Nhập Hoạt Động Tình Nguyện Thất Bại !",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(XuLyUpdateActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("TenTN" , txtTenTNUpdate.getText().toString().trim());
                params.put("NoiDung" , txtNoiDungUpdate.getText().toString().trim());
                params.put("SDT",txtSDTUpdate.getText().toString().trim());
                params.put("NgayGioBatDau" , txtNgayBDUpdate.getText().toString().trim());
                params.put("NgayGioKetThuc" ,txtNgayKTUpdate.getText().toString().trim());
                params.put("DiaDiem",txtDiaDiemUpdate.getText().toString().trim());
                params.put("SLMax" , txtSLMaxUpdate.getText().toString().trim());
                params.put("SLMin" , txtSLMinUpdate.getText().toString().trim());
                params.put("MAT" , txtMaTruongUpdate.getText().toString().trim());
                params.put("HinhAnh" , txtinkAnhUpdate.getText().toString().trim());
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

    // xử lý hiện thị ngày
    private void xuLyHienThiNgayKT() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR , year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH , dayOfMonth);

                txtNgayKTUpdate.setText(sdf2.format(calendar.getTime()));

              //  NgayKT = sdf2.format(calendar.getTime()).toString();

            }
        };
        DatePickerDialog datePickerDialong = new DatePickerDialog(XuLyUpdateActivity.this ,
                callBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialong.show();
    }
    // xử lý hiện thị ngày
    private void xuLyHienThiNgayBD() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR , year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH , dayOfMonth);

                txtNgayBDUpdate.setText(sdf2.format(calendar.getTime()));

            //    NgayBD = sdf2.format(calendar.getTime()).toString();

            }
        };
        DatePickerDialog datePickerDialong = new DatePickerDialog(XuLyUpdateActivity.this ,
                callBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialong.show();
    }
    // ánh xạ và khởi tạo và get dữ liệu
    private void addControls() {
        txtTenTNUpdate = (EditText) findViewById(R.id.txtTenTNUpdate);
        txtDiaDiemUpdate = (EditText) findViewById(R.id.txtDiaDiemUpdate);
        txtSDTUpdate = (EditText) findViewById(R.id.txtSDTUpdate);
        txtNoiDungUpdate = (EditText) findViewById(R.id.txtNoiDungTNUpdate);
        txtSLMaxUpdate = (EditText) findViewById(R.id.txtSLMaxUpdate);
        txtSLMinUpdate = (EditText) findViewById(R.id.txtSLMinUpdate);
        txtMaTruongUpdate = (EditText) findViewById(R.id.txtMaTruongUpdate);
        txtinkAnhUpdate = (EditText) findViewById(R.id.txtLinkAnhUpdate);

        txtNgayBDUpdate = (TextView) findViewById(R.id.txtNgayBDUpdate);
        txtNgayKTUpdate = (TextView) findViewById(R.id.txtNgayKTUpdate);

        btnNgayBDUpdate = (ImageButton) findViewById(R.id.btnNgayBDUpdate);
        btnNgayKTUpdate = (ImageButton) findViewById(R.id.btnNgayKTUpdate);
        btnCapNhapUpdate = (ImageButton) findViewById(R.id.btnCapNhapUpdate);

        Intent intent = getIntent();
        MATN = intent.getStringExtra("MATN");

        url = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/gettinhnguyentheomact.php?MATN1="+MATN;
        urlUpdate="http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatetinhnguyen.php?MATN1="+MATN;

        getdata(url);
    }

    //get thông tin tình nguyện
    private void getdata(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++)
                        {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                TinhNguyen1 tinhnguyen =new Gson().fromJson(String.valueOf(object), TinhNguyen1.class);
                                txtTenTNUpdate.setText(tinhnguyen.getTenTN().toString());
                                txtDiaDiemUpdate.setText(tinhnguyen.getDiaDiem().toString());
                                txtNgayKTUpdate.setText(tinhnguyen.getNgayGioKetThuc().toString());
                                txtNgayBDUpdate.setText(tinhnguyen.getNgayGioBatDau().toString());
                                txtSLMaxUpdate.setText(String.valueOf(tinhnguyen.getSLMax()));
                                txtSLMinUpdate.setText(String.valueOf(tinhnguyen.getSLMin()));
                                txtSDTUpdate.setText(String.valueOf(tinhnguyen.getSDT()));
                                txtNoiDungUpdate.setText(tinhnguyen.getNoiDung().toString());
                                txtMaTruongUpdate.setText(tinhnguyen.getMAT().toString());
                                txtinkAnhUpdate.setText(tinhnguyen.getHinhAnh().toString());
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
                        Toast.makeText(XuLyUpdateActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
