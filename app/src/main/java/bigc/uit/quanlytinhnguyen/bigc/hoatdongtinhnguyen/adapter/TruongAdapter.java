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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bigc.uit.quanlytinhnguyen.R;
import bigc.uit.quanlytinhnguyen.XyLyXemTinhNguyenTruongActivity;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.Truong;

/**
 * Created by Thien An on 2017-12-29.
 */

public class TruongAdapter extends BaseAdapter  implements Filterable {

    //Kế thừa BaseAdapter để xử lý dữ liệu
    //Khởi Tạo Tham truyền vào Adapter

    private Activity context;
    private  int resource;
    private List<Truong> objects;

    // khởi tại CustomFilter để serachview vì trong BaseAdapter không có sẵn Filter nên phải implements Filterable và tạo hàm
    CustomFilter filter;

    public TruongAdapter(Activity context, int resource, List<Truong> objects) {
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    // khởi tạo item chức năng trong activity
    private  class ViewHolder{
        TextView txtMTDST ,txtTenDST  ;
        ImageButton btnDST;
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

            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);;

            //ánh xạ
            holder.txtMTDST = (TextView) view.findViewById(R.id.txtMaTruongDST);
            holder.txtTenDST = (TextView) view.findViewById(R.id.txtTenTruongDST);
            holder.btnDST =(ImageButton) view.findViewById(R.id.btnXemDST);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        final Truong ds = this.objects.get(position);

        holder.txtMTDST.setText("Mã Trường : "+ds.getMAT().toString());
        holder.txtTenDST.setText("Tên Trường : " +ds.getTenTruog().toString());

        //xử lý sự kiển mở danh sách trường
        holder.btnDST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MAT = ds.getMAT().toString();
                xuLyMoDanhSachTinhNguyen(MAT);
            }
        });
        return view;

    }

    //xử lý mở danh sách tình nguyện chuyển qua xem chi tiết và chuyển mã trường qua
    private void xuLyMoDanhSachTinhNguyen(String mat) {
        Intent i = new Intent(context, XyLyXemTinhNguyenTruongActivity.class);
        i.putExtra("MAT" , mat);
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
                constraint = constraint.toString().toUpperCase();
                ArrayList<Truong> filters = new ArrayList<Truong>();
                //get specific items
                for (int i = 0; i < objects.size(); i++) {
                    if (objects.get(i).getMAT().toUpperCase().contains(constraint)) {
                        Truong p = new Truong(
                                objects.get(i).getMAT(),
                                objects.get(i).getTenTruog());
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
            objects=(ArrayList<Truong>) results.values;
            notifyDataSetChanged();
        }
    }
}
