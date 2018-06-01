package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter;

/**
 * Created by Thien An on 2017-12-11.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bigc.uit.quanlytinhnguyen.ChiTietHoatDongActivity;
import bigc.uit.quanlytinhnguyen.DanhSachSinhVienThamGiaActivity;
import bigc.uit.quanlytinhnguyen.R;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.MaTinhNguyenSinhVien;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.SoLuongSinhVienToiDa;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.SoLuongThamGia;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.TinhNguyen;

import static bigc.uit.quanlytinhnguyen.ControlTinhNguyenActivity.maSinhVien;

/**
 * Created by Thien An on 2017-12-10.
 */

public class TinhNguyenAdapter  extends BaseAdapter implements Filterable {

    //Kế thừa BaseAdapter để xử lý dữ liệu  , vì khai báo BaseAdapter nên không có sẵn hàm Filte nên phải implements Filterable
    //Filte là nơi để design và tùy chỉnh SearchView , Khai báo ở cuối hàm .
    //Khởi Tạo Tham truyền vào Adapter

    private  Activity context;
    private  int resource;
    private  List<TinhNguyen> objects;

    //Khởi tạo mã sinh viên để xử lý lấy và thay đổi dữ liệu
    String MATN="";

    // chuỗi thao tác với MYSQL  , tạo File PHP và thao tác thông qua JSON  .
    String url="http://quanlyhoatdongtinhnguyen.000webhostapp.com/inserttinhnguyensinhvien.php";

    //tại biến static để truyền nhận giữa cho activity 1 cách thuận tiện
    public static String maTinhNguyen;

    //khai báo chuỗi xử lý MYSQL
    String urlGetMa ;

    //Danh sách tình nguyện
    List<MaTinhNguyenSinhVien> dsMaTinhNguyen  = new ArrayList<>();

    //tạo biến gán
    int soLuongThamGia  ,soLuongMax;

    Boolean checkSLThamGia;

    //Biến để xử lý SearchView
    CustomFilter filter;

    public TinhNguyenAdapter( Activity context,  int resource,  List<TinhNguyen> objects) {
        this.context=context;
        this.resource=resource;
        this.objects=objects;

    }
    // Khai báo những item có trong activity
    private  class ViewHolder{
        TextView txtTenTinhNguyen ,txtSLThamGia ,txtNgayBD , txtNgayKT ,txtSLConLai , txtTenTruongDaiHoc  ;
        ImageButton btnChiTiet, btnDangKyNhanh ,btnDanhSachSinhVienTham ;
        ImageView imgAnimeDS;

    }

    //lấy số lượng cột trên danh sách tình nguyện , lấy từ số lượng trong danh sách mảng objects ( tình nguyện )
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

    @NonNull
    // xử lý getView
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {

            holder = new ViewHolder();

            //tạo LayoutInflater
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);;

            //ánh xạ
            holder.txtTenTinhNguyen = (TextView) view.findViewById(R.id.txtTenTinhNguyen);
            holder.txtNgayBD = (TextView) view.findViewById(R.id.txtNgayBD);
            holder.txtNgayKT = (TextView) view.findViewById(R.id.txtNgayKT);
            holder.txtSLConLai = (TextView) view.findViewById(R.id.txtSLConLai);
            holder.txtSLThamGia = (TextView) view.findViewById(R.id.txtSLThamGia);
            holder.btnChiTiet =(ImageButton) view.findViewById(R.id.btnChiTiet);
            holder.btnDangKyNhanh =(ImageButton) view.findViewById(R.id.btnDangKyNhanh);
            holder.txtTenTruongDaiHoc =(TextView) view.findViewById(R.id.txtTenTruongDaiHoc);
            holder.btnDanhSachSinhVienTham =(ImageButton) view.findViewById(R.id.btnDanhSachSinhVienTham);
            holder.imgAnimeDS = view.findViewById(R.id.imgAnimeDS);
            @SuppressLint("ResourceType") Animation animationRotale = AnimationUtils.loadAnimation(context, R.animator.animeds);
            holder.imgAnimeDS.startAnimation(animationRotale);
            // chuổi MYSQL
            urlGetMa= "http://quanlyhoatdongtinhnguyen.000webhostapp.com/gettinhnguyensinhvien.php?MASV="+maSinhVien;
            //xử lý get Mã Tình Nguyện
            getMaTinhNguyen(urlGetMa);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        // tạo mảng tình nguyện để lưu đối với từng dòng ví trị tại position
        final TinhNguyen tinhNguyen = this.objects.get(position);

        String SoLuongThamGia = String.valueOf(tinhNguyen.getSLThamGia());

        // gán giá trị
        holder.txtTenTinhNguyen.setText(tinhNguyen.getTenTN().toString());
        holder.txtNgayBD.setText("Ngày Bắt Đầu : " +tinhNguyen.getNgayGioBatDau().toString());
        holder.txtNgayKT.setText("Ngày Kết Thúc : " +tinhNguyen.getNgayGioKetThuc().toString());
        holder.txtSLThamGia.setText("Số Lượng Tham Gia : " + SoLuongThamGia);
        holder.txtSLConLai.setText("Số Lượng Còn Lại : " +(tinhNguyen.getSLMax()-tinhNguyen.getSLThamGia()));
        holder.txtTenTruongDaiHoc.setText("Thuộc : " + tinhNguyen.getMAT().toString());

