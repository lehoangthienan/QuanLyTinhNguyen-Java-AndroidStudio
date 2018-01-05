package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bigc.uit.quanlytinhnguyen.R;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.DangNhap;

/**
 * Created by Thien An on 2017-12-13.
 */

public class DangNhapAdapter extends BaseAdapter {

    // kế thừa từ BaseAdapter để thao tác dữ liệu

    //Khai báo tham số truyền vào Contructor

    private Activity context;
    private  int resource;
    private List<DangNhap> objects;

    public DangNhapAdapter( Activity context,  int resource,  List<DangNhap> objects) {
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    //khai báo những item có trong activity
    private  class ViewHolder{
        TextView txtTenTk, txtMk , txtMASVDN;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //Hàm tùy biến và xử lý
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {

            // Khởi tạo ViewHolder
            holder = new ViewHolder();

            //khai báo LayoutInflater
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);;

            // ánh xạ
            holder.txtTenTk = (TextView) view.findViewById(R.id.txtTenTK);
            holder.txtMk = (TextView) view.findViewById(R.id.txtMK);
            holder.txtMASVDN = (TextView) view.findViewById(R.id.txtMASVDN);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        final DangNhap dangNhap = this.objects.get(position);

        //gán dữ liệu
        holder.txtTenTk.setText(dangNhap.getTaiKhoan().toString());
        holder.txtMk.setText(dangNhap.getMatKhau().toString());
        holder.txtMASVDN.setText(dangNhap.getMASV().toString());




        return view;

    }
}
