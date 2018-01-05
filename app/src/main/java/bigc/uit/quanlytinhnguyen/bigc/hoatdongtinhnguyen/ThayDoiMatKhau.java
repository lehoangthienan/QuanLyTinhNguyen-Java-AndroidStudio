package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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

import bigc.uit.quanlytinhnguyen.R;

public class ThayDoiMatKhau extends AppCompatActivity {

    //khai báo
    EditText txtMatKhauCuTDMK , txtMatKhauMoiTDMK , txtNhapLaiMatKhauTDMK;
    ImageButton btnCapNhapTDMK , btnQuayLaiTDMK;

    String taiKhoan , matKhau;

    String url ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_mat_khau);

        // ẩn ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        // xử lý ánh xạ , khởi tạo
        addControls();

        // xử lý sự kiện
        addEvents();
    }

    //xử lý xự kiện của button trong activity
    private void addEvents() {
        btnQuayLaiTDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nhấn quay lại sẽ quay lại activity trước đó
                finish();
            }
        });
        btnCapNhapTDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyThayDoiMatKhau();
            }
        });
    }

    // xử lý thay đổi mật khẩu
    private void xuLyThayDoiMatKhau() {

        // lưu dữ liệu người dùng nhập
        String MatKhauCu = txtMatKhauCuTDMK.getText().toString().trim();
        String MatKhauMoi = txtMatKhauMoiTDMK.getText().toString().trim();
        String NhapLaiMatKhau  =txtNhapLaiMatKhauTDMK.getText().toString().trim();


        // kiểm tra có edittext nào mà người dùng chưa nhập , nếu có sẽ thông báo cho người dùng biết để nhập đầu đủ
        if(MatKhauCu.isEmpty()||MatKhauMoi.isEmpty()||NhapLaiMatKhau.isEmpty())
        {
            Toast.makeText(ThayDoiMatKhau.this, "Vui Lòng Nhập Đầy Đủ Thông Tin !", Toast.LENGTH_SHORT).show();
        }
        // sau khi đã nhập đầu đủ , tiến hành kiểm tra và thay đổi dữ liệu
        else
        {
            // kiểm tra mật khẩu cũ có đúng hay không , nếu đúng,  tiếp tục kiểm tra mật khẩu nhập lại có đúng hay không
            // nếu đúng yêu cầu sẽ tiến hành thao tác cập nhập mật khẩu
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
                                        //nếu thao tác cập nhập thành công sẽ thông báo cho người dùng viết
                                        // và set text lại bằng null để đảm bảo tình bảo mật
                                        Toast.makeText(ThayDoiMatKhau.this, "Thay Đổi Mật Khẩu Thành Công !", Toast.LENGTH_SHORT).show();
                                        txtMatKhauCuTDMK.setText("");
                                        txtNhapLaiMatKhauTDMK.setText("");
                                        txtMatKhauMoiTDMK.setText("");
                                       // startActivity(new Intent(ThayDoiMatKhau.this , ControlTinhNguyenActivity.class));
                                        //quay lại activity trước đó
                                        finish();
                                    }
                                    else
                                    {
                                        // thật bại sẽ báo cho người dùng biết .
                                        Toast.makeText(ThayDoiMatKhau.this, "Thay Đổi Mật Khẩu Thất Bại !", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                // báo lỗi
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(ThayDoiMatKhau.this, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                                    Log.d("AAA" , "ERROR!\n"+error.toString());
                                }
                            }
                    ){
                        @Override
                        // đẩy dữ liệu lên SQL
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
                    Toast.makeText(ThayDoiMatKhau.this, "Bạn Nhập Lại Mật Khẩu Mới Chưa Chính Xác , Xin Hãy Kiểm Tra Lại !", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(ThayDoiMatKhau.this, "Bạn Nhập Sai Mật Khẩu Cũ , Vui Lòng Nhập Chính Xác!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    // xử lý ánh xạ
    private void addControls() {
        // xử lý ánh xạ
        txtMatKhauCuTDMK = (EditText) findViewById(R.id.txtMatKhauCuTDMK);
        txtMatKhauMoiTDMK = (EditText) findViewById(R.id.txtMatKhauMoiTDMK);
        txtNhapLaiMatKhauTDMK = (EditText) findViewById(R.id.txtNhapLaiMatKhauMoiTDMK);
        btnCapNhapTDMK = (ImageButton) findViewById(R.id.btnCapNhapTDMK);
        btnQuayLaiTDMK = (ImageButton) findViewById(R.id.btnQuayLaiTDMK);

        // lấy dữ liệu chuyển qua từ control tình nguyện , dữ liệu từ control được lấy từ lúc đăng nhập
        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("TaiKhoan");
        matKhau = intent.getStringExtra("MatKhau");

        // đường dẩn thao tác với MYSQL
        url = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatematkhau.php?TaiKhoan="+taiKhoan;
    }
}
