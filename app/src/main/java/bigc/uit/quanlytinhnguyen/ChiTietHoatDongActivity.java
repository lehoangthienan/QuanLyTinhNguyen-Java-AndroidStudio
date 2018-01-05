package bigc.uit.quanlytinhnguyen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.SoLuongSinhVienToiDa;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.SoLuongThamGia;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.TenTruong;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.TinhNguyen1;

import static bigc.uit.quanlytinhnguyen.ControlTinhNguyenActivity.maSinhVien;

public class ChiTietHoatDongActivity extends AppCompatActivity {

    // khởi tạo
    String MATN ;

    ImageButton btnGoiCT , btnDangKyCT , btnHuyDKCT;
    TextView txtTenTinhNguyenCT ,txtDiaDiemCT,txtNgayKTCT,txtNgayBDCT,txtSLMAXCT,txtSLMINCT,txtSLThamGiaCT,txtSDT,txtNoiDung , txtTenTruongDaiHocCT;
    ImageView imgHinhAnh;

    // khởi tạo các đường dẩn thao tác với MYSQL
    String url;

    String url1 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/inserttinhnguyensinhvien.php";

    String url2 ="http://quanlyhoatdongtinhnguyen.000webhostapp.com/deletetinhnguyensinhvien.php";

    String url3 ;

    String url4 , url5;

    String urlGetTenTruong;

    String LinkHinhAnh;

    int soLuongMax=0 , soLuongThamGia=0 ,soLuongThamGia1=0;
// khởi tạo boolean kiểm tra số lượng đã đạt MAX hay chưa
    Boolean checkSLThamGia ;
    Boolean checkSLThamGia1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoat_dong);

        // ẩn thanh ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        // khởi tạo ánh xạ
        addControls();

        // xử lý sự kiện
        addEvents();
    }

    //xử lý các sự kiện của button
    private void addEvents() {
        // xự kiện quay số
        // Chú ý : phải cấp quyền gọi cho ứng dụng thì mới gọi được
        // dưới đây là các quyền về gọi , nhắn tin , và internet .
//        <uses-permission android:name="android.permission.READ_SMS" />
//    <uses-permission android:name="android.permission.READ_CONTACTS" />
//    <uses-permission android:name="android.permission.SEND_SMS" />
//    <uses-permission android:name="android.permission.CALL_PHONE" />
//    <uses-permission android:name="android.permission.INTERNET" />


                btnGoiCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyQuaySo();
            }
        });
        //xử lý sự kiện đăng ký
        btnDangKyCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSoLuongThamGiaTang(url5 , url4 ,url3);
                xuLyDangKy(url1);
            }
        });
        //xử lý sự kiện hũy đăng ký
        btnHuyDKCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSoLuongThamGiaGiam(url5 ,url3 );
                xuLyHuyDangKy(url2);
            }
        });

    }

    private void updateSoLuongThamGia(String url3) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        if(response.trim().equals("succes"))
//                        {
//                           checkSLThamGia = true;
//                        }
//                        else
//                        {
//                            checkSLThamGia = false;
//                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChiTietHoatDongActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("SLThamGia", String.valueOf(soLuongThamGia));
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
    private void updateSoLuongThamGia1(String url3) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        if(response.trim().equals("succes"))
