package kerryle.thienan.quanlytinhnguyen;

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

import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model.SoLuongSinhVienToiDa;
import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model.SoLuongThamGia;
import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model.TenTruong;
import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model.TinhNguyen1;

import static kerryle.thienan.quanlytinhnguyen.ControlTinhNguyenActivity.maSinhVien;

public class ChiTietHoatDongActivity extends AppCompatActivity {

    String MATN ;

    ImageButton btnGoiCT , btnDangKyCT , btnHuyDKCT;
    TextView txtTenTinhNguyenCT ,txtDiaDiemCT,txtNgayKTCT,txtNgayBDCT,txtSLMAXCT,txtSLMINCT,txtSLThamGiaCT,txtSDT,txtNoiDung , txtTenTruongDaiHocCT;
    ImageView imgHinhAnh;
    String url;

    String url1 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/inserttinhnguyensinhvien.php";

    String url2 ="http://quanlyhoatdongtinhnguyen.000webhostapp.com/deletetinhnguyensinhvien.php";

    String url3 ;

    String url4 , url5;

    String urlGetTenTruong;

    String LinkHinhAnh;

    int soLuongMax=0 , soLuongThamGia=0 ,soLuongThamGia1=0;

    Boolean checkSLThamGia ;
    Boolean checkSLThamGia1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoat_dong);

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        addControls();

        addEvents();
    }

    private void addEvents() {
        btnGoiCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyQuaySo();
            }
        });
        btnDangKyCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSoLuongThamGiaTang(url5 , url4 ,url3);
                xuLyDangKy(url1);
            }
        });
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
        intent.setData(uri);
        startActivity(intent);
    }

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
                                                            JSONObject object = response.getJSONObject(i);
                                                            SoLuongSinhVienToiDa slMax =new Gson().fromJson(String.valueOf(object), SoLuongSinhVienToiDa.class);
                                                            soLuongMax = slMax.getSLMax();
                                                            if(soLuongThamGia >= soLuongMax)
                                                            {
                                                                Toast.makeText(ChiTietHoatDongActivity.this, "Rất Tiếc ! Hoạt Động Tình Nguyện Này Đã Đủ Số Lượng !", Toast.LENGTH_LONG).show();

                                                            }
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
                                txtTenTinhNguyenCT.setText(tinhnguyen.getTenTN().toString());
                                txtDiaDiemCT.setText("Địa Điểm: "+tinhnguyen.getDiaDiem().toString());
                                txtNgayKTCT.setText("Ngày Kết Thúc: "+tinhnguyen.getNgayGioKetThuc().toString());
                                txtNgayBDCT.setText("Ngày Bắt Đầu: "+tinhnguyen.getNgayGioBatDau().toString());
                                txtSLMAXCT.setText("Số Lượng Tối Đa: "+String.valueOf(tinhnguyen.getSLMax()));
                                txtSLMINCT.setText("Số Lượng Tối Thiểu: "+String.valueOf(tinhnguyen.getSLMin()));
                                txtSLThamGiaCT.setText("Số Lượng Tham Gia: "+String.valueOf(tinhnguyen.getSLThamGia()));
                                txtSDT.setText("0"+String.valueOf(tinhnguyen.getSDT()));
                                LinkHinhAnh = tinhnguyen.getHinhAnh().toString();
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
