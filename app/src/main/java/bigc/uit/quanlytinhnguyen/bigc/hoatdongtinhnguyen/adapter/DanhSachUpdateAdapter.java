package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import bigc.uit.quanlytinhnguyen.R;
import bigc.uit.quanlytinhnguyen.XuLyUpdateActivity;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.DanhSachUpdate;

/**
 * Created by Thien An on 2017-12-21.
 */

public class DanhSachUpdateAdapter extends BaseAdapter {
    //Kế thừa BaseAdapter
    //Khởi Tạo Tham truyền vào Adapter

    private  Activity context;
    private  int resource;
    private  List<DanhSachUpdate> objects;

    public DanhSachUpdateAdapter(Activity context, int resource, List<DanhSachUpdate> objects) {
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    // Khai báo những item có trong activity
    private  class ViewHolder{
        TextView txtMaTinhNguyenDSUpdate ,txtTenTinhNguyenDSUpdate ;
        ImageButton btnUpdateTinhNguyen ;

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
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {

            holder = new ViewHolder();

            //khởi tạo LayoutInflater
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);;

            //ánh xạ
            holder.txtMaTinhNguyenDSUpdate = (TextView) view.findViewById(R.id.txtMaTinhNguyenDSUpdate);
            holder.txtTenTinhNguyenDSUpdate = (TextView) view.findViewById(R.id.txtTenTinhNguyenDSUpdate);
            holder.btnUpdateTinhNguyen =(ImageButton) view.findViewById(R.id.btnUpdateTinhNguyen);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        final DanhSachUpdate ds = this.objects.get(position);

        //gán
        holder.txtMaTinhNguyenDSUpdate.setText("Mã: "+ds.getMATN().toString());
        holder.txtTenTinhNguyenDSUpdate.setText("Tên: " +ds.getTenTN().toString());

        //xử lý nhấn button cập nhập tình nguyện
        holder.btnUpdateTinhNguyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MATN = ds.getMATN().toString();
                xuLySua(MATN);
            }
        });

        return view;


    }

    //đẩy mã tình nguyện qua bên xử lý update tình nguyện show ra những thông tin chi tiết đựa trên MATN ứng với possiton của từng dòng layout
    private void xuLySua(String matn) {
        Intent i = new Intent(context, XuLyUpdateActivity.class);
        i.putExtra("MATN" , matn);
        context.startActivity(i);
    }
}