//                        {
//                            checkSLThamGia1 = true;
//                        }
//                        else
//                        {
//                            checkSLThamGia1 = false;
//                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChiTietHoatDongActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("SLThamGia", String.valueOf(soLuongThamGia1));
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

    private void xuLyHuyDangKy(String url2) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("succes"))
                        {

                                Toast.makeText(ChiTietHoatDongActivity.this, "Hũy Đăng Kí Thành Công !",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ChiTietHoatDongActivity.this, "Hũy Đăng Kí Thất Bại !",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChiTietHoatDongActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("MATN",MATN.toString());
                params.put("MASV" ,maSinhVien.toString());
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

    // xử lý đăng ký và đẩy thông tin vào bảng hoatdongtinhnguyen
    private void xuLyDangKy(String url1) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("succes"))
                        {
                                Toast.makeText(ChiTietHoatDongActivity.this, "Đăng Kí Tham Gia Thành Công !",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ChiTietHoatDongActivity.this, "Đăng Kí Tham Gia Thất Bại !",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChiTietHoatDongActivity.this, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("MATN",MATN.toString());
                params.put("MASV" ,maSinhVien.toString());
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

//xử lý quay số .
    private void xuLyQuaySo() {
        Uri uri = Uri.parse("tel:" + txtSDT.getText().toString());
        Intent intent = new Intent(Intent.ACTION_CALL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        // mở ứng dụng gọi mặc định của máy
        intent.setData(uri);
        startActivity(intent);
    }

    // ánh xạ và get dữ liệu
    private void addControls() {
        Intent intent = getIntent();
        MATN = intent.getStringExtra("MATN");

        btnGoiCT  = (ImageButton) findViewById( R.id.btnGoiCT);
        btnDangKyCT  = (ImageButton) findViewById( R.id.btnDangKyCT);
        btnHuyDKCT  = (ImageButton) findViewById( R.id.btnHuyDKCT);

        txtTenTinhNguyenCT = (TextView) findViewById(R.id.txtTenTinhNguyenCT);
        txtDiaDiemCT = (TextView) findViewById(R.id.txtDiaDiemCT);
        txtNgayKTCT = (TextView) findViewById(R.id.txtNgayKTCT);
        txtNgayBDCT = (TextView) findViewById(R.id.txtNgayBDCT);
        txtSLMAXCT = (TextView) findViewById(R.id.txtSLMAXCT);
        txtSLMINCT = (TextView) findViewById(R.id.txtSLMINCT);
        txtSLThamGiaCT = (TextView) findViewById(R.id.txtSLThamGiaCT);
        txtSDT = (TextView) findViewById(R.id.txtSDT);
        txtNoiDung = (TextView) findViewById(R.id.txtNoiDung);
        imgHinhAnh = (ImageView) findViewById(R.id.imgHinhAnh);
        txtTenTruongDaiHocCT = (TextView) findViewById(R.id.txtTenTruongDaiHocCT);

        checkSLThamGia=false;
        checkSLThamGia1 = false;

        url = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/gettinhnguyentheomact.php?MATN1="+MATN;

        getdata(url);

        urlGetTenTruong="http://quanlyhoatdongtinhnguyen.000webhostapp.com/gettentruong.php?MATN="+MATN;

        getTenTruong(urlGetTenTruong);

        url3 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatesoluongthamgia.php?MATN="+MATN;
        url4 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/getsoluongmaxtinhnguyen.php?MATN="+MATN;
        url5 ="http://quanlyhoatdongtinhnguyen.000webhostapp.com/getsoluongthamgia.php?MATN="+MATN;

    }


    // xử lý cập nhập số lượng khi hũy đăng ký
    private void updateSoLuongThamGiaGiam(String url5, final String url3) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url5, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++)
                        {
                            try {
                                // giống như tăng số lượng
                                // giảm số lượng  tham gia và cập nhập
                                // không kiểm tra if vì nếu chưa đăng ký sẽ không hũy đăng ký được
                                // nên không cần kiểm tra SL tham gia với SL MAX
                                JSONObject object = response.getJSONObject(i);
                                SoLuongThamGia slThamGia =new Gson().fromJson(String.valueOf(object), SoLuongThamGia.class);
                                soLuongThamGia1 = slThamGia.getSLThamGia();
                                {
                                    soLuongThamGia1 = (soLuongThamGia1-1);
                                    updateSoLuongThamGia1(url3);
                                }
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
                        Toast.makeText(ChiTietHoatDongActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    // xử lý đăng ký thành công sẽ ẩn
    private void updateSoLuongThamGiaTang(String url5 , final String url4 , final String url3) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url5, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++)
                        {
                            try {
                                // lấy số lượng tham gia hiện tại để so sánh với số lượng MAX
                                JSONObject object = response.getJSONObject(i);
                                SoLuongThamGia slThamGia =new Gson().fromJson(String.valueOf(object), SoLuongThamGia.class);
                                soLuongThamGia = slThamGia.getSLThamGia();
                                {
                                    RequestQueue requestQueue = Volley.newRequestQueue(ChiTietHoatDongActivity.this);
                                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                                            Request.Method.GET, url4, null,
                                            new Response.Listener<JSONArray>() {
                                                @Override
                                                public void onResponse(JSONArray response) {
                                                    for (int i = 0; i < response.length(); i++)
                                                    {
                                                        try {
                                                            // lấy số lượng max
                                                            JSONObject object = response.getJSONObject(i);
                                                            SoLuongSinhVienToiDa slMax =new Gson().fromJson(String.valueOf(object), SoLuongSinhVienToiDa.class);
                                                            soLuongMax = slMax.getSLMax();
                                                            // nếu đã đủ số lượng sẽ thông báo cho người dùng và không cho đăng ký
                                                            if(soLuongThamGia >= soLuongMax)
                                                            {
                                                                Toast.makeText(ChiTietHoatDongActivity.this, "Rất Tiếc ! Hoạt Động Tình Nguyện Này Đã Đủ Số Lượng !", Toast.LENGTH_LONG).show();

                                                            }
                                                            // nếu vẩn còn số lượng , tăng số lượng tham gia lên 1 và cập nhập dữ liệu tham gia và dự liệu người dùng vào bảng hoatdongtinhnguyen
                                                            else
                                                            {
                                                                soLuongThamGia+=1;
                                                                updateSoLuongThamGia(url3);
                                                            }
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
                                                    Toast.makeText(ChiTietHoatDongActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                                }
                                            }
                                    );
                                    requestQueue.add(jsonArrayRequest);
                                }
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
                        Toast.makeText(ChiTietHoatDongActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }

    // xử lý lấy tên trường từ bảng truong từ mã trường có trong bảng tinhnguyen để show lên cho người dùng
    private void getTenTruong(String urlGetTenTruong) {
        // khai báo  compile 'com.android.volley:volley:1.1.0'
        //compile 'com.google.code.gson:gson:2.8.0'
        // trong gradle build để xử dụng đọc JSON và thao tác dự liệu với MYSQL
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
                                txtTenTruongDaiHocCT.setText("Thuộc : "+tentruong.getTenTruong());
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
                        Toast.makeText(ChiTietHoatDongActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }


    // lấy dữ liệu từ SQL lên textview show cho người dùng xem thông qua đọc dữ liệu JSON
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
                                //gán dữ liệu vào textview
                                JSONObject object = response.getJSONObject(i);
                                TinhNguyen1 tinhnguyen =new Gson().fromJson(String.valueOf(object), TinhNguyen1.class);
                                txtTenTinhNguyenCT.setText(tinhnguyen.getTenTN().toString());
                                txtDiaDiemCT.setText("Địa Điểm: "+tinhnguyen.getDiaDiem().toString());
                                txtNgayKTCT.setText("Ngày Kết Thúc: "+tinhnguyen.getNgayGioKetThuc().toString());
                                txtNgayBDCT.setText("Ngày Bắt Đầu: "+tinhnguyen.getNgayGioBatDau().toString());
                                txtSLMAXCT.setText("Số Lượng Tối Đa: "+String.valueOf(tinhnguyen.getSLMax()));
                                txtSLMINCT.setText("Số Lượng Tối Thiểu: "+String.valueOf(tinhnguyen.getSLMin()));
                                txtSLThamGiaCT.setText("Số Lượng Tham Gia: "+String.valueOf(tinhnguyen.getSLThamGia()));
                                txtSDT.setText("0"+String.valueOf(tinhnguyen.getSDT()));
                                LinkHinhAnh = tinhnguyen.getHinhAnh().toString();
                                // khai cáo Picasso trong grradlle như sau  compile 'com.squareup.picasso:picasso:2.5.2'
                                // sao đó dùng Picasso để đưa ảnh lên Image View để show cho người dùng xem
                                Picasso.with(ChiTietHoatDongActivity.this).load(LinkHinhAnh).into(imgHinhAnh);
                                txtNoiDung.setText("Nội Dung Tình Nguyện: "+tinhnguyen.getNoiDung().toString());
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
                        Toast.makeText(ChiTietHoatDongActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

}
