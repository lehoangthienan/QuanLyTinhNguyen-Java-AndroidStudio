package kerryle.thienan.quanlytinhnguyen.kerryle.thienan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import kerryle.thienan.quanlytinhnguyen.ControlTinhNguyenActivity;
import kerryle.thienan.quanlytinhnguyen.R;

public class ThayDoiMatKhau extends AppCompatActivity {

    EditText txtMatKhauCuTDMK , txtMatKhauMoiTDMK , txtNhapLaiMatKhauTDMK;
    ImageButton btnCapNhapTDMK , btnQuayLaiTDMK;

    String taiKhoan , matKhau;

    String url ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_mat_khau);

        addControls();

        addEvents();
    }

    private void addEvents() {
        btnQuayLaiTDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent QuayLai = new Intent(ThayDoiMatKhau.this, ControlTinhNguyenActivity.class);
                startActivity(QuayLai);
            }
        });
        btnCapNhapTDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyThayDoiMatKhau();
            }
        });
    }

    private void xuLyThayDoiMatKhau() {
        String MatKhauCu = txtMatKhauCuTDMK.getText().toString().trim();
        String MatKhauMoi = txtMatKhauMoiTDMK.getText().toString().trim();
        String NhapLaiMatKhau  =txtNhapLaiMatKhauTDMK.getText().toString().trim();

        if(MatKhauCu.isEmpty()||MatKhauMoi.isEmpty()||NhapLaiMatKhau.isEmpty())
        {
            Toast.makeText(ThayDoiMatKhau.this, "Vui Lòng Nhập Đầy Đủ Thông Tin !", Toast.LENGTH_LONG).show();
        }
        else
        {
            if(MatKhauCu.equals(matKhau)==true)
            {
                if(MatKhauMoi.equals(NhapLaiMatKhau)==true)
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.trim().equals("succes"))
                                    {
                                        Toast.makeText(ThayDoiMatKhau.this, "Thay Đổi Mật Khẩu Thành Công !", Toast.LENGTH_LONG).show();
                                        txtMatKhauCuTDMK.setText("");
                                        txtNhapLaiMatKhauTDMK.setText("");
                                        txtMatKhauMoiTDMK.setText("");
                                       // startActivity(new Intent(ThayDoiMatKhau.this , ControlTinhNguyenActivity.class));
                                    }
                                    else
                                    {
                                        Toast.makeText(ThayDoiMatKhau.this, "Thay Đổi Mật Khẩu Thất Bại !", Toast.LENGTH_LONG).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(ThayDoiMatKhau.this, "Xảy Ra Lỗi !",Toast.LENGTH_LONG).show();
                                    Log.d("AAA" , "ERROR!\n"+error.toString());
                                }
                            }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String ,String> params = new HashMap<>();
                            params.put("MatKhau",txtNhapLaiMatKhauTDMK.getText().toString().trim());
                            return params;
                        }

                    };
                    requestQueue.add(stringRequest);
                }
                else
                {
                    Toast.makeText(ThayDoiMatKhau.this, "Bạn Nhập Lại Mật Khẩu Mới Chưa Chính Xác , Xin Hãy Kiểm Tra Lại !", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(ThayDoiMatKhau.this, "Bạn Nhập Sai Mật Khẩu Cũ , Vui Lòng Nhập Chính Xác!", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void addControls() {
        txtMatKhauCuTDMK = (EditText) findViewById(R.id.txtMatKhauCuTDMK);
        txtMatKhauMoiTDMK = (EditText) findViewById(R.id.txtMatKhauMoiTDMK);
        txtNhapLaiMatKhauTDMK = (EditText) findViewById(R.id.txtNhapLaiMatKhauMoiTDMK);
        btnCapNhapTDMK = (ImageButton) findViewById(R.id.btnCapNhapTDMK);
        btnQuayLaiTDMK = (ImageButton) findViewById(R.id.btnQuayLaiTDMK);
        btnQuayLaiTDMK.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("TaiKhoan");
        matKhau = intent.getStringExtra("MatKhau");

        url = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatematkhau.php?TaiKhoan="+taiKhoan;
    }
}