        //xử lý xự kiện nhấn vào button chi tiết hoạt động tình nguyện
        holder.btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MATN = tinhNguyen.getMATN().toString();
                maTinhNguyen = MATN;
                xuLyMoChiTiet(MATN);
            }
        });

        //xử lý xự kiện nhấn vào button button đăng ký nhanh
        // phải cho hiện button thì khi nhấn vào sẽ mất những button ở 1 vài layout
        holder.btnDangKyNhanh.setVisibility(View.VISIBLE);
        holder.btnDangKyNhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MATN = tinhNguyen.getMATN().toString();
                maTinhNguyen = MATN;
                insert(url);
                holder.btnDangKyNhanh.setVisibility(View.INVISIBLE);
                String url3 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/updatesoluongthamgia.php?MATN="+MATN;
                String url4 = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/getsoluongmaxtinhnguyen.php?MATN="+MATN;
                String url5 ="http://quanlyhoatdongtinhnguyen.000webhostapp.com/getsoluongthamgia.php?MATN="+MATN;
                soLuongThamGia =0;
                soLuongMax=0;
                updateSoLuongThamGiaTang(url5 , url4 ,url3);
            }
        });
        //xử lý xự kiện nhấn vào button danh sách sinh viên tham gia hoạt động tình nguyện cụ thể
        holder.btnDanhSachSinhVienTham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MATN = tinhNguyen.getMATN().toString();
                maTinhNguyen = MATN;
                xuLyMoDanhSachSinhVien(maTinhNguyen);
            }
        });
        // hàm này đê xử lý ẩn những button đăng ký nhanh của những hoạt động ĐÃ đăng ký
        // Duyệt qua tất cả mã tình nguyện trong danh sách
        //Nếu có , tức là trùng mã tình nguyện với tình nguyện có trong bảng hoatdongtinhnguyen trong MYSQL thì sẽ ẩn cái button tại vị trí đó đi
        for(MaTinhNguyenSinhVien maTinhNguyen :dsMaTinhNguyen)
        {
            if(maTinhNguyen.getMATN().toString().trim().equals(tinhNguyen.getMATN().toString())==true)
            {
                holder.btnDangKyNhanh.setVisibility(View.INVISIBLE);
                // notifyDataSetChanged();
            }
        }


        return view;


    }


    //Xử lý chủ yếu là đọc file JSON thông qua Volley , Volley thì phải add vào build.gradle  compile 'com.android.volley:volley:1.1.0' vÀ  compile 'com.google.code.gson:gson:2.8.0'
    //xử lý và đẩy mã tình nguyên qua DanhSachSinhVienThamGiaActivity bằng Intent
    private void xuLyMoDanhSachSinhVien(String maTinhNguyen) {
        Intent i = new Intent(context, DanhSachSinhVienThamGiaActivity.class);
        i.putExtra("MATN" , maTinhNguyen);
        context.startActivity(i);
    }

    //cập nhập số lượng tham gia tăng và kiểm tra đã đạt số lượng MAX hay chưa . nếu đã đạt thông báo tới người dùng và không cho đăng ký
    private void updateSoLuongThamGiaTang(String url5, final String url4, final String url3) {
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
                                soLuongThamGia = slThamGia.getSLThamGia();
                                {
                                    RequestQueue requestQueue = Volley.newRequestQueue(context);
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
                                                                Toast.makeText(context, "Rất Tiếc ! Hoạt Động Tình Nguyện Này Đã Đủ Số Lượng !", Toast.LENGTH_SHORT).show();

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
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
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

    //cập nhập số lượng tham gia
    private void updateSoLuongThamGia(String url3) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                params.put("SLThamGia", String.valueOf(soLuongThamGia));
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
// lấy mã tình nguyện
    private void getMaTinhNguyen(String urlGetMa ) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, urlGetMa, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                dsMaTinhNguyen.add(new MaTinhNguyenSinhVien(
                                        object.getString("MATN")

                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // de cap nhap lai du lieu
                        notifyDataSetChanged();
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

//xử lý đăng ký tham gia tình nguyện
    private void insert(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("succes"))
                        {
                                Toast.makeText(context, "Đăng Kí Tham Gia Thành Công !",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context, "Đăng Kí Tham Gia Thất Bại !",Toast.LENGTH_SHORT).show();
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
                params.put("MATN",maTinhNguyen);
                params.put("MASV" ,maSinhVien);
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

//xử lý mở  ChiTietHoatDongActivity và đẩy mã tình nguyện qua bên ChiTietHoatDongActivity để show lên thông tin chi tiết của hoạt động có MATN đó ứng với vị tri
    //POSSION
    private void xuLyMoChiTiet(String a) {
        Intent i = new Intent(context, ChiTietHoatDongActivity.class);
        i.putExtra("MATN" , a);
        context.startActivity(i);
    }




    // Xử lý Menu tìm tiếm actionbar
    @Override
    public Filter getFilter() {
        // TODO Auto-generated method stub
        if(filter == null)
        {
            filter=new CustomFilter();
        }
        return filter;
    }
    //INNER CLASS
    class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // TODO Auto-generated method stub
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                //CONSTARINT TO UPPER
                constraint = constraint.toString().toLowerCase();
                ArrayList<TinhNguyen> filters = new ArrayList<TinhNguyen>();
                //get specific items
                for (int i = 0; i < objects.size(); i++) {
                    if (objects.get(i).getTenTN().toLowerCase().contains(constraint)) {
                        TinhNguyen p = new TinhNguyen(objects.get(i).getMATN(),
                                objects.get(i).getTenTN(),
                                objects.get(i).getNoiDung(),
                                objects.get(i).getNgayGioBatDau(),
                                objects.get(i).getNgayGioKetThuc(),
                                objects.get(i).getDiaDiem(),
                                objects.get(i).getSLMax(),
                                objects.get(i).getSLMin(),
                                objects.get(i).getSLThamGia(),
                                objects.get(i).getMAT());
                        filters.add(p);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = objects.size();
                results.values = objects;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // TODO Auto-generated method stub
            objects=(ArrayList<TinhNguyen>) results.values;
            notifyDataSetChanged();
        }
    }
}
