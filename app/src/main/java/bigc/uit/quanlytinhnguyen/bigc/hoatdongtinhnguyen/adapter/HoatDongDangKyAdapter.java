package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bigc.uit.quanlytinhnguyen.R;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.HoatDongDangKy;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.SoLuongThamGia;

import static bigc.uit.quanlytinhnguyen.ControlTinhNguyenActivity.maSinhVien;

/**
 * Created by Thien An on 2017-12-15.
 */

public class HoatDongDangKyAdapter extends BaseAdapter {
    //Kế thừa BaseAdapter để xử lý dữ liệu
    //Khởi Tạo Tham truyền vào Adapter

    private Activity context;
    private  int resource;
    private List<HoatDongDangKy> objects;

    int soLuongThamGia1;

    String url ="http://quanlyhoatdongtinhnguyen.000webhostapp.com/deletetinhnguyensinhvien.php";
    String MaTinhNguyen;
    public  static String MSVCheckAn ;
  //  String url1 = "http://192.168.1.8:8080/quanlytinhnguyen/getdanhsachdangky.php?MASV="+maSinhVien;

    public static Boolean checkHuyDangKy  = false;

    public HoatDongDangKyAdapter( Activity context,  int resource,  List<HoatDongDangKy> objects) {
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    // Khai báo những item có trong activity
    private  class ViewHolder{
        TextView txtTenTinhNguyenHDDK ,txtDiaDiemHDDK ,txtNgayGioBDHDDK , txtNgayKTHDDK   ;
        ImageButton btnHuyDangKyHDDK;

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if (view == null) {
            holder = new ViewHolder();

            //khởi tạo LayoutInflater
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource , null);

            //ánh xạ
            holder.txtTenTinhNguyenHDDK =(TextView) view.findViewById(R.id.txtTenTinhNguyenHDDK);
            holder.txtDiaDiemHDDK =(TextView) view.findViewById(R.id.txtDiaDiemHDDK);
            holder.txtNgayGioBDHDDK =(TextView) view.findViewById(R.id.txtNgayBDHDDK);
            holder.txtNgayKTHDDK =(TextView) view.findViewById(R.id.txtNgayKTHDDK);
            holder.btnHuyDangKyHDDK =(ImageButton) view.findViewById(R.id.btnHuyDangKyHDDK);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        final HoatDongDangKy hoatDongDangKy = this.objects.get(i);

      //  MaTinhNguyen = hoatDongDangKy.getMATN().toString();

        //gán
        holder.txtTenTinhNguyenHDDK.setText(hoatDongDangKy.getTenTN().toString());
        holder.txtDiaDiemHDDK.setText("Địa Điểm: "+hoatDongDangKy.getDiaDiem().toString());
        holder.txtNgayGioBDHDDK.setText("Ngày Bắt Đầu: "+hoatDongDangKy.getNgayGioBatDau().toString());
        holder.txtNgayKTHDDK.setText("Ngày Kết Thúc: "+hoatDongDangKy.getNgayGioKetThuc().toString());


        //xử lý nhấn button hũy đăng ký , hũy đăng ký đồng thời thay đổi số lượng tham gia
        holder.btnHuyDangKyHDDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaTinhNguyen = hoatDongDangKy.getMATN();
                MSVCheckAn = MaTinhNguyen;
                soLuongThamGia1 = 0 ;
                String url3 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatesoluongthamgia.php?MATN="+MaTinhNguyen;
                String url5 ="http://quanlyhoatdongtinhnguyen.000webhostapp.com/getsoluongthamgia.php?MATN="+MaTinhNguyen;
                updateSoLuongThamGiaGiam(url5 ,url3 );
                xuLyHuyDangKy(url);
                objects.remove(i);
                notifyDataSetChanged();
            }
        });

        return  view;
    }
    //xử lý giảm số lượng tham gia sau khi hũy đăng ký
    private void updateSoLuongThamGiaGiam(String url5, final String url3) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
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
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    //xử lý update số lượng tham gia
    private void updateSoLuongThamGia1(String url3) {
    RequestQueue requestQueue = Volley.newRequestQueue(context);
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
                    Toast.makeText(context, "Xảy Ra Lỗi !",Toast.LENGTH_LONG).show();
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
//xử lý hũy đăng kí
    private void xuLyHuyDangKy(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("succes"))
                        {
                            Toast.makeText(context, "Hũy Đăng Kí Thành Công !",Toast.LENGTH_SHORT).show();
                            checkHuyDangKy = true;
                        }
                        else
                        {
                            Toast.makeText(context, "Hũy Đăng Kí Thất Bại !",Toast.LENGTH_SHORT).show();
                            checkHuyDangKy = false;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Xảy Ra Lỗi !",Toast.LENGTH_SHORT).show();
                        Log.d("AAA" , "ERROR!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("MATN",MaTinhNguyen);
                params.put("MASV" ,maSinhVien);
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}
