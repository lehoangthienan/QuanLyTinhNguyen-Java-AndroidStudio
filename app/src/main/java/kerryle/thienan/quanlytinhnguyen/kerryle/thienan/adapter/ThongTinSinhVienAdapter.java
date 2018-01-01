package kerryle.thienan.quanlytinhnguyen.kerryle.thienan.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kerryle.thienan.quanlytinhnguyen.R;
import kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model.ThongTinSinhVien;

/**
 * Created by Thien An on 2017-12-29.
 */

public class ThongTinSinhVienAdapter extends BaseAdapter {
    private Activity context;
    private  int resource;
    private List<ThongTinSinhVien> objects;

    public ThongTinSinhVienAdapter(Activity context, int resource, List<ThongTinSinhVien> objects) {
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    private  class ViewHolder{
        TextView txtMaSinhVienDSSV ,txtTenSinhVienDSSV , txtTenTruongDSSV ;
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

            holder.txtMaSinhVienDSSV = (TextView) view.findViewById(R.id.txtMaSinhvienDSSV);
            holder.txtTenSinhVienDSSV = (TextView) view.findViewById(R.id.txtTenSinhVienDSSV);
            holder.txtTenTruongDSSV =(TextView) view.findViewById(R.id.txtTenTruongDSSV);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        final ThongTinSinhVien ds = this.objects.get(position);

        holder.txtMaSinhVienDSSV.setText("Mã Sinh Viên : "+ds.getMASV().toString());
        holder.txtTenSinhVienDSSV.setText("Tên Sinh Viên : " +ds.getTenSV().toString());
        holder.txtTenTruongDSSV.setText("Trường : " +ds.getMAT().toString());

        return view;

    }
}
